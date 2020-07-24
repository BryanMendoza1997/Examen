package com.example.examen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.examen.fragmen.general;
import com.example.examen.fragmen.language;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Bundle args = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Pais=getIntent().getStringExtra("Pais");
        args.putString("Pais", Pais);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        setToolbar();
        setFragmetDefault();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigationView.getMenu().getItem(0).setChecked(false);
                boolean Transaccion=false;
                Fragment fragment=null;
                switch (menuItem.getItemId())
                {
                    case R.id.menu_infogeneral:
                        fragment= new general();
                        fragment.setArguments(args);
                        Transaccion=true;
                        break;
                    case R.id.menu_lenguaje:
                        fragment= new language();
                        Transaccion=true;
                        break;
                }
                if(Transaccion)
                {
                    cambiarfradmento(fragment,menuItem);
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                return true;
            }
        });
    }
    public void setFragmetDefault(){
        // Supongamos que tu Fragment se llama TestFragment. Colocamos este nuevo Bundle como argumento en el fragmento.
        general newFragment = new general();
        newFragment.setArguments(args);
        cambiarfradmento(newFragment,navigationView.getMenu().findItem(R.id.menu_infogeneral));
    }
    public void cambiarfradmento(Fragment fragment,MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}