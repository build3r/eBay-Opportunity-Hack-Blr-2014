<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
$host="localhost";
$eide=$_POST['eide'];
$username="root";
$password="";
$db_name="Vazhai";
$tbl_name="Event";
$link=mysql_connect("$host","$username","$password");
mysql_select_db("$db_name",$link);
$sql="select * from event where eide='$eide'";
$result=mysql_query($sql);
$row=mysql_fetch_assoc($result);
?><html>
<head>
<title>EVENT FORM</title>
<h2>EVENT FORM</h2>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>

<table>
<tr><td><b>Event ID</b></td><td><?php echo($row['eide']);?></td></tr>
<tr><td><b>Event Name</b></td><td><?php echo($row['ename']);?></td></tr>
<tr><td><b>Event Location</b></td><td><?php echo($row['eloc']);?></td></tr>
<tr><td><b>Event Start Date</b></td><td><?php echo($row['esd']);?></td></tr>
<tr><td><b>Event Start Time</b></td><td><?php echo($row['est']);?></td></tr>
<tr><td><b>Event End Date</b></td><td><?php echo($row['eed']);?></td></tr>
<tr><td><b>Event End Time</b></td><td><?php echo($row['eet']);?></td></tr>
<tr><td><b>Event Coordinator Name</b></td><td><?php echo($row['ecorn']);?></td></tr>
<tr><td><b>Coordinator Contact Number</b></td><td><?php echo($row['ccn']);?></td></tr>
<!--<tr><td><b>Session Document</b></td><td><input type="file" name="sdoc"></td></tr>
    //<tr><td><b>Schedule Document</b></td><td><input type="file" name="scdoc"></td></tr> --!>
<tr><td><a href="home.php">Home</a></td></tr>
 
</table></body></html>