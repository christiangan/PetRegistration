package com.christian.reporting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2/23/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    static List<DatabaseModel> dbList;
    static Context context;
    RecyclerAdapter(Context context, List<DatabaseModel> dbList ){
        this.dbList = new ArrayList<DatabaseModel>();
        this.context = context;
        this.dbList = dbList;

    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        holder.name.setText(dbList.get(position).getLastName() + ", " + dbList.get(position).getFirstName()  + " " + dbList.get(position).getMiddleName());
        holder.email.setText(dbList.get(position).getEmail());
        holder.contact.setText(dbList.get(position).getContact());
        holder.datereg.setText(dbList.get(position).getDateRegistered());
        holder.sex.setText(dbList.get(position).getSexDesc());
        holder.bday.setText(dbList.get(position).getBday());

    }

//    @Override
    public int getItemCount() {
        return dbList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
//implements View.OnClickListener
        public TextView name,email,contact,datereg, sex,bday;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.rvname);
            email = (TextView)itemLayoutView.findViewById(R.id.rvemail);
            contact = (TextView)itemLayoutView.findViewById(R.id.rvcontact);
            datereg = (TextView)itemLayoutView.findViewById(R.id.rvdateregistered);
            sex = (TextView)itemLayoutView.findViewById(R.id.rvsex);
            bday = (TextView)itemLayoutView.findViewById(R.id.rvbday);

//            itemLayoutView.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(context, ClientDetails.class);
//
//            Bundle extras = new Bundle();
//            extras.putInt("position",getAdapterPosition());
//            intent.putExtras(extras);
//
//            /*
//            int i=getAdapterPosition();
//            intent.putExtra("position", getAdapterPosition());*/
//            context.startActivity(intent);
//            Toast.makeText(RecyclerAdapter.context, "you have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
//        }
    }
}
