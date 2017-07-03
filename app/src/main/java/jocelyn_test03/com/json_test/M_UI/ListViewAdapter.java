package jocelyn_test03.com.json_test.M_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jocelyn_test03.com.json_test.PersonModle.Persons;
import jocelyn_test03.com.json_test.R;

/**
 * Created by Jocelyn on 27/10/2016.
 */

public class ListViewAdapter extends BaseAdapter {

    Context c;
    ArrayList<Persons> psnList;
    LayoutInflater inflater;

    public ListViewAdapter(Context c, ArrayList<Persons> psnList) {
        this.c = c;
        this.psnList = psnList;
        //INITIALIE
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return psnList.size();
    }

    @Override
    public Object getItem(int position) {
        return psnList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return psnList.get(position).getId();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.model,parent,false);
        }

        TextView idTxt = (TextView) convertView.findViewById(R.id.tvId);
        TextView nameTxt = (TextView) convertView.findViewById(R.id.tvName);
        TextView ageTxt = (TextView) convertView.findViewById(R.id.tvAge);
        TextView addressTxt = (TextView) convertView.findViewById(R.id.tvAddress);

        idTxt.setText("ID:"+psnList.get(position).getId()+"");
        nameTxt.setText("Nane:"+psnList.get(position).getName());
        ageTxt.setText("Age:"+psnList.get(position).getAge()+"");
        addressTxt.setText("Address:"+psnList.get(position).getAddress());

        //Item click
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,psnList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
