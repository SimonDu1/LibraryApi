package no.ntnu.librarybackend;

import no.ntnu.librarybackend.model.Book;
import no.ntnu.librarybackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/")
	public String getMessage(){
		return "HELLO WORLD";
	}

	@GetMapping("/message")
	public String message(){
		return "You did it";
	}


	@GetMapping(path = "/getAllBooks", headers = "Accept=application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Book> getAllBooks(){
		Map<String,Book> books;
		books = bookRepository.getAllBooks();
		return books;
	}



}
