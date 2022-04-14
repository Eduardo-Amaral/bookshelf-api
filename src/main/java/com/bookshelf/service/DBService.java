package com.bookshelf.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.Category;
import com.bookshelf.repositories.BookRepository;
import com.bookshelf.repositories.CategoryRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepository bookRepository;

	public void DataBaseInstance() {
		Category cat1 = new Category(null, "Informatica", "Livros de TI");
		Category cat2 = new Category(null, "Fantasia", "Livros de Fantasia");

		Book b1 = new Book(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		Book b2 = new Book(null, "The Pragmatic Programmer", "Dave Thomas", "Lorem ipsum.", cat1);
		Book b3 = new Book(null, "O Último Desejo", "Andrzej Sapkowski", "Lorem ipsum..", cat2);
		Book b4 = new Book(null, "Espada do Destino", "Andrzej Sapkowski", "Lorem ipsum...", cat2);

		cat1.getBooks().addAll(Arrays.asList(b1, b2));
		cat2.getBooks().addAll(Arrays.asList(b3, b4));

		this.categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		this.bookRepository.saveAll(Arrays.asList(b1,b2,b3,b4));
	}
}
