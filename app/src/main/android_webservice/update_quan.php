<?php
	require "dbCon.php";
	$idQuan = $_POST['idQuan'];
	//$hinhmon = 0;
	$tenQuan = $_POST['tenQuanItem'];
	$diaChi = $_POST['diaChiItem'];
	$khoangCach = $_POST['khoangCachItem'];
	//$giatien = 1;

	$query = "UPDATE quan_an SET tenquan = '$tenQuan', diachi = '$diaChi', khoangcach = '$khoangCach' WHERE id = '$idQuan'";
	if (mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>