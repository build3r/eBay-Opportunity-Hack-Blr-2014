<?php
session_start();
?>
<html>
<head>
<title>VOLUNTEER FORM</title>
<h4>VOLUNTEER FORM</h4>
</head>
<body background="b1.jpg"><font face="calibri">
<p align="right"><font size="5"><?php echo($_SESSION['un']);?></font></p>
<p align="right"><a href="home.php">Home</a></p>
<form action="volunteerformcode.php" method="post">
<table>
<tr><td>*Year of Joining</td><td><input type="text" name="yojv" required></td></tr>
<tr><td>*Registration ID</td><td><input type="text" name="ridv" required></td><!--<td>*Upload photo</td><td><input type="file" name="picv" required></td>--!></tr>
<tr><td>*Name</td><td><input type="text" name="namev" required></td></tr>
<tr><td>*Contact Number</td><td><input type="text" name="cnumv" required></td></tr>
 <tr><td>*Gender</td><td>Male<input name="sexv" type="radio" value="Male"></td><td>Female<input name="sexv" type="radio" value="Female"></td></tr>
<tr><td>*EmailID</td><td><input type="text" name="eidv" required></td></tr>
<tr><td>*Role</td><td>Mentor<input type="radio" name="rolev" value="Mentor"></td><td>Supporter<input type="radio" name="rolev" value="Supporter"></td><td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbspContributer<input type="radio" name="rolev" value="Contributer"></td></tr>
<tr><td>*Status</td><td>Active<input type="radio" name="statv" value="Active"></td><td>Inactive<input type="radio" name="statv" value="Inactive"></td></tr>
<tr><td>&nbspOccupation</td><td><input type="text" name="occv"></td></tr>
<tr><td>*Date of Birth</td><td><input type="date" name="dobv"></td></tr>
<tr><td>&nbspBlood group</td><td><input type="text" name="bldgpv" ></td></tr>
<tr><td>&nbspNative</td><td><input type="text" name="natv" ></td></tr>
<tr><td>*Permanent Address</td><td><textarea rows="5" cols="30" name="paddv"></textarea></td></tr>
<tr><td>&nbspCurrent Address</td><td><textarea rows="5" cols="30" name="caddv"></textarea></td></tr>
<tr><td>*Languages Known</td><td><textarea rows="5" cols="30" name="langv"></textarea></td></tr>
 <tr><td>*TIG Team</td><td><input type="text" name="tigtv" required></td></tr>
<tr><td>&nbspInterests</td><td><textarea rows="5" cols="30" name="intrsv"></textarea></td></tr>
<tr><td>&nbspReference</td><td><input type="text" name="refv" ></td></tr>
<tr><td>&nbspWard's Name</td><td><input type="text" name="wrdv" ></td></tr>
<tr><td>&nbspWard's Registration ID</td><td><input type="text" name="wrgid" ></td></tr>
<tr><td>&nbspWard's Contact Number</td><td><input type="text" name="wcnumv" ></td></tr>
<tr><td>&nbspCurrent Sessions</td><td><textarea rows="5" cols="20" name="cursv"></textarea></td></tr>
<tr><td>&nbspExperienced Sessions</td><td><textarea rows="5" cols="20" name="expsv"></textarea></td></tr>
<tr><td></td><td><input type="submit" value="Upload details" name="volformsub"></td></tr>

</table></body></html>