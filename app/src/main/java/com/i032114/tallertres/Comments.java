package com.i032114.tallertres;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i032114.tallertres.Adapter.CommentsAdapter;
import com.i032114.tallertres.Conection.Conection;
import com.i032114.tallertres.Model.CommentsModel;
import com.i032114.tallertres.Parser.CommentsParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class Comments extends AppCompatActivity {

    ProgressBar progressBar2;
    Button button2;
    RecyclerView recyclerView2;
    List<CommentsModel> photoModelList2;
    CommentsAdapter photoAdapter2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        progressBar2 = (ProgressBar) findViewById(R.id.id_pb_item_comm);
        button2 = (Button) findViewById(R.id.id_btn_comme);
        recyclerView2 = (RecyclerView) findViewById(R.id.id_rv_item_comm);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(linearLayoutManager);
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
            taskPhoto.execute("https://jsonplaceholder.typicode.com/comments?postId="+albumId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        photoAdapter2 = new CommentsAdapter(photoModelList2, getApplicationContext());
        recyclerView2.setAdapter(photoAdapter2);
    }

    public class TaskPhoto extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar2.setVisibility(View.VISIBLE);
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
                photoModelList2 = CommentsParser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar2.setVisibility(View.GONE);
        }
    }


}
