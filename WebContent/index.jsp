<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
<meta charset='utf-8' />

<!-- This page was Created By mgnavratil -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<!-- jQuery and JS bundle w/ Popper.js -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
<link href='static/scripts/main.css' rel='stylesheet' />
<script src='static/scripts/main.js'></script>
<link href="static/jquery-ui/jquery-ui.css" rel="stylesheet">
<script src="static/jquery-ui/external/jquery/jquery.js"></script>
<script src="static/jquery-ui/jquery-ui.js"></script>
<script src="/localhosts/jsonrequest?userId=<%=request.getParameter("userId")%>"></script>

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}
</style>
</head>
<body>
	
	<div class="alert alert-danger alert-dismissible fade show text-center" role="alert" id="error">
	  <strong>Holy guacamole!</strong> You should fill in the event fields below!
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>

	<div class="container-fluid">
		<div class="row justify-content-md-center">
			<div class="col">
				<button type="button" id="add" class="btn btn-primary btn-lg "> New Event </button>
				<div id="EventDiv" class="pt-5">
					<div class="card" style="width: 26rem;">
						<div class="card-header">New Event</div>

						<div class="card-body">

							<form name="dataSubmit">
								<div class="form-group">
									Event Name <input type="text" class="form-control"
										id="EventNameID" name="EventName" aria-describedby="emailHelp"
										autocomplete="off" /> URL <input type="text" class="form-control" id="urlID" name="Date" autocomplete="off" />
									Start Date <input type="date" id="datepicker" class="form-control"/>
									Start Time <input type="time" class="form-control" id="StartTimeID"	name="startTime" autocomplete="off" /> 
									<div>
										Recurring Date?	<input type="checkbox" name="recurringcheck" id="grayout" class="recurring"> <br>
										<input type="checkbox" id="sun" style="display:none;"><label for="sun" style="display:none;">&nbsp;Sunday </label>
										<input type="checkbox" id="mon" style="display:none;"><label for="mon" style="display:none;">&nbsp;Monday </label>
										<input type="checkbox" id="tue" style="display:none;"><label for="tue" style="display:none;">&nbsp;Tuesday </label>
										<input type="checkbox" id="wed" style="display:none;"><label for="wed" style="display:none;">&nbsp;Wednesday </label> <br style="display:none;">
										<input type="checkbox" id="thr" style="display:none;"><label for="thr" style="display:none;">&nbsp;Thursday </label>
										<input type="checkbox" id="fri" style="display:none;"><label for="fri" style="display:none;">&nbsp;Friday </label>
										<input type="checkbox" id="sat" style="display:none;"><label for="sat" style="display:none;">&nbsp;Saturday </label> <br style="display:none;">
										<label for="enddate" style="display:none;">End Date: </label> <input type="date" id="enddate" style="display:none;" class="form-control"/> 
										
									</div>
								</div>

								<a id="SubmitID" href="#" class="btn btn-primary" type="submit">Submit</a>
							</form>
						</div>
					</div>
				
				<div class="card mt-5" style="width: 26rem;">
					<div class="card-header">Enter A Share Code</div>
						<div class="card-body">
						<div class="form-group">
							<input type="text" id="shareCodeId" class="form-control"/> 
						</div>
						<a id="ShareCodeSubmit" href="#" class="btn btn-primary" type="submit">Add Share Code</a>
						
					</div>
				</div>
				</div>
			</div>
			<div class="col-9">
				<div id='calendar'></div>
			</div>
		</div>
	</div>
	<div id="dialog" style="overflow-y: none;">
		<div id="infostuff">
			<h5>Event Name:</h5>
			<div id="nameOfEvent"></div>
			<h5>Event Time:</h5>
			<div id="Time"></div>
			<br>
			<div id="link"></div>
			<br>
			<span>Click to copy share id onto clipboard: </span> 
			<button id="ShareCode" onclick="copyToClipboard('#ShareCode')" class="btn btn-outline-primary btn-sm" style="width:100px"></button>
			<button id="DeleteEvent" class="btn btn-outline-danger btn-sm ml-3" style="width:100px">Delete Event</button>
			
		</div>
		<div id="DeleteAlert"> Are you sure you want to delete?
			<button id="DeleteNah" class="btn btn-outline-primary btn-sm" style="width:100px">Cancel</button>
			<button id="DeleteYea" class="btn btn-outline-danger btn-sm ml-3" style="width:100px">Confirm</button>
		</div>
				
	</div>
	UTC Time: <%out.println((new java.util.Date()).toLocaleString());%>
</body>
</html>
<script>

function copyToClipboard(element) {   
	var $temp = $("<input>");   
	$("body").append($temp);   
	$temp.val($(element).text()).select();   
	document.execCommand("copy");   
	$temp.remove(); 
}

$('#grayout').click(function() {
//	$(this).siblings().attr('disabled', !this.checked);

	$(this).siblings().attr("style", this.checked ? "" : "display:none;");
//	}); 

});

