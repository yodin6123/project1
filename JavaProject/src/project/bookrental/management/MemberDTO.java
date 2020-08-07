package project.bookrental.management;

import java.io.Serializable;

public class MemberDTO implements Serializable {
	
	private static final long serialVersionUID = 9208015767233591628L;
	private String memId;
	private String memPwd;
	private String memName;
	private String memPhone;
	private SeparateBookDTO sbDTO;
	
	public MemberDTO() {}
	
	public MemberDTO(String memId, String memPwd, String memName, String memPhone) {
		super();
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SeparateBookDTO getSbDTO() {
		return sbDTO;
	}

	public void setSbDTO(SeparateBookDTO sbDTO) {
		this.sbDTO = sbDTO;
	}
	
	public String toString() {
		return sbDTO.getBookId()+"\t"+sbDTO.getIsbn()+"\t"+sbDTO.getBookDTO().getBookName()+"\t"+sbDTO.getBookDTO().getAuthor()+"\t"+sbDTO.getBookDTO().getPublisher()+"\t"+memId+"\t"+memName+"\t"+memPhone+"\t";
	}

}
