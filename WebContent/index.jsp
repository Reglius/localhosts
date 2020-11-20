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
<script	src="/localhosts/jsonrequest?userId=<%=request.getParameter("userId")%>"></script>

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
	<div class="container-fluid">
		<div class="row justify-content-md-center">
			<div class="col">
				<button type="button" id="add" class="btn btn-primary btn-lg ">
					<span class="ui-icon ui-icon-plus"> </span>New Event
				</button>
				<div id="EventDiv" class="pt-5">
					<div class="card" style="width: 26rem;">
						<div class="card-header">New Event</div>

						<div class="card-body">

							<form name="dataSubmit">
								<div class="form-group">
									Event Name <input type="text" class="form-control"
										id="EventNameID" name="EventName" aria-describedby="emailHelp"
										autocomplete="off" /> URL <input type="text"
										class="form-control" id="urlID" name="Date" autocomplete="off" />
									Start Date <input type="text" class="form-control"
										id="datepicker" autocomplete="off" /> Start Time <input
										type="time" class="form-control" id="StartTimeID"
										name="startTime" autocomplete="off" />
									Recurring Date<input type="checkbox" id="isSelected" aria-label="Checkbox for following text input">
								</div>

								<a id="SubmitID" href="#" class="btn btn-primary" type="submit">Submit</a>
							</form>
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
		<h5>Event Name:</h5>
		<div id="nameOfEvent"></div>
		<br>
		<div id=link></div>
		<br>
	</div>
	UTC Time:
	<%
		out.println((new java.util.Date()).toLocaleString());
	%>
</body>
</html>
<script>
$( document ).ready(function() { // this will auto hide the div for event UI when the DOM is ready to be loaded. 
  	$("#EventDiv").hide();
	$("#dialog").hide();
	test();
	
});

$( "#add" ).on( "click", function() { // used when button is clicked to show  the add event UI 
    $( "#EventDiv").show(); // shows the div for adding new events 
});

$( function() { // thisis for the date picker 
    $("#datepicker").datepicker( 
      {
        dateFormat: 'yy-mm-dd', //formamt based on how the calader API works 
      }
    );
});

$( "#SubmitID" ).on( "click", function(){ // this funtion adds new event to the data object 

var eventName  =  $("#EventNameID").val();   // gets the input data
var urlID      = $("#urlID").val();   
var StartTimeID =  $("#StartTimeID").val();
var date = $( "#datepicker").val(); 
var start = date + " " + StartTimeID + ":00";
var  check = $("#isSelected").is(':checked');
var xhttp = new XMLHttpRequest();
xhttp.open("POST", 
		"savedata?utoken=" + '<%=request.getParameter("userId")%>'
												+ "&date=" + start
												+ "&title="	+ eventName
												+ "&url=" + urlID
												+ "&zone=" + Intl.DateTimeFormat().resolvedOptions().timeZone,true);
						xhttp.send();

						var newRec = {
							start : start,
							title : eventName,
							url : urlID
						}; // format for the rec 
						data.push(newRec); // adds new json rec

						$("#EventDiv").hide(); // hides the UI 

						// logic below refreshes the new  events that were added 
						var calendarEl = document.getElementById('calendar');
						var calendar = new FullCalendar.Calendar(
								calendarEl,
								{
									initialDate : start,
									timeZone : 'UTC',
									editable : true,
									selectable : true,
									businessHours : true,
									dayMaxEvents : true, // allow "more" link when too many events
									events : data,
									eventClick : function(info) {
										info.jsEvent.preventDefault(); // don't let the browser navigate

										if (info.event.url) {
											$('#nameOfEvent').html(
													info.event.title);
											$('#link')
													.html(
															'<a class="btn btn-outline-primary btn-sm"  href="' + info.event.url + ' ">Launch Meeting </a>');
											$('#Time').html(info.event.start);
											$("#dialog").dialog({
												title : "Event Info",
												width : 500
											});
										}
									}
								});

						calendar.render();
					});

	function test() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(
				calendarEl,
				{
					initialDate : new Date(),
					timeZone : 'UTC',
					editable : true,
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
