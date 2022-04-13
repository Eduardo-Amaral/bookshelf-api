package com.bookshelf;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookshelf.domain.Book;
import com.bookshelf.domain.Category;
import com.bookshelf.repositories.BookRepository;
import com.bookshelf.repositories.CategoryRepository;

@SpringBootApplication
public class BookshelfApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepository bookRepository;
	

    public static void main(String[] args) {
        SpringApplication.run(BookshelfApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null,"Informatica", "Livros de TI");
		
		Book b1 = new Book(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		
		cat1.getBooks().addAll(Arrays.asList(b1));
		
		this.categoryRepository.saveAll(Arrays.asList(cat1));
		this.bookRepository.saveAll(Arrays.asList(b1));
		
	}

}
