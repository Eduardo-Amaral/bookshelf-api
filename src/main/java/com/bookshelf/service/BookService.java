package com.bookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.domain.Book;
import com.bookshelf.repositories.BookRepository;
import com.bookshelf.service.exceptions.ObjectNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryService categoryService;

	public Book findById(Integer id) throws ObjectNotFoundException {
		Optional<Book> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + " Tipo: " + Book.class.getName()));

	}

	public List<Book> findAll(Integer id_cat) throws ObjectNotFoundException {
		categoryService.findById(id_cat);
		return repository.findAllByCategory(id_cat);
	}
}
