package com.example.devity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.devity.model.Book;
import com.example.devity.service.BookService;

@RestController
@RequestMapping("books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> find() {
    return ResponseEntity.ok(bookService.findAll());
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> findSearch(@RequestParam(required = false) String description,
      @RequestParam(required = false) String title) {
    return ResponseEntity.ok(bookService.findByDetail(description, title));
  }

  @GetMapping(value = "/books/{id}")
  public ResponseEntity<Book> findBookById(@PathVariable long id) {
    return ResponseEntity.ok(bookService.findById(id));
  }

  @PostMapping(value = "/books")
  public ResponseEntity<Book> create(@RequestBody Book book) {
    return ResponseEntity.ok(bookService.create(book));
  }

  @DeleteMapping(value = "/books/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable long id) {
    bookService.delete(id);
    return ResponseEntity.ok(true);
  }
}