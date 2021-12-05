<?php
	require "dbCon.php";
	
	//$hinhmon = 0;
	$tenMon = $_POST['tenMonItem'];
	$moTa = $_POST['moTaItem'];
	$giaTien = (int)$_POST['giaTienItem'];
	//$giatien = 1;

	$query = "INSERT INTO bun_cdq values(null, 2131165347, '$tenMon', '$moTa', '$giaTien')";
	if (mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>