package com.pxp200.krakenapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pxp200.krakenapp.Manager.Manager;
import com.pxp200.krakenapp.model.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class ResourcesActivity extends AppCompatActivity implements Manager.UserUpdateListener{

    @BindView(R.id.resources_recycler)
    RecyclerView recyclerView;

    ResourcesAdapter adapter;


    Manager manager;
    ArrayList<Resource> fakeRes = new ArrayList<>(Arrays.asList(
            new Resource("Pizza", "Delicious", 42069, "https://cdn.modpizza.com/wp-content/uploads/2016/11/mod-pizza-maddy-default-e1479167621575.png"),
            new Resource("Lasagna", "asdsa s", 1234, "https://atmedia.imgix.net/2832f13af92f5bcf3ef860796044d2355e770c03?w=800&fit=max")));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        ButterKnife.bind(this);
        adapter = new ResourcesAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        manager = KrakenApplication.getManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.addListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.removeListener(this);
    }

    @Override
    public void updated(Manager manager) {
        final Map<String, Double> resUser = manager.user.getResources();
        final ArrayList<Resource> resStatic = manager.staticResources;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                adapter.setResources(resStatic, resUser);
            }
        });
    }

    public class ResourcesAdapter extends RecyclerView.Adapter<ResourceViewHolder>
    {
        ArrayList<Resource> resources;
        Map<String, Double> resUser;

        public void setResources(ArrayList<Resource> resources, Map<String, Double> resUser) {
            this.resources = resources;
            this.resUser = resUser;
            this.notifyDataSetChanged();
        }

        @Override
        public ResourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ResourceViewHolder(inflater.inflate(R.layout.view_resource_cell, parent, false));
        }

        @Override
        public void onBindViewHolder(ResourceViewHolder holder, int position) {
            holder.bind(resources.get(position), resUser.get(resources.get(position).getId()));
        }

        @Override
        public int getItemCount() {
            if(resources == null) {
                return 0;
            }
            return resources.size();
        }
    }

    public static class ResourceViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.resource_cell_image)
        ImageView image;
        @BindView(R.id.resource_cell_name)
        TextView name;

        @BindView(R.id.resource_cell_value)
        TextView value;

        @BindView(R.id.resource_cell_description)
        TextView description;


        public ResourceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Resource data, double amount) {
            //Glide.with(image).load(data.getImageUrl()).into(image);
            name.setText(data.getName());
            value.setText(Double.toString(amount));
            description.setText(data.getDescription());
        }
    }
}
