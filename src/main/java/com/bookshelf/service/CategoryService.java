package com.bookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.domain.Category;
import com.bookshelf.dtos.CategoryDTO;
import com.bookshelf.repositories.CategoryRepository;
import com.bookshelf.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category findById(Integer id) throws ObjectNotFoundException {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category create(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Category update(Integer id, CategoryDTO objDto) throws ObjectNotFoundException {
		Category obj = findById(id);
		obj.setName(objDto.getName());
		obj.setDescription(objDto.getDescription());
		return repository.save(obj);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		findById(id);
		repository.deleteById(id);
	}
}
