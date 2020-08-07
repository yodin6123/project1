package project.employee.management;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

public class EmployeeMngCtrl implements InterEmployeeMngCtrl {

	private final String DEPTLISTFILENAME = "C:/iotestdata/project/employeemng/deptlist.dat";
	private final String EMPLOYEELISTFILENAME = "C:/iotestdata/project/employeemng/employeelist.dat";

	private EmpMngSerializable serial = new EmpMngSerializable();

	// 부서등록
	@SuppressWarnings("unchecked")
	@Override
	public void registerDept(Scanner sc) {

		System.out.println("\n== 부서 등록하기 ==");

		/*
		 * //
		 * -----------------------------------------------------------------------------
		 * // int nDtno = 0; do {
		 * 
		 * try { System.out.print("▶ 부서번호 : "); // 입력 : 10 관리부 서울시 강남구 대치동엔터 // 몰라엔터 =>
		 * 버퍼에 남아있기 때문에 계속 에러로 무한반복 nDtno = sc.nextInt(); sc.nextInt();는 입력되는 데이터의 종결자는
		 * 공백 또는 엔터이다. sc.nextInt();는 입력된 값이 공백이 있으면 공백을 무시를 하고서 종결자(공백 또는 엔터) 전 까지의
		 * int타입 데이터(10)만 읽어들여서 nDtno 변수에 넣어주고 종결자(공백 또는 엔터)를 포함한 나머지( 관리부 서울시 강남구 대치동)는
		 * 버퍼에서 삭제되지 않고 그대로 남아있게 된다.
		 * 
		 * break; } catch(InputMismatchException e) {
		 * System.out.println("~~~~~~~~~~ 오류 : 부서번호는 숫자로만 입력하세요!!\n"); sc.nextLine(); //
		 * 버퍼에 남아있는 몰라엔터 삭제! } } while(true);
		 * 
		 * System.out.print("▶ 부서명 : "); String sDname = sc.next(); sc.next();는 입력되는
		 * 데이터의 종결자는 공백 또는 엔터이다. sc.next();는 입력된 값이 공백이 있으면 공백을 무시를 하고서 종결자(공백 또는 엔터) 전
		 * 까지의 String타입 데이터(관리부)만 읽어들여서 sDname 변수에 넣어주고 종결자(공백 또는 엔터)를 포함한 나머지( 서울시 강남구
		 * 대치동)는 버퍼에서 삭제되지 않고 그대로 남아있게 된다.
		 * 
		 * 
		 * System.out.print("▶ 부서위치 : "); String sLoc = sc.nextLine(); sc.nextLine();은
		 * 버퍼에서 종결자(엔터)를 포함한 모든 String타입 데이터( 서울시 강남구 대치동엔터)를 읽어들여서 종결자(엔터) 전 까지의
		 * String타입 데이터( 서울시 강남구 대치동)를 sLoc 변수에 넣어주고 종결자(엔터)는 버퍼에서 삭제되어진다.
		 * 
		 * 
		 * System.out.println("~~~~~ 확인용 nDtno: " + nDtno);
		 * System.out.println("~~~~~ 확인용 sDname: " + sDname);
		 * System.out.println("~~~~~ 확인용 sLoc: " + sLoc);
		 * 
		 * //
		 * -----------------------------------------------------------------------------
		 * //
		 */

		// 유효성 검사
		int nDeptNo = 0;

		do {
			System.out.print("▶ 부서번호 : ");
			String deptNo = sc.nextLine();

			try {
				nDeptNo = Integer.parseInt(deptNo);
				break;
			} catch (NumberFormatException e) {
				System.out.println("~~~~ 오류 : 부서번호는 숫자로만 입력하세요!!!\n");
				// nextLine이라 버퍼에 남아있지 않아 버퍼 삭제없이도 무한반복 탈출이 가능하다!
			}
		} while (true);

		System.out.print("▶ 부서명 : ");
		String deptName = sc.nextLine();

		System.out.println("입력하신 부서명은 " + deptName + "입니다.");

		System.out.print("▶ 부서위치 : ");
		String deptLoc = sc.nextLine();

		DeptDTO deptDTO = new DeptDTO(nDeptNo, deptName, deptLoc);

		File file = new File(DEPTLISTFILENAME);
		// DEPTLISTFILENAME 에 해당하는 파일객체 생성하기

		Map<String, DeptDTO> deptMap = null;

		int n = 0;

		if (!file.exists()) { // 파일이 존재하지 않는 경우. 즉, 최초로 부서등록을 하는 경우이다.
			deptMap = new HashMap<>();
			deptMap.put(String.valueOf(nDeptNo), deptDTO); // 부서번호가 key 값
			n = serial.objectToFileSave(deptMap, DEPTLISTFILENAME);// DEPTLISTFILENAME 파일에 deptDTO 객체를 저장시킨다.
		} else { // 파일이 존재하는 경우. 두번째 이후로 부서를 등록하는 경우이다.
			Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME); // DEPTLISTFILENAME 파일에 저장된 객체를 불러온다.
			deptMap = (HashMap<String, DeptDTO>) deptMapObj;
			deptMap.put(String.valueOf(nDeptNo), deptDTO);
			/*
			 * // --- 확인용 시작 --- // Set<String> keySet = deptMap.keySet(); // keySet() : 키
			 * 뭉치를 추출(Set 타입) for(String key : keySet) { System.out.println("~~~ 확인용 key : "
			 * + key); // "10" "20" DeptDTO dto = deptMap.get(key);
			 * System.out.println("~~~ 확인용 dto : " + dto);
			 * 
			 * 1. 부서번호 : 10 2. 부서명 : 관리부 3. 부서위치 : 서울
			 * 
			 * 1. 부서번호 : 20 2. 부서명 : 연구부 3. 부서위치 : 인천
			 * 
			 * }
			 * 
			 * // --- 확인용 끝 --- //
			 */
			n = serial.objectToFileSave(deptMap, DEPTLISTFILENAME);// DEPTLISTFILENAME 파일에 deptDTO 객체를 저장시킨다.
		}

