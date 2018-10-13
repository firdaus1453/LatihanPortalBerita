package me.firdaus1453.latihanportalberita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.List;

import me.firdaus1453.latihanportalberita.adapter.AdapterBerita;
import me.firdaus1453.latihanportalberita.network.ApiService;
import me.firdaus1453.latihanportalberita.network.InitRetrofit;
import me.firdaus1453.latihanportalberita.responseAPI.BeritaItem;
import me.firdaus1453.latihanportalberita.responseAPI.ResponseBerita;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // Deklarasi Widget
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Inisialisasi Widget
        recyclerView = findViewById(R.id.rvListBerita);

        // RecyclerView harus pakai layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Eksekusi method
        tampilBerita();
    }

    private void tampilBerita() {
        ApiService api = InitRetrofit.getInstance();

        //Siapkan request
        retrofit2.Call<ResponseBerita> beritaCall = api.request_show_all_berita();

        //Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBerita> call, Response<ResponseBerita> response) {

                // Mematiskan response sukses
                if (response.isSuccessful()){
                    // Tampung data response ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();

                    boolean status = response.body().isStatus();
                    //  Apabila response status true
                    if (status){

                        // Buat adapter recyclerview
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this,data_berita);
                        recyclerView.setAdapter(adapter);

                    }else {
                        // Kalau status false
                        Toast.makeText(MainActivity.this, "Tidak Ada berita",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Koneksi gagal "+t.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
