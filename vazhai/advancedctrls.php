<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
?>
<html>
<head>
	<title>Advanced controls</title>
</head>
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<body background='b1.jpg'>
<font face='Calibri'>
<h1>ADVANCED CONTROLS</h1>
	<table>
	<caption></caption>
	<tr><td><A HREF="createuserform.php"><img src="cnu.jpg"></A></td>
	<td><A HREF="changepass.php"><img src="cp.jpg"></A>
	<td><A HREF="deleteuserform.php"><img src="du.jpg"></A></td>	
	</table>
	</form>	
</body>
</html>



