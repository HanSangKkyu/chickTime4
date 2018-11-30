package com.example.chicken.chickentimer4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;



public class MsAdapter extends ArrayAdapter<FF> {
    List<FF> mFF;
    Context context;
    Button delMenuBtn;

    public MsAdapter( @NonNull Context context, int resource, @NonNull List<FF> objects) {
        super(context, resource, objects);
        this.mFF = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
        View v = convertView;
        MsAdapterHolder holder;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.ms_dialog_layout, null);
            holder = new MsAdapterHolder();

            holder.Fname = (TextView)v.findViewById(R.id.Fname);
            holder.first = (TextView)v.findViewById(R.id.first);
            holder.second = (TextView)v.findViewById(R.id.second);
            holder.delBtn = (Button)v.findViewById(R.id.delMenuBtn);

            v.setTag(holder);

        }else{
            holder = (MsAdapterHolder)v.getTag();
        }
        final FF ff = mFF.get(position);
        holder.Fname.setText(ff.getName().toString());
        holder.first.setText(ff.getTime().toString());
        holder.second.setText(ff.getTime_p().toString());

        if (ff != null) {
            /*TextView fName = v.findViewById(R.id.Fname);
            fName.setText(ff.getName().toString());
            TextView first = v.findViewById(R.id.first);
            first.setText(ff.getTime().toString());
            TextView second = v.findViewById(R.id.second);
            second.setText(ff.getTime_p().toString());*/
        }

        holder.delBtn = v.findViewById(R.id.delMenuBtn); // 메뉴 삭제하는 버튼
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FF에서 메뉴 삭제하기
            }
        });

        return super.getView(position, convertView, parent);


    }
    class MsAdapterHolder{
        TextView Fname,first,second;
        Button delBtn;
    }
}
