package com.app.softxperttask.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.app.softxperttask.Model.CarModel;
import com.app.softxperttask.R;
import com.bumptech.glide.Glide;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<CarModel> modelList;

    private OnItemClickListener mItemClickListener;


    public RecyclerViewAdapter(Context context, ArrayList<CarModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }
    public void ResetList()
    {
        modelList.clear();
        notifyDataSetChanged();
    }

    public void updateList(ArrayList<CarModel> modelList) {
        this.modelList.addAll(modelList);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            final CarModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            Glide.with(mContext).load(model.getImageUrl()).into(genericViewHolder.imgCar);
            genericViewHolder.itemTxtBrand.setText(model.getBrand());
            genericViewHolder.itemTxtisUsed.setText(isUsed(model.getIsUsed()));


        }
    }

    private String isUsed(Boolean isUsed) {
        if (isUsed)
            return "Used";
        return "New";
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private CarModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, CarModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCar;
        private TextView itemTxtBrand;
        private TextView itemTxtisUsed;


        public ViewHolder(final View itemView) {
            super(itemView);


            this.imgCar = (ImageView) itemView.findViewById(R.id.img_car);
            this.itemTxtBrand = (TextView) itemView.findViewById(R.id.item_txt_brand);
            this.itemTxtisUsed = (TextView) itemView.findViewById(R.id.item_txt_isused);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

