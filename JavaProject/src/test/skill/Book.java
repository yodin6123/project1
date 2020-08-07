package test.skill;

public class Book {

	private String category;      // 도서 카테고리  >> field, attribute, 속성
	private String bookName;      // 도서명
	private int bookPrice;        // 정가
	private int bookDiscountRate; // 할인율

	public Book() {
	}

	public Book(String category, String bookName, int bookPrice, int bookDiscountRate) {
		this.category = category;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookDiscountRate = bookDiscountRate;
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

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookDiscountRate() {
		return bookDiscountRate;
	}

	public void setBookDiscountRate(int bookDiscountRate) {
		this.bookDiscountRate = bookDiscountRate;
	}

	
	public int bookDiscountPrice(int bookPrice, int bookDiscountRate) {
		return bookPrice * bookDiscountRate / 100;
	}

	public int totalPrice(Book[] bookArray) {
		int sum = 0;

		for (int i = 0; i < bookArray.length; i++) {
			sum += bookArray[i].getBookPrice();
		}

		return sum;
	}

	public int totalDiscountPrice(int[] bookdp) {
		int sum = 0;

		for (int i = 0; i < bookdp.length; i++) {
			sum += bookdp[i];
		}

		return sum;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/*
	public String getBookPriceComma() {   // method, operation(기능)
		DecimalFormat df = new DecimalFormat("#,###");   // 10진수 세자리마다 쉼표를 찍어주는 형식
		return df.format(bookPrice);   // 인자의 값을 형식에 맞게 변환하여 String 타입으로 반환
	 */
	
	/*
	할인가 = 정가 * (100-할인율) * 0.01
	public int getBookSalePrice() {   // 할인가
		return (int)(bookPrice*(100-bookDiscountRate)*0.01);
	 */
	
	/*
	public String getBookSalePriceComma() {   // 할인가(int)를 세자리마다 콤마(,)를 찍어주는 method
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(getBookSalePrice());
	 */

}
