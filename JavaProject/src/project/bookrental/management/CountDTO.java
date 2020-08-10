package project.bookrental.management;

import java.io.Serializable;

public class CountDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1494433239947375316L;
	 private String count;
	   private SeparateBookDTO seperateBookDTO;
	   
	   public CountDTO () {}
	   public CountDTO (String count, SeparateBookDTO seperateBookDTO) {
	      this.count = count;
	      this.seperateBookDTO = seperateBookDTO;
	   }
	   public String getCount() {
	      return count;
	   }
	   public void setCount(String count) {
	      this.count = count;
	   }
	   public SeparateBookDTO getSeperateBookDTO() {
	      return seperateBookDTO;
	   }
	   public void setSeperateBookDTO(SeparateBookDTO seperateBookDTO) {
	      this.seperateBookDTO = seperateBookDTO;
	   }
	   public static long getSerialversionuid() {
	      return serialVersionUID;
	   }
	   
	   @Override
	   public String toString() {
	      return "ISBN : " + seperateBookDTO.getIsbn() + ", 제목 : " + seperateBookDTO.getBookDTO().getBookName() + "인 책은  " + count + "회 대여되었습니다.";
	   }

}
