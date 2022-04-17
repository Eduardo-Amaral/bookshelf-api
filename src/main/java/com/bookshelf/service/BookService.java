package com.bookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.Category;
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
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + " Tipo: " + Book.class.getName()));

	}

	public List<Book> findAll(Integer id_cat) throws ObjectNotFoundException {
		categoryService.findById(id_cat);
		return repository.findAllByCategory(id_cat);
	}

	public Book update(Integer id, Book obj) throws ObjectNotFoundException {
		Book newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Book newObj, Book obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setAuthorName(obj.getAuthorName());
		newObj.setText(obj.getText());
	}

	public Book create(Integer id_cat, Book obj) throws ObjectNotFoundException {
		obj.setId(null);
		Category cat = categoryService.findById(id_cat);
		obj.setCategory(cat);
		return repository.save(obj);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		Book obj = findById(id);
		repository.delete(obj);
	}
}
