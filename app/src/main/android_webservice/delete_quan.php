<?php
	require "dbCon.php";
	$idquan = $_POST['idQuan'];
	$query = "DELETE FROM quan_an WHERE id = '$idquan'";
	if(mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>