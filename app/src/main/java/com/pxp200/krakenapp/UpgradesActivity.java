package com.pxp200.krakenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pxp200.krakenapp.model.Resource;
import com.pxp200.krakenapp.model.Upgrade;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpgradesActivity extends AppCompatActivity {

    @BindView(R.id.upgrades_recycler)
    RecyclerView recyclerView;

    UpgradesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrades);
        ButterKnife.bind(this);
        adapter = new UpgradesAdapter();
        adapter.setUpgrades(new ArrayList<Upgrade>()); //TODO
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    public class UpgradesAdapter extends RecyclerView.Adapter<UpgradeViewHolder>
    {
        ArrayList<Upgrade> upgrades;

        public void setUpgrades(ArrayList<Upgrade> upgrades) {
            this.upgrades = upgrades;
            this.notifyDataSetChanged();
        }

        @Override
        public UpgradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new UpgradeViewHolder(inflater.inflate(R.layout.view_resource_cell, parent, false));
        }

        @Override
        public void onBindViewHolder(UpgradeViewHolder holder, int position) {
            holder.bind(upgrades.get(position));
        }

        @Override
        public int getItemCount() {
            return upgrades.size();
        }
    }

    public static class UpgradeViewHolder extends RecyclerView.ViewHolder
    {

        public UpgradeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Upgrade data) {
        }
    }
}
