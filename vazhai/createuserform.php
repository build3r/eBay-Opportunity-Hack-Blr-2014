<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
?>
<html>
<head>
	<title>CREATE NEW USER</title>
</head>
<body background='b1.jpg'>
<font face='Calibri'>
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>

	<form method="POST" action="createuser.php">
	<table>
	<caption><strong>CREATE USER</strong</caption>
	<tr><td></td><td></td><td><a href="home.php">Home</a></td>
	<tr><td>Reg ID :</td><td><input type="text" name="ridv"></td></tr>
	<tr><td>Password :</td><td><input type="password" name="pwd"></td></tr>
	<tr><td>Priority :</td><td><input type="text" name="prio"></td></tr>
	<!-- <tr><td>Group :</td><td><input type="text" name="grp"></td></tr> --!>
	<tr><td></td><td><input type="submit" value="Create"></td></tr>
	</form>
	</table>