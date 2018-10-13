package me.firdaus1453.latihanportalberita.network;

import java.util.List;

import me.firdaus1453.latihanportalberita.responseAPI.ResponseBerita;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    //Endpoint untuk mengambil data berita
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();
}
