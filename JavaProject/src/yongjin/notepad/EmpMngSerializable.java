package yongjin.notepad;

import java.io.*;

public class EmpMngSerializable {
	
	// 직렬화하는 메소드 생성하기(메모리상에 올라온 객체를 하드디스크 파일에 저장시키기)
	public int objectToFileSave(Object obj, String saveFilename) {
		
		// 어떤 형태의 객체 obj 를 saveFilename 으로 저장하기
		try {
			FileOutputStream fost = new FileOutputStream(saveFilename, false);
			// 출력노드 스트림(빨대꽂기)
			// 파일이름(saveFilename)을 이용해서 FileOutputStream 객체를 생성한다.
			// 생성된 객체는 두번째 파라미터 boolean append 값에 따라서 true 이면 기존 파일에 내용을 덧붙여 추가할 것이고,
			// false 이면 기존 내용은 삭제하고 새로운 내용이 기록되도록 하는 것이다.
			
			BufferedOutputStream bufOst = new BufferedOutputStream(fost, 1024);
			// 1kb 버퍼 크기의 보조스트림, 필터스트림
			// 필터스트림(노드 스트림에 오리발 장착하기)
			
			ObjectOutputStream objOst = new ObjectOutputStream(bufOst);
			// objOst 는 객체 obj 를 파일명 saveFilename 에 기록하는(저장하는) 스트림 객체 생성하기
			
			objOst.writeObject(obj);
			// 객체 obj 를 파일명 saveFilename 에 기록하는(저장하는) 메소드
			
			objOst.close(); // 사용된 자원반납하기 (사용된 객체를 메모리 공간에서 삭제하기) ==> 반대 순서로
			bufOst.close(); // 사용된 자원반납하기 (사용된 객체를 메모리 공간에서 삭제하기)
			fost.close();   // 사용된 자원반납하기 (사용된 객체를 메모리 공간에서 삭제하기)
			
			return 1;  // 저장 성공 결과
			
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// e.printStackTrace();
			return 0;
		}
	}
	
	// 역직렬화하는 메소드 생성하기(하드디스크에 저장된 파일을 읽어다가 객체로 만들어 메모리에 올리기)
	public Object getObjectFromFile(String fileName) {
		
		// 파일명 fileName 을 읽어서 객체 Object 변환하기
		try {
			FileInputStream finst = new FileInputStream(fileName);
			// 입력노드 스트림(빨대꽂기)
			
			BufferedInputStream bufInst = new BufferedInputStream(finst, 1024);
			// 필터스트림(노드 스트림에 오리발 장착하기)
			
			ObjectInputStream objInst = new ObjectInputStream(bufInst);
			// 파일명 fileName 을 읽어서 객체로 만들어주는 스트림 객체 생성하기
			
			Object obj = objInst.readObject();
			// 파일명 fileName 을 읽어서 객체로 만들어주는 메소드
			
			objInst.close(); // 사용된 자원반납하기 (사용된 객체를 메모리 공간에서 삭제하기)
			bufInst.close(); // 사용된 자원반납하기 (사용된 객체를 메모리 공간에서 삭제하기)
			finst.close();   // 사용된 자원반납하기 (사용된 객체를 메모리 공간에서 삭제하기)
			
			return obj;
			
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();	
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		}		
		
		return null;   // 예외 발생 시 반환값
	}

}