		if (n == 1) {
			System.out.println(">>> 부서등록 성공!! <<<");
		} else {
			System.out.println(">>> 부서등록 실패!! <<<");
		}

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void registerEmployee(Scanner sc) {

		// trim() : 좌우 공백을 없애는 메서드
		// isEmpty() : 값이 비어있다면 true, 아니면 false

		String id = null;
		do {
			System.out.print("▶ 아이디 : ");
			id = sc.nextLine();

			if (id == null || id.trim().isEmpty()) {
				System.out.println("~~~ 아이디를 입력하세요!!\n");
				// continue;
			} else if (!isUseID(id)) {
				System.out.println("~~~ " + id + " 는 이미 존재하므로 다른 ID를 입력하세요!!\n");
				// continue;
			} else {
				break;
			}
		} while (true);

		System.out.print("▶ 암호 : ");
		String pwd = sc.nextLine();

		System.out.print("▶ 사원명 : ");
		String name = sc.nextLine();

		System.out.print("▶ 생년월일(예 1994.08.04) : ");
		String birth = sc.nextLine();

		System.out.print("▶ 주소 : ");
		String address = sc.nextLine();

		System.out.print("▶ 직급 : ");
		String position = sc.nextLine();

		int nPay = 0;
		do {
			System.out.print("▶ 급여 : ");
			String pay = sc.nextLine();
			try {
				nPay = Integer.parseInt(pay);
				break;
			} catch (NumberFormatException e) {
				System.out.println("~~~~ 오류 : 급여는 숫자로만 입력하세요!!!\n");
			}
		} while (true);

		int nDeptNoOfEmp = 0;
		DeptDTO myDeptDTO = null;
		do {
			System.out.print("▶ 부서번호 : ");
			String deptNoOfEmp = sc.nextLine();

			// 입력받은 숫자가 없는 부서번호라면?
			// 부서정보가 저장되어진 파일에 가서 모든 부서번호를 읽어온다.
			// 읽어온 부서번호 중 위에서 사용자가 입력한 부서번호가 없다면
			// 존재하지 않는 부서번호임을 띄워주고 다시 부서번호를 입력받도록 하면 된다.
			Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME);
			Map<String, DeptDTO> deptMap = (HashMap<String, DeptDTO>) deptMapObj;
			Set<String> keyset = deptMap.keySet();
			boolean flag = false;
			for (String key : keyset) {
				if (key.equals(deptNoOfEmp)) {
					flag = true;
					break;
				}
			} // end of for

			if (!flag) {
				System.out.println("~~~ 입력하신 부서번호 " + deptNoOfEmp + "는 우리 회사에 존재하지 않는 부서번호입니다.\n");
				continue;
			}

			try {
				nDeptNoOfEmp = Integer.parseInt(deptNoOfEmp);
				myDeptDTO = deptMap.get(deptNoOfEmp);
				break;
			} catch (NumberFormatException e) {
				System.out.println("~~~~ 오류 : 부서번호는 숫자로만 입력하세요!!!");
			}

		} while (true);

