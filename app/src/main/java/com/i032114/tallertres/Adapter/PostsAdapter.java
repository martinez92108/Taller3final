package com.i032114.tallertres.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i032114.tallertres.Model.PostsModel;
import com.i032114.tallertres.R;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 14/10/17.
 */

public class PostsAdapter  extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    List<PostsModel> postsModelList = new ArrayList<>();
    Context context;


    public PostsAdapter(List<PostsModel> postsModelList, Context context) {
        this.postsModelList = postsModelList;
        this.context = context;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes

        holder.userid.setText(Integer.toString(postsModelList.get(position).getUserid()));
        holder.id.setText(Integer.toString(postsModelList.get(position).getId()));
        holder.title.setText(postsModelList.get(position).getTitle());
        holder.body.setText(postsModelList.get(position).getBody());





    }
    public int getItemCount() {
        return postsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userid;
        TextView id;
        TextView title;
        TextView body;





        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);
            userid =(TextView)item.findViewById(R.id.id_tv_item_userid) ;
            id =(TextView)item.findViewById(R.id.id_tv_item_id1) ;
            title =(TextView)item.findViewById(R.id.id_item_title) ;
            body =(TextView)item.findViewById(R.id.id_item_body) ;



        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context,Comment.class);
            contextItem.startActivity(intent);
        }
    }

}
