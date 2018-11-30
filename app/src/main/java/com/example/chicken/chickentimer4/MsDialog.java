package com.example.chicken.chickentimer4;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MsDialog extends Dialog {

    private TextView title;// 편의점 이름 내용
    private EditText m_name,m1,m2;
    private View.OnClickListener checkBtListener,cloBtnListner;//등록,닫기 버튼 리스너
    private MsAdapter msAdapter; //리스트뷰 어뎁터
    private Button regBtn,cloBtn;//등록, 닫기버튼
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ms_dialog_layout);

        cloBtn = (Button)findViewById(R.id.msClose);
        regBtn = (Button)findViewById(R.id.addBtn);
        title = (TextView)findViewById(R.id.which);
        listView = (ListView)findViewById(R.id.menuList);

        listView.setAdapter(msAdapter);
        regBtn.setOnClickListener(checkBtListener);
        cloBtn.setOnClickListener(cloBtnListner);
    }

    public MsDialog(@NonNull Context context, MsAdapter msAdapter, View.OnClickListener checkBtListener,View.OnClickListener cloBtnListner) {
        super(context);
        //this.title = title;
        this.msAdapter = msAdapter;
        this.checkBtListener = checkBtListener;
        this.cloBtnListner = cloBtnListner;
    }
}
