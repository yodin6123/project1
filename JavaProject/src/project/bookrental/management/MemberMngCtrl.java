package project.bookrental.management;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MemberMngCtrl implements InterLibrarymngctrl {

	private final String MEMBERLISTFILENAME = "C:/iotestdata/project/librarymng/memberlist.dat";
	private final String SBOOKFILENAME = "C:/iotestdata/project/librarymng/separatebook.dat";
	private LibMngSerializable serial = new LibMngSerializable();

	// 일반회원 전용메뉴
	@Override
	public void onlyUser(Scanner sc) {
		MemberDTO mDTO = null;

		String memMenuSecond = "1.일반회원가입\t2.로그인\t3.로그아웃\t4.도서검색하기\t5.나의대여현황보기\t6.나가기\n" + "=> 메뉴번호선택 : ";
		String memMenuNo = "";
		do {
			String memMenuFirst = (mDTO != null) ? "\n>>>> 일반회원 전용 Menu <<<<"
					: "\n>>>> 일반회원 전용 Menu [" + mDTO.getMemName() + " 로그인중..]<<<<";
			System.out.println(memMenuFirst);
			System.out.print(memMenuSecond);
			memMenuNo = sc.nextLine();
			switch (memMenuNo) {
			// 일반회원가입
			case "1":
				registerUser(sc);
				break;
			// 로그인
			case "2":
				loginUser(sc);
				break;
			// 로그아웃
			case "3":
				break;
			// 도서검색하기
			case "4":
				mDTO = loginUser(sc);
				if(mDTO!=null) {
					searchBook(mDTO, sc);
				} else {
					System.out.println(">>> 회원 전용 로그인을 해주세요!! <<<");
				}
				break;
			// 나의대여현황보기
			case "5":
				
				break;
			// 나가기
			case "6":
				break;
			default:
				break;
			}
		} while (!"6".equals(memMenuNo)); // end of do~while

	} // end of onlyUser()

	// 일반회원 가입하기
	@Override
	public void registerUser(Scanner sc) {
		System.out.println("\n== 일반회원 가입하기 ==");
		String memId = "";
		do {
			System.out.print("▶ 회원ID: ");
			memId = sc.nextLine();
			// 아이디 유효성 검사
			if (!isUseMemId(memId)) {
				System.out.println("~~~ " + memId + " 는 이미 존재하므로 다른 사서 ID를 입력하세요!!");
			} else {
				break;
			} // end of if~else
		} while (true); // end of do~while

		System.out.println("▶ 암호: ");
		String memPwd = sc.nextLine();

		System.out.println("▶ 성명: ");
		String memName = sc.nextLine();

		System.out.println("▶ 연락처: ");
		String memPhone = sc.nextLine();

		MemberDTO mDTO = new MemberDTO(memId, memPwd, memName, memPhone);
		List<MemberDTO> memList = new ArrayList<>();

		File file = new File(MEMBERLISTFILENAME);
		// 파일이 존재하지 않는 경우
		if (!file.exists()) {
			memList.add(mDTO);
			// 파일이 존재하는 경우
		} else {
			Object memListObj = serial.getObjectFromFile(MEMBERLISTFILENAME);
			memList = (ArrayList<MemberDTO>) memListObj;
			memList.add(mDTO);
		} // end of if~else

		// 파일 생성 여부
		int n = serial.objectToFileSave(memList, MEMBERLISTFILENAME);
		if (n == 1) {
			System.out.println(">>> 회원등록 성공!! <<<\n");
		} else {
			System.out.println(">>> 회원등록 실패!! <<<\n");
		} // end of if~else
	} // end of registerUser

	// 일반회원 아이디 유효성 검사
	@Override
	public boolean isUseMemId(String memId) {
		// 최초 등록 시 일반회원 아이디 사용 가능
		boolean isUse = true;
		Object memListObj = serial.getObjectFromFile(MEMBERLISTFILENAME);
		if (memListObj != null) {
			List<MemberDTO> memList = (ArrayList<MemberDTO>) memListObj;
			for (MemberDTO dto : memList) {
				if (memId.equals(dto.getMemId())) {
					isUse = false;
					break;
				} // end of if
			} // end of for
		} // end of if
		return isUse;
	} // end of isUseMemId

	// 일반회원 로그인
	@Override
	public MemberDTO loginUser(Scanner sc) {
		MemberDTO mDTO = null;

		Object memListObj = serial.getObjectFromFile(MEMBERLISTFILENAME);
		List<MemberDTO> memList = (ArrayList<MemberDTO>) memListObj;
		System.out.println("\n == 로그인 하기 ==");
		System.out.print("▶ 회원아이디: ");
		String memId = sc.nextLine();
		System.out.print("▶ 암호: ");
		String memPwd = sc.nextLine();

		for (int i = 0; i < memList.size(); i++) {
			if (memList.get(i).getMemId().equals(memId) && memList.get(i).getMemPwd().equals(memPwd)) {
				mDTO = memList.get(i);
				System.out.println(">>> 로그인 성공!!! <<<");
				break;
			} else {
				System.out.println(">>> 로그인 실패!!! <<<");
			} // end of if
		} // end of for

		return mDTO;
	} // end of loginUser()

	// 도서검색
	@Override
	public void searchBook(MemberDTO mDTO, Scanner sc) {
		Object sbListObj = serial.getObjectFromFile(SBOOKFILENAME);
		List<SeparateBookDTO> sbList = (ArrayList<SeparateBookDTO>)sbListObj;

		SeparateBookDTO bDTO = new SeparateBookDTO();

		System.out.println("\n >>> 도서검색하기 <<<");
		System.out.println("[주의사항] 검색어를 입력치 않고 엔터를 하면 검색대상에서 제외됩니다.");
		System.out.print("▶ 도서분류카테고리(Programming , DataBase 등): ");
		String category = sc.nextLine();
		System.out.print("▶ 도서명: ");
		String bookName = sc.nextLine();
		System.out.print("▶ 작가명: ");
		String author = sc.nextLine();
		System.out.print("▶ 출판사명: ");
		String publisher = sc.nextLine();
		
		// 하나의 조건을 거쳐가며 모든 조건을 만족하는 객체 찾아낸다.
		// 조건을 거치며 찾아낸 객체는 List에 저장하고 업데이트한다.
		List<SeparateBookDTO> newList = new ArrayList<>();
		// 카테고리 조건을 만족하는 객체를 새로운 리스트에 저장하고 공백 시 파일로 불러온 리스트를 그대로 새로운 리스트로 넘겨준다.
		if(!category.trim().isEmpty()) {
			for(int i=0; i<sbList.size(); i++) {
				if(category.equals(sbList.get(i).getBookDTO().getCategory())) {
					bDTO = sbList.get(i);
					newList.add(bDTO);
				} else { 
					System.out.println("~~~~ 검색에 일치하는 도서가 없습니다. ~~~~");
				}// end of if
			} // end of for
		} else {
			newList = sbList;
		} // end of if
		
		// 도서명 조건을 만족하는 객체를 새로운 리스트에 추가하고 공백 시 그대로 리스트를 넘겨준다.
		if(!bookName.trim().isEmpty()) {
			for(int i=0; i<sbList.size(); i++) {
				if(bookName.equals(sbList.get(i).getBookDTO().getBookName())) {
					bDTO = sbList.get(i);
					newList.add(bDTO);
				} else { 
					System.out.println("~~~~ 검색에 일치하는 도서가 없습니다. ~~~~");
				}// end of if
			} // end of for
		} // end of if
		
		// 작가명 조건을 만족하는 객체를 새로운 리스트에 추가하고 공백 시 그대로 리스트를 넘겨준다.
		if(!author.trim().isEmpty()) {
			for(int i=0; i<sbList.size(); i++) {
				if(author.equals(sbList.get(i).getBookDTO().getAuthor())) {
					bDTO = sbList.get(i);
					newList.add(bDTO);
				} else { 
					System.out.println("~~~~ 검색에 일치하는 도서가 없습니다. ~~~~");
				}// end of if
			} // end of for
		} // end of if
		
		// 출판사명 조건을 만족하는 객체를 새로운 리스트에 추가하고 공백 시 그대로 리스트를 넘겨준다.
		if(!publisher.trim().isEmpty()) {
			for(int i=0; i<sbList.size(); i++) {
				if(publisher.equals(sbList.get(i).getBookDTO().getPublisher())) {
					bDTO = sbList.get(i);
					newList.add(bDTO);
				} else { 
					System.out.println("~~~~ 검색에 일치하는 도서가 없습니다. ~~~~");
				}// end of if
			} // end of for
		} // end of if
		
		System.out.println("======================================================================\n"
				+ "ISBN\t도서아이디\t도서명\t작가명\t출판사\t가격\t대여상태\n"
				+ "======================================================================");
		// toString() 재정의 및 호출
		for(SeparateBookDTO dto : newList) {
			System.out.println(dto);
		}
		
	} // end of searchBook()
	

	// 사서 전용메뉴
	@Override
	public void onlyLibrarian(Scanner sc) {
	}

	@Override
	public void registerLibrarian(Scanner sc) {
	}

	@Override
	public boolean isUseLibID(String id) {
		return false;
	}

	@Override
	public LibrarianDTO loginLibrarian(Scanner sc) {
		return null;
	}

	@Override
	public void registerBookInfo(LibrarianDTO lDTO, Scanner sc) {
	}

	@Override
	public boolean isUseIsbn(String isbn) {
		return false;
	}

	@Override
	public void registerSeperateBook(LibrarianDTO lDTO, Scanner sc) {
	}

	@Override
	public boolean isUseBookId(String bookId) {
		return false;
	}

	@Override
	public void lendBook(LibrarianDTO lDTO, Scanner sc) {}
	
	@Override
	public void lendingBookInfo(LibrarianDTO lDTO) {}
	
	
	

	@Override
	public void returnBook(Scanner sc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void myRentalBookInfo(Scanner sc) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean returnDateConf(String memId) {
		// TODO Auto-generated method stub
		return false;
	}

}
