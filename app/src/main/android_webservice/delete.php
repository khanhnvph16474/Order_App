<?php
	require "dbCon.php";
	$idmon = $_POST['idMon'];
	$query = "DELETE FROM order_item WHERE id = '$idmon'";
	if(mysqli_query($conn, $query)) {
		echo "success";
	}
	else echo "error";
?>