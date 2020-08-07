package yongjin.notepad;

import java.io.File;
import java.util.*;


public class EmployeeMngCtrl implements InterEmployeeMngCtrl {

	private final String DEPTLISTFILENAME = "C:/iotestdata/project/employeemng/deptlist.dat";        
	private final String EMPLISTFILENAME = "C:/iotestdata/project/employeemng/employeelist.dat";
	
	private EmpMngSerializable serial = new EmpMngSerializable();
	
	// == 부서등록 == //
	@SuppressWarnings("unchecked")
	@Override
	public void registerDept(Scanner sc) {
		
		System.out.println("\n== 부서등록하기 ==");
		
		// ---------------------------------------------- //
		/*
		int nDtno = 0;
		do {
			
			try {
				System.out.print("▶부서번호: "); //          10         관리부            서울시 강남구 대치동엔터
				                               //몰라엔터
				nDtno = sc.nextInt(); //
				                      //  sc.nextInt();는 입력되는 데이터의 종결자는 공백 또는 엔터이다.
				                      //  sc.nextInt();는 입력된 값이 공백이 있으면 공백을 무시를 하고서 종결자(공백 또는 엔터) 전 까지의 int타입 데이터(10)만 읽어들여서 
				                      //  nDtno 변수에 넣어주고  종결자(공백 또는 엔터)를 포함한 나머지 데이터(         관리부            서울시 강남구 대치동엔터)는 버퍼에서 삭제되지 않고 그대로 남아있게 된다.                     
				                      //
				break;
			} catch(InputMismatchException e) {
				System.out.println("~~~~~ 오류 : 부서번호는 숫자로만 입력하세요!!!\n");
				sc.nextLine();
			}
		} while (true);
		
		System.out.print("▶부서명: ");
		String sDname = sc.next(); //
		                           //  sc.next();는 입력되는 데이터의 종결자는 공백 또는 엔터이다.
		                           //  sc.next();는 입력된 값이 공백이 있으면 공백을 무시를 하고서 종결자(공백 또는 엔터) 전 까지의 String타입 데이터(관리부)만 읽어들여서
		                           //  sDname 변수에 넣어주고  종결자(공백 또는 엔터)를 포함한 나머지 데이터(            서울시 강남구 대치동엔터)는 버퍼에서 삭제되지 않고 그대로 남아있게 된다.
		                           //
		
		System.out.print("▶부서위치: ");
		String sLoc = sc.nextLine(); //
		                             //  sc.nextLine(); 은 버퍼에서 종결자(엔터)를 포함한 모든 String타입 데이터(            서울시 강남구 대치동엔터)를 읽어들여서 
		                //               종결자(엔터)전까지의 String타입 데이터(            서울시 강남구 대치동)를 sLoc 변수에 넣어주고 종결자(엔터)는 버퍼에서 삭제되어진다.
		                             //
		
		System.out.println("~~~~~ 확인용 nDtno:"+nDtno);
		System.out.println("~~~~~ 확인용 sDname:"+sDname);
		System.out.println("~~~~~ 확인용 sLoc:"+sLoc);
		
		// ---------------------------------------------- //
		*/
		
		
		// 유효성 검사 
		int nDeptno = 0;
		do {
			System.out.print("▶부서번호: ");  //  10   20   30
			String deptno = sc.nextLine();  // "10"  "뭐요?"
			
			try {
				nDeptno = Integer.parseInt(deptno);
				break;
			} catch(NumberFormatException e) {
				System.out.println("~~~~ 오류 : 부서번호는 숫자로만 입력하세요!!!\n");
			}
			
		} while (true);
		
		System.out.print("▶부서명: ");  // 관 리 부    연구부    생산부
		String dname = sc.nextLine();  //                    엔터
		
		System.out.print("▶부서위치: "); // 서울시 강남구 대치동    인천    수원 
		String loc = sc.nextLine();
		
		DeptDTO deptdto = new DeptDTO(nDeptno, dname, loc);
		
		File file = new File(DEPTLISTFILENAME); 
		// DEPTLISTFILENAME 에 해당하는 파일객체 생성하기 
		
		Map<String, DeptDTO> deptMap = null;
		int n = 0;
		
		if(!file.exists()) { // 파일이 존재하지 않는 경우. 즉, 최초로 부서등록을 하는 경우이다.
			deptMap = new HashMap<>();
			deptMap.put(String.valueOf(nDeptno), deptdto);
			n = serial.objectToFileSave(deptMap, DEPTLISTFILENAME); // DEPTLISTFILENAME 파일에 deptdto 객체를 저장시킨다. 
		}
		else { // 파일이 존재하는 경우. 두번째 이후로 부서를 가입하는 경우이다. 
			Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME); // DEPTLISTFILENAME 파일에 저장된 객체를 불러온다.
			deptMap = (HashMap<String, DeptDTO>) deptMapObj;
			deptMap.put(String.valueOf(nDeptno), deptdto);
			
			// --------- 확인용 시작 --------- //
			/*
			Set<String> keySet = deptMap.keySet(); // "10"  "20"
			for(String key : keySet) {
				System.out.println("~~~ 확인용 key:"+key); // 10  20 
				DeptDTO dto = deptMap.get(key);
				System.out.println("~~~ 확인용 dto:"+dto); 

				//   1.부서번호:10  2.부서명:관리부  3.부서위치:서울 
				//   1.부서번호:20  2.부서명:연구부  3.부서위치:인천 
			}
		    */
			// --------- 확인용 끝 --------- //
			
			n = serial.objectToFileSave(deptMap, DEPTLISTFILENAME); // DEPTLISTFILENAME 파일에 deptdto 객체를 저장시킨다. 
			
		}
		
