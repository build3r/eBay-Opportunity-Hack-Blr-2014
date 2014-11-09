<?php
include 'header.php';
include 'database.php';

$sql = "SELECT * FROM student,mentor where student.mentorID=mentor.mentorID ";
$result = $conn->query($sql);

if ($result->num_rows > 0) {  ?>

<body>

    <div id="main_container">
        <div id="header_container">
            <div id="top_header">
                <div id="dateTime">Sunday, 9-11-2014</div>
            </div>
            <div id="main_header">
                <div id="logo_placeholder">
                    <a href="index.php"><img src="images/nisvartha_logo.png" width="265" height="153" alt="Nisvartha Foundation" /></a>
                </div>
            </div>
        </div>
        <div id="pageHeader">
            <h1>Student Details</h1>
        </div>
        
		<div id="content_Container" style="width: 984px;">
        <div id="body_content" class="noNavigation">
		    <div id="top-bar" class="">
		
						<div class="ns right">
						
							<div ng-include="menuFragment" id="navbar" class="ng-scope">
							
							<ul class="ng-scope">
								<li><a href="/index.php">Home</a></li>
								<li><a href="/index.php">Student Lists</a></li>
								<li><a href="/donatenow.php">Donate Now</a></li>
								<li><a href="/donorlist.php">Donor List</a></li>
							</ul>
							
							</div>
							
						</div>
						
			 </div>
			 
			<div id="ng-view-box" class="ng-scope" ng-view="">
			<h2 class="page-title ng-scope">
				<span class="ng-scope">Students List</span>
			</h2>
			<div class="slist-search ng-scope">
			
						<div class="checkbox" style="float:left; width:100px;">

						<label>
						<input type="checkbox" value="" id="selectall">
						Select All
						</label>
						</div>
						<input id="send_msg" type="button" class="btn btn-default" value="Send Message" />
			</div>

            <table id="myTable" class="standard listview ng-scope">
                <thead style="background: #7B68EE">
                    <tr>
                        <th>SN</th>
                        <th>Name</th>
                        <th>Mobile</th>
                        <th>Mentor Name</th>
                    </tr>
                </thead>

                <tbody>
                    <?php while($row = $result->fetch_assoc())
                    {
                    echo "
                    <tr id='".$row["studentID"]."'>
                        ";

                        echo "
                        <td>
                            ".$row["studentID"];
                            echo"
                        <td>
                            ".  $row["name"];
                            echo"
                        <td>
                            ". $row["mobile"];
                            echo"
                        <td>
                            " .$row["mname"];
                            echo"
                    </tr>";
                    }
                    ?>
                </tbody>
            
			</table>

            
			</div>
        </div>
		</div>
        <?php include 'footer.php';?>

    </div>
	
</body>


<?php
} else {
    echo "0 results";
}
$conn->close();
?>

<script>
    $(document).ready(function () {
        $('#myTable').dataTable();
    });
</script>

<script src="table.js"></script>

</html>