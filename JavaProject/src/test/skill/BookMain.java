package test.skill;

public class BookMain {

	public static void main(String[] args) {
		
		// Book 이라는 클래스의 객체를 5개를 저장할 수 있는 배열 bookArray 를 생성하세요.
		Book[] bookArray = new Book[5];
		
		bookArray[0] = new Book("IT", "SQL Plus", 50000, 5);
		bookArray[1] = new Book("IT", "Java 2.0", 40000, 3);
		bookArray[2] = new Book("IT", "JSP Servlet", 60000, 6);
		bookArray[3] = new Book("Nobel", "davincicode", 30000, 10);
		bookArray[4] = new Book("Nobel", "cloven hoot", 50000, 15);
		
		Book book = new Book();
		
		int[] bookdp = new int[bookArray.length];
		
		System.out.println("==================================================");
		System.out.println("카테고리\t도서명\t\t정가\t할인율\t판매세일가");
		System.out.println("==================================================");
		
		for(int i = 0; i < bookArray.length; i++) {
						
			bookdp[i] = book.bookDiscountPrice(bookArray[i].getBookPrice(), bookArray[i].getBookDiscountRate()); 
						
			System.out.printf("%s\t%s\t%,d원\t%d\t%,d원\n", bookArray[i].getCategory(), bookArray[i].getBookName(),
					bookArray[i].getBookPrice(), bookArray[i].getBookDiscountRate(), bookdp[i]);
						
		}
		
		System.out.println("==================================================");
		System.out.printf("판매정가합 : %,d원\t판매세일가합 : %,d원", book.totalPrice(bookArray),
				book.totalDiscountPrice(bookdp));
		
		/*
		 * 풀이
		System.out.println("============================================================");
		System.out.println("카테고리\t도서명\t\t정가\t할인율\t판매세일가");
		System.out.println("============================================================");
		
		int sumPrice = 0; sumSalePrice = 0;
		for(Book book : bookArray) {
		
			String info = book.getCategory+"\t"+book.getBookName()+"\t"+book.getBookPriceComma()+"원"+"\t\t"+
				book.getBookDiscountRate()+"%\t"+book.getBookSalePriceComma()+"원";
			System.out.println(info);
		
			sumPrice += book.getBookPrice();   // 정가의 누적을 구한다.
			sumSalePrice += book.getBookSalePrice();   // 할인가의 누적을 구한다.
		}// end of for
		
		System.out.println("============================================================");
		
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println("판매정가합 : "+df.format(sumPrice)+"원\t판매세일가합 : "+df.format(sumSalePrice)+"원");
		
		 */
		

	}

}
