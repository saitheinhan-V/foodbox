package com.team06.foodbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.team06.foodbox.R;

public class CategoriesDirectionActivity extends AppCompatActivity {

    private String direction;
    private TextView tvDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_direction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDirection=findViewById(R.id.tv_direction);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            direction=bundle.getString("direction");
        }

        String[] directionArray=direction.split("/");
        StringBuilder stringBuilder=new StringBuilder();
        for(int j=0;j<directionArray.length;j++){

            stringBuilder.append(directionArray[j]+"\n");
        }

        tvDirection.setText(stringBuilder.toString());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return true;
    }
}
