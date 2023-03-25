package com.example.tp_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tp_6.model.Antenne;

import java.util.ArrayList;

public class AntenneAdapter extends BaseAdapter {

    ArrayList<Antenne> antennes;
    Context context;

    public AntenneAdapter(ArrayList<Antenne> antennes, Context context) {
        this.antennes = antennes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return antennes.size();
    }

    @Override
    public Object getItem(int position) {
        return antennes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        LayoutInflater mInflater = LayoutInflater.from(context);
        //(1) : Réutilisation du layout
        if (convertView == null) {
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.antenne_data, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }
        TextView tv = (TextView) layoutItem.findViewById(R.id.textViewId);
        tv.setText(antennes.get(position).getFields().getOp_site_id());

        tv = (TextView) layoutItem.findViewById(R.id.textViewNomReg);
        tv.setText(antennes.get(position).getFields().getCom_name());

        tv = (TextView) layoutItem.findViewById(R.id.textViewVille);
        tv.setText(antennes.get(position).getFields().getOp_name());

        String enterprise = antennes.get(position).getFields().getOp_name();
        ImageView iv = layoutItem.findViewById(R.id.imageViewLogo);
        switch (enterprise){
            case "Bouygues Telecom" :
                iv.setImageResource(R.drawable.bt_logo);
                break;
            case "Société française du radiotéléphone" :
                iv.setImageResource(R.drawable.sfr_logo);
                break;
            case "Free mobile" :
                iv.setImageResource(R.drawable.free_logo);
                break;
            case "Orange" :
                iv.setImageResource(R.drawable.orange_logo);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + enterprise);
        }


        return layoutItem;    }
}
