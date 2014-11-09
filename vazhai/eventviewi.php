<?php
session_start();
?>
<html>
<head> 
	<title>Event View</title>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	<table>
	<caption><h2>EVENT SEARCH</h2></CAPTION>
	<form name="event_id_sub" method="POST" action="eventview.php">
	<tr><td>Search By Id</td><td><input type="text" name="eide"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>

	<form method="POST" action="eventviewa.php">
	<tr><td>Search By Event Name</td><td><input type="text" name="ename"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>
	</table>
	
</body>
</html>