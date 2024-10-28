package com.softulp.imnoapp.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softulp.imnoapp.model.LoginView;
import com.softulp.imnoapp.model.Propietario;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public class ApiClient {
    public static final String URL_BASE = "http://192.168.100.4:5000/api/"; // IP de tu PC

    private static SharedPreferences sp;
    private static String token;
    private Propietario propietario;

    public static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("Datos",0);
        }
        return sp;
    }



    public static InmobiliariaService getApiInmobiliariaService() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(InmobiliariaService.class);
    }



    public interface InmobiliariaService {

        @POST("Propietario/login")
        Call<String> login(@Body LoginView loginView);

        @GET("Propietario/perfil")
        Call<Propietario> get(@Header("Authorization") String token);

        @PUT("Propietario")
        Call<Propietario> editar(@Header("Authorization")String token,@Body Propietario propietario);
    }

    public static void guardarToken(String token,Context context){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token",token);
        editor.apply();
    }

    public static String leerToken(Context context ){
        SharedPreferences sp = conectar(context);
        return sp.getString("token",null);
    }


}

