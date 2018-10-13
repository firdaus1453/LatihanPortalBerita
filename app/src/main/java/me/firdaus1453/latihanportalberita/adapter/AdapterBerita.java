package me.firdaus1453.latihanportalberita.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import me.firdaus1453.latihanportalberita.MainActivity;
import me.firdaus1453.latihanportalberita.R;
import me.firdaus1453.latihanportalberita.activities.DetailActivity;
import me.firdaus1453.latihanportalberita.responseAPI.BeritaItem;

import static me.firdaus1453.latihanportalberita.network.InitRetrofit.API_URL;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {

    public static final String JDL = "jdl_berita";
    public static final String TGL = "tgl_berita";
    public static final String PNS = "pns_berita";
    public static final String ISI = "isi_berita";
    public static final String FTO = "fto_berita";
    //Buat variable global untuk konstruktor
    Context context;
    List<BeritaItem> berita;

    public AdapterBerita(Context context, List<BeritaItem> berita) {
        this.context = context;
        this.berita = berita;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Membuat layout menjadi object dijava
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        // menampilkan data ke tampilan
        viewHolder.tvJudul.setText(berita.get(i).getJudulBerita());
        viewHolder.tvTglTerbit.setText(berita.get(i).getTanggalPosting());
        viewHolder.tvPenulis.setText(berita.get(i).getPenulis());

        // Mengambil alamat foto
        final String urlGambarBerita = API_URL + "images/" + berita.get(i).getFoto();

        // set image ke widget imageview dengan menggunakan glide
        Glide.with(context).load(urlGambarBerita).into(viewHolder.ivGambarBerita);

        // Membuat onclick pada berita
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuat intent
                Intent intent = new Intent(context,DetailActivity.class);
                // Memasukkan data ke intent dengan putExtra
                intent.putExtra(JDL, berita.get(i).getJudulBerita());
                intent.putExtra(TGL, berita.get(i).getTanggalPosting());
                intent.putExtra(PNS, berita.get(i).getPenulis());
                intent.putExtra(ISI, berita.get(i).getIsiBerita());
                intent.putExtra(FTO, urlGambarBerita);

                //menjalankan Intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Menghitung jumlah data
        return berita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Membuat variable tipedata widget
        ImageView ivGambarBerita;
        TextView tvJudul,tvTglTerbit,tvPenulis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inisiasi widget ke dalam java
            ivGambarBerita = itemView.findViewById(R.id.ivGambarBerita);
            tvJudul = itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = itemView.findViewById(R.id.tvPenulis);
        }
    }
}
