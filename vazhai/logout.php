<?php
session_start();
session_destroy();
?>
<html>
<body background="b1.jpg">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<?php
echo"Sucessfully logged out<br>";
echo"Press the link to redirect to login<br>";
?>
<a href="login.php">Click Here</a>
</body>
</html>











