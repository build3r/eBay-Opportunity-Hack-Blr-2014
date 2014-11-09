<?php
//$servername = "localhost";
//$username = "root";
//$password = "";
//$dbname = "nisvarth";

$servername = "us-cdbr-azure-central-a.cloudapp.net";
$username = "bad28acd339170";
$password = "29beef36";
$dbname = "nisvarthaDB";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

?>