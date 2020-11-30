package db;

public class Recurring {
	int recurId;
	String utoken;
	String endDate; //ending date in the format YYYY-mm-dd
	String days; //1000000 for only sunday 1100000 for sunday and monday...
	
   public Recurring() {}
   
   public Recurring(int recurId, String utoken, String endDate, String days) {
      this.recurId = recurId;
      this.utoken = utoken;
      this.endDate = endDate;
      this.days = days;
   }
   
   public int getRecurId() {
	   return recurId;
   }
   
   public void setRecurId(int recurId) {
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
