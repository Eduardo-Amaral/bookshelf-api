package com.bookshelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookshelf.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
