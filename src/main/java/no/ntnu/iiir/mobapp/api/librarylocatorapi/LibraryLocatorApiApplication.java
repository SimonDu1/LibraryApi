package no.ntnu.iiir.mobapp.api.librarylocatorapi;

import no.ntnu.iiir.mobapp.api.librarylocatorapi.repository.BookRepository;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing.XMLParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class LibraryLocatorApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryLocatorApiApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(LibraryLocatorApiApplication.class, args);
	}
}
