package db;

public class Events {
	   private int id;
	   private String utoken; 
	   private String date;   
	   private String recurringID; 
	   private String title;
	   private String url;

	   public Events() {}
	   
	   public Events(String utoken, String date, String recurringID) {
	      this.utoken = utoken;
	      this.date = date;
	      this.recurringID = recurringID;
	   }
	   
	   public int getId() {
	      return id;
	   }
	   
	   public void setId( int id ) {
	      this.id = id;
	   }
	   
	   public String getUToken() {
	      return utoken;
	   }
	   
	   public void setUToken( String utoken ) {
	      this.utoken = utoken;
	   }
	   
	   public String getDate() {
	      return date;
	   }
	   
	   public void setDate( String date ) {
	      this.date = date;
	   }
	   
	   public String getRecurringID() {
	      return recurringID;
	   }
	   
	   public void setRecurringID( String recurringID ) {
	      this.recurringID = recurringID;
	   }
	   
	   public String getTitle() {
	      return title;
	   }
	   
	   public void setTitle( String title ) {
	      this.title = title;
	   }
	   
	   public String getURL() {
	      return url;
	   }
	   
	   public void setURL( String url) {
	      this.url = url;
	   }
	   
	   @Override
	   public String toString() {
		   return String.format("%s, %s, %s, %s, %s, %s", id, utoken, date,recurringID, title, url);
	   }
	}