		EmployeeDTO eDTO = new EmployeeDTO(id, pwd, name, birth, address, position, nPay, nDeptNoOfEmp, myDeptDTO);

		ArrayList<EmployeeDTO> employeeList = null;

		File file = new File(EMPLOYEELISTFILENAME);

		if (!file.exists()) { // 파일이 존재하지 않는 경우, 최초로 사원을 등록할 경우
			employeeList = new ArrayList<EmployeeDTO>();
			employeeList.add(eDTO);
		} else { // 파일이 존재하는 경우, 두번째 이후로 사원을 등록할 경우
			Object employeeListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
			employeeList = (ArrayList<EmployeeDTO>) employeeListObj;
			employeeList.add(eDTO);
		}

		int n = serial.objectToFileSave(employeeList, EMPLOYEELISTFILENAME);

		if (n == 1) {
			System.out.println(">>> 사원등록 성공!! <<<");
		} else {
			System.out.println(">>> 사원등록 실패!! <<<");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isUseID(String id) {

		boolean isUse = true;
		// 최초로 가입시 파일에 저장된 empListObj 객체가 없기 때문에. 즉, 중복된 아이디가 없으므로 사용가능하도록 한다.

		Object empListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);

		if (empListObj != null) {
			// 파일에 저장된 empListObj 객체가 있다면

			// 입력받은 id가 empListObj 객체 속에 이미 존재한다면 사용불가한 아이디 이다.
			ArrayList<EmployeeDTO> empList = (ArrayList<EmployeeDTO>) empListObj;

			for (EmployeeDTO emp : empList) {
				if (id.equals(emp.getId())) { // equals() : 비교할 대상은 null 값이 오면 안된다!(nullPointerException)
					// 입력한 id가 이미 존재하는 경우
					isUse = false;
					break;
				}
			}
			/*
			 * // 입력받은 id가 empListObj 객체 속에 이미 존재하지 않는면 사용가능한 아이디 이다. isUse = true; ==> 초기값과
			 * 같기 때문에 작성 필요!
			 */
		}

		return isUse;

	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public EmployeeDTO login(Scanner sc) {
		System.out.println("\n == 로그인 하기 ==");
		System.out.print("▶ 아이디 : ");
		String id = sc.nextLine();

		System.out.print("▶ 비밀번호 : ");
		String pwd = sc.nextLine();

		EmployeeDTO eDTO = new EmployeeDTO();

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		ArrayList<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;
		for (int i = 0; i < eList.size(); i++) {
			EmployeeDTO dto = eList.get(i);

			if (id.equals(dto.getId()) && pwd.equals(dto.getPwd())) {
				System.out.println(">>> 로그인 성공!! <<<\n");
				return dto;
			}
		}

		System.out.println(">>> 로그인 실패!! <<<\n");

		return null;
	}

	@Override
	public void employeeMenu(Scanner sc, EmployeeDTO loginEmp) {

		String eMenu = "";
		do {
			System.out.println(">>>> 사원관리 Menu [" + loginEmp.getName() + " 님 로그인중..]<<<<");
			System.out.println("1. 내정보 보기\t2. 내정보 변경하기\t3. 모든사원정보보기\t4. 사원검색하기\t5. 사원사직시키기\t6. 나가기");
			System.out.print("=> 메뉴번호선택 : ");
			eMenu = sc.nextLine();

			switch (eMenu) {
			case "1":
				// System.out.println("\n== 내정보 =="+loginEmp(.toString)); => 해당 화면 출력
				// 부서정보는 EmployeeDTO에서 필드에 객체를 생성!
				// DeptDTO 파일에서 부서정보를 불러와 일치 정보를 EmployeeDTO 생성자에 넣어준다(수정 작업 필요)
				System.out.println("\n== 내정보 ==\n" + loginEmp);
				break;
			case "2":
				updateMyInfo(loginEmp, sc);
				break;
			case "3":
				showAllEmployee();
				break;
			case "4":
				searchEmployeeMenu(loginEmp, sc);
				break;
			case "5":
				deleteEmployee(loginEmp, sc);
				break;
			case "6":
				break;
			default:
				System.out.println("~~~~ 존재하지 않는 메뉴번호입니다.!!/n");
				break;

			}
		} while (!"6".equals(eMenu));

	}

	@SuppressWarnings("unchecked")
	@Override
	public EmployeeDTO updateMyInfo(EmployeeDTO loginEmp, Scanner sc) {

		System.out.println("\n== 내 정보 변경하기 ==");

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		ArrayList<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;

		EmployeeDTO emp = null;
		int i = 0;
		for (; i < eList.size(); i++) {
			if (loginEmp.getId().equals(eList.get(i).getId())) {
				emp = eList.get(i);
				i = eList.indexOf(emp);
				System.out.println(emp);
				break;
			}
		}

		if (emp != null) {

			System.out.print("\n▶ 암호변경: ");
			String updatePwd = sc.nextLine();
			if (updatePwd.trim().isEmpty()) {
				updatePwd = emp.getPwd();
			}

			System.out.print("▶ 주소변경: ");
			String updateAddress = sc.nextLine();
			if (updateAddress.trim().isEmpty()) {
				updateAddress = emp.getAddress();
			}

			System.out.print("▶ 직급변경: ");
			String updatePosition = sc.nextLine();
			if (updatePosition.trim().isEmpty()) {
				updatePosition = emp.getPosition();
			}

			int nPay = 0;
			do {
				System.out.print("▶ 급여변경: ");
				String updatePay = sc.nextLine();
				if (updatePay.trim().isEmpty()) {
					nPay = emp.getPay();
					break;
				} else {
					try {
						nPay = Integer.parseInt(updatePay);
						break;
					} catch (NumberFormatException e) {
						System.out.println("~~~~ 오류 : 급여는 숫자로만 입력하세요!!!\n");
					}
				}

			} while (true);

			boolean isOK = false;
			do {
				System.out.print("▷ 변경하시겠습니까?[Y/N] => ");
				String conf = sc.nextLine();
				if ("Y".equalsIgnoreCase(conf)) {
					isOK = true;
					break;
				} else if ("N".equalsIgnoreCase(conf)) {
					isOK = false;
					break;
				} else {
					System.out.println("~~~ Y 또는 N을 입력하셔야 합니다.");
				}
			} while (true);

			if (isOK) {
				emp.setPwd(updatePwd);
				emp.setAddress(updateAddress);
				emp.setPosition(updatePosition);
				emp.setPay(nPay);

				eList.remove(i);

				eList.add(i, emp);

				int n = serial.objectToFileSave(eList, EMPLOYEELISTFILENAME);
				if (n == 1) {
					System.out.println(">>> 내정보 변경 성공!! <<<\n");
					return emp; // 변경 성공하면 변경 후 리턴 값!
				} else {
					System.out.println(">>> 내정보 변경 실패!! <<<\n");
				}
			} else {
				System.out.println(">>> 내정보 변경을 취소하셨습니다. <<<\n");
			}

		} else {
			System.out.println("~~~~ 내정보 변경작업 실패함!!!");
		}

		return loginEmp; // 변경 실패하면 원래 값 리턴!
	}

	@SuppressWarnings("unchecked")
	@Override
	public void showAllEmployee() {

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;

		System.out.println("\n>>> 모든사원 정보 출력 <<<");
		System.out.println(
				"==============================================================================================================");
		System.out.println("아이디\t암호\t사원명\t생년월일\t\t나이\t주소\t\t직급\t급여\t\t부서번호\t부서명\t부서위치");
		System.out.println(
				"==============================================================================================================");
		for (int i = 0; i < eList.size(); i++) {
			System.out.print(eList.get(i).getId() + "\t");

			String pwd = eList.get(i).getPwd();
			String repeat = new String(new char[pwd.length() - 3]).replace("\0", "*");
			String nPwd = pwd.substring(0, 3).concat(repeat);

			System.out.print(nPwd + "\t");
			System.out.print(eList.get(i).getName() + "\t");
			System.out.print(eList.get(i).getBirth() + "\t");
			System.out.print(eList.get(i).showAge() + "세\t");
			System.out.print(eList.get(i).getAddress() + "\t");
			System.out.print(eList.get(i).getPosition() + "\t");
			System.out.print(eList.get(i).getSalaryComma() + "\t");
			System.out.print(eList.get(i).getDeptNo() + "\t");
			System.out.print(eList.get(i).getDeptDTO().getDeptName() + "\t");
			System.out.print(eList.get(i).getDeptDTO().getDeptLoc() + "\n");
		}

	}

	@Override
	public void searchEmployeeMenu(EmployeeDTO loginEmp, Scanner sc) {
		String sMenu = "";
		do {
			System.out.println("\n>>>> 사원관리 Menu [" + loginEmp.getName() + " 님 로그인중..]<<<<");
			String[] search = { "사원명 검색", "연령대 검색", "직급 검색", "급여범위 검색", "부서명 검색" };
			for (int i = 0; i < search.length; i++) {
				System.out.print((i + 1) + "." + search[i] + "\t");
			}
			System.out.print("6.나가기\n");
			System.out.print("=> 메뉴번호선택 : ");
			sMenu = sc.nextLine();
			System.out.println();
			
			switch (sMenu) {
			case "1":
				searchEmployeeByName(sc);
				break;
			case "2":
				searchEmployeeByAge(sc);
				break;
			case "3":
				searchEmployeeByPos(sc);
				break;
			case "4":
				searchEmployeeBySalary(sc);
				break;
			case "5":
				searchEmployeeByDName(sc);
				break;
			case "6":
				break;
			default:
				System.out.println("~~~~ 존재하지 않는 메뉴번호 입니다.!!");
			}
		} while (!"6".equals(sMenu));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void searchEmployeeByName(Scanner sc) {

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;

		String name = "";
		do {
			System.out.print("\n▶ 검색할 사원명 : ");
			name = sc.nextLine();
			if (name.trim().isEmpty()) {
				System.out.println("~~~ 검색할 사원명을 입력하세요!!\n");
			} else {
				break;
			}
		} while (true);

		EmployeeDTO eDTO = null;

		List<EmployeeDTO> eList2 = new ArrayList<EmployeeDTO>();

		for (int i = 0; i < eList.size(); i++) {
			if (name.equals(eList.get(i).getName())) {
				eDTO = eList.get(i);
				eList2.add(eDTO);
			}
		}

		if (eDTO != null) {
			String str = "\n>>> 사원명 검색 <<<";
			printEmployee(str, eList2);
		} else {
			System.out.println(">>> 검색하신 " + name + " 는(은) 존재하지 않습니다. <<<");
		}

	}

	@Override
	public void printEmployee(String title, List<EmployeeDTO> empList) {

		System.out.println(title);
		System.out.println(
				"==============================================================================================================\n"
						+ "아이디\t암호\t사원명\t생년월일\t\t나이\t주소\t\t직급\t급여\t\t부서번호\t부서명\t부서위치\n"
						+ "==============================================================================================================");

		for (EmployeeDTO eDTO : empList) {

			System.out.print(eDTO.getId() + "\t");

			String pwd = eDTO.getPwd();
			String repeat = new String(new char[pwd.length() - 3]).replace("\0", "*");
			String nPwd = pwd.substring(0, 3).concat(repeat);

			System.out.print(nPwd + "\t");
			System.out.print(eDTO.getName() + "\t");
			System.out.print(eDTO.getBirth() + "\t");
			System.out.print(eDTO.showAge() + "세\t");
			System.out.print(eDTO.getAddress() + "\t");
			System.out.print(eDTO.getPosition() + "\t");
			System.out.print(eDTO.getSalaryComma() + "\t");
			System.out.print(eDTO.getDeptNo() + "\t");
			System.out.print(eDTO.getDeptDTO().getDeptName() + "\t");
			System.out.print(eDTO.getDeptDTO().getDeptLoc() + "\n");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void searchEmployeeByAge(Scanner sc) {

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;

		String age = "";
		int nAge = 0;
		do {
			System.out.print("\n▶ 검색할 연령대(예 20대 검색은 20으로 입력): ");
			age = sc.nextLine();
			try {
				nAge = Integer.parseInt(age);
				break;
			} catch (NumberFormatException e) {
				System.out.println("~~~ 연령대는 숫자로만 입력하세요!!");
			}

		} while (true);

		EmployeeDTO eDTO = null;

		List<EmployeeDTO> eList2 = new ArrayList<EmployeeDTO>();

		for (int i = 0; i < eList.size(); i++) {
			if ((nAge / 10) == (eList.get(i).showAge() / 10)) {
				eDTO = eList.get(i);
				eList2.add(eDTO);
			}
		}

		if (eDTO != null) {
			String str = "\n>>> 연령대 검색 <<<";
			printEmployee(str, eList2);
		} else {
			System.out.println(">>> 검색하신 연령대" + age + " 는(은) 존재하지 않습니다. <<<");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void searchEmployeeByPos(Scanner sc) {

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;

		String position = "";
		do {
			System.out.print("\n▶ 검색할 직급명 : ");
			position = sc.nextLine();
			if (position.trim().isEmpty()) {
				System.out.println("~~~ 검색할 직급명을 입력하세요!!\n");
			} else {
				break;
			}
		} while (true);

		EmployeeDTO eDTO = null;

		List<EmployeeDTO> eList2 = new ArrayList<EmployeeDTO>();

		for (int i = 0; i < eList.size(); i++) {
			if (position.equals(eList.get(i).getPosition())) {
				eDTO = eList.get(i);
				eList2.add(eDTO);
			}
		}

		if (eDTO != null) {
			String str = "\n>>> 직급 검색 <<<";
			printEmployee(str, eList2);
		} else {
			System.out.println(">>> 검색하신 " + position + " 는(은) 존재하지 않습니다. <<<");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void searchEmployeeBySalary(Scanner sc) {
		
		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>)eListObj;
		
		String payMin = "";
		String payMax = "";
		int nPayMin = 0;
		int nPayMax = 0;
		
		do {
			System.out.print("\n▶ 검색할 급여 최소값: ");
			payMin = sc.nextLine();
			try {
				nPayMin = Integer.parseInt(payMin);
				break;
			} catch(NumberFormatException e) {
				System.out.println("~~~ 급여는 숫자로만 입력하세요!!");
			}
		} while(true);
			
		do {
			System.out.print("▶ 검색할 급여 최대값: ");
			payMax = sc.nextLine();
			try {
				nPayMax = Integer.parseInt(payMax);
				break;
			} catch(NumberFormatException e) {
				System.out.println("~~~ 급여는 숫자로만 입력하세요!!");
			}
		} while(true);		
		
		EmployeeDTO eDTO = null;
		
		List<EmployeeDTO> eList2 = new ArrayList<EmployeeDTO>();
		
		for(int i = 0; i<eList.size(); i++) {
			if(eList.get(i).getPay()>=nPayMin && eList.get(i).getPay()<=nPayMax) {
				eDTO = eList.get(i);
				eList2.add(eDTO);
			}
		}
		
		DecimalFormat df = new DecimalFormat("#,###");
		
		if(eDTO != null) {
			String str = "\n>>> 급여범위 검색["+df.format(nPayMin)+"원 ~ "+df.format(nPayMax)+"원] <<<";
			printEmployee(str, eList2);
		} else {
			System.out.println(">>> 검색하신 급여범위에 해당하는 사원은 존재하지 않습니다. <<<");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void searchEmployeeByDName(Scanner sc) {

		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>) eListObj;

		String dName = "";
		do {
			System.out.print("\n▶ 검색할 부서명 : ");
			dName = sc.nextLine();
			if (dName.trim().isEmpty()) {
				System.out.println("~~~ 검색할 부서명을 입력하세요!!\n");
			} else {
				break;
			}
		} while (true);

		EmployeeDTO eDTO = null;

		List<EmployeeDTO> eList2 = new ArrayList<EmployeeDTO>();

		for (int i = 0; i < eList.size(); i++) {
			if (dName.equals(eList.get(i).getDeptDTO().getDeptName())) {
				eDTO = eList.get(i);
				eList2.add(eDTO);
			}
		}

		if (eDTO != null) {
			String str = "\n>>> 부서명 검색 <<<";
			printEmployee(str, eList2);
		} else {
			System.out.println(">>> 검색하신 " + dName + " 는(은) 존재하지 않습니다. <<<");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteEmployee(EmployeeDTO loginEmp, Scanner sc) {
		
		Object eListObj = serial.getObjectFromFile(EMPLOYEELISTFILENAME);
		List<EmployeeDTO> eList = (ArrayList<EmployeeDTO>)eListObj;
		
		String resignName = "";
		boolean president = false;
		if(loginEmp.getPosition().equals("사장")) {
				System.out.print("▶ 사직시킬 사원명 입력 : ");
				resignName = sc.nextLine();
				president = true;
		}
		
		if(president) {
			int i=0;
			for(; i<eList.size(); i++) {
				if(eList.get(i).getName().equals(resignName)) {
					EmployeeDTO eDTO = eList.get(i);
					i = eList.indexOf(eDTO);
				}
			}
			
			boolean isOK = false;
			do {
				System.out.print("▷ 사직을 완료하시겠습니까?[Y/N] => ");
				String conf = sc.nextLine();
				if ("Y".equalsIgnoreCase(conf)) {
					isOK = true;
				} else if ("N".equalsIgnoreCase(conf)) {
					isOK = false;
					break;
				} else {
					System.out.println("~~~ Y 또는 N을 입력하셔야 합니다.");
				}
			} while (true);
				
			if(isOK) {
				eList.remove(i);
				int n = serial.objectToFileSave(eList, EMPLOYEELISTFILENAME);
				if(n==1) {
					System.out.println(">>> 사직 처리 성공!! <<<\n");
				} else {
					System.out.println(">>> 사직 처리 실패!! <<<\n");
				}
				
			} else {
				System.out.println(">>> 사직 처리를 취소하셨습니다. <<<\n");
			}
		} else {
			System.out.println(">>> 해당 메뉴에 대한 권한이 없습니다. <<<\n");
		}
	
	}

}