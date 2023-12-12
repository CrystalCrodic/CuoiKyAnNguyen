package stu.edu.vn.onthicuoiky.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import stu.edu.vn.onthicuoiky.models.SinhVien;

public class DBHelper extends SQLiteOpenHelper {
    private final String TABLE = "CREATE TABLE IF NOT EXISTS SINHVIEN(masv INTEGER PRIMARY KEY AUTOINCREMENT, tensv VARCHAR(50), gioitinh VARCHAR(5))";

    private final String DROP = "DROP TABLE IF EXISTS SINHVIEN";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void InsertData(SinhVien sv){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO SINHVIEN VALUES(null, ?, ?)";
        SQLiteStatement pstm = db.compileStatement(sql);
        pstm.clearBindings();
        pstm.bindString(1, sv.getTenSV());
        pstm.bindString(2, sv.getGioitinh());
        pstm.executeInsert();
    }

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
