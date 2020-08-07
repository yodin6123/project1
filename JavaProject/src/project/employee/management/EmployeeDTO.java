package project.employee.management;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

public class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 2534699851589679551L;
	private String id;
	private String pwd;
	private String name;
	private String birth;
	private String address;
	private String position;
	private int pay;
	private int deptNo;
	private DeptDTO deptDTO;

	public EmployeeDTO() {
	}

	public EmployeeDTO(String id, String pwd, String name, String birth, String address, String position, int pay,
			int deptNo, DeptDTO myDeptDTO) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.address = address;
		this.position = position;
		this.pay = pay;
		this.deptNo = deptNo;
		this.deptDTO = myDeptDTO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DeptDTO getDeptDTO() {
		return deptDTO;
	}

	public void setDeptDTO(DeptDTO deptDTO) {
		this.deptDTO = deptDTO;
	}
	
	public String getSalaryComma() { 
        // 숫자 세자리 수 마다 콤마찍기
		DecimalFormat df = new DecimalFormat("#,###"); 
		String sSalary = (String)df.format(pay);

		return sSalary;
	}
	
	public int showAge() { // 현재나이를 알려준다.
		Calendar currentdate = Calendar.getInstance(); // 현재날짜와 시간을 얻어온다.
		int currentYear = currentdate.get(Calendar.YEAR); // 현재년도를 얻어온다.
		
		return currentYear - Integer.parseInt(birth.substring(0, birth.indexOf("."))) + 1;
	}

	@Override
	public String toString() {
		return "▷아이디: " + id + "\n" +
				"▷암호: " + pwd + "\n" +
				"▷사원명: " + name + "\n" +
				"▷생년월일: " + birth + "\n" + 
				"▷나이: " + showAge() + "세\n" + 
				"▷주소: " + address + "\n" + 
				"▷직급: " + position + "\n" + 
				"▷급여: " + getSalaryComma() + "\n" +
				"▷ 부서번호: " + deptNo + "\n" +
				"▷ 부서명: " + deptDTO.getDeptName() + "\n" + 
				"▷ 부서위치: " + deptDTO.getDeptLoc() + "\n";

	}

}
