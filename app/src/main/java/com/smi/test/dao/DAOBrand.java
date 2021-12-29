package com.smi.test.dao;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.smi.test.model.Brand;

import java.util.HashMap;

public class DAOBrand
{
    private DatabaseReference databaseReference;
    public DAOBrand()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("brands").child("pic") ;
    }
    public Task<Void> add(Brand brand)
    {
        return databaseReference.push().setValue(brand);
    }

    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key)
    {
        if(key == null)
        {
           return databaseReference.orderByKey().limitToFirst(50);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(50);
    }

    public Query get()
    {
        return databaseReference;
    }
}
