package com.i032114.tallertres;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i032114.tallertres.Adapter.PostsAdapter;
import com.i032114.tallertres.Adapter.UserAdapter;
import com.i032114.tallertres.Conection.Conection;
import com.i032114.tallertres.Model.PostsModel;
import com.i032114.tallertres.Model.UserModels;
import com.i032114.tallertres.Parser.PostsParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class Posts extends AppCompatActivity {


    ProgressBar progressBar;
    Button button;
    RecyclerView recyclerView;
    List<PostsModel> urlDetailsList;
   PostsAdapter adapterUrl;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item_post);
        button = (Button) findViewById(R.id.id_btn_posts);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item_psts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadData(Integer.toString(getIntent().getExtras().getInt("albumId")));

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

    public void loadData(String albumId){
        if (isOnLine()){
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/posts?userId="+albumId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
       adapterUrl = new PostsAdapter(urlDetailsList, getApplicationContext());
        recyclerView.setAdapter(adapterUrl);
    }

    public class TaskPhoto extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
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
               urlDetailsList = PostsParser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }
    }
}
