package com.hit.LibrarySystem.service;

import com.hit.LibrarySystem.dto.request.CreateAuthorRequest;
import com.hit.LibrarySystem.dto.request.UpdateAuthorRequest;
import com.hit.LibrarySystem.dto.response.AuthorResponse;
import com.hit.LibrarySystem.entity.Author;
import com.hit.LibrarySystem.exception.extended.DuplicateResourceException;
import com.hit.LibrarySystem.exception.extended.ResourceNotFoundException;
import com.hit.LibrarySystem.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Page<AuthorResponse> findAll(Pageable pageable) {
        // 1. Phuong thuc nhan tham so pageable chua so trang, kich thuoc, sap xep
        // 2. Goi authorRepository.findAll de truy van data. Tra ve Page<Author>
        // 3. Chuyen doi sang AuthorResponse
        // 4. Tra ve Page<AuthorResponse>
        return authorRepository.findAll(pageable).map(AuthorResponse::from);
    }

    @Transactional(readOnly = true)
    public AuthorResponse findById(Long id) {
        // 1. Goi authorRepository.findById(id) tra ve 1 Optional<Author>
        // 2. Neu tim thay thuc hien anh xa sang AuthorResponse
        // 3. Neu ko tim thay thuc hien nem ra ngoai le
        // 4. Tra ve ket qua
        return authorRepository.findById(id)
                .map(AuthorResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public AuthorResponse create(CreateAuthorRequest request) {
        // 1. Kiem tra trung lap email
        if (authorRepository.existsByEmail(request.getEmail())) {
            // 2. Neu email da ton tai, nem Duplicate
            throw new DuplicateResourceException("Author", "email", request.getEmail());
        }
        // 3. Tao doi tuong moi tu builder
        Author author = Author.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        // 4. Luu tru authorRepository.save(author)
        // 5. Chuyen thanh response va tra ve
        return AuthorResponse.from(authorRepository.save(author));
    }

    public AuthorResponse update(Long id, UpdateAuthorRequest request) {
        // 1. Kiem tra id tac gia co ton tai khong
        Author author = authorRepository.findById(id)
                // 2. Neu ko thi nem loi
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        // 3. Cap nhat name va phone
        author.setName(request.getName());
        author.setPhone(request.getPhone());
        // 4. Tra ve du lieu AuthorResponse
        return AuthorResponse.from(author);
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author", "id", id);
        }
        authorRepository.deleteById(id);
    }
}
