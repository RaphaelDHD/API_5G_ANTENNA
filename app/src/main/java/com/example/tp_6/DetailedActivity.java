package com.example.tp_6;

import android.content.Intent;
import android.os.Bundle;

import com.example.tp_6.model.Antenne;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailedActivity extends AppCompatActivity {

    private ArrayList<Antenne> favoris = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.antenne_detailed);
        Intent intentMain = getIntent();
        Antenne antenne = (Antenne) intentMain.getParcelableExtra("antenne");
        favoris = intentMain.getParcelableArrayListExtra("favoris");
        TextView textView = findViewById(R.id.textViewOperateurDetailed); //nom operateur
        textView.setText(antenne.getFields().getOp_name());
        textView = findViewById(R.id.textViewIdDetailed); //id
        textView.setText(antenne.getFields().getOp_site_id());
        textView = findViewById(R.id.textViewDeptDetailed); // nom dept
        textView.setText(antenne.getFields().getDep_name());
        textView = findViewById(R.id.textViewNomRegDetailed); //nom reg
        textView.setText(antenne.getFields().getReg_name());
        textView = findViewById(R.id.textViewCodePostalDetailed); // code postal
        textView.setText("Code postal : " + antenne.getFields().getOp_code());
        try {
            textView = findViewById(R.id.textViewLongDetailed); // longitude
            textView.setText("Longitude : " + Double.toString(antenne.getFields().getGeo_point_2d().get(0)));
            textView = findViewById(R.id.textViewLatDetailed); // latitude
            textView.setText("Latitude : " + Double.toString(antenne.getFields().getGeo_point_2d().get(1)));
        }
        catch(Exception e){

        }

        String enterprise = antenne.getFields().getOp_name();
        ImageView iv = findViewById(R.id.imageViewLogoDetailed);
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


    }


}