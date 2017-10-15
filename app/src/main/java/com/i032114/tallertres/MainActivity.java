package com.i032114.tallertres;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i032114.tallertres.Adapter.UserAdapter;
import com.i032114.tallertres.Conection.Conection;
import com.i032114.tallertres.Model.UserModels;
import com.i032114.tallertres.Parser.UserParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar1;
    RecyclerView recyclerView1;
    List<UserModels> modelsList;
    UserAdapter userAdapter;

    Toolbar toolbar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar1=(Toolbar)findViewById(R.id.id_toolbar);
        shoeTollbar(getResources().getString(R.string.str_tb_p1));


        progressBar1 = (ProgressBar) findViewById(R.id.id_pb_item_user);
        recyclerView1 = (RecyclerView) findViewById(R.id.id_rv_item_user);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(linearLayoutManager);

        loadData();

    }

    public  void  shoeTollbar(String title){
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle(title);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
       loadData();


        return super.onOptionsItemSelected(item);
    }
    public void showpantallaDos(){
        Intent a = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(a);

    }






    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }


    public void loadData(){
        if (isOnLine()){
            TaskUrl1 taskUrl1 = new TaskUrl1();
            taskUrl1.execute("https://jsonplaceholder.typicode.com/users");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
       userAdapter= new UserAdapter(modelsList, getApplicationContext());
        recyclerView1.setAdapter(userAdapter);
    }

    public class TaskUrl1 extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar1.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = Conection.getData(strings[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
               modelsList = UserParser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar1.setVisibility(View.GONE);
        }
    }



}
