<?php
session_start();
?>
<html>
<head>
<title>EVENT FORM</title>
<h4>EVENT FORM</h4>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<form action="eventformcode.php" method="post">
<table>
<tr><td>*Event ID</td><td><input type="text" name="eide" required></td></tr>
<tr><td>*Event Name</td><td><input type="text" name="ename" required></td></tr>
<tr><td>*Event Location</td><td><input type="text" name="eloc" required></td></tr>
<tr><td>*Event Start Date</td><td><input type="date" name="esd"></td></tr>
<tr><td>*Event Start Time</td><td><input type="time" name="est"></td></tr>
<tr><td>*Event End Date</td><td><input type="date" name="eed"></td></tr>
<tr><td>*Event End Time</td><td><input type="time" name="eet"></td></tr>
<tr><td>*Event Coordinator Name</td><td><input type="text" name="ecorn" required></td></tr>
<tr><td>*Coordinator Contact Number</td><td><input type="text" name="ccn" required></td></tr>
<!--<tr><td></td><td>*Session Document</td><td><input type="file" name="sdoc"></td></tr>
    <tr><td></td><td>*Schedule Document</td><td><input type="file" name="scdoc"></td></tr> --!>
 <tr><td></td><td><input type="submit" value="Upload details" name="eventformsub"></td></tr>
</table></body></html>