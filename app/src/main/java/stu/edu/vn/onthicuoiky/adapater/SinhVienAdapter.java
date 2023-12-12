package stu.edu.vn.onthicuoiky.adapater;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import stu.edu.vn.onthicuoiky.R;
import stu.edu.vn.onthicuoiky.models.SinhVien;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Activity activity;
    int layout;
    ArrayList<SinhVien> dssv;

    public SinhVienAdapter(Activity activity, int layout, ArrayList<SinhVien> dssv) {
        super(activity, layout, dssv);
        this.activity = activity;
        this.layout = layout;
        this.dssv = dssv;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layout, null);

        TextView txtMaSv, txtTenSv, txtGioTinh;
        txtMaSv = convertView.findViewById(R.id.txtMa);
        txtTenSv = convertView.findViewById(R.id.txtTen);
        txtGioTinh = convertView.findViewById(R.id.txtGioiTinh);
        SinhVien sv = dssv.get(position);
        txtMaSv.setText(sv.getMaSV()+"");
        txtTenSv.setText(sv.getTenSV());
        txtGioTinh.setText(sv.getGioitinh());
        return convertView;
    }
}
