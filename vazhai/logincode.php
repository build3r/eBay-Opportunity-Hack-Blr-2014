<body background="b1.jpg"><font face="calibri">
<?php	
        error_reporting(E_ALL ^ E_DEPRECATED);
	session_start();
	$username="root";
	$password="";
	$s="localhost";
	$dbh=mysql_connect("$s","$username","$password") or die("connection not established!!!");
	$db="vazhai";
	$x=mysql_select_db("$db",$dbh) or die("Cannot select DB");
	$un=$_POST['uname'];
	$pwd=$_POST['pwd'];
	$sql="SELECT * FROM login WHERE ridv='$un'";
	$result=mysql_query($sql);
	$r=mysql_fetch_assoc($result);
	if(isset($r))
	{	
		$_SESSION['un']=$un;
		$_SESSION['prio']=$r['prio'];		
		if($pwd==$r['pwd'])
		{	
			echo "Login  successful <br><br> <a href='home.php'>Click here to proceed!</a>";
		}
		else 
		{
			echo " Login unsuccessful <br> <a href='login.php'> Click here to return to the log in page! </a>";				
		}
	}
	else
	{
		echo "User Not registered !<br> <a href='login.php'> Click here to return to the log in page! </a>";
	}
?></body>
 