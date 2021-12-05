<?php
	require "dbCon.php";
	
	//$hinhmon = 0;
	$tenMon = $_POST['tenMonItem'];
	$moTa = $_POST['moTaItem'];
	$giaTien = (int) $_POST['giaTienItem'];
	//$giatien = 1;

	$query = "INSERT INTO com_tam_plt values(null, 2131165345, '$tenMon', '$moTa', '$giaTien')";
	if (mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>