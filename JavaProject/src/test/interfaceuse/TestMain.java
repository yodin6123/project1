package test.interfaceuse;

public class TestMain {

	public static void main(String[] args) {
		
		InterDeveloper ad = new Bdeveloper(); // A개발자 결과 체크 후 B개발자 결과 체크를 위해 주석 문 처리 필요없이 인터페이스로 받고 생성자만 바꿔주면 된다!
		ad.repeatName("이순신", -20);
		
	}

}
