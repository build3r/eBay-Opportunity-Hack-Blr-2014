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
	<form name="vol_reg_id_sub" method="POST" action="volunteerformedit.php">
	
	<table>
	<caption><h2>EDIT VOLUNTEER DETAILS</h2></CAPTION>
	<tr><td>Enter Registration ID of the Volunteer</td><td><input type="text" name="ridv"></td><tr>
	<tr><td></td><td><input type="submit"/></td>
	</table>
	
</body>
</html>