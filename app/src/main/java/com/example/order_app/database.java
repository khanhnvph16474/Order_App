package com.example.order_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context, "app_order", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableBun=
                "create table Bun (" +
                        "id integer PRIMARY KEY AUTOINCREMENT , " +
                        "hinhmon int NOT NULL, " +
                        "tenmon varchar(255) NOT NULL," +
                        "mota varchar(255) NOT NULL," +
                        "giatien int NOT NULL)";
        sqLiteDatabase.execSQL(createTableBun);

        createTableBun = "INSERT INTO Bun VALUES(NULL, 2131165346, 'Bún bò tái bắp', 'Chả cua,bắp bò tái', 30000)";
        sqLiteDatabase.execSQL(createTableBun);
        createTableBun = "INSERT INTO Bun VALUES(NULL, 2131165347, '\\r\\nBún bò bắp gân', 'Chả cua, bắp bò chín, gân', 30000)";
        sqLiteDatabase.execSQL(createTableBun);
        createTableBun = "INSERT INTO Bun VALUES(NULL, 2131165348, 'Bún bò cung đình', 'Chả cua,bắp bò chín,gân,giò heo,đuôi bò', 45000)";
        sqLiteDatabase.execSQL(createTableBun);
        createTableBun = "INSERT INTO Bun VALUES(NULL, 2131165346, '\\r\\nBún bò giò heo', 'Chả cua, bắp bò chín, gân, giò heo.', 35000)";
        sqLiteDatabase.execSQL(createTableBun);
        createTableBun = "INSERT INTO Bun VALUES(NULL, 2131165346, 'Tô em bé', 'Bò tái bằm', 20000)";
        sqLiteDatabase.execSQL(createTableBun);
        createTableBun = "INSERT INTO Bun VALUES(NULL, 2131165347, 'bún nước lọc', 'chỉ có nước', 20000)";
        sqLiteDatabase.execSQL(createTableBun);

        String createTableComTam=
                "create table ComTam (" +
                        "id integer PRIMARY KEY AUTOINCREMENT, " +
                        "hinhmon int NOT NULL, " +
                        "tenmon varchar(255) NOT NULL," +
                        "mota varchar(255) NOT NULL," +
                        "giatien int NOT NULL)";
        sqLiteDatabase.execSQL(createTableComTam);


        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165342, '\\r\\nCombo Lộc', 'Giá gốc: 65.000 Bao gồm: Cơm Sườn + Chả hấp + Canh rong biển + Coca tươi + Khăn lạnh', 55000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165343, 'Combo Phúc', 'Giá gốc: 68.000 Bao gồm: Cơm Ba Rọi + Trứng Ốp la + Canh Rong Biển + Coca tươi + Khăn lạnh', 55000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165344, 'Cơm tấm sườn non', '', 55000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165345, 'Cơm tấm đùi gà nướng ngũ vị', '', 35000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165345, 'Cơm tấm sườn', '', 29000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165345, 'Cơm tấm ba rọi', '', 29000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165345, 'Cơm thêm', '', 4000)";
        sqLiteDatabase.execSQL(createTableComTam);
        createTableComTam = "INSERT INTO ComTam VALUES(NULL, 2131165345, 'Đùi gà quay mắm', '', 35000)";
        sqLiteDatabase.execSQL(createTableComTam);


        String createTableOderItem=
                "create table OrderItem (" +
                        "id integer PRIMARY KEY AUTOINCREMENT, " +
                        "hinhmon int NOT NULL, " +
                        "tenmon varchar(255) NOT NULL," +
                        "giatien int NOT NULL," +
                        "soluong int NOT NULL)";
        sqLiteDatabase.execSQL(createTableOderItem);

        createTableOderItem= "INSERT INTO OrderItem VALUES(NULL, 2131165343, 'Combo Phúc', 55000, 0)";
        sqLiteDatabase.execSQL(createTableOderItem);
        createTableOderItem= "INSERT INTO OrderItem VALUES(NULL, 2131165344, 'Cơm tấm sườn non', 55000, 0)";
        sqLiteDatabase.execSQL(createTableOderItem);
        createTableOderItem= "INSERT INTO OrderItem VALUES(NULL, 2131165345, 'uius', 30000, 0)";
        sqLiteDatabase.execSQL(createTableOderItem);
        createTableOderItem= "INSERT INTO OrderItem VALUES(NULL, 2131165344, 'Cơm tấm sườn non', 55000, 0)";
        sqLiteDatabase.execSQL(createTableOderItem);


        String createTableQuanAn =
                "create table QuanAn (" +
                        "id integer PRIMARY KEY AUTOINCREMENT, " +
                        "hinhquan integer NOT NULL, " +
                        "tenquan varchar(255) NOT NULL," +
                        "diachi varchar(255) NOT NULL," +
                        "khoangcach varchar(255) NOT NULL)";
        sqLiteDatabase.execSQL(createTableQuanAn);

        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165283, 'Cơm Tấm Phúc Lộc Thọ', '31-33 Lê Văn Việt, P, Thủ Đức, Hồ Chí Minh', '2.1')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165293, 'Quán Cơm Kim Yến', '63 Song Hành, Thảo Điền, Quận 2, Hồ Chí Minh', '1')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165280, 'Cung Đình Quán', '236 Lê Thánh Tôn, Phường Bến Thành, Quận 1, Hồ Chí Minh', '10')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165281, 'Bún riêu Gánh', '4 Đường Phan Bội Châu, Phường Bến Thành, Quận 1, Hồ Chí Minh', '20')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165338, 'Bánh Huế Thu Thảo', '36/2A Hoàng Xuân Nhị, P. Phú Trung, Tân Phú, TP. HCM', '5')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165292, 'Funfarm', '25/1 Giải Phóng, P. 4, Tân Bình, TP. HCM', '6.9')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165341, 'Viva Star Coffee', '45 Hoàng Hoa Thám, P. 13, Tân Bình, TP. HCM', '4.5')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165317, 'Lava Coffee', '61 Quang Trung, P. 10, Gò Vấp, TP. HCM', '3.7')";
        sqLiteDatabase.execSQL(createTableQuanAn);
        createTableQuanAn = "INSERT INTO QuanAn VALUES(NULL, 2131165282, 'Chill Tea - Thống Nhất', '63 Thống Nhất, P. Bình Thọ, Thủ Đức, TP. HCM', '3')";
        sqLiteDatabase.execSQL(createTableQuanAn);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
