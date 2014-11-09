<?php
session_start();?><body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<?php
$temp=$_SESSION['stregid'];
error_reporting(E_ALL ^ E_DEPRECATED);
$host="localhost"; // Host name 
$username="root"; // Mysql username 
$password=""; // Mysql password 
$db_name="vazhai"; // Database name 
$link=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name",$link) or die("Cannot select db");

$yoj=$_POST['yoj'];
$regid=$_POST['regid'];
$name=$_POST['name'];
$sex=$_POST['sex'];
$dob=$_POST['dob'];
$fs=$_POST['fs'];
$fname=$_POST['fname'];
$foccu=$_POST['foccu'];
$ms=$_POST['ms'];
$mname=$_POST['mname'];
$moccu=$_POST['moccu'];
$gname=$_POST['gname'];
$goccu=$_POST['goccu'];
$cnumm=$_POST['cnumm'];
$cnumh=$_POST['cnumh'];
$cnumg=$_POST['cnumg'];
$addr=$_POST['addr'];
$std1=$_POST['std1'];
$schl=$_POST['schl'];
$intrs=$_POST['intrs'];
$pc=$_POST['pc'];
$pcdt=$_POST['pcdt'];
$resec=$_POST['resec'];
$bldgp=$_POST['bldgp'];
$mename=$_POST['mename'];
$menct=$_POST['menct'];
$tigt=$_POST['tigt'];

$sql="UPDATE student SET yoj='$yoj',regid='$regid',name='$name',sex='$sex',dob='$dob',fs='$fs',fname='$fname',foccu='$foccu',ms='$ms',mname='$mname',moccu='$moccu',gname='$gname',goccu='$goccu',cnumm='$cnumm',cnumh='$cnumh',cnumg='$cnumg',addr='$addr',std1='$std1',intrs='$intrs',pc='$pc',pcdt='$pcdt',resec='$resec',bldgp='$bldgp',mename='$mename',menct='$menct',tigt='$tigt' WHERE regid='$temp'";
$result=mysql_query($sql);
if($result)
{
echo("Successful");
echo("<br> <a href='home.php'>Home</a>");
}
else
{
echo(mysql_error());
echo("<br> <a href='studformediti.php'>Retry</a>");
}
mysql_close($link);
?></body>