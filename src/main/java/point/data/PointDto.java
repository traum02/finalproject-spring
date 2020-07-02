package point.data;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PointDto {
   private String id;
   private String ptype;
   private int pprice;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "Asia/Seoul")
   private Timestamp pdate;
   
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPtype() {
      return ptype;
   }
   public void setPtype(String ptype) {
      this.ptype = ptype;
   }
   public int getPprice() {
      return pprice;
   }
   public void setPprice(int pprice) {
      this.pprice = pprice;
   }
   public Timestamp getPdate() {
      return pdate;
   }
   public void setPdate(Timestamp pdate) {
      this.pdate = pdate;
   }
   @Override
   public String toString() {
      return "PointDto [id=" + id + ", ptype=" + ptype + ", pprice=" + pprice + ", pdate=" + pdate + "]";
   }
   
   
}