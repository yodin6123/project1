package project.bookrental.management;

import java.io.Serializable;
import java.util.List;

public class BookDTO implements Serializable {
	
	private static final long serialVersionUID = 8360338530535567473L;
	private String isbn;
	private String category;
	private String bookName;
	private String author;
	private String publisher;
	private int price;
	private List<String> bookIdList;
	
	public BookDTO() {}
	
	public BookDTO(String isbn, String category, String bookName, String author, String publisher, int price) {
		this.isbn = isbn;
		this.category = category;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getBookIdList() {
		return bookIdList;
	}

	public void setBookIdList(List<String> bookIdList) {
		this.bookIdList = bookIdList;
	}
	
	

}
