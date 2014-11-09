<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
?>
<html>
<head>
	<title>Change Password</title>
</head>
<body background='b1.jpg'>
<font face='Calibri'>
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	
	<form method="POST" action="updatepwdprio.php">
	<table>
	<caption><strong>CHANGE PASSWORD</strong</caption>
	<tr><td>Reg ID :</td><td><input type="text" name="ridv2"></td></tr>
	<!--<tr><td>Priority :</td><td><input type="text" name="prio"></td></tr>--!>
	<tr><td>Password :</td><td><input type="password" name="pwd"></td></tr>
	<tr><td></td><td><input type="submit" value="Update"></td></tr>
	</table>
	</form>
	</body>
	</html>