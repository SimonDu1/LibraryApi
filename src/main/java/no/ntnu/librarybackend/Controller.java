package no.ntnu.librarybackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String getMessage(){
		return "HELLO WORLD";
	}

	@GetMapping("/message")
	public String message(){
		return "You did it";
	}



}
