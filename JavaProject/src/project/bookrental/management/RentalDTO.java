package project.bookrental.management;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RentalDTO implements Serializable {

	private static final long serialVersionUID = 162100024755372078L;
	private String memId;
	private String bookId;
	private String lendDate;
	private String returnDate;
	private String fee;
	private boolean ifExtend; // 연장했었으면 true, 안했었으면 false;
	private MemberDTO mDTO;
	private SeparateBookDTO sbDTO;
	
	public RentalDTO() {}
	
	public RentalDTO(String memId, String bookId, MemberDTO mDTO, SeparateBookDTO sbDTO) {
		this.memId = memId;
		this.bookId = bookId;
		this.mDTO = mDTO;
		this.sbDTO = sbDTO;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getLendDate() {
		return lendDate;
	}

	public void setLendDate() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DATE, -3);
		lendDate = sdfm.format(time.getTime());
		time.add(Calendar.DATE, 2);
		this.returnDate = sdfm.format(time.getTime());
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	@SuppressWarnings("unused")
	public String getFee(String returnDate) {
		Calendar time = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date to = Date.valueOf(returnDate);
			Date current = new Date(System.currentTimeMillis());
			
			long calDate = to.getTime() - current.getTime();
	        long calDateDays = calDate / ( 24*60*60*1000); 
	        calDateDays = Math.abs(calDateDays);
	        fee = String.valueOf(calDateDays);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fee;
	}

	public void setFee() {
		
	}
	
	public MemberDTO getmDTO() {
		return mDTO;
	}

	public void setmDTO(MemberDTO mDTO) {
		this.mDTO = mDTO;
	}

	public SeparateBookDTO getSbDTO() {
		return sbDTO;
	}

	public void setSbDTO(SeparateBookDTO sbDTO) {
		this.sbDTO = sbDTO;
	}
	
	public boolean isIfExtend() {
		return ifExtend;
	}

	public void setIfExtend(boolean ifExtend) {
		this.ifExtend = ifExtend;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return bookId + "\t" +
				sbDTO.getIsbn() + "\t" +
				sbDTO.getBookDTO().getBookName() + "\t" +
				sbDTO.getBookDTO().getAuthor() + "\t" +
				sbDTO.getBookDTO().getPublisher() + "\t" +
				memId + "\t" +
				mDTO.getMemName() + "\t" +
				mDTO.getMemPhone() + "\t" + 
				lendDate + "\t" +
				returnDate;
	}
	
	public String toStringSnd() {
		return bookId + "\t" +
				sbDTO.getIsbn() + "\t" +
				sbDTO.getBookDTO().getBookName() + "\t" +
				sbDTO.getBookDTO().getAuthor() + "\t" +
				sbDTO.getBookDTO().getPublisher() + "\t" +
				memId + "\t" +
				lendDate + "\t" +
				returnDate;
	}
	
	public String toString2() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar time = Calendar.getInstance();
		String temp = sdfm.format(time.getTime());
		time.add(Calendar.DATE, 2);
		String temp2 = sdfm.format(time.getTime());
		return bookId + "\t" +
				sbDTO.getIsbn() + "\t" +
				sbDTO.getBookDTO().getBookName() + "\t" +
				sbDTO.getBookDTO().getAuthor() + "\t" +
				sbDTO.getBookDTO().getPublisher() + "\t" +
				memId + "\t" +
				mDTO.getMemName() + "\t" +
				mDTO.getMemPhone() + "\t" + 
				temp + "\t" +
				temp2;
	}
	
	public String toStringSnd2() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar time = Calendar.getInstance();
		String temp = sdfm.format(time.getTime());
		time.add(Calendar.DATE, 2);
		String temp2 = sdfm.format(time.getTime());
		return bookId + "\t" +
				sbDTO.getIsbn() + "\t" +
				sbDTO.getBookDTO().getBookName() + "\t" +
				sbDTO.getBookDTO().getAuthor() + "\t" +
				sbDTO.getBookDTO().getPublisher() + "\t" +
				memId + "\t" +
				mDTO.getMemName() + "\t" +
				mDTO.getMemPhone() + "\t" + 
				temp + "\t" +
				temp2;
	}
	
}
