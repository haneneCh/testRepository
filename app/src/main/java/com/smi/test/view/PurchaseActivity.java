package com.smi.test.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smi.test.R;
import com.smi.test.model.Brand;
import com.smi.test.model.Purchase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {
    private TextView chiffreAffaire, commission, nbrVente, nameBrand;
    private ImageView imgBrand ;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference refDB = db.getReference("conversions") ;
    String key = null;
    long idBrand ;
    String ca , nbVente, comsion , displayName, urlImg ;
    long amount=0;
    int sommeVente = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        chiffreAffaire = findViewById(R.id.txt_ca);
        commission= findViewById(R.id.txt_commission);
        nbrVente= findViewById(R.id.txt_vente);
        nameBrand= findViewById(R.id.txt_brand);
        imgBrand=findViewById(R.id.image_brand);
        idBrand = getIntent().getLongExtra("idBrandClicked" , 0) ;
        displayName= getIntent().getStringExtra("displayName") ;
         urlImg= getIntent().getStringExtra("imgUrlBrand") ;
        Picasso.get().load(urlImg).into(imgBrand);
        nameBrand.setText(displayName);

        loadData();

    }


    private void loadData() {
        idBrand = getIntent().getLongExtra("idBrandClicked" , 0) ;
        //  swipeRefreshLayout.setRefreshing(true);
        //Log.d("TAG",   " / ************************************" + idBrand);
        get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Purchase purchase = new Purchase();
                Long x = null ;
                 x = snapshot.child("purchase").child("offerId").getValue(Long.class);
                Log.d("TAG", " / /////////////////// purchaseBrandId  " + purchase.getOfferId());
               // if (purchase.getOfferId()==idBrand) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        data.getValue(Purchase.class);
                        Log.d("TAG", " / ///////////////////purchaseBrandId  " + purchase.getOfferId());
                      //  amount = data.child("amount").getValue(Long.class);
                        amount++;
                        Log.d("TAG", " / ///////////////////AMOUUNTTTT  " + amount);
                        Log.d("TAG", " / ************************************" + purchase.getAmount() + " / ***********************" + purchase.getComission() + "/***************" + sommeVente);
                   /*  amount= data.child("amount").getValue(Long.class);
                    comsion = data.child("commission").getValue(String.class);*/
                        sommeVente++;
                        key = data.getKey();
                        Log.d("TAG", " / ************************************" + ca + " / ***********************" + comsion + "/***************" + sommeVente);
                 //   }
                }
                Log.d("TAG",   "//////Total/////////////////////  " +amount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public Query get(String key) {
        if (key == null) {
            return refDB.orderByKey().limitToFirst(15);
        }
        return refDB.orderByKey().startAfter(key).limitToFirst(15);
    }

    public Query getById(long id) {

            return refDB.orderByKey().limitToFirst(50);

        //return refDB.orderByKey().startAfter(key).limitToFirst(15);
    }


}
   /* private void loadData() {
         idBrand = getIntent().getStringExtra("idBrandClicked");
        //  swipeRefreshLayout.setRefreshing(true);
        get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // ArrayList<String> urls = new ArrayList<>();
                Purchase purchase = new Purchase();
                for (DataSnapshot data : snapshot.getChildren()) {
                     data.getValue(Purchase.class);
                    Log.d("TAG",   " / ************************************" + purchase.getAmount() + " / ***********************"+purchase.getComission()+"/***************"+sommeVente );
                   *//*  amount= data.child("amount").getValue(Long.class);
                    comsion = data.child("commission").getValue(String.class);*//*
                    sommeVente++ ;
                    key = data.getKey();
                    Log.d("TAG",   " / ************************************" + ca + " / ***********************"+comsion+"/***************"+sommeVente );
                }
                chiffreAffaire.setText(String.valueOf(amount));
                commission.setText(comsion);
                nbrVente.setText(Integer.toString(sommeVente));





                    //     Brand emp = data.getValue(Brand.class);
                    //    txtData.setText(emp.getDisplayName());
                    //    emp.setKey(data.getKey());

                   *//*  textx = data.child("displayName").getValue(String.class);
                     String imgUrl = data.child("pic").getValue(String.class);


                   // double timestamp = data.child("timestamp").getValue(Double.class);
                    Log.d("TAG",   " / ************************************" + textx + " / ***********************"+imgUrl );
                     urls.add(imgUrl);*//*
                    //Log.d("TAG", urls.toString());

                    //emps.add(emp);


                *//*CoursesGVAdapter adapter = new CoursesGVAdapter(BrandActivity.this, brdArrayList);
                brandGV.setAdapter(adapter);*//*

                //txtData.setText(textx);
                //  txtData.setText(emps.toString());
              *//*  adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                isLoading =false;
                swipeRefreshLayout.setRefreshing(false);*//*
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }*/