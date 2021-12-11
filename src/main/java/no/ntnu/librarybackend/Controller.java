package no.ntnu.librarybackend;

import no.ntnu.librarybackend.model.Book;
import no.ntnu.librarybackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
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

	/**
	 * Gets search result from API, searching for author, title, and ISBN.
	 * @param query search query
	 * @return search result
	 */
	@GetMapping(path = "/search={query}", headers = "Accept=application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Book> search(@PathVariable(value = "query") String query) {
		HashMap<String, Book> result = new HashMap<>();

		Book isbnBook = findBooksByIsbn(query);

		result.putAll(searchForBooksByAuthor(query));
		result.putAll(searchForBooksByTitle(query));

		if (isbnBook != null) {
			result.put(isbnBook.getIsbn(), isbnBook);
		}

		return result;
	}

	/**
	 * Gets books which contain the query string in the title.
	 * @param query search query
	 * @return search result
	 */
	private Map<String, Book> searchForBooksByTitle(String query) {
		HashMap<String, Book> result = new HashMap<>();
		Collection<Book> allBooks = bookRepository.getAllBooks().values();

		for (Book book : allBooks) {
			if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
				result.put(book.getIsbn(), book);
			}
		}

		return result;
	}

	/**
	 * Searches for books where the author contains query
	 * @param query Search query
	 * @return List of books containing query in author
	 */
	private Map<String, Book> searchForBooksByAuthor(String query) {
		HashMap<String, Book> result = new HashMap<String, Book>();
		Collection<Book> allBooks = bookRepository.getAllBooks().values();

		for (Book book : allBooks) {
			if (book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
				result.put(book.getIsbn(), book);
			}
		}

		return result;
	}

	private Book findBooksByIsbn(String isbn) {
		return bookRepository.getAllBooks().get(isbn);
	}

}
