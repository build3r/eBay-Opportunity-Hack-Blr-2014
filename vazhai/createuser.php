<?php
	session_start();
	$un="root";
	$pw="";
	$s="localhost";
	$db="vazhai";
	$dbh=mysql_connect($s,$un,$pw);
	$dbf=mysql_select_db($db,$dbh);
	$ridv=$_POST['ridv'];
	$pwd=$_POST['pwd'];
	$prio=$_POST['prio'];
	if($dbf)
	{
		$sql="INSERT INTO login(ridv,pwd,prio) VALUES('$ridv','$ridv','$prio')";
		$r=mysql_query($sql);
		echo "Insertion Successful ! <a href='home.php'>Home</a>";
	}
	else
		echo "Database Not found ! Retry some other time ! <a href='home.php'>Click here to return to home</a>"; 
?>
