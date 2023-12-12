package stu.edu.vn.onthicuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import stu.edu.vn.onthicuoiky.adapater.SinhVienAdapter;
import stu.edu.vn.onthicuoiky.models.SinhVien;

public class MainActivity2 extends AppCompatActivity {

    EditText edtTen;

    RadioButton rdbNam, rdbNu;

    Button btnSave;
    private int chon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtTen = findViewById(R.id.edtTenSV);
        rdbNam = findViewById(R.id.rdbNam);
        rdbNu = findViewById(R.id.rdbNu);
        btnSave = findViewById(R.id.btnSave);

        rdbNam.setChecked(true);

        Intent intent = getIntent();
        if(intent.hasExtra("SINHVIEN")){
            chon = 1;
            SinhVien sv = (SinhVien) intent.getSerializableExtra("SINHVIEN");

            edtTen.setText(sv.getTenSV());

            if(sv.getGioitinh() == "NAM"){
                rdbNam.setChecked(true);
            }else{
                rdbNu.setChecked(true);
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sex = "NỮ";
                if(rdbNam.isChecked()){
                    Sex = "NAM";
                }
                SinhVien sv = new SinhVien(1, edtTen.getText().toString(), Sex);

                if(chon == -1){
                    MainActivity.dbHelper.InsertData(sv);
                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                }
                if(chon == 1){
                    SinhVien getSvIntent = (SinhVien) intent.getSerializableExtra("SINHVIEN");
                    sv.setMaSV(getSvIntent.getMaSV());
                    MainActivity.dbHelper.UpdateData(sv);
                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                }
            }
        });
    }
}