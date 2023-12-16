package stu.edu.vn.onthicuoiky.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import stu.edu.vn.onthicuoiky.models.SinhVien;

public class DBHelper extends SQLiteOpenHelper {
    // Lệnh tạo Bảng
    private final String TABLE = "CREATE TABLE IF NOT EXISTS SINHVIEN(masv INTEGER PRIMARY KEY AUTOINCREMENT, tensv VARCHAR(50), gioitinh VARCHAR(5))";

    // Lệnh Xoá Bản
    private final String DROP = "DROP TABLE IF EXISTS SINHVIEN";

    // Contructor (MainActivity.this, "tên database.sqlite", null, 1)
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    // Gọi hàm tạo table ở đây
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
    }
    
    // Gọi Hàm xoá Table ở đây
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
    }

    // Hàm Lấy data (Only dùng cho Select * From ...)
    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    // Không Dụng Tới
    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    // Làm Hàm Thêm Tuỳ Theo Table
    public void InsertData(SinhVien sv){
        SQLiteDatabase db = getWritableDatabase(); // Cho Phép Sửa Table
        String sql = "INSERT INTO SINHVIEN VALUES(null, ?, ?)"; // Giống PHP
        SQLiteStatement pstm = db.compileStatement(sql); // Chạy lệnh SQL trên
        pstm.clearBindings(); // Không cần ghi
        pstm.bindString(1, sv.getTenSV()); // ? thứ nhất bind vào
        pstm.bindString(2, sv.getGioitinh()); // ? thứ 2 bind vào
        pstm.executeInsert(); // Thực Thi SQL
    }

    // Như Insert
    public void DeleteData(SinhVien sv){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM SINHVIEN WHERE masv = ?";
        SQLiteStatement pstm = db.compileStatement(sql);
        pstm.clearBindings();
        pstm.bindLong(1, sv.getMaSV());
        pstm.executeUpdateDelete();
    }

    public void UpdateData(SinhVien sv){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE SINHVIEN SET tensv = ?, gioitinh = ? WHERE masv = ?";
        SQLiteStatement pstm = db.compileStatement(sql);
        pstm.clearBindings();

        pstm.bindString(1, sv.getTenSV());
        pstm.bindString(2, sv.getGioitinh());
        pstm.bindLong(3, sv.getMaSV());
        pstm.executeUpdateDelete();
    }
}
