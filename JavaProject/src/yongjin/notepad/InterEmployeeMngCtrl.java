package yongjin.notepad;

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
	
	EmployeeDTO updateMyInfo(EmployeeDTO loginEmp, Scanner sc);

}
