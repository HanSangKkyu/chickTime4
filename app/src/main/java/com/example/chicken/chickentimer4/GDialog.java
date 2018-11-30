package com.example.chicken.chickentimer4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class GDialog extends Dialog {
    private EditText ed;
    private Button addCS,closeBt;
    private GAdapter gAdapter;
    private GridView gView;
    private View.OnClickListener checkBtListener,closeCheckListener;//버튼 리스너

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.css_dialog_layout);

        ed = (EditText)findViewById(R.id.newCS);
        addCS = (Button)findViewById(R.id.addCS);
        gView = (GridView)findViewById(R.id.gridView);
        closeBt = (Button)findViewById(R.id.cssClose);

        gView.setAdapter(gAdapter);

        addCS.setOnClickListener(checkBtListener);
        closeBt.setOnClickListener(closeCheckListener);
        addCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = ed.getText().toString();
                Log.i("test_", val+"");

                //여기서 편의점 등록 하게 하면됨.
            }
        });
    }

    public GDialog(Context context, GAdapter gAdapter, View.OnClickListener checkBtListener, View.OnClickListener closeCheckListener) {
        super(context);
        this.gAdapter = gAdapter;
        this.checkBtListener = checkBtListener;
        this.closeCheckListener = closeCheckListener;
    }

}




