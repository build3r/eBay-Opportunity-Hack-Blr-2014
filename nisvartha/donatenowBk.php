<?php
// Merchant key here as provided by Payu
$MERCHANT_KEY = "JBZaLc";

// Merchant Salt as provided by Payu
$SALT = "GQs7yium";

// End point - change to https://secure.payu.in for LIVE mode
$PAYU_BASE_URL = "https://test.payu.in";

// Success URL
$surl = "http://nisvartha.azurewebsites.net/paymentsuccess.php";

// failure URL
$furl = "http://nisvartha.azurewebsites.net/paymenterror.php";

//Service provider
$service_provider = "payu_paisa";

// Product Info
$productinfo = "Nisvartha Donation";

$action = '';

$posted = array();
if(!empty($_POST)) {
    //print_r($_POST);
  foreach($_POST as $key => $value) {    
    $posted[$key] = $value; 
	
  }
}

$formError = 0;

if(empty($posted['txnid'])) {
  // Generate random transaction id
  $txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
} else {
  $txnid = $posted['txnid'];
}
$hash = '';
// Hash Sequence
$hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
if(empty($posted['hash']) && sizeof($posted) > 0) {
  if(
          empty($posted['key'])
          || empty($posted['txnid'])
          || empty($posted['amount'])
          || empty($posted['firstname'])
          || empty($posted['email'])
          || empty($posted['phone'])
          || empty($posted['productinfo'])
          || empty($posted['surl'])
          || empty($posted['furl'])
		  || empty($posted['service_provider'])
  ) {
    $formError = 1;
  } else {
    //$posted['productinfo'] = json_encode(json_decode('[{"name":"tutionfee","description":"","value":"500","isRequired":"false"},{"name":"developmentfee","description":"monthly tution fee","value":"1500","isRequired":"false"}]'));
	$hashVarsSeq = explode('|', $hashSequence);
    $hash_string = '';	
	foreach($hashVarsSeq as $hash_var) {
      $hash_string .= isset($posted[$hash_var]) ? $posted[$hash_var] : '';
      $hash_string .= '|';
    }

    $hash_string .= $SALT;


    $hash = strtolower(hash('sha512', $hash_string));
    $action = $PAYU_BASE_URL . '/_payment';
  }
} elseif(!empty($posted['hash'])) {
  $hash = $posted['hash'];
  $action = $PAYU_BASE_URL . '/_payment';
}
?>

<html>

<head>
    <meta charset="UTF-8">
    <title>Nisvartha Foundation - Donation</title>
    <link href="css/reset.css" rel="stylesheet" type="text/css" />
    <link href="css/Style_Master.css" rel="stylesheet" type="text/css" />
    <link href="css/Style_AllPage.css" rel="stylesheet" type="text/css" />
    <link href="css/Table.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="scripts/jquery/jquery.js"></script>
    <script type="text/javascript" src="scripts/localScript.js"></script>
    <script type="text/javascript" src="scripts/commons.js"></script>
	<script>
    var hash = '<?php echo $hash ?>';
    function submitPayuForm() {
      if(hash == '') {
        return;
      }
      var payuForm = document.forms.payuForm;
      payuForm.submit();
    }
  </script>
</head>

