<?php

include 'database.php';
//include 'sms.php';


if(true)
{

//$data=json_decode($_POST['data']);
//$msg=$_POST['msg'];
$fname= $_POST['fname'];
$lname= $_POST['lname'];
$email= $_POST['email'];
$mobile= $_POST['mobile'];
$address= $_POST['address'];
$city=$_POST['city'];
$state=$_POST['state'];
$country=$_POST['country'];
$pincode=$_POST['pincode'];
$amount=$_POST['amount'];
$date = date('m/d/Y h:i:s a', time());
$sql=" INSERT INTO donorDetails VALUES('','$fname','$lname','$email',$mobile,'$address','$city','$state','$country',$pincode,'$date')";


if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$id=$conn->insert_id;

$sql2= "INSERT INTO donorPaymentStatus(donorID,amount) VALUES($id,$amount)";
if ($conn->query($sql2) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql2 . "<br>" . $conn->error;
}

}
?>