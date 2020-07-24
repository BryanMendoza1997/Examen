package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

public class Principal extends AppCompatActivity implements Asynchtask {

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
        WebService ws= new WebService("https://restcountries.eu/rest/v2/all", datos, Principal.this,Principal.this);
        ws.execute("GET");

    }

    public void processFinish(String result) throws JSONException {
        String imag;
        JSONArray JSONlista =  new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            products.add( new Datos(
                    banco.getString("name"),
                    "http://www.geognos.com/api/en/countries/flag/"+banco.getString("alpha2Code").toString()+".png"));
        }
        MyAdapter adapter=new MyAdapter(Principal.this, products, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Intent segundo=new Intent(Principal.this,MainActivity.class);
                segundo.putExtra("Pais" , name);
                startActivity(segundo);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}