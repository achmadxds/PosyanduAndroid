package com.example.posyanduandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.ListViewHolder> {
  private ArrayList<LayananModel> dataList;
  private LayananAdapter.OnItemClickListener mListener;
  private Context mContext;

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  public void setOnItemClickListener(LayananAdapter.OnItemClickListener listener) {
    mListener = listener;
  }

  public LayananAdapter(Context mContext, ArrayList<LayananModel> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  @Override
  public LayananAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.activity_layanan_list, parent, false);
    return new LayananAdapter.ListViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(LayananAdapter.ListViewHolder holder, int position) {
    holder.tv_dashboard.setText(dataList.get(position).getTitle());
  }

  @Override
  public int getItemCount() {
    return (dataList != null) ? dataList.size() : 0;
  }

  public class ListViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.layout_layanan_list)
    LinearLayout linearLayout;
    @BindView(R.id.img_layanan_list)
    ImageView img_dashboard;
    @BindView(R.id.tv_layanan_list)
    TextView tv_dashboard;

    public ListViewHolder(View view, final LayananAdapter.OnItemClickListener listener) {
      super(view);

      ButterKnife.bind(this, itemView);

      linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (listener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
              listener.onItemClick(position);
            }
          }
        }
      });
    }
  }
}