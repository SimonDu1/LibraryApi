package no.ntnu.iiir.mobapp.api.librarylocatorapi.repository;

import no.ntnu.iiir.mobapp.api.librarylocatorapi.model.Book;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing.XMLParser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public JSONArray getAllBooks() {
		List<Book> books = new ArrayList<>();
		JSONArray result = new JSONArray();
		var sql = "select * from books";
		books = jdbcTemplate.query(sql,
				(rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("bookisbn"))
		);
		for (int i = 0; i < books.size(); i++) {
			result.put(XMLParser.parseXML("47BIBSYS_NTNU_UB", books.get(i).getIsbn()));
		}
		return result;
	}
}
