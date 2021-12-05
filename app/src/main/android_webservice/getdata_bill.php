<?php
	require "dbCon.php";
	//Check connection
	// if ($conn->connect_error) {
	// 	die("Connection failed: " . $conn->connect_error);
	// } 
	// echo "Connected successfully";
	$query = "SELECT * from order_item";
	$data = mysqli_query($conn, $query);
	

	class MonAn
	{
		
		function MonAn($id, $hinhmon, $tenmon, $giatien, $soluong)
		{
			$this->ID = $id;
			$this->HinhMon = $hinhmon;
			$this->TenMon = $tenmon;
			$this->GiaTien = $giatien;
			$this->SoLuong = $soluong;
		}
	}
	//2. tạo mảng
	$mangMonAn = array();
	//3. thêm phần tử vào mảng
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangMonAn, new MonAn($row['id'], $row['hinhmon'], $row['tenmon'], $row['giatien'], $row['soluong']));
	}
	// array_push($mangSV, new SinhVien("1", "conkak1", "6969"));
	// array_push($mangSV, new SinhVien("2", "conkak2", "7969"));
	//4. chuyển dịnh mạng của mảng thành Json
	echo json_encode($mangMonAn);
?>