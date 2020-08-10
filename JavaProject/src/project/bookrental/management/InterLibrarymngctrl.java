package project.bookrental.management;

import java.util.Scanner;

public interface InterLibrarymngctrl {
	
	/*
	 * >> 사서 전용 메뉴 <<
	 * 메뉴 목록 출력
	 * 메뉴별 세부 프로그램 메소드 호출
	 */
	void onlyLibrarian(Scanner sc);  // 사서 전용 메뉴

	/*
	 * 사서가입하기
	 * 아이디와 암호를 입력받는다.
	 * 동일한 아이디 입력 시 유효성 검사를 통해 다시 입력받는다.
	 * 사서DTO에 생성자를 통해 값을 초기화한다.
	 * 사서DTO 객체를 List에 넣어 파일화한다.
	 */
	void registerLibrarian(Scanner sc);  // 사서가입
	
	boolean isUseLibID(String id); // 사서 아이디 유효성 검사
	
	/*
	 * 로그인
	 * 아이디와 암호를 입력받는다.
	 * 사서DTO 객체를 파일에서 불러와 값을 비교 후 동일할 경우에만 접속을 허용한다.
	 */
	LibrarianDTO loginLibrarian(Scanner sc); // 사서 로그인
	
	/*
	 * 도서정보 등록
	 * 도서정보 각각을 입력받는다.
	 * 정보입력에 조건을 걸어 유효성 검사를 실시한다.
	 * 책DTO 객체를 생성하여 생성자를 통해 값을 초기화한다.
	 * 객체를 Map에 넣어 파일화한다.
	 * 로그인 상태에서 메서드 호출 가능(매개변수)
	 */
	void registerBookInfo(LibrarianDTO lDTO, Scanner sc); // 도서정보등록
	
	boolean isUseIsbn(String isbn); // ISBN 유효성 검사
	
	/*
	 * 개별도서 등록
	 * ISBN을 입력받는다.
	 * 파일로 부터 객체를 불러와 입력된 ISBN 과 비교한다.
	 * 비교하여 일치하는 정보가 없을 시 실패 후 빠져나온다.
	 * 일치 시 도서아이디를 입력 받는다.
	 */
	void registerSeperateBook(LibrarianDTO lDTO, Scanner sc); // 개별도서 등록
	
	boolean isUseBookId(String bookId); // 도서아이디 유효성 검사
	
	/*
	 * 도서대여해주기
	 * 회원DTO 연결
	 * 회원id를 입력받는다.
	 * 아이디 불일치, 공백 시 반복문
	 * 이 후 대여권수를 입력받는다.
	 */
	void lendBook(LibrarianDTO lDTO, Scanner sc); // 도서대여해주기
	
	/*
	 * 대여중인도서조회
	 * 개별 도서DTO 연결
	 * 대여 불가(대여중)인 도서 목록
	 * 대여 중인 회원의 정보를 출력하기 위해 개별 도서DTO에 회원DTO 객체 생성
	 * 출력
	 */
	void lendingBookInfo(LibrarianDTO lDTO); // 대여중인도서조회
	
	/*
	 * 도서반납
	 * 대여DTO에 접근하여 입력받은 반납권수와 도서ID와 일치하는 대여도서 객체를 리스트와 파일에서
	 * 제거하고 파일로 만들며, 출력을 진행한다.
	 * 대여DTO에는 연체료 데이터를 초기화할 메서드를 정의한다.
	 */
	void returnBook(LibrarianDTO lDTO, Scanner sc); // 도서반납해주기

		
	/*
	 * >> 일반회원 전용메뉴 <<
	 * 메뉴 목록 출력
	 * 메뉴별 세부 프로그램 메소드 호출
	 */
	void onlyUser(Scanner sc); // 일반회원 전용메뉴
	
	/*
	 * 일반회원 가입하기
	 * 아이디와 암호를 입력받는다.
	 * 동일한 아이디 입력 시 유효성 검사를 통해 다시 입력받는다.
	 * 회원DTO에 생성자를 통해 값을 초기화한다.
	 * 회원DTO 객체를 List에 넣어 파일화한다.
	 */
	void registerUser(Scanner sc); // 일반회원 가입
	
	boolean isUseMemId(String memId); // 도서아이디 유효성 검사
	
	/*
	 * 로그인
	 * 아이디와 암호를 입력받는다.
	 * 회원DTO 객체를 파일에서 불러와 값을 비교 후 동일할 경우에만 접속을 허용한다.
	 */
	MemberDTO loginUser(Scanner sc); // 일반회원 로그인
	
	/*
	 * 도서검색
	 * 검색 키워드를 입력받는다.
	 * 검색값에 따라 조건을 준다.
	 * 입력하지 않았을 때는 조건을 모두 만족하는 조건식을 세운다.
	 * 출력은 toString 메소드를 재정의하여 출력한다.
	 */
	void searchBook(MemberDTO mDTO, Scanner sc); // 도서검색
	
	/*
	 * 나의대여현황보기
	 * 대여DTO에 접근하여 로그인한 회원 아이디와 일치하는 대여 객체를 결과로 출력한다.
	 */
	void myRentalBookInfo(MemberDTO mDTO, Scanner sc); // 나의대여현황
	
}
