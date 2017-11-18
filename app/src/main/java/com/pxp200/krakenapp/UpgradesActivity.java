package com.pxp200.krakenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pxp200.krakenapp.model.Resource;
import com.pxp200.krakenapp.model.Upgrade;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

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
        adapter.setUpgrades(new ArrayList<Upgrade>(Arrays.asList(new Upgrade("Pizza")))); //TODO
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    public class UpgradesAdapter extends RecyclerView.Adapter<UpgradeViewHolder>
    {
        private int expandedPosition = -1;
        ArrayList<Upgrade> upgrades;

        public void setUpgrades(ArrayList<Upgrade> upgrades) {
            this.upgrades = upgrades;
            this.notifyDataSetChanged();
        }

        @Override
        public UpgradeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new UpgradeViewHolder(inflater.inflate(R.layout.view_upgrade_cell, parent, false));
        }

        @Override
        public void onBindViewHolder(final UpgradeViewHolder holder, final int position) {
            final boolean isExpanded = position == expandedPosition;
            holder.expandedPart.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            holder.itemView.setActivated(isExpanded);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandedPosition = isExpanded ? -1:position;
                    TransitionManager.beginDelayedTransition(recyclerView);
                    notifyDataSetChanged();
                    holder.expandIcon.setImageResource(isExpanded ? R.drawable.ic_arrow_drop_up_black_24dp : R.drawable.ic_arrow_drop_down_black_24dp);
                }
            });
            holder.bind(upgrades.get(position));
        }

        @Override
        public int getItemCount() {
            return upgrades.size();
        }
    }

    public static class UpgradeViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.upgrade_name)
        TextView name;
        @BindView(R.id.upgrade_expanded_part)
        RelativeLayout expandedPart;

        @BindView(R.id.upgrade_expand_icon)
        ImageView expandIcon;

        public UpgradeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Upgrade data) {
            name.setText(data.getName());
        }
    }
}
