<?php
session_start();?>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<?php
error_reporting(E_ALL ^ E_DEPRECATED);
$host="localhost"; // Host name 
$username="root"; // Mysql username 
$password=""; // Mysql password 
$db_name="vazhai"; // Database name 
$link=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name",$link) or die("Cannot select db");
$eide=$_POST['eide'];
$ename=$_POST['ename'];
$eloc=$_POST['eloc'];
$esd=$_POST['esd'];
$est=$_POST['est'];
$eed=$_POST['eed'];
$eet=$_POST['eet'];
$ecorn=$_POST['ecorn'];
$ccn=$_POST['ccn'];
//BLOB INCOMING--------------------------------------------------------------------------------------------------------------------------------------

$sql="INSERT INTO event(eide,ename,eloc,esd,est,eed,eet,ecorn,ccn) values('$eide','$ename','$eloc','$esd','$est','$eed','$eet','$ecorn','$ccn')";
$result=mysql_query($sql);
if($result)
{
echo("Successful");
echo("<br> <a href='home.php'>Home</a>");
}
else
{
echo(mysql_error());
echo("<br> <a href='eventform.php'>Retry</a>");
}
?></body>
