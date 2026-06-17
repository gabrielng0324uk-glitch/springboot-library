package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BookRequest;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {

		Book book = bookService.getBookById(bookId);
		if (book != null) {
			return ResponseEntity.status(HttpStatus.OK).body(book);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest){
		Integer bookId = bookService.createBook(bookRequest);
		Book book = bookService.getBookById(bookId);
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}

	@PutMapping("/book/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, 
			@RequestBody BookRequest bookRequest){
		
		if (bookId == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		bookService.updateBook(bookId, bookRequest);
		Book updateBook = bookService.getBookById(bookId);
		return ResponseEntity.status(HttpStatus.OK).body(updateBook);
	}
	
	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable Integer bookId){
		if (bookId == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		bookService.deleteBookById(bookId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}
