<?php
session_start();
?>
<html>
<head>
	
	<title>Login</title>

</head>
<body background="b1.jpg"><font face="calibri">

<table>
<img src="logo.jpg" width="1400" height="200"><br>
<tr><td><td></tr><font size="20" face="calibri">LOGIN PAGE</font>
<form action="logincode.php" method="post">

<tr>
<td><strong>Username</strong></td>
<td>:</td>
<td><input type="text" placeholder="Type Here" name="uname"></td>
</tr>
<tr>
<td><strong>Password</strong></td>
<td>:</td>
<td><input type="password" name="pwd"></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</form>
</table>
</body>
</html>




