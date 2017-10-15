package com.i032114.tallertres.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i032114.tallertres.Model.UserModels;
import com.i032114.tallertres.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 12/10/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    List<UserModels> Modellist= new ArrayList<>();
    Context context;


    public UserAdapter(List<UserModels> modellist, Context context) {
        Modellist = modellist;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        //String a;
       holder.id.setText(Integer.toString(Modellist.get(position).getId()));

        holder.name.setText(Modellist.get(position).getName());
        holder.username.setText(Modellist.get(position).getUsername());
        holder.address.setText(Modellist.get(position).getAddress());
        holder.company.setText(Modellist.get(position).getCompany());

        //a= urlDetailsList.get(position).getUrl1();
        String [] a;
        a=new String[10];
        a[0]="https://developer.android.com/_static/images/android/touchicon-180.png";
        a[1]="https://boygeniusreport.files.wordpress.com/2012/11/android-icon.jpg?quality=98&strip=all";
        a[2]="http://cdn02.androidauthority.net/wp-content/uploads/2016/06/android-win-2-300x162.png";
        a[3]="http://www.mariodelafuente.org/putolinux/wp-content/uploads/2017/03/antivirus-para-android.png";
        a[4]="https://i.blogs.es/b6f611/android-o-logo-1/original.jpg";
        a[5]="https://androidzone.org/wp-content/uploads/2013/02/android-musical2.jpg";
        a[6]="https://cdn4.cnet.com/img/ZoiMc37DQlcPHBc6ga0m-nbC1yo=/1600x900/2017/08/21/357e40f7-28bd-474c-a0c0-e09d048f4b04/android-oreo-actualizacion.png";
        a[7]="http://farm5.staticflickr.com/4078/4775107201_4bdefccce9_z.jpg";
        a[8]="https://images.idgesg.net/images/article/2017/05/linux-distros-100724403-large.jpg";
        a[9]="https://regmedia.co.uk/2015/12/11/linux_and_windows.jpg?x=1200&y=794";
        int x = (int)(Math.random()*9);

        Picasso.with(context).load(a[x]).into(holder.imageViewcard);


    }
    public int getItemCount() {return Modellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id;
        TextView name;
        TextView username;
        TextView address;
        TextView company;

        ImageView imageViewcard;

        public ViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);
            id=(TextView) item.findViewById(R.id.id_item_id);
            name=(TextView) item.findViewById(R.id.id_item_name);
            username=(TextView) item.findViewById(R.id.id_item_username);
            address=(TextView) item.findViewById(R.id.id_item_address);
            company=(TextView) item.findViewById(R.id.id_item_company);
            imageViewcard = (ImageView) item.findViewById(R.id.img_item_cardview);
        }

        @Override
        public void onClick(View view) {
            Context contextitem = view.getContext();
            Intent inten = new Intent(contextitem,Comment.class);
            contextitem.startActivity(inten);

            //Toast.makeText(context,"click en el item",Toast.LENGTH_SHORT).show();
        }
    }

}
