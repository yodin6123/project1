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
	private final String RENTALBOOKLISTFILENAME = "C:/iotestdata/project/librarymng/rentallist.dat";
	private LibMngSerializable serial = new LibMngSerializable();

	// 일반회원 전용메뉴
	@Override
	public void onlyUser(Scanner sc) {
		MemberDTO mDTO = null;

		String memMenuSecond = "1.일반회원가입\t2.로그인\t3.로그아웃\t4.도서검색하기\t5.나의대여현황보기\t6.나가기\n" + "=> 메뉴번호선택 : ";
		String memMenuNo = "";
		do {
			String memMenuFirst = (mDTO==null) ? "\n>>>> 일반회원 전용 Menu <<<<"
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
				if(mDTO != null) {
					System.out.println("~~~~ 이미 로그인된 상태입니다!!!");
				} else {
					mDTO = loginUser(sc);
				}
				break;
			// 로그아웃
			case "3":
				mDTO = null;
				break;
			// 도서검색하기
			case "4":
				if(mDTO!=null) {
					searchBook(mDTO, sc);
				} else {
					System.out.println(">>> 회원 전용 로그인을 해주세요!! <<<");
				}
				break;
			// 나의대여현황보기
			case "5":
				if(mDTO!=null) {
					myRentalBookInfo(mDTO, sc);
				} else {
					System.out.println(">>> 회원 전용 로그인을 해주세요!! <<<");
				}
				break;
			// 나가기
			case "6":
				break;
			default:
				System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!!");
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

		System.out.print("▶ 암호: ");
		String memPwd = sc.nextLine();

		System.out.print("▶ 성명: ");
		String memName = sc.nextLine();

		System.out.print("▶ 연락처: ");
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

		boolean status = false;
		for (int i = 0; i < memList.size(); i++) {
			if (memList.get(i).getMemId().equals(memId) && memList.get(i).getMemPwd().equals(memPwd)) {
				mDTO = memList.get(i);
				status = true;
				break;
			} // end of if
		} // end of for
		
		if(status) {
			System.out.println(">>> 로그인 성공!!! <<<");
		} else {
			System.out.println(">>> 로그인 실패!!! <<<");
		}

		return mDTO;
	} // end of loginUser()

	// 도서검색
	@Override
	public void searchBook(MemberDTO mDTO, Scanner sc) {
		

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
		
		ArrayList<SeparateBookDTO> answer_category = new ArrayList<SeparateBookDTO>();
		ArrayList<SeparateBookDTO> answer_bookname = new ArrayList<SeparateBookDTO>();
		ArrayList<SeparateBookDTO> answer_author = new ArrayList<SeparateBookDTO>();
		ArrayList<SeparateBookDTO> answer_publisher = new ArrayList<SeparateBookDTO>();
		
		ArrayList<SeparateBookDTO> list = new ArrayList<SeparateBookDTO>();
		
		Object sbListObj = serial.getObjectFromFile(SBOOKFILENAME);
		list = (ArrayList<SeparateBookDTO>)sbListObj;
		
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;
		if (list != null) {
			if (!category.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookDTO().getCategory().equalsIgnoreCase(category)) {
						answer_category.add(list.get(c));
					}
				} 
			} else {
				flag1 = true;
				answer_category = list;
			}
			if (!bookName.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookDTO().getBookName().equalsIgnoreCase(bookName)) {
						answer_bookname.add(list.get(c));
					}
				}
			} else {
				flag2 = true;
				answer_bookname = list;
			}
			if (!author.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookDTO().getAuthor().equalsIgnoreCase(author)) {
						answer_author.add(list.get(c));
					}
				}
			} else {
				flag3 = true;
				answer_author = list;
			}
			if (!publisher.trim().isEmpty()) {
				for (int c=0; c<list.size(); c++) {
					if (list.get(c).getBookDTO().getPublisher().equalsIgnoreCase(publisher)) {
						answer_publisher.add(list.get(c));
					}
				}
			} else {
				flag4 = true;
				answer_publisher = list;
			}
		} 
		
		System.out.println("====================================================================================================\n"
				+ "ISBN\t\t\t도서아이디\t\t\t도서명\t작가명\t출판사\t\t가격\t\t대여상태\n"
				+ "====================================================================================================");
		String temp = null;
		 
		for (int i=0; i<list.size(); i++) {
			if (answer_category.contains(list.get(i)) && answer_bookname.contains(list.get(i)) && answer_author.contains(list.get(i)) && answer_publisher.contains(list.get(i))) {
				flag5 = true;
				if (list.get(i).getIsLendable() == false) {
					temp = "비치중";
				} else {
					temp = "대여중";
				}
				System.out.print(list.get(i).toString()); 
				System.out.printf("        %,d", list.get(i).getBookDTO().getPrice());
				System.out.printf("		%s\n", temp);
			}
		}
		
		if (!flag5) System.out.println("~~~ 검색에 일치하는 도서가 없습니다 ~~~");
		
	} // end of searchBook()
	
	@Override
	public void myRentalBookInfo(MemberDTO mDTO, Scanner sc) {
		Object rListObj = serial.getObjectFromFile(RENTALBOOKLISTFILENAME);
		List<RentalDTO> rList = (ArrayList<RentalDTO>)rListObj;
		
		System.out.println("==============================================================================================================\n"
				+ "도서아이디\t\t\tISBN\t\t\t도서명\t작가명\t출판사\t회원ID\t대여일자\t\t반납예정일\n"
				+ "==============================================================================================================");
		boolean lendStatus = true;
		for(int i=0; i<rList.size(); i++) {
			if(mDTO.getMemId().equals(rList.get(i).getMemId())) {
				lendStatus = false;
				System.out.println(rList.get(i).toStringSnd());
			} 
		}
		
		if(lendStatus) {
			System.out.println("~~~ 대여해가신 도서가 없습니다. ~~~");
		}
	}
	

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
	public void returnBook(LibrarianDTO lDTO, Scanner sc) {}
	
}
