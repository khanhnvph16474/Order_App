<?php
	require "dbCon.php";
	
	//$hinhmon = 0;
	$tenQuan = $_POST['tenQuanItem'];
	$diaChi = $_POST['diaChiItem'];
	$khoangCach = $_POST['khoangCachItem'];
	//$giatien = 1;

	$query = "INSERT INTO quan_an values(null, 2131165317, '$tenQuan', '$diaChi', '$khoangCach')";
	if (mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>