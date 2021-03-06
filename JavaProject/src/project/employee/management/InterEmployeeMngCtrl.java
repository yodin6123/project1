package project.employee.management;

import java.util.List;
import java.util.Scanner;

public interface InterEmployeeMngCtrl {
	// 반드시 구현해야 할 기능 목록
	
	void registerDept(Scanner sc);     // 부서등록
	
	void registerEmployee(Scanner sc); // 사원등록
	
	boolean isUseID(String id);        // 중복 아이디 검사하기
	
	EmployeeDTO login(Scanner sc);     // 로그인
	/*
	 * 아이디와 암호를 입력받는다.
	 * 파일로부터 불러온 객체의 정보와 비교하여 불일치시 로그인 실패, 실패하면 메뉴선택으로 돌아감
	 * 일치시 로그인 성공, 사원관리 메뉴로 전환
	 * 이는 로그인 값을 통해 해당 사원의 객체 정보 값을 값는 주소값을 불러온다.
	 */
	
	void employeeMenu(Scanner sc, EmployeeDTO loginEmp); // 사원관리 메뉴
	/*
	 * 메뉴 : 이름 + 님 로그인 중 출력
	 * 메뉴 선택 입력받는다.
	 * 메뉴에 없는 번호 입력 시 존재하지 않는 메뉴번호 출력
	 */
	
	EmployeeDTO updateMyInfo(EmployeeDTO loginEmp, Scanner sc); // 내정보 변경하기 void infoUpdate(Scanner sc)?
	// 변경된 값을 가지고 있어야 main()에 있는 참조변수를 통한 올바른 데이터 활용이 가능!
	/*
	 * 암호변경: 값이 있을 때 변경하고 엔터나 공백은 변경없이 그대로
	 * 
	 * 직급변경: 값이 있을 때 변경하고 엔터나 공백은 변경없이 그대로
	 * 급여변경: 유효성 검사(숫자 값만 입력)
	 * 직원 파일을 불러와 해당 직원의 변경 값을 초기화해준다!
	 * 변경하시겠습니까 확인 창 -> 대소문자 구분 없는 문자 일치
	 */
	
	void showAllEmployee(); // 모든 사원 정보 void allEO()?
	/*
	 * 암호 블럭
	 */
	
	void searchEmployeeMenu(EmployeeDTO loginEmp, Scanner sc);  // 사원검색하기 메뉴
	
	void searchEmployeeByName(Scanner sc);  // 사원명 검색
	
	void printEmployee(String title, List<EmployeeDTO> empList);  // 사원검색 결과 출력하기
	/*
	 * 
	 */
	
	void searchEmployeeByAge(Scanner sc);  // 연령대 검색
	
	void searchEmployeeByPos(Scanner sc);  // 직급 검색
	
	void searchEmployeeBySalary(Scanner sc);  // 급여범위 검색
	
	void searchEmployeeByDName(Scanner sc);  // 부서명 검색
	
	void deleteEmployee(EmployeeDTO loginEmp, Scanner sc);  // 사원사직시키기(사장님으로 로그인 했을때만 가능하도록 한다.)
	/*
	 * 사장 로그인 확인
	 * 사직시킬 사원명 입력
	 * 해당 사원에 맞는 객체를 불러와 리스트에서 인덱스 추출 후 리스트에서 제거
	 * 성공 시 사직 처리 완료 출력, 실패 시 실패 출력
	 */
	

}
