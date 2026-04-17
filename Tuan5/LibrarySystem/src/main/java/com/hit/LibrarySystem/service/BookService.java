package com.hit.LibrarySystem.service;

import com.hit.LibrarySystem.dto.request.CreateBookRequest;
import com.hit.LibrarySystem.entity.Author;
import com.hit.LibrarySystem.entity.Book;
import com.hit.LibrarySystem.entity.BookStatus;
import com.hit.LibrarySystem.entity.Category;
import com.hit.LibrarySystem.exception.extended.BadRequestException;
import com.hit.LibrarySystem.exception.extended.DuplicateResourceException;
import com.hit.LibrarySystem.exception.extended.ResourceNotFoundException;
import com.hit.LibrarySystem.repository.AuthorRepository;
import com.hit.LibrarySystem.repository.BookRepository;
import jakarta.transaction.TransactionScoped;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Page<Book> getBooksByAuthor(Long authorId, Pageable pageable) {
        // 1. Kiem tra id tac gia co ton tai ko
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author", "id", authorId);
        }
        return bookRepository.findByAuthorId(authorId, pageable);
    }

    @Transactional
    public Book create(CreateBookRequest request) {
        // 1. Tim kiem xem authorId co ton tai chua
        // 2. Neu 0 co thi nem ngoai le
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", request.getAuthorId()));

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Book", "Isbn", request.getIsbn());
        }
        // 3. Tao thuc the moi
        Book book = Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .category(request.getCategory())
                .totalCopies(request.getTotalCopies())
                .availableCopies(request.getTotalCopies())
                .publishYear(request.getPublishYear())
                .author(author)
                .build();
        // 4. Luu
        // 5. Tra ve
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
    }

    @Transactional
    public Book update(Long id, CreateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Book", "Isbn", request.getIsbn());
        }

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", request.getAuthorId()));

        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setCategory(request.getCategory());
        book.setTotalCopies(request.getTotalCopies());
        book.setPublishYear(request.getPublishYear());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", "id", id);
        }
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book borrow(Long id) {
        // 1. Kiem tra sach co ton tai hay ko
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        // 2. Kiem tra sach da ngung luu hanh hay ko -> 400
        if (book.getStatus().equals(BookStatus.DISCONTINUED)) {
            throw new BadRequestException("Sach da ngung luu hanh");
        }
        // 3. Kiem tra so luong sach con lai
        if (book.getAvailableCopies() <= 0) {
            throw new BadRequestException("Sach sa het");
        }
        // 4. Giam so luong 1
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        // 5. Neu chuyen ve ko -> chuyen status ve OUT_OF_STOCK
        if (book.getAvailableCopies() == 0) {
            book.setStatus(BookStatus.OUT_OF_STOCK);
        }

        // 6. Luu thay doi
        return bookRepository.save(book);
    }

    //    ⭐ Trả sách (POST /api/books/{id}/return)
//
//    Book phải tồn tại → 404
//    availableCopies == totalCopies → 400 "Sách đã đủ, không thể trả thêm"
//    Tăng availableCopies lên 1
//    Nếu đang OUT_OF_STOCK → chuyển status về AVAILABLE
    @Transactional
    public Book returnBook(Long id) {
        // 1. Kiem tra sach co ton tai hay ko
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        // 2. Kiem tra so luong sach
        if (book.getAvailableCopies() == book.getTotalCopies()) {
            throw new BadRequestException("Sach da du, khong the tra them");
        }
        // 3. Tang so luong them 1
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        if (book.getStatus().equals(BookStatus.OUT_OF_STOCK)) {
            book.setStatus(BookStatus.AVAILABLE);
        }
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> searchByName(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }

    @Transactional(readOnly = true)
    public List<Book> searchByCategory(String categoryName) {
        Category category;
        try {
            category = Category.valueOf(categoryName.toLowerCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ResourceNotFoundException("Book", "Category", categoryName);
        }
        return bookRepository.findByCategory(category);
    }
}
