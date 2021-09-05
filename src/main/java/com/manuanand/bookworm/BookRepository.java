package com.manuanand.bookworm;

import org.springframework.data.repository.CrudRepository;

import com.manuanand.bookworm.Book;

// This will be AUTO IMPLEMENTED by Spring into a Bean called nodeRepository
// CRUD refers Create, Read, Update, Delete

public interface BookRepository extends CrudRepository<Book, Integer> {

}
