package me.firdaus1453.latihanportalberita.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import me.firdaus1453.latihanportalberita.R;
import me.firdaus1453.latihanportalberita.adapter.AdapterBerita;

public class DetailActivity extends AppCompatActivity {

    // Deklarasi
    ImageView ivImageBerita;
    TextView tvTglTerbit, tvPenulis;
    WebView wvKontentBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inisialisasi
        ivImageBerita = findViewById(R.id.ivGambarBerita);
        tvTglTerbit = findViewById(R.id.tvTglTerbit);
        tvPenulis = findViewById(R.id.tvPenulis);
        wvKontentBerita = findViewById(R.id.wvContentBerita);

        // Menjalankan method tampilberita
        tampilBerita();
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    private void tampilBerita() {
        // Menangkap data dari Intent
        String judul_berita = getIntent().getStringExtra(AdapterBerita.JDL);
        String tanggal_berita = getIntent().getStringExtra(AdapterBerita.TGL);
        String penulis_berita = getIntent().getStringExtra(AdapterBerita.PNS);
        String isi_berita = getIntent().getStringExtra(AdapterBerita.ISI);
        String foto_berita = getIntent().getStringExtra(AdapterBerita.FTO);

        // Set judul action / toolbar
        getSupportActionBar().setTitle(judul_berita);

        // Set ke widget
        tvPenulis.setText("Oleh : " + penulis_berita);
        tvTglTerbit.setText(tanggal_berita);

        // Untuk gambar
        Glide.with(this).load(foto_berita).into(ivImageBerita);

        // set isi berita sebagai html ke webview
        wvKontentBerita.getSettings().setJavaScriptEnabled(true);
        wvKontentBerita.loadData(isi_berita,"text/html; charset=utf-8","UTF-8");
    }
}