		if(n==1) {
			System.out.println(">>> 부서등록 성공!! <<<");
		}
		else {
			System.out.println(">>> 부서등록 실패!! <<<");
		}
		
	}// end of public void registerDept(Scanner sc)--------------------------

	
	
	// == 사원등록 == //
	@SuppressWarnings("unchecked")
	@Override
	public void registerEmployee(Scanner sc) {
		
		// 아이디 중복 검사 및 유효성 검사하기
		String id = null;
		do {
			System.out.print("\n▶아이디: ");    //  leess     emojh     hongkd     youks 
		 	id = sc.nextLine();
		 	
		 	if(id==null || id.trim().isEmpty()) {
		 		System.out.println("~~~ 아이디를 입력하세요!!");
		 		// continue;
		 	}
		 	else if(!isUseID(id)){
		 		System.out.println("~~~ "+id+" 는 이미 존재하므로 다른 ID를 입력하세요!!"); 
		 		// continue;
		 	}
		 	else {
		 		break;
		 	}
		 	
		} while (true);
		
		
		System.out.print("▶암호: ");   //  1234    1234    1234    1234 
		String pwd = sc.nextLine();
		
		System.out.print("▶사원명: ");  //  이순신       엄정화       홍길동       유관순 
		String ename = sc.nextLine();
		
		System.out.print("▶생년월일(예 1994.08.04): ");  //  1994.08.04    2000.09.10     1988.10.01      1998.07.30     
		String birthday = sc.nextLine();
		
		System.out.print("▶주소: ");   //  서울시 마포구    서울시 강남구     서울시 강북구      서울시 영등포구      
		String address = sc.nextLine();
		
		System.out.print("▶직급: ");   //  사장   부장   과장   사원       
		String jik = sc.nextLine();
		
		// 유효성 검사 
		int salary = 0;
		do {
			System.out.print("▶급여: ");   //  7000000  6000000  5000000   4000000      
			String sSalary = sc.nextLine();
			
			try {
				salary = Integer.parseInt(sSalary);
				break;
			} catch(NumberFormatException e) {
				System.out.println("~~~~ 오류 : 급여는 숫자로만 입력하세요!!\n");
			}
			
		} while(true);
		
		
		// 유효성 검사 
		int deptno = 0;
		DeptDTO mydeptDto = null;
		do {
			System.out.print("▶부서번호: ");   //  10  20  30  10      
			String sDeptno = sc.nextLine();  // 90을 입력하면 존재하지 않는 부서번호이다.
			
			/*
			   부서정보가 저장되어진 파일에 가서 모든 부서번호를 읽어온다.
			   읽어온 부서번호들과 위에서 사용자가 입력한 부서번호가 일치하지 않는 경우라면 
			   존재하지 않는 부서번호임을 띄워주고 다시 부서번호를 입력받도록 하면 된다.     
			*/
			Object deptMapObj = serial.getObjectFromFile(DEPTLISTFILENAME);
			Map<String, DeptDTO> deptMap = (Map<String, DeptDTO>) deptMapObj;
			Set<String> keyset = deptMap.keySet();
			boolean flag = false;
			for(String key : keyset) { // 10 20 30
				if(key.equals(sDeptno)) {
					flag = true;
					break;
				}
			}// end of for----------------------------
			
			if(!flag) {
				System.out.println("~~~ 입력하신 부서번호 "+sDeptno+"는 우리회사에 존재하지 않는 부서번호 입니다\n");
				continue;
			}
			
			try {
				deptno = Integer.parseInt(sDeptno);
				mydeptDto = deptMap.get(sDeptno);
				break;
			} catch(NumberFormatException e) {
				System.out.println("~~~~ 오류 : 부서번호는 숫자로만 입력하세요!!\n");
			}
			
		} while(true);
		
		
		EmployeeDTO employee = new EmployeeDTO(id, pwd, ename, birthday, address, jik, salary, deptno, mydeptDto);
		
		File file = new File(EMPLISTFILENAME);
		List<EmployeeDTO> empList = null;
				
		if(!file.exists()) { // 파일이 존재하지 않는 경우, 최초로 사원을 등록할 경우 
			empList = new ArrayList<>();
			empList.add(employee);
		}
		else { // 파일이 존재하는 경우, 두번째 이후로 사원을 등록할 경우 
			Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
			empList = (List<EmployeeDTO>) empListObj;
			empList.add(employee);
		}
		
		int n = serial.objectToFileSave(empList, EMPLISTFILENAME);
		
		if(n==1)
			System.out.println(">>> 사원등록 성공!! <<<");
		else
			System.out.println(">>> 사원등록 실패!! <<<");
		
	}// end of public void registerEmployee(Scanner sc)-----------------------------


	
    // == 중복 아이디 검사하기 == //
	@SuppressWarnings("unchecked")
	@Override
	public boolean isUseID(String id) {
		
		boolean isUse = true;
		// 최초로 가입시  파일에 저장된 empListObj 객체가 없기때문에 즉,중복된 아이디가 없으므로 사용가능하도록 한다.
		
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME);
		
		if(empListObj != null) {
			// 파일에 저장된 empListObj 객체가 있다라면
			
			// 입력받은 id 가 empListObj 객체속에 존재한다라면 사용불가인 아이디이다.
			List<EmployeeDTO> empList = (List<EmployeeDTO>) empListObj; 
			
			for(EmployeeDTO emp : empList) {
				if( id.equals(emp.getId()) ) { // 입력한 id 가 이미 존재하는 경우
					isUse = false;
					break;
				}
			}// end of for--------------------------------
			
		}
		
		return isUse;
	}



	// == 로그인 ==
	@SuppressWarnings("unchecked")
	@Override
	public EmployeeDTO login(Scanner sc) {
		
		System.out.println("\n == 로그인 하기 == "); 
		
		System.out.print("▶ 아이디: ");
		String id = sc.nextLine();
		
		System.out.print("▶ 암호: ");
		String pwd = sc.nextLine();
		
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME); 
		
		if(empListObj != null) { // 파일로 부터 객체정보를 얻어온 경우이라면   
			List<EmployeeDTO> empList = (List<EmployeeDTO>) empListObj;	
			
			for(int i=0; i<empList.size(); i++) {
				EmployeeDTO emp = empList.get(i);
				
				if(id.equals(emp.getId()) && pwd.equals(emp.getPwd())) {
					System.out.println(">>> 로그인 성공!!! <<<");
					return emp;
				}
			}// end of for------------------
			
			System.out.println(">>> 로그인 실패!!! <<<");
		}
		
		else { // 파일로 부터 객체정보를 얻어온 것이 없다라면  
			System.out.println(">>> 가입한 회원이 없습니다. <<<"); 
		}
		
		return null;
	}// end of public EmployeeDTO login(Scanner sc)---------------------------------------------------
		
		
	// == 사원관리 메뉴 == 
	@Override
	public void employeeMenu(Scanner sc, EmployeeDTO loginEmp) {
		
		String sMenuno = "";
		do {
			String sMenu = "\n>>>> 사원관리 Menu ["+loginEmp.getEname()+" 님 로그인중..]<<<<\n" + 
				      "1.내정보 보기   2.내정보 변경하기   3.모든사원정보보기   4.사원검색하기    5.사원사직시키기   6.나가기\n" + 
	                  "=> 메뉴번호선택 : ";
		
			System.out.print(sMenu);
		
			sMenuno = sc.nextLine(); // 사원관리 메뉴번호 선택하기
		
			switch (sMenuno) {
				case "1":  // 내정보 보기
					System.out.println("\n== 내정보 ==\n"+loginEmp);
					break;
					
				case "2":  // 내정보 변경하기
					loginEmp = updateMyInfo(loginEmp, sc);
					break;
					
				case "3":  // 모든사원정보보기 
					
					break;
					
				case "4":  // 사원검색하기
					
					break;
					
				case "5":  // 사원사직시키기
					
					break;	
					
				case "6":  // 나가기
					break;	
		
				default:
					System.out.println("~~~~ 존재하지 않는 메뉴번호 입니다.!!");
					break;
			}// end of switch-------------------	
			
		} while (!"6".equals(sMenuno));
		
	}// end of public void employeeMenu(Scanner sc, EmployeeDTO loginEmp)------------------------------------



	// == 내정보 변경하기 == 
	@SuppressWarnings("unchecked")
	@Override
	public EmployeeDTO updateMyInfo(EmployeeDTO loginEmp, Scanner sc) {  
		
		System.out.println("\n== 내정보 변경하기 ==");
		
		Object empListObj = serial.getObjectFromFile(EMPLISTFILENAME); 
		
		List<EmployeeDTO> empList = (List<EmployeeDTO>) empListObj;	
		
		EmployeeDTO emp = null;
		int i=0;
		for(; i<empList.size(); i++) {
			if(loginEmp.getId().equals(empList.get(i).getId())) {
				emp = empList.get(i);
				System.out.println(emp);
				break;
			}
		}		
		
		if(emp != null) {
			
			System.out.print("\n▶ 암호변경: ");
			String pwd = sc.nextLine(); // 
			if(pwd.trim().isEmpty()) { // 암호변경에 있어서 엔터나 공백만을 입력할 경우 암호를 변경하지 않고 원래의 암호를 그대로 사용하겠음.
				pwd = emp.getPwd();
			}
			
			System.out.print("▶ 주소변경: ");
			String address = sc.nextLine();
			if(address.trim().isEmpty()) { // 주소변경에 있어서 엔터나 공백만을 입력할 경우 주소를 변경하지 않고 원래의 주소를 그대로 사용하겠음.
				address = emp.getAddress();
			}
			
			System.out.print("▶ 직급변경: ");
			String jik = sc.nextLine();
			if(jik.trim().isEmpty()) { // 직급변경에 있어서 엔터나 공백만을 입력할 경우 직급을 변경하지 않고 원래의 직급을 그대로 사용하겠음.
				jik = emp.getJik();
			}
			
			// 유효성 검사
			int salary = 0;
			do {
				System.out.print("▶ 급여변경: ");
				String sSalary = sc.nextLine();
				if(sSalary.trim().isEmpty()) { // 급여변경에 있어서 엔터나 공백만을 입력할 경우 급여를 변경하지 않고 원래의 급여를 그대로 사용하겠음. 
					salary = emp.getSalary();
					break;
				}
				else {
					try {
						 salary = Integer.parseInt(sSalary);
						 break;
					} catch(NumberFormatException e) {
						System.out.println("~~~~ 오류 : 급여는 숫자로만 입력하세요!!!\n");
					}
				}
			} while (true);
			
			
			boolean isOK = false;
			do {
				System.out.print("▷ 변경하시겠습니까?[Y/N] => ");
				String yn = sc.nextLine();
				if("Y".equalsIgnoreCase(yn)) {
					isOK = true;
					break;
				}
				else if("N".equalsIgnoreCase(yn)) {
				//	isOK = false;
					break;
				}
				else {
					System.out.println("~~~ Y 또는 N을 입력하셔야 합니다.");
				}
			} while(true);
			
			
			if(isOK) {
				emp.setPwd(pwd);
				emp.setAddress(address);
				emp.setJik(jik);
				emp.setSalary(salary);
				
				// **** ArrayList 타입인 empList 에 저장되어진 EmployeeDTO 객체 삭제하기 **** //
				empList.remove(i);	// empList.remove(삭제할 EmployeeDTO 객체의 인덱스번호);
	
				empList.add(i, emp); // 삭제되어진 그 인덱스(위치)자리에 새로운 EmployeeDTO 객체를 넣어주기 
				
				int n = serial.objectToFileSave(empList, EMPLISTFILENAME);
				
				if(n==1) {
					System.out.println(">>> 내정보 변경 성공!! <<<");
					return emp;
				}	
				else {
					System.out.println(">>> 내정보 변경 실패!! <<<");
				}
			}
			
			else {
				System.out.println(">>> 내정보 변경을 취소하셨습니다. <<<");
			}
			
		}
		
		else {
			System.out.println("~~~~ 내정보 변경작업 실패함!!!");
		}
		
		return loginEmp;
	}// end of public void EmployeeDTO updateMyInfo(EmployeeDTO loginEmp, Scanner sc)---------------------------
	
	
	
	

}








