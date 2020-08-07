package project.bookrental.management;

import java.io.Serializable;

public class RentalDTO implements Serializable {

	private static final long serialVersionUID = 162100024755372078L;
	private String memId;
	private String bookId;
	private String lendDate;
	private String returnDate;
	private int arrears;
	private MemberDTO mDTO;
	private SeparateBookDTO sbDTO;
	
}
