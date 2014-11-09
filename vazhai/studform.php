<?php
session_start();
?>
<html>
<head>
<title>STUDENT FORM</title>
<h4>STUDENT FORM</h4>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<form action="studentformcode.php" method="post" name="1">
  <table>
   <tr><td>*Year of Admission</td><td><input type="text" name="yoj" required></td></tr>
   <tr><td>*Registration ID</td><td><input type="text" name="regid" required></td></tr>
   <tr><td>*Name</td><td><input type="text" name="name" required></td><td></td><!--<td>*Upload photo</td><td><input type="file" name="pic" required></td>--!></tr>
   <tr><td>*Gender</td><td>Male<input type="radio" name="sex" value="Male"></td><td>Female<input type="radio" name="sex" value="Female"></td></tr>
   <tr><td>*Date of Birth</td><td><input type="date" name="dob"></td></tr>
   <tr><td>*Father Status</td><td>Available<input type="radio" name="fs" value="Available"></td><td>Not Available<input type="radio" name="fs" value="Not Available"></td><td> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Late<input type="radio" name="fs" value="Late"></td></tr>
   <tr><td>*Father Name</td><td><input type="text" name="fname" required</td></tr>
   <tr><td>&nbspFather Occupation</td><td><input type="text" name="foccu" ></td></tr>
   <tr><td>*Mother Status</td><td>Available<input type="radio" name="ms" value="Available"></td><td>Not Available<input type="radio" name="ms" value="Not Available"></td><td> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbspLate<input type="radio" name="ms" value="Late"></td></tr>
   <tr><td>*Mother Name</td><td><input type="text" name="mname" required</td></tr>
   <tr><td>&nbspMother Occupation</td><td><input type="text" name="moccu" ></td></tr>
   <tr><td>&nbspGaurdian Name</td><td><input type="text" name="gname" </td></tr>
   <tr><td>&nbspGuardian Occupation</td><td><input type="text" name="goccu" ></td></tr>
   <tr><td>Conatct Numbers:</td><td>Mobile</td><td><input type="text" name="cnumm"></td></tr>
   <tr><td></td><td>Home</td><td><input type="text" name="cnumh"></td>
   <tr><td></td><td>Gaurdian</td><td><input type="text" name="cnumg"></td>
   <tr><td>*Address</td><td><textarea name="addr" rows="10" cols="30"></textarea></td></tr>
   <tr><td>*Standard</td><td><input list="std11" name="std1"><datalist id="std11"><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></datalist></td></tr>
   <tr><td>&nbspSchool</td><td><input type="text" name="schl"></td></tr>
   <tr><td>&nbspInterests</td><td><textarea rows="5" cols="20" name="intrs"></textarea></td></tr>
    <tr><td>*Physically Challenged</td><td>&nbsp &nbspYes<input type="radio" name="pc" value="Yes"></td><td>No<input type="radio" name="pc" value="No"></td></tr>
   <tr><td>If yes details</td><td><textarea rows="10" cols="30" name="pcdt"></textarea></td></tr>
   <tr><td>&nbspReason for Selection</td><td><input type="text" name="resec" ></td></tr>
    <tr><td>&nbspBlood group</td><td><input type="text" name="bldgp" ></td></tr>
    <tr><td>*Mentor name</td><td><input type="text" name="mename" ></td></tr>
     <tr><td>&nbspMentor contact</td><td><input type="text" name="menct" ></td></tr>
  <!-- <tr><td>Student Selection Form</td><td></td><td><input type="file" name="ss"></td></tr>
	<tr><td>Ward Profile Details</td><td>Year 1<td><input type="file" name="f1"></td></tr>
    <tr><td></td><td>Year 2</td><td><input type="file" name="f2"></td></tr>
    <tr><td></td><td>Year 3</td><td><input type="file" name="f3"></td></tr>
     <tr><td></td><td>Year 4</td><td><input type="file" name="f4"></td></tr>
     <tr><td></td><td>Year 5</td><td><input type="file" name="f5"></td></tr>
     <tr><td></td><td>Year 6</td><td><input type="file" name="f6"></td></tr> --!>
     <tr><td>*TIG Team</td><td><input type="text" name="tigt" required></td></tr>
    <tr><td></td><td><input type="submit" value="Upload details" name="studformsub"></td></tr>
</form>
</body>
</html>