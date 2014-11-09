<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
?>
<html>
<head>
	<title>Home</title>
</head>
<body background="b1.jpg">
<font size='20' color='green' face='calibri'>DATA PORTAL OPTIONS</font>
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<p align="right"><a href="logout.php">LOGOUT</a></p>
<table>

	<tr><td><a href='studentviewi.php'><img src="vsd.jpg"></a></td>
	<td><a href='volunteerviewi.php'><img src="vvd.jpg"></a></td>
	<td><a href='eventviewi.php'><img src="ved.jpg"></a></td></tr>
	<?php if($_SESSION['prio']==1)
	{
	echo("<tr><td><a href='studentformediti.php'><img src='esd.jpg'></a></td>");
	echo("<td><a href='volunteerformediti.php'><img src='evd.jpg'></a></td>");
	echo("<td><a href='eventformediti.php'><img src='eed.jpg'></a></tr></td>");
	}
        if($_SESSION['prio']==1)
	{
	echo("<tr><td><a href='studform.php'><img src='isr.jpg'></a></td>");
	echo("<td><a href='volunteerform.php'><img src='ivr.jpg'></a></td>");
	echo("<td><a href='eventform.php'><img src='ier.jpg'></a></tr></td>");
        }
         if($_SESSION['prio']==1)
	{
	echo("<tr><td></td><td><a href='advancedctrls.php'><img src='ac.jpg'></a></td>");
	
        }
?>
	
</body>
</html>