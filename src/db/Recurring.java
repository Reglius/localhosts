package db;

public class Recurring {
	String recurId;
	String utoken;
	String endDate; //ending date in the format YYYY-mm-dd
	String days; //"Monday" "Tuesday" "Wednesday" ...
	
   public Recurring() {}
   
   public Recurring(String recurId, String utoken, String endDate, String days) {
      this.recurId = recurId;
      this.utoken = utoken;
      this.endDate = endDate;
      this.days = days;
   }
	   
   
   public String getRecurId() {
	   return recurId;
   }
   
   public void setRecurId(String recurId) {
	   this.recurId = recurId;
   }
   
   public String getUToken() {
	   return utoken;
   }
   
   public void setUToken(String utoken) {
	   this.utoken = utoken;
   }
   
   public String getEndDate() {
	   return endDate;
   }
   
   public void setEndDate(String endDate) {
	   this.endDate = endDate;
   }
   
   public String getDays() {
	   return days;
   }
   
   public void setDays(String days) {
	   this.days = days;
   }
}
