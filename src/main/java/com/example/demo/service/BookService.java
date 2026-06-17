package com.example.demo.service;

import com.example.demo.dto.BookRequest;
import com.example.demo.model.Book;

public interface BookService {

    Book getBookById(Integer bookId);

    Integer createBook(BookRequest bookRequest);

    void updateBook(Integer bookId, BookRequest bookRequest);

    void deleteBookById(Integer bookId);
}