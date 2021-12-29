package com.smi.test.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smi.test.R;
import com.smi.test.model.Brand;

import java.util.ArrayList;

public class BrandActivity extends AppCompatActivity {
    final String TAG = "try just try plz";
    String key = null;
    //    ListView listBrand = findViewById(R.id.list_brand);
    TextView textView, txtData;
   // Button btnValid;
    ImageView imgBrand;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference refDB = db.getReference("brands");
    String textx;
    GridView brandGV;
    ArrayList<Brand> brdArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        //btnValid = findViewById(R.id.btn_open);
        textView = findViewById(R.id.txt_view);
        txtData = findViewById(R.id.txt_data);
        // imgBrand= findViewById(R.id.img_brand) ;
        textView.setText(refDB.getKey());
        brandGV = findViewById(R.id.brand_gv);

        loadData();

    }


    private void loadData() {

        //  swipeRefreshLayout.setRefreshing(true);
        get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //ArrayList<String> urls = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Brand b = data.getValue(Brand.class);
                    brdArrayList.add(b);

                    //     Brand emp = data.getValue(Brand.class);
                    //    txtData.setText(emp.getDisplayName());
                    //    emp.setKey(data.getKey());

                   /*  textx = data.child("displayName").getValue(String.class);
                     String imgUrl = data.child("pic").getValue(String.class);
                    Picasso.get().load(imgUrl).into(imgBrand);

                   // double timestamp = data.child("timestamp").getValue(Double.class);
                    Log.d("TAG",   " / ************************************" + textx + " / ***********************"+imgUrl );
                     urls.add(imgUrl);*/
                   // Log.d("TAG", urls.toString());

                    //emps.add(emp);

                    key = data.getKey();
                }
                BrandGVAdapter adapter = new BrandGVAdapter(BrandActivity.this, brdArrayList);
                brandGV.setAdapter(adapter);
              //  txtData.setText(brand.ge);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public Query get(String key) {
        if (key == null) {
            return refDB.orderByKey().limitToFirst(50);
        }
        return refDB.orderByKey().startAfter(key).limitToFirst(50);
    }

}





