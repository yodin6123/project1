package test.interfacenouse;

public class Bdeveloper {
	
	// 홍길동이라는 이름을 10번 반복해서 출력하는 메소드를 만들어보세요.
	// 홍길동, 10번이 아닌 이순신, 20번이 될 수 있지 않을까라는 생각
	// 확장성 고려
	public void nameRepeatB(String name, int cnt) {
		
		// 유효성 검사
		if(name == null || name.trim().isEmpty()) {
			System.out.println("~~~ 성명은 공백은 안됩니다. 올바르게 입력하세요!!");
			return;  // 메소드 종료
		}
		
		if(cnt < 1) {
			System.out.println("~~~ 반복횟수는 1이상 이어야 합니다.");
			return;
		}
		
		for(int i=0; i<cnt; i++) {
			System.out.println((i+1)+"."+name);
		}
	}

}
