package com.example.demo.service;

import org.springframework.stereotype.Component;

import com.example.demo.dao.BookDao;
import com.example.demo.dto.BookRequest;
import com.example.demo.model.Book;

@Component
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;

	BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public Book getBookById(Integer bookId) {
		// TODO Auto-generated method stub
		return bookDao.getBookById(bookId);
	}

	@Override
	public Integer createBook(BookRequest bookRequest) {
		// TODO Auto-generated method stub
		return bookDao.createBook(bookRequest);
	}

	@Override
	public void updateBook(Integer bookId, BookRequest bookRequest) {
		// TODO Auto-generated method stub
		bookDao.updateBook(bookId, bookRequest);
	}

	@Override
	public void deleteBookById(Integer bookId) {
		// TODO Auto-generated method stub
		bookDao.deleteBookById(bookId);
	}

}
