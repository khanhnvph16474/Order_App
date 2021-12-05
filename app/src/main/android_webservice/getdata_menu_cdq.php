<?php
	require "dbCon.php";
	//Check connection
	// if ($conn->connect_error) {
	// 	die("Connection failed: " . $conn->connect_error);
	// } 
	// echo "Connected successfully";
	$query = "SELECT * from bun_cdq";
	$data = mysqli_query($conn, $query);
	

	class QuanAn
	{
		
		function QuanAn($id, $hinhmon, $tenmon, $mota, $giatien)
		{
			$this->ID = $id;
			$this->HinhMon = $hinhmon;
			$this->TenMon = $tenmon;
			$this->MoTa = $mota;
			$this->GiaTien = $giatien;
		}
	}
	//2. tạo mảng
	$mangQuanAn = array();
	//3. thêm phần tử vào mảng
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangQuanAn, new QuanAn($row['id'], $row['hinhmon'], $row['tenmon'], $row['mota'], $row['giatien']));
	}
	echo json_encode($mangQuanAn);
?>