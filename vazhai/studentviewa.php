<?php
session_start();
error_reporting(E_ALL ^ E_DEPRECATED);
$host="localhost";
$nm=$_POST['nm'];
$username="root";
$password="";
$db_name="Vazhai";
$tbl_name="Student";
$link=mysql_connect("$host","$username","$password");
mysql_select_db("$db_name",$link);
$sql="select * from Student where name like'%$nm%'";
$result=mysql_query($sql);
?>

<html>
<head>
<title>STUDENT FORM</title>
<h2>STUDENT FORM</h2>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<?php
while($row=mysql_fetch_assoc($result))
{
?>

  <table>
   <tr><td><b>Year of Admission</b></td><td><?php echo($row['yoj']);?></td></tr>
   <tr><td><b>Registration ID</b></td><td><?php echo($row['regid']);?></td></tr>
   <tr><td><b>Name</b></td><td><?php echo($row['name']);?></td><td></td><td>
   <tr><td><b>Gender</b></td><td><?php echo($row['sex']);?></td></tr>
   <tr><td><b>Date of Birth</b></td><td><?php echo($row['dob']);?></td></tr>
   <tr><td><b>Father Status</b></td><td><?php echo($row['fs']);?></td></tr>
   <tr><td><b>Father Name</b></td><td><?php echo($row['fname']);?></td></tr>
   <tr><td><b>Father Occupation</b></td><td><?php echo($row['foccu']);?></td></tr>
   <tr><td><b>Mother Status</b></td><td><?php echo($row['ms']);?></td></tr>
   <tr><td><b>Mother Name</b></td><td><?php echo($row['mname']);?></td></tr>
   <tr><td><b>Mother Occupation</b></td><td><?php echo($row['moccu']);?></td></tr>
   <tr><td><b>Gaurdian Name</b></td><td><?php echo($row['gname']);?></td></tr>
   <tr><td><b>Guardian Occupation</b></td><td><?php echo($row['goccu']);?></td></tr>
   <tr><td><b>Contact Numbers:</b></td><td><b>Mobile</b></td><td><?php echo($row['cnumm']);?></td></tr>
   <tr><td></td><td><b>Home</b></td><td><?php echo($row['cnumh']);?></td>
   <tr><td></td><td><b>Gaurdian</b></td><td><?php echo($row['cnumg']);?></td>
   <tr><td><b>Address</b></td><td><?php echo($row['addr']);?></td></tr>
   <tr><td><b>Standard</b></td><td><?php echo($row['std1']);?></td></tr>
   <tr><td><b>School</b></td><td><?php echo($row['schl']);?></td></tr>
   <tr><td><b>Interests</b></td><td><?php echo($row['intrs']);?></td></tr>
    <tr><td><b>Physically Challenged</b></td><td><?php echo($row['pc']);?></td></tr>
   <tr><td><b>If yes details</b></td><td><?php echo($row['pcdt']);?></td></tr>
   <tr><td><b>Reason for Selection</b></td><td><?php echo($row['resec']);?></td></tr>
    <tr><td><b>Blood group</b></td><td><?php echo($row['bldgp']);?></td></tr>
    <tr><td><b>Mentor name</b></td><td><?php echo($row['mename']);?></td></tr>
     <tr><td><b>Mentor contact</b></td><td><?php echo($row['menct']);?></td></tr>
  <!-- <tr><td><b>Student Selection Form</td><td></td><td><input type="file" name="ss"></td></tr>
//	<tr><td><b>Ward Profile Details</b></td><td><b>Year 1</b><td><input type="file" name="f1"></td></tr>
  //  <tr><td></td><td><b>Year 2</b></td><td><input type="file" name="f2"></td></tr>
    //<tr><td></td><td><b>Year 3</b></td><td><input type="file" name="f3"></td></tr>
     //<tr><td></td><td><b>Year 4</b></td><td><input type="file" name="f4"></td></tr>
     //<tr><td></td><td><b>Year 5</b></td><td><input type="file" name="f5"></td></tr>
     //<tr><td></td><td><b>Year 6</b></td><td><input type="file" name="f6"></td></tr> --!>
     <tr><td><b>TIG Team</b></td><td><?php echo($row['tigt']);?></td></tr>
	----------------------------------------------------------------------------------------------------------------------------------------------------<br>
----------------------------------------------------------------------------------------------------------------------------------------------------
    
<?php }
?>
<tr><td><a href="home.php">Home</a></td></tr>
</body>
</html>