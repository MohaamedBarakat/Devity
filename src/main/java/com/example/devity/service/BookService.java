package com.example.devity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.devity.configuration.AuthenticationContext;
import com.example.devity.model.Book;
import com.example.devity.model.User;
import com.example.devity.repository.BookRepository;
import com.example.devity.repository.UserRepository;

@Service
public class BookService {

  private BookRepository bookRepository;
  private UserRepository userRepository;

  public BookService(BookRepository bookRepository, UserRepository userRepository) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
  }

  public Book findById(long id) {
    return bookRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException((String.format("book with this id = %d not found", id))));
  }

  public List<Book> findAll(String description, String title) {
    List<Book> books = new ArrayList<>();
    if (description == null && title == null) {
      Iterable<Book> bookIterable = this.bookRepository.findAll();
      return StreamSupport.stream(bookIterable.spliterator(), false)
          .collect(Collectors.toUnmodifiableList());
    }

    if (description != null) {
      books.addAll(bookRepository.findByTitle(title));
    }
    if (title != null) {
      books.addAll(bookRepository.findByDescription(description));
    }

    return books;
  }

  public Book create(Book book) {
    String username = AuthenticationContext.getAuthContext().getName();
    User systemUser = userRepository.findByUsername(username).orElseThrow();
    book.setUser(systemUser);
    return bookRepository.save(book);
  }

  public void delete(long id) {
    Book book = findById(id);
    if (!Objects.equals(AuthenticationContext.getAuthContext().getName(), book.getUser().getName())) {
      bookRepository.deleteById(id);
    } else {
      throw new IllegalStateException("user can delete only there own books");
    }
  }
}