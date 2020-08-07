package project.bookrental.management;

import java.io.Serializable;

public class LibrarianDTO implements Serializable {
	
	private static final long serialVersionUID = 8730883131804955087L;
	String libId;
	String libPwd;
	
	public LibrarianDTO() {}
	
	public LibrarianDTO(String libId, String libPwd) {
		this.libId = libId;
		this.libPwd = libPwd;
	}

	public String getLibId() {
		return libId;
	}

	public void setLibId(String libId) {
		this.libId = libId;
	}

	public String getLibPwd() {
		return libPwd;
	}

	public void setLibPwd(String libPwd) {
		this.libPwd = libPwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
