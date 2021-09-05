package com.manuanand.bookworm;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/author") // This means URL's start with /author (after Application path)
public class AuthorController {
	@Autowired // This means to get the bean called authorRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private AuthorRepository authorRepository;

	///
	// Author Repository
	///
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody Author addAuthor (
			@RequestParam String name, @RequestParam String email) {

		Author newAuthor = new Author();
		newAuthor.setName(name);
		newAuthor.setEmail(email);
		
		authorRepository.save(newAuthor);
		
		return newAuthor;
	}

	@GetMapping(path="/")
	public @ResponseBody Iterable<Author> getAllAuthors() {
		
		// This returns a JSON or XML with the authors
		return authorRepository.findAll();
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Author getSpecificAuthor(@PathVariable String id) {
		
		Integer authorId = null;
		try {
			authorId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return null;
		}

		Optional<Author> author = authorRepository.findById(authorId);
		if (!author.isEmpty()) {
			return author.get();
		} 

		return null;
	}
}