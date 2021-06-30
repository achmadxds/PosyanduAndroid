package com.example.posyanduandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ListViewHolder> {

  private ArrayList<JadwalModel> dataList;
  private OnItemClickListener mListener;
  private Context mContext;

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public JadwalAdapter(Context mContext, ArrayList<JadwalModel> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.jadwal_list, parent, false);
    return new ListViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(ListViewHolder holder, int position) {
    if (holder.getAdapterPosition() == 0) {
      holder.tv_tanggal.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.tv_kode.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.tv_program.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.tv_tempat.setBackgroundResource(R.drawable.table_header_cell_bg);

      holder.tv_kode.setText("Kode");
      holder.tv_program.setText("Program");
      holder.tv_tempat.setText("Tempat");

      String pattern = "yyyy-MM-dd";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      String date = simpleDateFormat.format(dataList.get(position).getTanggal());
      holder.tv_tanggal.setText("Tanggal");

    } else {
      holder.tv_tanggal.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.tv_kode.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.tv_program.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.tv_tempat.setBackgroundResource(R.drawable.table_content_cell_bg);

      int pos = position -1;
      holder.tv_kode.setText(dataList.get(pos).getKode());
      holder.tv_program.setText(dataList.get(pos).getProgram());
      holder.tv_tempat.setText(dataList.get(pos).getTempat());

      String pattern = "yyyy-MM-dd";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      String date = simpleDateFormat.format(dataList.get(pos).getTanggal());
      holder.tv_tanggal.setText(date);
    }

  }

  @Override
  public int getItemCount() {
    return dataList.size() + 1; // one more to add header row
  }

  public class ListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_kode_jadwal)
    TextView tv_kode;
    @BindView(R.id.tv_program_jadwal)
    TextView tv_program;
    @BindView(R.id.tv_tempat_jadwal)
    TextView tv_tempat;
    @BindView(R.id.tv_tanggal_jadwal)
    TextView tv_tanggal;

    public ListViewHolder(View itemView, final OnItemClickListener listener) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }

}
