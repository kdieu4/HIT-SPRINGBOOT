package com.hit.LibrarySystem.repository;

import com.hit.LibrarySystem.entity.Book;
import com.hit.LibrarySystem.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);

    boolean existsByIsbn(String isbn);

    Optional<Book> findById(Long id);

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByCategory(Category category);
}