<body onload="submitPayuForm()">

    <div id="main_container" >

        <div id="header_container">

            <div id="top_header">
                <div id="dateTime">Sunday, 9-11-2014</div>
                <div id="welcomeGuest">
                    
                </div>
            </div>
            <div id="main_header">

                <div id="logo_placeholder">
                    <a href="index.php"><img src="images/nisvartha_logo.png" width="145" height="153" alt="Nisvartha Foundation" /></a>
                </div>
            </div>
        </div>
        <div id="pageHeader">


            <h1>

               Donation
            </h1>

        </div>

        
        <div id="content_Container">

            <div id="navigation_wrapper">

                <ul class="main_nav">
                    <li><a href='index.php'>Home</a></li>
                    <li><a href='programs.php'>Programs</a></li>
                    <li><a href='updates.php'>Updates</a></li>
                    <li><a href='members.php'>Members</a></li>
                    <li><a href='donatenow.php' class="selected">Donate Now</a></li>
					<li><a href='donorlist.php'>Donar List</a></li>
                    <li><a href='management.php'>Management</a></li>
                    <li><a href='awards.php'>Awards</a></li>
                    <li><a href='gallery.php'>Photogallary</a></li>
                    <li><a href='contact_us.php'>Contact Us / Forms </a></li>
                    <li><a href='index.php'>Students</a></li>
                </ul>
            </div>

            <div id="body_content">

               
                <h2 class="white"> Thank you for your support </h2>
              <?php if($formError) { ?>
								<span style="color:red">Please fill all mandatory fields.</span>
								<br/>
								<br/>
							<?php } ?> 
                <form action="<?php echo $action; ?>" method="post" name="payuForm" class="donate">
				<input type="hidden" name="key" value="<?php echo $MERCHANT_KEY ?>" />
				<input type="hidden" name="hash" value="<?php echo $hash ?>"/>
				<input type="hidden" name="txnid" value="<?php echo $txnid ?>" />
				<input type="hidden" name="surl" value="<?php echo $surl ?>" />
				<input type="hidden" name="furl" value="<?php echo $furl ?>" />
				<input type="hidden" name="service_provider" value="<?php echo $service_provider ?>" />
				<input type="hidden" name="productinfo" value="<?php echo $productinfo ?>" />
				
                <table width="657" border="0" cellspacing="0" cellpadding="0" class="donate">
                    <tr>
                        <td width="150" colspan="2">
                            <div class="mandatory">All fields are Mandatory </div>
                        </td>                      
                    </tr>
					<tr>
                        <td width="150" colspan="2">
                           
                        </td>
                       
                    </tr>
                    <tr>
                        <td width="150">
                            <strong>First Name:</strong>
                        </td>
                        <td>
                        <input name="firstname" id="firstname" value="<?php echo (empty($posted['firstname'])) ? '' : $posted['firstname']; ?>" size="34" maxlength="32"/>
						</td>
                    </tr>
                   <tr>
                        <td width="150">
                            <strong>Last Name:</strong>
                        </td>
                        <td>
						<input name="lastname" id="lastname" value="<?php echo (empty($posted['lastname'])) ? '' : $posted['lastname']; ?>" size="34" maxlength="32"/>
						</td>
                    </tr>
                    
                    <tr>
                        <td width="150">
                            <strong>Email ID:</strong>
                        </td>
                        <td>
							<input name="email" id="email" value="<?php echo (empty($posted['email'])) ? '' : $posted['email']; ?>" size="34" maxlength="64"/>
                       
					
					   </td>
                    </tr>

                    <tr>
                        <td width="150">
                            <strong>Mobile:</strong>
                        </td>
                        <td> 
							<input name="phone" value="<?php echo (empty($posted['phone'])) ? '' : $posted['phone']; ?>" maxlength="32" size="34"/>							
                        </td>
                    </tr>

                    <tr>
                        <td width="150">
                            <strong>Address:</strong>
                        </td>
                        <td>
							<input name="address1" value="<?php echo (empty($posted['address1'])) ? '' : $posted['address1']; ?>" maxlength="64" size="34"/>
                        </td>
                    </tr>

                    <tr>
                        <td width="150">
                            <strong>City:</strong>
                        </td>
                        <td>
							<input name="city" value="<?php echo (empty($posted['city'])) ? '' : $posted['city']; ?>" size="34" maxlength="32"/>
                        </td>
                    </tr>

                    <tr>
                        <td width="150">
                            <strong>State:</strong>
                        </td>
                        <td>
                            <select id="ddlstate" class="form-select required"><option selected="selected" value="">Please select</option><option value="107">Andaman &amp; Nicobar</option><option value="79">Andhra Pradesh</option><option value="80">Arunachal Pradesh</option><option value="81">Assam</option><option value="82">Bihar</option><option value="108">Chandigarh</option><option value="83">Chattisgarh</option><option value="109">Dadra and Nagar Haveli</option><option value="110">Daman &amp; Diu</option><option value="111">Delhi</option><option value="84">Goa</option><option value="85">Gujarat</option><option value="86">Haryana</option><option value="87">Himachal Pradesh</option><option value="88">Jammu &amp; Kashmir</option><option value="89">Jharkhand</option><option value="90">Karnataka</option><option value="91">Kerala</option><option value="92">Madhya Pradesh</option><option value="93">Maharashtra</option><option value="94">Manipur</option><option value="95">Meghalaya</option><option value="96">Mizoram</option><option value="97">Nagaland</option><option value="98">Orissa</option><option value="99">Punjab</option><option value="100">Rajasthan</option><option value="101">Sikkim</option><option value="102">Tamil Nadu</option><option value="103">Tripura</option><option value="105">Uttar Pradesh</option><option value="104">Uttarakhand</option><option value="106">West Bengal</option></select>
                        </td>
                    </tr>

                    <tr>
                        <td width="150">
                            <strong>Country:</strong>
                        </td>
                        <td>
                            <select id="ddlcountry" class="form-select required"><option value="124">Canada</option><option selected="selected" value="356">India</option><option value="840">United States</option><option value="840">Other</option></select>
                        </td>
                    </tr>

                    <tr>
                        <td width="150">
                            <strong>Pin Code:</strong>
                        </td>
                        <td>
							<input name="zipcode" value="<?php echo (empty($posted['zipcode'])) ? '' : $posted['zipcode']; ?>" size="34" maxlength="10" />
                        </td>
						
                    </tr>
					<tr>
                        <td width="150">
                            <strong>Amount:</strong>
                        </td>
                        <td>
							<input name="amount" value="<?php echo (empty($posted['amount'])) ? '' : $posted['amount'] ?>" size="34" maxlength="10" />
                        </td>
					
                    </tr>

                    <tr>
                        <td width="150">
                            
                        </td>
                        <td>
                            <input name="cancel" id="can" type="submit" value="Cancel" />
                            
							<?php if(!$hash) { ?>
									<input type="submit" value="Submit" />
							<?php } ?>
                        </td>
                    </tr>


                </table><br />

                <br />
                <br />
				</form>
            </div>



           
            <!--Body Content Ends-->

        </div>
        <!--Content Container Ends-->

        <div id="footer">
            <div id="top_footer_bg"></div>
            <div id="bottom_footer">
                <div id="copyright">Nisvartha Foundation supporting higher education since 2009.</div>
                <div id="footerLinks">
                    <a href=support_us.php>Support Us</a>
                    &nbsp;&nbsp;|&nbsp;&nbsp;<a href="mailto:nisvartha@gmail.com">Write to Us</a>
                </div>
            </div>
        </div>

    </div>

    <!--Main Container Ends-->

</body>

</html>