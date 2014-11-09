<?php
session_start();
?>
<html>
<head> 
	<title></title>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	<form name="event_id_sub" method="POST" action="eventformedit.php">
	
	<table>
	<caption><h2>EDIT EVENT DETAILS</h2></CAPTION>
	<tr><td>Enter Event ID of the Event</td><td><input type="text" name="eide"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</table>
	
</body>
</html>