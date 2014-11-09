<?php
session_start();
?>
<html>
<head> 
	<title>VIEW STUDENT DETAILS</title>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	<form name="stud_reg_id_sub" method="POST" action="studentview.php">
	
	<table>
	
	<caption><h2>STUDENT SEARCH</h2></CAPTION>
	<tr><td>Search by ID</td><td><input type="text" name="regid"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>
	<form method="POST" action="studentviewa.php">
	<tr><td>Search by Name</td><td><input type="text" name="nm"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</form>
	</table>
	
</body>
</html>