<?php
session_start();?><body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<?php

$temp=$_SESSION['vridv'];
error_reporting(E_ALL ^ E_DEPRECATED);
$host="localhost"; // Host name 
$username="root"; // Mysql username 
$password=""; // Mysql password 
$db_name="vazhai"; // Database name 
$link=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 
mysql_select_db("$db_name",$link) or die("Cannot select db");

$yojv=$_POST['yojv'];
$ridv=$_POST['ridv'];
//$picv=$_POST['picv'];-------------------------------------------
$namev=$_POST['namev'];
$cnumv=$_POST['cnumv'];
$sexv=$_POST['sexv'];
$eidv=$_POST['eidv'];
$rolev=$_POST['rolev'];
$statv=$_POST['statv'];
$occv=$_POST['occv'];
$dobv=$_POST['dobv'];
$bldgpv=$_POST['bldgpv'];
$natv=$_POST['natv'];
$paddv=$_POST['paddv'];
$caddv=$_POST['caddv'];
$langv=$_POST['langv'];
$tigtv=$_POST['tigtv'];
$intrsv=$_POST['intrsv'];
$refv=$_POST['refv'];
$wrdv=$_POST['wrdv'];
$wrgid=$_POST['wrgid'];
$wcnumv=$_POST['wcnumv'];
$cursv=$_POST['cursv'];
$expsv=$_POST['expsv'];

$sql="update volunteer SET yojv='$yojv',ridv='$ridv',namev='$namev',cnumv='$cnumv',sexv='$sexv',eidv='$eidv',rolev='$rolev',statv='$statv',occv='$occv',dobv='$dobv',bldgpv='$bldgpv',natv='$natv',paddv='$paddv',caddv='$caddv',langv='$langv',tigtv='$tigtv',intrsv='$intrsv',refv='$refv',wrdv='$wrdv',wrgid='$wrgid',wcnumv='$wcnumv',cursv='$cursv',expsv='$expsv' WHERE ridv='$temp'";

$result=mysql_query($sql);
if($result)
{
echo("Successful");
echo("<br> <a href='home.php'>Home</a>");
}
else
{
echo(mysql_error());
echo("<br> <a href='volunteerformediti.php'>Retry</a>");
}
mysql_close($link);
?></body>