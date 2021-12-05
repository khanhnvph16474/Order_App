<?php
	require "dbCon.php";
	//Check connection
	// if ($conn->connect_error) {
	// 	die("Connection failed: " . $conn->connect_error);
	// } 
	// echo "Connected successfully";
	$query = "SELECT * from quan_an";
	$data = mysqli_query($conn, $query);
	

	class QuanAn
	{
		
		function QuanAn($id, $hinhquan, $tenquan, $diachi, $khoangcach)
		{
			$this->ID = $id;
			$this->HinhQuan = $hinhquan;
			$this->TenQuan = $tenquan;
			$this->DiaChi = $diachi;
			$this->KhoangCach = $khoangcach;
		}
	}
	//2. tạo mảng
	$mangQuanAn = array();
	//3. thêm phần tử vào mảng
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangQuanAn, new QuanAn($row['id'], $row['hinhquan'], $row['tenquan'], $row['diachi'], $row['khoangcach']));
	}
	// array_push($mangSV, new SinhVien("1", "conkak1", "6969"));
	// array_push($mangSV, new SinhVien("2", "conkak2", "7969"));
	//4. chuyển dịnh mạng của mảng thành Json
	echo json_encode($mangQuanAn);
?>