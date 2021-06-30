package com.example.posyanduandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ListViewHolder> {

  private ArrayList<DashboardModel> dataList;
  private OnItemClickListener mListener;
  private Context mContext;

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public DashboardAdapter(Context mContext, ArrayList<DashboardModel> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.dashboard_list, parent, false);
    return new ListViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(ListViewHolder holder, int position) {
    holder.tv_dashboard.setText(dataList.get(position).getTitle());
    holder.img_dashboard.setImageResource(dataList.get(position).getImage());
  }

  @Override
  public int getItemCount() {
    return (dataList != null) ? dataList.size() : 0;
  }

  public class ListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.layout_dashboard_list)
    LinearLayout linearLayout;
    @BindView(R.id.img_dashboard_list)
    ImageView img_dashboard;
    @BindView(R.id.tv_dashboard_list)
    TextView tv_dashboard;

    public ListViewHolder(View itemView, final OnItemClickListener listener) {
      super(itemView);

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