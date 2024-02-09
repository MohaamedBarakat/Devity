package com.example.devity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.devity.model.Book;
import com.example.devity.repository.BookRepository;

@Service
public class BookService {

  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> findAll() {
    Iterable<Book> bookIterable = this.bookRepository.findAll();

    return StreamSupport.stream(bookIterable.spliterator(), false)
        .collect(Collectors.toUnmodifiableList());
  }

  public Book findById(long id) {
    return bookRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException((String.format("book with this id = %d not found", id))));
  }

  public List<Book> findByDetail(String description, String title) {
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
    return bookRepository.save(book);
  }

  public void delete(long id) {
    bookRepository.deleteById(id);
  }
}