package com.hit.LibrarySystem.repository;

import com.hit.LibrarySystem.dto.response.AuthorResponse;
import com.hit.LibrarySystem.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);

    Page<Author> findAll(Pageable pageable);

    Optional<Author> findById(Long id);

    boolean existsByEmail(String email);

}
