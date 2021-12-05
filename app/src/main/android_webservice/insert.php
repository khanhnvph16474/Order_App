<?php
	require "dbCon.php";
	
	$hinhmon = (int)$_POST['hinhmonItem'];
	//$hinhmon = 0;
	$tenmon = $_POST['tenmonItem'];
	$giatien = (int)$_POST['giatienItem'];
	//$giatien = 1;

	$query = "INSERT INTO order_item values(null, '$hinhmon', '$tenmon', '$giatien', 0)";
	if (mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>