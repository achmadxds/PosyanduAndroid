package com.example.posyanduandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KonsultasiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_CLIENT = 1;
    private static int TYPE_ADMIN = 2;
    private List<KonsultasiModel> dataList;
    private Context mContext;

    public KonsultasiAdapter(Context mContext, List<KonsultasiModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_CLIENT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.client_chat, parent, false);
            return new ClientViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.admin_chat, parent, false);
            return new AdminViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CLIENT)
            ((ClientViewHolder) holder).setDetails(dataList.get(position));
        else ((AdminViewHolder) holder).setDetails(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getTgl_balas() == null) {
            return TYPE_CLIENT;
        } else return TYPE_ADMIN;
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_client_chat)
        TextView tv_client;

        public ClientViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        private void setDetails(KonsultasiModel konsultasiModel) {
            tv_client.setText(konsultasiModel.getIsi());
        }
    }

    class AdminViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_admin_chat)
        TextView tv_admin;

        public AdminViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        private void setDetails(KonsultasiModel konsultasiModel) {
            tv_admin.setText(konsultasiModel.getBalasan());
        }
    }

}
