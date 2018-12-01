package com.example.chicken.chickentimer4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.chicken.chickentimer4.MainActivity.arrayAdapter2;
import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.msAdapter;
import static com.example.chicken.chickentimer4.MainActivity.msList;
import static com.example.chicken.chickentimer4.MainActivity.spinner2;


public class MsDialog extends Dialog {

    private TextView title;// 편의점 이름 내용
    private EditText m_name, m1, m2;
    private Button addBtn; // 등록버튼
    private ListView listView;
    private String csName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_dialog_layout);

        title = (TextView) findViewById(R.id.which);
        listView = (ListView) findViewById(R.id.menuList);
        listView.setAdapter(msAdapter);
        m_name = findViewById(R.id.menuName);
        m1 = findViewById(R.id.first);
        m2 = findViewById(R.id.second);
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() { // 등록하기
            @Override
            public void onClick(View v) {
                String menuN = m_name.getText().toString();

                if (c.get(csName).keySet().contains(menuN)) {
                    Toast.makeText(v.getContext(), "이미 존재하는 제품입니다.", Toast.LENGTH_SHORT);
                    return;
                }

                ArrayList<Integer> time = new ArrayList<>();
                if (menuN.length() == 0) {
                    Toast.makeText(v.getContext(), "메뉴명을 입력해주세요.", Toast.LENGTH_SHORT);
                    return;
                }
                if (m1.getText().toString().length() == 0) {
                    Toast.makeText(v.getContext(), "시간을 입력해주세요.", Toast.LENGTH_SHORT);
                    return;
                }
                time.add(Integer.parseInt(m1.getText().toString()));
                if (m2.getText().toString().length() > 0) { // 두번째 EditText에도 입력이 있을 때
                    time.add(Integer.parseInt(m2.getText().toString()));
                }
                c.get(csName).put(menuN, time);
                msList = new HashMap<>(c.get(csName)); // 누른 버튼에 편의점으로 다이어로그를 세팅한다.
//                msAdapter.notifyDataSetChanged();
                msAdapter = new MsAdapter(getContext(), R.layout.ms_dialog_layout, msList, csName);
                listView.setAdapter(msAdapter);

                arrayAdapter2 = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item, c.get(csName).keySet().toArray());
                spinner2.setAdapter(arrayAdapter2);
            }
        });

        title.setText(csName + " 편의점 메뉴 관리");
    }

    public MsDialog(@NonNull Context context, MsAdapter msAdapter, String csName) {
        super(context);
        //this.title = title;
        this.csName = csName;
    }
}
