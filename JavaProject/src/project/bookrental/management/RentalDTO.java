package project.bookrental.management;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class RentalDTO implements Serializable {

	private static final long serialVersionUID = 162100024755372078L;
	private String memId;
	private String bookId;
	private String lendDate;
	private String returnDate;
	private int fee;
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		lendDate = format.format(cal.getTime());
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date dLendDate = format.parse(lendDate);
			Calendar cal = new GregorianCalendar(Locale.KOREA);
			cal.setTime(dLendDate);
			cal.add(Calendar.DAY_OF_MONTH, 3);
			
			returnDate = format.format(cal.getTime());
		} catch (ParseException e) {
			System.out.println("~~~ 반납예정일은 날짜모양을 띄는 String 타입이어야 합니다.");
		}
	}

	public int getFee() {
		return fee;
	}

	public void setFee() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date dReturnDate = f.parse(returnDate);
			String sToday = f.format(new Date());
			Date today = f.parse(sToday);
			long over = today.getTime() - dReturnDate.getTime();
			long overDays = over/(1000*60*60*24);
			
			if(overDays>=1) {
				fee = ((int)overDays) * 100;
			}
		} catch (ParseException e) {
			
		}
	}
	
	public String printFee() {
		DecimalFormat df = new DecimalFormat("#.###");
		String sFee = (String)df.format(fee);
		return sFee + "원";
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
				returnDate + "\n";
	}
	
	public String toStringSnd() {
		return bookId + "\t" +
				sbDTO.getIsbn() + "\t" +
				sbDTO.getBookDTO().getBookName() + "\t" +
				sbDTO.getBookDTO().getAuthor() + "\t" +
				sbDTO.getBookDTO().getPublisher() + "\t" +
				memId + "\t" +
				lendDate + "\t" +
				returnDate + "\n";
	}
	
}
