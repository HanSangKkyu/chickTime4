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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static com.example.chicken.chickentimer4.MainActivity.arrayAdapter1;
import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.cssList;
import static com.example.chicken.chickentimer4.MainActivity.gDialog;
import static com.example.chicken.chickentimer4.MainActivity.msAdapter;
import static com.example.chicken.chickentimer4.MainActivity.msDialog;
import static com.example.chicken.chickentimer4.MainActivity.msList;
import static com.example.chicken.chickentimer4.MainActivity.queue;
import static com.example.chicken.chickentimer4.MainActivity.registedList;
import static com.example.chicken.chickentimer4.MainActivity.spinner1;


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
                msList = new HashMap<>(c.get(cssList.get(position))); // 누른 버튼에 편의점으로 다이어로그를 세팅한다.
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


                for (int i = 0; i < registedList.size(); i++) {
                    if (registedList.get(i).getCompany() == str) {
                        Toast.makeText(context, "타이머로 등록된 편의점은 삭제할 수 없습니다.", Toast.LENGTH_SHORT);
                        return;
                    }
                }
                for (int i = 0; i < queue.size(); i++) {
                    if (queue.get(i).getCompany() == str) {
                        Toast.makeText(context, "큐에 등록된 편의점은 삭제할 수 없습니다.", Toast.LENGTH_SHORT);
                        return;
                    }
                }


                Log.i("deleteStore", str);
                cssList.remove(position);
                notifyDataSetChanged();
                //실제 데이터 부분에도 삭제 해주기, 중복되는 부분 있으면 안된다.
                c.remove(str);


                // 스피너 업데이트
                arrayAdapter1 = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item, c.keySet().toArray());
                spinner1.setAdapter(arrayAdapter1);
            }
        });
        return v;

    }


}
