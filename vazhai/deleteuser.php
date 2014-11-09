<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$ridv=$_POST['ridv3'];
	$un="root";
	$pw="";
	$s="localhost";
	$db="vazhai";
	$dbh=mysql_connect($s,$un,$pw);
	$dbf=mysql_select_db($db,$dbh);
	if($dbf)
	{
		$sql="DELETE FROM login WHERE ridv='$ridv'";
		$r=mysql_query($sql); 
		echo "Deletion Successful! <a href='home.php'>Home</a>";
	}
	else
		echo "Database Not found! Retry some other time ! <a href='home.php'>Home</a>"; 
?>
