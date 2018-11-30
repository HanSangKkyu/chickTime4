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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GAdapter extends ArrayAdapter<FF> {
    List<FF> mFF;
    Button delCssBtn;
    Context context;
    TextView csname;

    public GAdapter( @NonNull Context context, int resource, @NonNull List<FF> objects) {
        super(context, resource, objects);
        this.mFF = objects;
        this.context = context;
        Log.i("test_", "GAdapter");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,  @NonNull ViewGroup parent) {
        View v = convertView;
        GAdapterHolder holder;
        Log.i("test_", "GAdapter+getView");

        if(v==null){
            Log.i("test_", "GAdapter+getView_ifnull");
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.ms_dialog_layout, null);
            csname = (TextView)v.findViewById(R.id.csname);

            holder = new GAdapterHolder();
            holder.cssTxt = csname;
            v.setTag(holder);

        }else{
            Log.i("test_", "GAdapter+getView_elsenull");
            holder = (GAdapterHolder)v.getTag();
            csname = holder.cssTxt;
        }
        final FF ff = mFF.get(position);
        if (ff != null) {
            Log.i("test_", position + " " + mFF.get(position).getName() + "1");
            Log.i("test_", position + " " + ff.getName() + "1");
            csname = (TextView)v.findViewById(R.id.csname);
            csname.setText(ff.getName().toString());
        }
        Log.i("test_", position + " " + mFF.get(position).getName() + "");
        Log.i("test_", position + " " + ff.getName() + "");
        csname.setText(ff.getName());


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //메뉴로 들어가기. 어떻게?
            }
        });

        //holder.cssTxt.setText(ff.getName().toString());
        holder.delbtn = v.findViewById(R.id.delCSBtn); // 메뉴 삭제하는 버튼
        holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FF에서 CSS 삭제하기
            }
        });

        return super.getView(position, convertView, parent);
    }

    class GAdapterHolder{
        Button delbtn;
        TextView cssTxt;
    }

}
