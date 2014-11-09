<?php
session_start();
?>
<html>
<head> 
	<title>Edit Student Details</title>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	<form name="stud_reg_id_sub" method="post" action="studentformedit.php">
	
	<table>
	<caption><h2>EDIT STUDENT DETAILS</h2></CAPTION>
	<tr><td>Enter Registration ID of the Student</td><td><input type="text" name="regid"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</table>
	
</body>
</html>