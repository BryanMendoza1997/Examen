package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.examen.Adaptadores.MyAdapter;
import com.example.examen.WebServices.Asynchtask;
import com.example.examen.WebServices.WebService;
import com.example.examen.clases.Datos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal extends AppCompatActivity {

    List<Datos> products;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mRecyclerView=(RecyclerView)findViewById(R.id.RecyclerViewProducts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        products=new ArrayList<>();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://restcountries.eu/rest/v2/all", datos, Principal.this, (Asynchtask) Principal.this);
        ws.execute("GET");

    }

    public void processFinish(String result) throws JSONException {
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            products.add( new Datos(
                    banco.getString("name"),
                    banco.getString("flag")
            ));
        }
        MyAdapter adapter=new MyAdapter(Principal.this,products);
        mRecyclerView.setAdapter(adapter);
    }
}