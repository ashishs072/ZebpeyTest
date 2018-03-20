package com.analyticdata.zebpayapp.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.analyticdata.zebpayapp.R;
import com.analyticdata.zebpayapp.UIUtils.UIUtils;
import com.analyticdata.zebpayapp.networking.pojo.Item;
import com.analyticdata.zebpayapp.networking.pojo.StackUserProfile;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M1030099 on 3/20/2018.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> implements Filterable{

    private Context mContext;
    private ArrayList<Item> mList;
    private ArrayList<Item> mSearchList;

    public ProfileAdapter(Context context, ArrayList<Item> list) {
        this.mContext = context;
        this.mList = list;
        this.mSearchList = list;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_adapter,
                parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getImageLink())
                .thumbnail(0.5f)
                .override(200, 300)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImage);
        //String Title= UIUtils.convertintoCamelCase(mList.get(position).getmTitle());
        holder.mName.setText(mList.get(position).getName());
        holder.mReputation.setText(String.valueOf(mList.get(position).getReputation()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mReputation;
        public ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.profileName);
            mImage = (ImageView) itemView.findViewById(R.id.userImage);
            mReputation = (TextView) itemView.findViewById(R.id.rankNumber);
            // mImage.setOnClickListener(this);

        }
    }


    @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        mList = mSearchList;
                    } else {
                        ArrayList<Item> filteredList = new ArrayList<>();
                        for (Item row : mSearchList) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
                                filteredList.add(row);
                            }
                        }

                        mList = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    mList = (ArrayList<Item>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

    }
