package project.test.serialization;

import java.util.*;

public class MySerialMainExecution {

	public static void main(String[] args) {
		
		List<Student> studList = new ArrayList<Student>();
		
		Student stud1 = new Student("한석규", 21, "서울시 강동구");
		Student stud2 = new Student("두석규", 22, "서울시 강서구");
		Student stud3 = new Student("세석규", 23, "서울시 강남구");
		Student stud4 = new Student("네석규", 24, "서울시 강북구");
		
		studList.add(stud1);
		studList.add(stud2);
		studList.add(stud3);
		studList.add(stud4);
		
		MySerializable serial = new MySerializable();
		serial.objectToFileSave(studList, "C:/iotestdata/serializable/students.dat");   // 리눅스, 유닉스는 /만 가능(호환)
		// 직렬화 시켜주는 메소드 호출
		
		/////////////////////////////////////////////////////////////
		
		Object obj = serial.getObjectFromFile("C:/iotestdata/serializable/students.dat");
		// 역직렬화 시켜주는 메소드 호출
		
		if(obj != null) {
			@SuppressWarnings("unchecked")  // 컴파일러가 일반적으로 경고하는 내용 중 "이것은 경고표시를 하지마라"는 뜻으로 쓰일 때 사용하는 것이다.
			List<Student> resultList = (List<Student>)obj;
			System.out.println(">>> 파일(C:/iotestdata/serializable/students.dat)에 저장된 객체 정보 출력하기 <<<");
			
			for(Student stud : resultList) {
				System.out.println(stud);
			}// end of for
		}
		
		
	/*
		for(Student stud : studList) {
			// System.out.println(stud.toString());  // project.test.serialization.Student@7852e922
			System.out.println(stud);                // project.test.serialization.Student@7852e922
		}// end of for
	*/

	}// end of main()

}
