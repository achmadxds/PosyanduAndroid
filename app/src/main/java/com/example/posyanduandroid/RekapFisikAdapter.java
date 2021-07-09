package com.example.posyanduandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.interfaces.JSONArrayRequestListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RekapFisikAdapter extends RecyclerView.Adapter<RekapFisikAdapter.ListViewHolder> {

  private ArrayList<RekapFisikModel> dataList;
  private OnItemClickListener mListener;
  private JSONArrayRequestListener mContext;

  public interface OnItemClickListener {
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public RekapFisikAdapter(JSONArrayRequestListener mContext, ArrayList<RekapFisikModel> dataList) {
    this.mContext = mContext;
    this.dataList = dataList;
  }

  @Override
  public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.activity_rekapfisikbalita_list, parent, false);
    return new ListViewHolder(view, mListener);
  }

  @Override
  public void onBindViewHolder(ListViewHolder holder, int position) {
    if (holder.getAdapterPosition() == 0) {
      holder.panjangRekapFisikBalita.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.namaRekapFisikBalita.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.beratRekapFisikBalita.setBackgroundResource(R.drawable.table_header_cell_bg);
      holder.tanggalRekapFisikBalita.setBackgroundResource(R.drawable.table_header_cell_bg);

      holder.namaRekapFisikBalita.setText("Nama");
      holder.beratRekapFisikBalita.setText("Berat");
      holder.tanggalRekapFisikBalita.setText("Tanggal");
      holder.panjangRekapFisikBalita.setText("Panjang");

    } else {
      holder.panjangRekapFisikBalita.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.namaRekapFisikBalita.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.beratRekapFisikBalita.setBackgroundResource(R.drawable.table_content_cell_bg);
      holder.tanggalRekapFisikBalita.setBackgroundResource(R.drawable.table_content_cell_bg);

      int pos = position -1;
      holder.namaRekapFisikBalita.setText(dataList.get(pos).getNama());
      holder.beratRekapFisikBalita.setText(dataList.get(pos).getBerat());
      holder.tanggalRekapFisikBalita.setText(dataList.get(pos).getTanggal());
      holder.panjangRekapFisikBalita.setText(dataList.get(pos).getPanjang());
    }

  }

  @Override
  public int getItemCount() {
    return dataList.size() + 1; // one more to add header row
  }

  public class ListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_kode_jadwal)
    TextView namaRekapFisikBalita;
    @BindView(R.id.tv_program_jadwal)
    TextView beratRekapFisikBalita;
    @BindView(R.id.tv_tempat_jadwal)
    TextView tanggalRekapFisikBalita;
    @BindView(R.id.tv_tanggal_jadwal)
    TextView panjangRekapFisikBalita;

    public ListViewHolder(View itemView, final OnItemClickListener listener) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }

}
