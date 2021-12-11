package no.ntnu.librarybackend.model;


/**
 * Represents a book in a library system.
 */
public class Book {
  private Long id;
  private String isbn;
  private String title;
  private String author;
  private String summary;
  private String image;

  public Book(Long id,String isbn) {
    this.id = id;
    this.isbn = isbn;
  }

  public Book(String isbn, String title, String author, String summary, String image) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.summary = summary;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", isbn='" + isbn + '\'' +
            '}';
  }
}