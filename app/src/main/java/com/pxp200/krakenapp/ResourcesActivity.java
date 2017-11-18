package com.pxp200.krakenapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pxp200.krakenapp.model.Resource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class ResourcesActivity extends AppCompatActivity {

    @BindView(R.id.resources_recycler)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        recyclerView.setAdapter(new ResourcesAdapter());
    }

    public class ResourcesAdapter extends RecyclerView.Adapter
    {

        ArrayList<Resource> resources;

        public void setResources(ArrayList<Resource> resources) {
            this.resources = resources;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof  ResourceViewHolder) {
                ((ResourceViewHolder)holder).bind(resources.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return resources.size();
        }
    }

    public class ResourceViewHolder extends RecyclerView.ViewHolder
    {

        public ResourceViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Resource data) {
            //TODO
        }
    }
}
