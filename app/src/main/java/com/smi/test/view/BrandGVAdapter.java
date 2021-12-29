package com.smi.test.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smi.test.R;
import com.smi.test.model.Brand;
import com.smi.test.model.Purchase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrandGVAdapter extends ArrayAdapter<Brand> {
    public long idBrand ;
    public BrandGVAdapter(@NonNull Context context, ArrayList<Brand> brandArrayList) {
        super(context, 0, brandArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Brand brand = getItem(position);

        ImageView imageBrand = listitemView.findViewById(R.id.image_brand);
        Picasso.get().load(brand.getPic()).into(imageBrand);
        idBrand= brand.getOfferId() ;
        Log.d("TAG", "+++++++++++++++++++++++++++"+idBrand) ;
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "la marque selectionnée  est: ******"+brand.getOfferId()+" " + brand.getDisplayName(), Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), PurchaseActivity.class));
                brand.getOfferId();


            }

        });
        return listitemView;
    }
}
