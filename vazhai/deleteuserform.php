<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
?>
<html>
<head>
	<title>Delete User</title>
</head>

<body background='b1.jpg'>
<font face='Calibri'>
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
	
	
	<form method="POST" ACTION="deleteuser.php">
	<table >
	<caption><strong>DELETE USER</strong</caption>
	<tr><td>Reg ID :</td><td><input type="text" name="ridv3"></td></tr>
	<tr><td></td><td><input type="submit" value="Delete"></td></tr>
	<tr></tr><tr></tr><tr><td><a href="home.php">Home</a></td>
	</table>
	</form>	
</body>
</html>
