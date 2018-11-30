package com.example.chicken.chickentimer4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.msDialog;


public class MsAdapter extends ArrayAdapter<String> {
    List<String> menuName;
    Context context;
    Button delMenuBtn;
    String csName;

    public MsAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, String selectedCs) {
        super(context, resource, objects);
        this.menuName = objects;
        this.context = context;
        this.csName = selectedCs;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.ms_row, null);
        }

        TextView Fname = v.findViewById(R.id.Fname);
        TextView first = v.findViewById(R.id.first);
        TextView second = v.findViewById(R.id.second);

        Fname.setText(menuName.get(position));
        Log.i("cscs", csName + "");
        Log.i("cscs", menuName + "");
        first.setText(c.get(csName).get(menuName.get(position)).get(0).toString());
        second.setText(c.get(csName).get(menuName.get(position)).get(1).toString());

        Button delMenuBtn = v.findViewById(R.id.delMenuBtn);
        delMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 제품 삭제 시키기

            }
        });

        return v;


    }

}
