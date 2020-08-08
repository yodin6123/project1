package project.bookrental.management;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

public class SeparateBookDTO implements Serializable {

	private static final long serialVersionUID = -1152724621094187774L;
	private String isbn;
	private String bookId;
	private boolean isLendable; // 비치 중일 때 true, 대여 중일 때 false
	private BookDTO bookDTO;
	private Calendar lendDate;
	
	public SeparateBookDTO() {}
	
	public SeparateBookDTO(String isbn, String bookId, boolean isLendable, BookDTO bookDTO) {
		this.isbn = isbn;
		this.bookId = bookId;
		this.isLendable = isLendable;
		this.bookDTO = bookDTO;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public boolean getIsLendable() {
		return isLendable;
	}

	public void setLendable(boolean isLendable) {
		this.isLendable = isLendable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}
	
	public Calendar getLendDate() {
		return lendDate;
	}

	public void setLendDate(Calendar lendDate) {
		this.lendDate = lendDate;
	}

	public String getPriceComma(int price) {
		DecimalFormat df = new DecimalFormat("#.###");
		String sPrice = df.format(price);
		return sPrice;
	}
	
	public String sIsLendable(boolean isLendable) {
		String strLend = "";
		if(isLendable) {
			strLend = "비치중";
		} else {
			strLend = "대여중";
		}
		return strLend;
	}
	
	public String toString() {
		return isbn + "\t" +
				bookId + "\t" + 
				bookDTO.getBookName() + "    " + 
				bookDTO.getAuthor() + "\t" +
				bookDTO.getPublisher() + "\t" + 
				getPriceComma(bookDTO.getPrice()) + "\t" + 
				sIsLendable(isLendable);
	}

}
