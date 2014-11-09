<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
$host="localhost";
$namev=$_POST['namev'];
$username="root";
$password="";
$db_name="Vazhai";
$tbl_name="Volunteer";
$link=mysql_connect("$host","$username","$password");
mysql_select_db("$db_name",$link);
$sql="select * from Volunteer where namev like '%$namev%'";
$result=mysql_query($sql);
?>
<html>
<head>
<title>VOLUNTEER FORM</title>
<h2>VOLUNTEER FORM</h2>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<?php
while($row=mysql_fetch_assoc($result))
{
?>
<table>
<tr><td><b>Year of Joining</b></td><td><?php echo($row['yojv']);?></td></tr>
<tr><td><b>Registration ID</b></td><td><?php echo($row['ridv']);?></td><!--<td>//*Upload photo</td><td><input type="file" name="picv" required></td>--!></tr>
<tr><td><b>Name</b></td><td><?php echo($row['namev']);?></td></tr>
<tr><td><b>Contact Number</b></td><td><?php echo($row['cnumv']);?></td></tr>
 <tr><td><b>Gender</b></td><td><?php echo($row['sexv']);?></td></tr>
<tr><td><b>EmailID</b></td><td><?php echo($row['eidv']);?></td></tr>
<tr><td><b>Role</b></td><td><?php echo($row['rolev']);?></td></tr>
<tr><td><b>Status</b></td><td><?php echo($row['statv']);?></td></tr>
<tr><td><b>Occupation</b></td><td><?php echo($row['occv']);?></td></tr>
<tr><td><b>Date of Birth</b></td><td><?php echo($row['dobv']);?></td></tr>
<tr><td><b>Blood group</b></td><td><?php echo($row['bldgpv']);?></td></tr>
<tr><td><b>Native</b></td><td><?php echo($row['natv']);?></td></tr>
<tr><td><b>Permanent Address</b></td><td><?php echo($row['paddv']);?></td></tr>
<tr><td><b>Current Address</b></td><td><?php echo($row['caddv']);?></td></tr>
<tr><td><b>Languages Known</b></td><td><?php echo($row['langv']);?></td></tr>
 <tr><td><b>TIG Team</b></td><td><?php echo($row['tigtv']);?></td></tr>
<tr><td><b>Interests</b></td><td><?php echo($row['intrsv']);?></td></tr>
<tr><td><b>Reference</b></td><td><?php echo($row['refv']);?></td></tr>
<tr><td><b>Ward's Name</b></td><td><?php echo($row['wrdv']);?></td></tr>
<tr><td><b>Ward's Registration ID</b></td><td><?php echo($row['wrgid']);?></td></tr>
<tr><td><b>Ward's Contact Number</b></td><td><?php echo($row['wcnumv']);?></td></tr>
<tr><td><b>Current Sessions</b></td><td><?php echo($row['cursv']);?></td></tr>
<tr><td><b>Experienced Sessions</b></td><td><?php echo($row['expsv']);?></td></tr>

----------------------------------------------------------------------------------------------------------------------------------------------------<br>
----------------------------------------------------------------------------------------------------------------------------------------------------
<?php }
?>
<tr><td><a href="home.php">Home</a></td></tr>


</table></body></html>