package com.i032114.tallertres.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i032114.tallertres.MainActivity;
import com.i032114.tallertres.Model.CommentsModel;
import com.i032114.tallertres.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 14/10/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    List<CommentsModel> commentsModelList = new ArrayList<>();
    Context context;


    public CommentsAdapter(List<CommentsModel> commentsModelList, Context context) {
        this.commentsModelList = commentsModelList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Configuracion del ViewAdapter

        // Obtener la vista (item.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commens, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        // Encargado de trabajar con el item.xml y sus componentes
        holder.postid.setText(Integer.toString(commentsModelList.get(position).getPostId()));
        holder.id1.setText(Integer.toString(commentsModelList.get(position).getId()));
        holder.emai.setText(commentsModelList.get(position).getEmail());
        holder.body.setText(commentsModelList.get(position).getBody());



    }

    public int getItemCount() {
        return commentsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView postid;
        TextView id1;
        TextView emai;
        TextView body;



        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);
            postid=(TextView)item.findViewById(R.id.id_tv_item_postid);
            id1=(TextView)item.findViewById(R.id.id_tv_item_id2);
            emai=(TextView)item.findViewById(R.id.id_item_emailp);
            body=(TextView)item.findViewById(R.id.id_item_bodyp);


        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            contextItem.startActivity(intent);
        }
    }


}
