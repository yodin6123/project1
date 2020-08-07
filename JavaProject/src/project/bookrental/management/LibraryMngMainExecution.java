package project.bookrental.management;

import java.util.Scanner;

public class LibraryMngMainExecution {

	public static void main(String[] args) {
		
		InterLibrarymngctrl ilmc = new LibrarianMngCtrl();
		
		Scanner sc = new Scanner(System.in);
		
		String pMenuNo = "";
		do {
			String libProgramFirst = "\n\t===> 도서대여 프로그램 <===\n"+
					"1.사서 전용메뉴\t2.일반회원 전용메뉴\t3.프로그램 종료\n"+
					"=> 메뉴번호선택 : ";
			System.out.print(libProgramFirst);
			pMenuNo = sc.nextLine();
			switch(pMenuNo) {
				// 사서 전용메뉴
				case "1" :
					ilmc.onlyLibrarian(sc);
					break;
				// 일반회원 전용메뉴
				case "2" :
					ilmc.onlyUser(sc);
					break;
				// 프로그램 종료
				case "3" :
					break;
				// 이외의 값 입력 시
				default :
					break;
			} // end of switch~case
		} while(!"3".equals(pMenuNo)); // end of do~while
		
		sc.close();

	} // end of main()

}
