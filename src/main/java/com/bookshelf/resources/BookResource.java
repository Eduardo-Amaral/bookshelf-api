package com.bookshelf.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookshelf.domain.Book;
import com.bookshelf.dtos.BookDTO;
import com.bookshelf.service.BookService;
import com.bookshelf.service.exceptions.DataIntegrityViolationException;
import com.bookshelf.service.exceptions.ObjectNotFoundException;

@CrossOrigin("*")
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

	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(@RequestParam(value = "category", defaultValue = "0") Integer id_cat)
			throws ObjectNotFoundException {
		List<Book> list = service.findAll(id_cat);
		List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Integer id, @Valid @RequestBody Book obj)
			throws ObjectNotFoundException {
		Book newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Book> updatePatch(@PathVariable Integer id, @Valid @RequestBody Book obj)
			throws ObjectNotFoundException {
		Book newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@PostMapping
	public ResponseEntity<Book> create( @RequestParam(value = "category", defaultValue = "0") Integer id_cat,
			@Valid @RequestBody Book obj) throws ObjectNotFoundException {
		Book newObj = service.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id)
			throws ObjectNotFoundException, DataIntegrityViolationException {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