$( document ).ready(function() { // this will auto hide the div for event UI when the DOM is ready to be loaded. 
	$("#error").hide();
	$("#EventDiv").hide();
	$("#dialog").hide();
	$("#DeleteAlert").hide();
	test();
	
});

$( "#add" ).on( "click", function() { // used when button is clicked to show  the add event UI
	
	var check = $("#add").text().trim();
	
	if(check == "New Event") {
    	$( "#EventDiv").show(); // shows the div for adding new events
		$("#add").text("Close Event");

	 } else {
	 	$("#EventDiv").hide();
		$("#add").text("New Event");

	 }
});

//$( function() { // thisis for the date picker 
//    $("#datepicker").datepicker( 
//      {
//        dateFormat: 'yy-mm-dd', //formamt based on how the calader API works 
//      }
//    );
//});

$( "#SubmitID" ).on( "click", function(){ // this funtion adds new event to the data object 
	
	var eventName = $("#EventNameID").val();   // gets the input data
	var urlID = $("#urlID").val();   
	var StartTimeID = $("#StartTimeID").val();
	var date = $( "#datepicker").val(); 
	var start = date + " " + StartTimeID + ":00";
	var check = $("#isSelected").is(':checked');
	var binaryWeekly = "";
	var enddate = $("#enddate").val();
	
	if (!urlID.includes("http://") && !urlID.includes("https://")) {
		urlID = "http://" + urlID;
	}
	
	if(eventName == '' || urlID == ''  || StartTimeID == '' ||  date == '' || start == '' ) 
	{
		$( "#error").show();
	} else {
	   
		if (document.getElementsByName("recurringcheck")[0].checked) {
		    binaryWeekly += document.getElementById('sun').checked ? 1 : 0;
		    binaryWeekly += document.getElementById('mon').checked ? 1 : 0;
		    binaryWeekly += document.getElementById('tue').checked ? 1 : 0;
		    binaryWeekly += document.getElementById('wed').checked ? 1 : 0;
		    binaryWeekly += document.getElementById('thr').checked ? 1 : 0;
		    binaryWeekly += document.getElementById('fri').checked ? 1 : 0;
		    binaryWeekly += document.getElementById('sat').checked ? 1 : 0;
		} else {
			binaryWeekly = "0000000";
		}
		var xhttp = new XMLHttpRequest();
		xhttp.open("POST", 
				"savedata?utoken=" + '<%=request.getParameter("userId")%>'
						+ "&date="
						+ start
						+ "&title="
						+ eventName
						+ "&url="
						+ urlID
						+ "&zone="
						+ Intl.DateTimeFormat()
								.resolvedOptions().timeZone
						+ "&recurring="
						+ binaryWeekly
						+ "z" + enddate, true);
		xhttp.send();
		setTimeout(   function() { location.reload(); }, 1000);
	}
});

$( "#ShareCodeSubmit" ).on( "click", function(){ 
	var eventName = $("#shareCodeId").val();
	var urlID = $("#urlID").val();   

	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", 
			"sharecodeinput?shareId=" + eventName
			+ "&userId=" + '<%=request.getParameter("userId")%>'
			, true);
	xhttp.send();

	setTimeout(   function() { location.reload(); }, 1000);
});

$("#DeleteEvent").on( "click", function(){ // this funtion adds new event to the data object 
	$("#DeleteAlert").show();
	$("#infostuff").hide();

	//var eventId = $("#ShareCode").val();   // gets the input data
	
	//var xhttp = new XMLHttpRequest();
	//xhttp.open("POST", 
			//"deletevent?eventId=" + eventId, true);
	//xhttp.send();
	//setTimeout(   function() { location.reload(); }, 1000);
	
});

$("#DeleteNah").on( "click", function(){ // this funtion adds new event to the data object 
	$("#infostuff").show();
	$("#DeleteAlert").hide();
	
});

$("#DeleteYea").on( "click", function(){ // this funtion adds new event to the data object 
	var delId = $("#ShareCode").text();   // gets the input data
	
	var xhttp = new XMLHttpRequest();
	xhttp.open("POST", "deleteevent?eventId=" + delId, true);
	xhttp.send();
	
	setTimeout(   function() { location.reload(); }, 1000);
	
});

function test() {
	var calendarEl = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(
			calendarEl,
			{
				initialDate : new Date(),
				timeZone : 'local',
				nextDayThreshold: '12:00:00',
				editable : false,
				eventColor:'#0275d8',
				selectable : true,
				businessHours : true,
				dayMaxEvents : true, // allow "more" link when too many events
				events : data,
				eventClick : function(info) {
					info.jsEvent.preventDefault(); // don't let the browser navigate

					if (info.event.url) {
						$('#nameOfEvent').html(info.event.title);
						$('#link')
								.html(
										'<a class="btn btn-outline-primary btn-sm"  href="' + info.event.url + ' ">Launch Meeting </a>');
						$('#Time').html(info.event.start);
						$('#ShareCode').html(info.event.id);
						$("#dialog").dialog({
							title : "Event Info",
							width : 500
						});
					}
				}
			});

	calendar.render();
}
</script>
