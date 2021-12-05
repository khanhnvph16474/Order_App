<?php
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "app_order"; //nhớ thay đổi tên DB
	$conn = mysqli_connect($servername, $username, $password, $dbname);
 	mysqli_set_charset($conn,'UTF8');
?>