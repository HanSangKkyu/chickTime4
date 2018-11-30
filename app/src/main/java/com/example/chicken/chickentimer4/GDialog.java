package com.example.chicken.chickentimer4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.chicken.chickentimer4.MainActivity.arrayAdapter1;
import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.cssList;
import static com.example.chicken.chickentimer4.MainActivity.gAdapter;

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
                //여기서 편의점 등록 하게 하면됨.

                c.put(val, new HashMap<String, ArrayList<Integer>>());
                cssList = new ArrayList<>(c.keySet());
                Log.i("cssList", cssList + "");
                gAdapter.notifyDataSetChanged();
                arrayAdapter1.notifyDataSetChanged();
            }
        });
    }

    public GDialog(Context context, GAdapter gAdapter) {
        super(context);
        // this.gAdapter = gAdapter;
    }

}




