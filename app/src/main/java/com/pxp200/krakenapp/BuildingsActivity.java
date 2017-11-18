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
import com.pxp200.krakenapp.Manager.Manager;
import com.pxp200.krakenapp.model.BuildingInfo;
import com.pxp200.krakenapp.model.Resource;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuildingsActivity extends AppCompatActivity {

    @BindView(R.id.buildings_recycler)
    RecyclerView recyclerView;
    BuildingsAdapter adapter;

    Manager manager;


    ArrayList<BuildingInfo> fakeB = new ArrayList<>(Arrays.asList(new BuildingInfo("Pizzeria"), new BuildingInfo("Pizza Factory")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        ButterKnife.bind(this);

        manager = KrakenApplication.getManager(this);

        adapter = new BuildingsAdapter();
        adapter.setResources(fakeB);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public class BuildingsAdapter extends RecyclerView.Adapter<BuildingViewHolder>
    {
        private int expandedPosition = -1;
        ArrayList<BuildingInfo> buildingInfos;

        public void setResources(ArrayList<BuildingInfo> buildingInfos) {
            this.buildingInfos = buildingInfos;
            this.notifyDataSetChanged();
        }

        @Override
        public BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new BuildingViewHolder(inflater.inflate(R.layout.view_building_cell, parent, false));
        }

        @Override
        public void onBindViewHolder(final BuildingViewHolder holder, final int position) {
            final boolean isExpanded = position == expandedPosition;
            holder.expandedPart.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            holder.itemView.setActivated(isExpanded);
            holder.expandIcon.setImageResource(isExpanded ? R.drawable.ic_arrow_drop_up_black_24dp : R.drawable.ic_arrow_drop_down_black_24dp);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandedPosition = isExpanded ? -1:position;
                    TransitionManager.beginDelayedTransition(recyclerView);
                    notifyDataSetChanged();
                }
            });
            holder.bind(buildingInfos.get(position));
        }

        @Override
        public int getItemCount() {
            return buildingInfos.size();
        }
    }

    public static class BuildingViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.building_consumes)
        TextView consumes;
        @BindView(R.id.buildings_produces)
        TextView produces;
        @BindView(R.id.building_name)
        TextView name;
        @BindView(R.id.building_you_own)
        TextView youOwn;

        @BindView(R.id.building_expand_icon)
        ImageView expandIcon;

        @BindView(R.id.building_expanded_part)
        RelativeLayout expandedPart;

        public BuildingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(BuildingInfo data) {
            consumes.setText(data.getConsumesString());
            produces.setText(data.getProducesString());
            name.setText(data.getName());

            //get own count
            youOwn.setText("You own: " + 123);

        }
    }
}
