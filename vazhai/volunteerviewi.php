<?php
session_start();
?>
<html>
<head> 
	<title>VIEW VOLUNTEER DETAILS</title>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	<form name="vol_reg_id_sub" method="POST" action="volunteerview.php">
	
	<table>
	<caption><h2>VOLUNTEER SEARCH</h2></CAPTION>
	<tr><td>Search by REGID</td><td><input type="text" name="ridv"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>

	<form method="POST" action="volunteerviewa.php">
	<tr><td>Search by Name</td><td><input type="text" name="namev"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>

	<form method="POST" action="volunteerviewb.php">
	<tr><td>Search by Ward Name</td><td><input type="text" name="wrdv"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>
	</table>
	
</body>
</html>