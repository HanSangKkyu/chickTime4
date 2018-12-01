package com.example.chicken.chickentimer4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.chicken.chickentimer4.MainActivity.arrayAdapter1;
import static com.example.chicken.chickentimer4.MainActivity.arrayAdapter2;
import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.cssList;
import static com.example.chicken.chickentimer4.MainActivity.gAdapter;
import static com.example.chicken.chickentimer4.MainActivity.spinner1;
import static com.example.chicken.chickentimer4.MainActivity.spinner2;

public class GDialog extends Dialog {
    private EditText ed;
    private Button addCS, closeBt;
    //  private GAdapter gAdapter;
    private GridView gView;
    private OnDismissListener onDismissListener = null;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.css_dialog_layout);

        ed = (EditText) findViewById(R.id.newCS);
        addCS = (Button) findViewById(R.id.addCS);
        gView = (GridView) findViewById(R.id.gridView);
        closeBt = (Button) findViewById(R.id.cssClose);

        gView.setAdapter(gAdapter);

        addCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = ed.getText().toString();


                if (c.keySet().contains(val)) {
                    Toast.makeText(view.getContext(), "이미 존재하는 편의점입니다.", Toast.LENGTH_SHORT);
                    return;
                }

                //여기서 편의점 등록 하게 하면됨.

                c.put(val, new HashMap<String, ArrayList<Integer>>());
                cssList = new ArrayList<>(c.keySet());
                Log.i("cssList", cssList + "");
                gAdapter.notifyDataSetChanged();

                // 스피너 업데이트
                arrayAdapter1 = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, c.keySet().toArray());
                spinner1.setAdapter(arrayAdapter1);
            }
        });
    }

    public GDialog(Context context, GAdapter gAdapter) {
        super(context);
        // this.gAdapter = gAdapter;
    }

}




