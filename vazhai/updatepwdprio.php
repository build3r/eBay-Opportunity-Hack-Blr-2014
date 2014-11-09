<?php
	$ridv=$_POST['ridv2'];
	$pwd=$_POST['pwd'];
	//$prio=$_POST['prio'];
	$un="root";
	$pw="";
	$s="localhost";
	$db="vazhai";
	$dbh=mysql_connect($s,$un,$pw);
	$dbf=mysql_select_db($db,$dbh);
	if($dbf)
	{
		$sql="UPDATE login SET pwd='$pwd' WHERE ridv='$ridv'";
		$r=mysql_query($sql);
		echo "Updation Successfull! <a href='home.php'>Home</a>";
	}
	else
		echo "Database Not found ! Retry some other time ! <a href='home.php'>Home</a>"; 
?>
