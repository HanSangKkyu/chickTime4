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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.chicken.chickentimer4.MainActivity.arrayAdapter2;
import static com.example.chicken.chickentimer4.MainActivity.c;
import static com.example.chicken.chickentimer4.MainActivity.registedList;
import static com.example.chicken.chickentimer4.MainActivity.spinner2;


public class MsAdapter extends ArrayAdapter<String> {
    List<String> menuName;
    Context context;
    Button delMenuBtn;
    String csName;
    HashMap<String, ArrayList<Integer>> msList;
    ArrayList<String> keyset;


    public MsAdapter(@NonNull Context context, int resource, HashMap<String, ArrayList<Integer>> msList, String selectedCs) {
        super(context, resource);
        this.msList = msList;

        this.context = context;
        this.csName = selectedCs;
    }

    @Override
    public int getCount() {
        return msList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.ms_row, null);
        }

        final TextView Fname = v.findViewById(R.id.Fname);
        TextView first = v.findViewById(R.id.first);
        TextView second = v.findViewById(R.id.second);


        keyset = new ArrayList<>(msList.keySet());
        Fname.setText(keyset.get(position));
        first.setText(msList.get(keyset.get(position)).get(0).toString());
        if (msList.get(keyset.get(position)).size() > 1) {
            second.setText(msList.get(keyset.get(position)).get(1).toString());
        } else {
            second.setText("없음");
        }

        Button delMenuBtn = v.findViewById(R.id.delMenuBtn);
        delMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < registedList.size(); i++) {
                    if (registedList.get(i).getName() == Fname.getText().toString()) {
                        // 삭제할려는 제품이 타이머로 등록돼있다면
                        Toast.makeText(v.getContext(), "타이머에 등록된 제품은 삭제할 수 없습니다.", Toast.LENGTH_SHORT);
                        return;
                    }
                }

                msList.remove(Fname.getText().toString());
                c.get(csName).remove(Fname.getText().toString());

                notifyDataSetChanged();

                Log.i("cResult", msList.get(position) + "");
                Log.i("cResult", c.get(csName) + "");


                // 스피너 업데이트
                arrayAdapter2 = new ArrayAdapter(v.getContext(), R.layout.support_simple_spinner_dropdown_item, c.get(csName).keySet().toArray());
                spinner2.setAdapter(arrayAdapter2);
            }
        });

        return v;


    }

}
