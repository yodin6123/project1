package project.employee.management;

import java.util.Scanner;

public class EmployeeMngMainExecution {

	public static void main(String[] args) {
		
		InterEmployeeMngCtrl ctrl = new EmployeeMngCtrl();
		
		Scanner sc = new Scanner(System.in);
		
		String sMenuno;
		
		EmployeeDTO eDTO = null;
		do {
			if(eDTO != null) {
				System.out.println("\n>>>> Main Menu ["+eDTO.getName()+" 님 로그인중..]<<<<");
			} else {
				System.out.println("\n>>>> Main Menu <<<<");
			}			
			System.out.println("1.부서등록\t2.사원등록\t3.로그인\t4.로그아웃\t5.프로그램 종료");
			System.out.print("=> 메뉴번호선택 : ");
			sMenuno = sc.nextLine();
						
			switch(sMenuno) {
				case "1" :
					ctrl.registerDept(sc);
					break;
				case "2" :
					ctrl.registerEmployee(sc);
					break;
				case "3" :
					if(eDTO != null) {
						System.out.println("~~~~ 이미 로그인된 상태입니다!!!");
					} else {
						eDTO = ctrl.login(sc);
						if(eDTO != null) {
							ctrl.employeeMenu(sc, eDTO);
						}
					}
					break;
				case "4" :
					eDTO = null;
					break;
				case "5" :
					break;
				default :
					System.out.println(">>> 메뉴에 없는 번호 입니다. 다시 선택하세요!!");
					break;
			}
			
		} while(!"5".equals(sMenuno));
		
		System.out.println(">>> 프로그램을 종료합니다. <<<");
		sc.close();		

	}// end of main()

}
