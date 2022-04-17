package com.bookshelf.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshelf.domain.Book;
import com.bookshelf.service.BookService;
import com.bookshelf.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/books")
public class BookResource {

	@Autowired
	private BookService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id) throws ObjectNotFoundException {
		Book obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
