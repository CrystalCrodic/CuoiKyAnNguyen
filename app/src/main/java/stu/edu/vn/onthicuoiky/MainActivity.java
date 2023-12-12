package stu.edu.vn.onthicuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import stu.edu.vn.onthicuoiky.adapater.SinhVienAdapter;
import stu.edu.vn.onthicuoiky.db.DBHelper;
import stu.edu.vn.onthicuoiky.models.SinhVien;

public class MainActivity extends AppCompatActivity {

    public static DBHelper dbHelper;
    ListView lv;
    FloatingActionButton btnAdd;

    SinhVienAdapter adapter;

    ArrayList<SinhVien> dssv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this, "test1110.sqlite", null, 1);

        lv = findViewById(R.id.lv);
        btnAdd = findViewById(R.id.btnAdd);
        dssv = new ArrayList<>();
        adapter = new SinhVienAdapter(MainActivity.this, R.layout.view, dssv);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        registerForContextMenu(lv);

        HienThiDanhSach();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(myintent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == R.id.Xoa){
            SinhVien sv = dssv.get(info.position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xoá Sinh Viên Này");
            builder.setMessage("Bạn Muốn Xoá Sinh Viên Này Không");
            builder.setPositiveButton("Có", (dialog, which) -> {
                dbHelper.DeleteData(sv);
                HienThiDanhSach();
            });
            builder.setNegativeButton("Không", (dialog, which) -> {

            });

            builder.show();
        }

        if(item.getItemId() == R.id.Sua){
            SinhVien sv = dssv.get(info.position);
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("SINHVIEN", sv);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    public void HienThiDanhSach(){
        dssv.clear();
        Cursor cursor = dbHelper.GetData("SELECT * FROM SINHVIEN");
        while (cursor.moveToNext()){
            SinhVien sv = new SinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            dssv.add(sv);
        }
        adapter.notifyDataSetChanged();
    }
}