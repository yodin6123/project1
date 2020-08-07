package project.bookrental.management;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class LibrarianMngCtrl implements InterLibrarymngctrl {
	
	private final String LIBRARIANLISTFILENAME = "C:/iotestdata/project/librarymng/librarianlist.dat";
	private final String BOOKLISTFILENAME = "C:/iotestdata/project/librarymng/booklist.dat";
	private final String SBOOKFILENAME = "C:/iotestdata/project/librarymng/separatebook.dat";
	private final String MEMBERLISTFILENAME = "C:/iotestdata/project/librarymng/memberlist.dat";
	private LibMngSerializable serial = new LibMngSerializable();

	// 사서 전용 메뉴
	@Override
	public void onlyLibrarian(Scanner sc) {
		LibrarianDTO lDTO = null;
		
		String libMenuSecond = "1.사서가입\t2.로그인\t3.로그아웃\t4.도서정보등록\t5.개별도서등록\n"+
							"6.도서대여해주기\t7.대여중인도서조회\t8.도서반납해주기\t9.나가기\n"+
							"=> 메뉴번호선택 : ";
		String libMenuNo = "";
		do {
			String libMenuFirst = (lDTO==null) ? "\n>>>> 사서 전용 메뉴 <<<<" : "\n>>>> 사서 전용 메뉴 ["+lDTO.getLibId()+" 로그인중..]<<<<";
			System.out.println(libMenuFirst);
			System.out.print(libMenuSecond);
			libMenuNo = sc.nextLine();
			switch(libMenuNo) {
				// 사서가입
				case "1" :
					registerLibrarian(sc);
					break;
				// 로그인
				case "2" :
					loginLibrarian(sc);
					break;
				// 로그아웃
				case "3" :
					break;
				// 도서정보등록
				case "4" :
					lDTO = loginLibrarian(sc);
					if(lDTO!=null) {
						registerBookInfo(lDTO, sc);
					} else {
						System.out.println(">>> 사서 전용 로그인을 해주세요!! <<<");
					}
					break;
				// 개별도서등록
				case "5" :
					lDTO = loginLibrarian(sc);
					if(lDTO!=null) {
						registerSeperateBook(lDTO, sc);
					} else {
						System.out.println(">>> 사서 전용 로그인을 해주세요!! <<<");
					}
					break;
				// 도서대여해주기
				case "6" :
					lDTO = loginLibrarian(sc);
					if(lDTO!=null) {
						lendBook(lDTO, sc);
					} else {
						System.out.println(">>> 사서 전용 로그인을 해주세요!! <<<");
					}
					break;
				// 대여중인도서조회
				case "7" :
					break;
				// 도서반납해주기
				case "8" :
					break;
				// 나가기
				case "9" :
					break;
				default :
					break;
			}
		} while(!"9".equals(libMenuNo)); // end of do~while
		
	} // end of librarianMenu
	
	// 사서가입
	@Override
	public void registerLibrarian(Scanner sc) {
		System.out.println("\n== 사서가입하기 ==");
		String libId = "";
		do {
			System.out.print("▶ 사서ID: ");
			libId = sc.nextLine();
			if(!isUseLibID(libId)) {
				System.out.println("~~~ "+libId+" 는 이미 존재하므로 다른 사서 ID를 입력하세요!!");
			} else {
				break;
			} // end of if~else
		} while(true); // end of do~while
		
		System.out.println("▶ 암호: ");
		String libPwd = sc.nextLine();
		
		LibrarianDTO lDTO = new LibrarianDTO(libId, libPwd);
		List<LibrarianDTO> libList = new ArrayList<>();
		
		File file = new File(LIBRARIANLISTFILENAME);
		// 파일이 존재하지 않는 경우
		if(!file.exists()) {
			libList.add(lDTO);
		// 파일이 존재하는 경우
		} else {
			Object libListObj = serial.getObjectFromFile(LIBRARIANLISTFILENAME);
			libList = (ArrayList<LibrarianDTO>)libListObj;
			libList.add(lDTO);
		}  // end of if~else
		
		int n = serial.objectToFileSave(libList, LIBRARIANLISTFILENAME);
		if(n==1) {
			System.out.println(">>> 사서등록 성공!! <<<\n");
		} else {
			System.out.println(">>> 사서등록 실패!! <<<\n");
		}
	}  // end of registerLibrarian()

	@Override
	public boolean isUseLibID(String id) {
		// 최초 가입 시 아이디 사용 가능
		boolean isUse = true;
		Object libListObj = serial.getObjectFromFile(LIBRARIANLISTFILENAME);
		if(libListObj!=null) {
			List<LibrarianDTO> libList = (ArrayList<LibrarianDTO>)libListObj;
			for(LibrarianDTO dto : libList) {
				if(id.equals(dto.getLibId())) {
					isUse = false;
					break;
				}
			}
		}
		return isUse;
	}

	@Override
	public LibrarianDTO loginLibrarian(Scanner sc) {
		LibrarianDTO lDTO = null;
		
		Object libListObj = serial.getObjectFromFile(LIBRARIANLISTFILENAME);
		List<LibrarianDTO> libList = (ArrayList<LibrarianDTO>)libListObj;
		System.out.println("\n == 로그인 하기 ==");
		System.out.print("▶ 사서아이디: ");
		String libId = sc.nextLine();
		System.out.print("▶ 암호: ");
		String libPwd = sc.nextLine();
		
		for(int i=0; i<libList.size(); i++) {
			if(libList.get(i).getLibId().equals(libId) && libList.get(i).getLibPwd().equals(libPwd)) {
				lDTO = libList.get(i);
				System.out.println(">>> 로그인 성공!!! <<<");
				break;
			} else {
				System.out.println(">>> 로그인 실패!!! <<<");
			} // end of if
		} // end of for
		
		return lDTO;
	} // end of login()

	@Override
	public void registerBookInfo(LibrarianDTO lDTO, Scanner sc) {
		System.out.println("\n== 도서정보 등록하기 ==");
		String isbn = "";
		do {
			System.out.printf("▶ 국제표준도서번호(ISBN) : ");
			isbn = sc.nextLine();
			// 엔터 또는 공백일 경우
			if(isbn.trim().isEmpty()) {
				System.out.println("~~~ 국제표준도서번호(ISBN)를 입력하세요!!");
			} else if(!isUseIsbn(isbn)) {
				System.out.println("~~~ "+isbn+" 는 이미 존재하므로 다른 국제표준도서번호(ISBN)을 입력하세요!!");
			} else {
				break;
			}
		} while(true); // end of do~while
				
		System.out.printf("▶ 도서분류카테고리 : ");
		String category = sc.nextLine();
		System.out.printf("▶ 도서명 : ");
		String bookName = sc.nextLine();
		System.out.printf("▶ 작가명 : ");
		String author = sc.nextLine();
		System.out.printf("▶ 출판사 : ");
		String publisher = sc.nextLine();
		
		int price = 0;
		do {
			System.out.printf("▶ 가격 : ");
			String sPrice = sc.nextLine();
			try {
				price = Integer.parseInt(sPrice);
				break;
			} catch(NumberFormatException e) {
				System.out.println("~~~~ 오류 : 가격은 숫자로만 입력하세요!!!");
			}
		} while(true); // end of do~while
		
		BookDTO bDTO = new BookDTO(isbn, category, bookName, author, publisher, price);
		List<BookDTO> bookList = new ArrayList<>();
		
		File file = new File(BOOKLISTFILENAME);
		// 파일이 존재하지 않는 경우
		if(!file.exists()) {
			bookList.add(bDTO);
		// 파일이 존재하는 경우
		} else {
			Object bookListObj = serial.getObjectFromFile(BOOKLISTFILENAME);
			bookList = (ArrayList<BookDTO>)bookListObj;
			bookList.add(bDTO);
		}  // end of if~else
		
		int n = serial.objectToFileSave(bookList, BOOKLISTFILENAME);
		if(n==1) {
			System.out.println(">>> 도서정보등록 성공!! <<<\n");
		} else {
			System.out.println(">>> 도서정보등록 실패!! <<<\n");
		} // end of if~else
		
	} // end of registerBookInfo()
	
	@Override
	public boolean isUseIsbn(String isbn) {
		// 최초 등록 시 ISBN 사용 가능
		boolean isUse = true;
		Object bookListObj = serial.getObjectFromFile(BOOKLISTFILENAME);
		if(bookListObj!=null) {
			List<BookDTO> bookList = (ArrayList<BookDTO>)bookListObj;
			for(BookDTO dto : bookList) {
				if(isbn.equals(dto.getIsbn())) {
					isUse = false;
					break;
				} // end of if
			} // end of for
		} // end of if
		
		return isUse;
	} // end of isUseIsbn

	// 개별도서 등록
	@Override
	public void registerSeperateBook(LibrarianDTO lDTO, Scanner sc) {
		Object bookListObj = serial.getObjectFromFile(BOOKLISTFILENAME);
		List<BookDTO> bookList = (ArrayList<BookDTO>)bookListObj;
		BookDTO bDTO = null;
		
		System.out.println("\n== 개별도서 등록하기 ==");
		System.out.print("▶ 국제표준도서번호(ISBN) : ");
		String isbn = sc.nextLine();
		// ISBN 확인
		int i = 0;
		for(; i<bookList.size(); i++) {
			if(!bookList.get(i).getIsbn().equals(isbn)) {
				System.out.println(">>> 등록된 ISBN 이 아닙니다. 도서등록 실패!! <<<");
				return;
			} else {
				bDTO = bookList.get(i);
			} // end of if~else
		} // end of for
		
		// 도서아이디 검증
		String bookId = "";
		do {
			System.out.print("▶ 도서아이디 : ");
			bookId = sc.nextLine();
			if(bookId.trim().isEmpty()) {
				System.out.println("~~~ 도서아이디를 입력하세요!!");
			// 도서아이디 중복 검사
			} else if(!isUseBookId(bookId)) {
				System.out.println("~~~ "+bookId+" 는 이미 존재하므로 다른 도서아이디를 입력하세요!!");
			} else {
				break;
			} // end of if~else
		} while(true); // end of do~while
		
		boolean isRentable = true;
		
		// 개별도서DTO 생성
		SeparateBookDTO sbDTO = new SeparateBookDTO(isbn, bookId, isRentable, bDTO);
		List<SeparateBookDTO> sbList = new ArrayList<>();
				
		File file = new File(SBOOKFILENAME);
		// 파일이 존재하지 않는 경우
		if(!file.exists()) {
			sbList.add(sbDTO);
		} else {
			Object sbListObj = serial.getObjectFromFile(SBOOKFILENAME);
			sbList = (ArrayList<SeparateBookDTO>)sbListObj;
			sbList.add(sbDTO);
		}
		
		int n = serial.objectToFileSave(sbList, SBOOKFILENAME);
		if(n==1) {
			System.out.println(">>> 도서등록 성공!! <<<");
		} else {
			System.out.println(">>> 도서등록 실패!! <<<");
		} // end of if~else
	} // end of registerSeperateBook()
	
	// 도서아이디 유효성 검사
	@Override
	public boolean isUseBookId(String bookId) {
		// 최초 등록 시 ISBN 사용 가능
		boolean isUse = true;
		Object sbListObj = serial.getObjectFromFile(SBOOKFILENAME);
		if(sbListObj!=null) {
			List<SeparateBookDTO> sbList = (ArrayList<SeparateBookDTO>)sbListObj;
			for(SeparateBookDTO dto : sbList) {
				if(bookId.equals(dto.getBookId())) {
					isUse = false;
					break;
				} // end of if
			} // end of for
		} // end of if
		
		return isUse;
	} // end of isUseIsbn
	
	// 도서대여해주기
	@Override
	public void lendBook(LibrarianDTO lDTO, Scanner sc) {
		Object mListObj = serial.getObjectFromFile(MEMBERLISTFILENAME);
		List<MemberDTO> mList = (ArrayList<MemberDTO>)mListObj;
		
		Object sbListObj = serial.getObjectFromFile(SBOOKFILENAME);
		List<SeparateBookDTO> sbList = (ArrayList<SeparateBookDTO>)sbListObj;
		
		System.out.println("\n >>> 도서대여하기 <<<");
		// 일치하는 회원ID 입력 시 다음 단계
		boolean bk = false;
		do {
			System.out.print("▶ 회원ID: ");
			String memId = sc.nextLine();
			for(MemberDTO dto : mList) {
				if(!dto.getMemId().equals(memId)) {
					System.out.println("~~~ 등록된 회원ID가 아닙니다 ~~~\n");
				} else if(!returnDateConf(memId)) {
					System.out.println("~~~~~ 반납예정일을 넘긴 미반납된 도서가 존재하므로 도서대여가 불가능합니다.!!!");
				} else {
					bk = true;
					break;
				} // end of if~else
			} // end of for
		} while(bk); // end of do~while
		
		SeparateBookDTO sbDTO = null;
		
		System.out.print("▶ 총대여권수: ");
		int lendNum = Integer.parseInt(sc.nextLine());
		
		// 총대여권수만큼 도서ID 입력
		int j = 0;
		for(int i=1; i<=lendNum; i++) {
			boolean bk2 = false; // 도서ID 입력 무한반복
			do {
				System.out.print("▶ 도서ID: ");
				String bookId = sc.nextLine();
				for(; j<sbList.size(); j++) {
					if(!sbList.get(j).getBookId().equals(bookId)) {
						System.out.println("~~~ 존재하지 않는 도서ID 입니다. 다시 입력하세요!! ~~~\n");
						i--; // 잘못 입력 시 입력 기회 다시 부여
					// 도서ID 입력 시
					} else {
						boolean state = sbList.get(i).getIsLendable(); // 도서 대여상태
						state = !state; // 대여상태 전환
						sbDTO = sbList.get(j); // 해당 도서 객체 주소값
						j = sbList.indexOf(sbDTO); // 해당 도서 객체 인덱스
						bk2 = true; // 무한반복 탈출
					} // end of if~else
				} // end of for
			} while(bk2); // end of while
		} // end of for
		
		// 대여일자
		Calendar lendD = Calendar.getInstance();
		sbDTO.setLendDate(lendD);
		
		// 객체 수정을 위해 삭제 후 다시 추가
		sbList.remove(j);
		sbList.add(j, sbDTO);
		int n = serial.objectToFileSave(sbList, SBOOKFILENAME);
		if(n==1) {
			System.out.println(">>> 대여등록 성공!! <<<");
			System.out.println(">>> 대여도서 비치중에서 대여중으로 변경함 <<<");
		} else {
			System.out.println(">>> 대여등록 실패!! <<<");
		}
	} // end of lendBook
	
	// 반납예정일 확인
	@Override
	public boolean returnDateConf(String memId) {
		boolean overDate = false;
		
		Object mListObj = serial.getObjectFromFile(MEMBERLISTFILENAME);
		List<MemberDTO> mList = (ArrayList<MemberDTO>)mListObj;
		
		Calendar rDate = null;
		for(MemberDTO dto : mList) {
			if(memId.equals(dto.getMemId())) {
				rDate = dto.getSbDTO().getLendDate();
			}
		}
		
		Calendar cal = Calendar.getInstance();
		// 날짜 같으면 0, 비교 대상이 작으면 1, 비교 대상이 크면 -1
		if(rDate.compareTo(cal)>=0) {
			overDate = true;
		}
		return overDate;
	}
	
	
	// 대여중인도서조회
	@Override
	public void lendingBookInfo(LibrarianDTO lDTO) {
		Object memListObj = serial.getObjectFromFile(MEMBERLISTFILENAME);
		List<MemberDTO> memList = (ArrayList<MemberDTO>)memListObj;
		
		System.out.println("======================================================================\n"
				+ "도서ID\tISBN\t도서명\t작가명\t출판사\t회원ID\t회원명\t연락처\t대여일자\t반납예정일\n"
				+ "======================================================================");
		for(MemberDTO dto : memList) {
			if(!dto.getSbDTO().getIsLendable()) {
				System.out.println(dto);
			}
		}
		
	}

	
	// 일반회원 전용메뉴
	@Override
	public void onlyUser(Scanner sc) {}

	@Override
	public void registerUser(Scanner sc) {}

	@Override
	public boolean isUseMemId(String memId) {
		return false;
	}

	
	
	

	

	@Override
	public void returnBook(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public MemberDTO loginUser(Scanner sc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void searchBook(MemberDTO mDTO, Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void myRentalBookInfo(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
} // end of class
