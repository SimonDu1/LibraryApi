package no.ntnu.iiir.mobapp.api.librarylocatorapi.controller;


import no.ntnu.iiir.mobapp.api.librarylocatorapi.repository.BookRepository;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing.XMLParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Component
public class BookController{

	@Autowired
	BookRepository bookRepository;


	@PostMapping(path = "/getBookDetail")
	@ResponseBody
	public JSONArray getBookDetail(@RequestBody String requestBody) {
		JSONArray book = new JSONArray();
		book.put(XMLParser.parseXML("47BIBSYS_NTNU_UB",requestBody));
		return book;
	}

	@GetMapping("/message")
	public String message(){
		return "You did it";
	}

	@GetMapping(path = "/getAllBooks", headers = "Accept=application/json; charset=UTF-8")
	@ResponseBody
	public String getAllBooks() throws JSONException {
		JSONArray books = new JSONArray();
		books = bookRepository.getAllBooks();

		return books.toString();
	}

}
