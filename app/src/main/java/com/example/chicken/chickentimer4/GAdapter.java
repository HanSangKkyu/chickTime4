package com.example.chicken.chickentimer4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.cssList;
import static com.example.chicken.chickentimer4.MainActivity.gDialog;
import static com.example.chicken.chickentimer4.MainActivity.msAdapter;
import static com.example.chicken.chickentimer4.MainActivity.msDialog;
import static com.example.chicken.chickentimer4.MainActivity.msList;


public class GAdapter extends BaseAdapter {
    //List<String> csString; // 편의점 이름
    Button delCssBtn;
    Context context;
    TextView csname; // 편의점 이름을 출력하는 텍스트 뷰
    ImageView csBack;

    public GAdapter(@NonNull Context context) {
        //this.csString = objects;
        this.context = context;
        Log.i("test_", "GAdapter");
    }

    @Override
    public int getCount() {
        return cssList.size();
    }

    @Override
    public Object getItem(int position) {
        return cssList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.css_row, null);
        }

        csname = (TextView) v.findViewById(R.id.csname);
        csname.setText(cssList.get(position)); // 편의점 이름 성절
        csBack = (ImageView) v.findViewById(R.id.csBack);
        csBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msList = new ArrayList<>(c.get(cssList.get(position)).keySet()); // 누른 버튼에 편의점으로 다이어로그를 세팅한다.
                msAdapter = new MsAdapter(v.getContext(), R.layout.ms_dialog_layout, msList, cssList.get(position));

                msDialog = new MsDialog(v.getContext(), msAdapter, cssList.get(position));
                msDialog.show(); // 다음 다이얼로그 띄우기

                gDialog.dismiss(); // 현재 다이얼로그 닫기
            }
        });
        delCssBtn = v.findViewById(R.id.delCSBtn);
        delCssBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = cssList.get(position);
                Log.i("deleteStore", str);
                cssList.remove(position);
                notifyDataSetChanged();
                //실제 데이터 부분에도 삭제 해주기, 중복되는 부분 있으면 안된다.
                c.remove(str);
            }
        });
        return v;

    }


}
