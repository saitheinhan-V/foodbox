package com.team06.foodbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team06.foodbox.R;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;

public class MethodActivity extends AppCompatActivity {


    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView imgBackground;

    private String description,title,image;

    private FoodBoxDBHandler foodBoxDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle=findViewById(R.id.tv_method_title);
        tvDescription=findViewById(R.id.tv_method_description);
        imgBackground=findViewById(R.id.img_method_background);

        foodBoxDBHandler=new FoodBoxDBHandler(this);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            description=bundle.getString("method_description");
            title=bundle.getString("method_title");
            image=bundle.getString("method_image");
            Log.d("GLIDE",image);
        }

        String[] descriptionArray=description.split("/");

        StringBuilder stringBuilder=new StringBuilder();
        for(int j=0;j<descriptionArray.length;j++){

            stringBuilder.append(descriptionArray[j]+"\n");
        }

       Glide.with(getApplicationContext()).load(image).apply(new RequestOptions()
               .placeholder(R.drawable.curry)).into(imgBackground);
        tvTitle.setText(title);
        tvDescription.setText(stringBuilder.toString());

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
