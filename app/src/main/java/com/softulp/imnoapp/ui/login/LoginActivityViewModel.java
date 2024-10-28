package com.softulp.imnoapp.ui.login;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import com.softulp.imnoapp.MainActivity;
import com.softulp.imnoapp.model.LoginView;

import com.softulp.imnoapp.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivityViewModel extends AndroidViewModel {
    private static SharedPreferences sp;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }


    public void log(String mail,String clave){

            LoginView loginView = new LoginView(mail,clave);

            ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliariaService();
            Call<String> llamada = api.login(loginView);  // Pasa el objeto directamente aquí
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()){
                        // Si es exitosa paso al menu
                        Toast.makeText(getApplication(), "Bienvenido!", Toast.LENGTH_SHORT).show();
                        // Obténgo el token de respuesta
                        String token = "Bearer " + response.body();

                        guardarToken(token);
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Porque estamos iniciando una activity desde un lugar que no es una activity
                        getApplication().startActivity(intent);


                    } else {
                        Toast.makeText(getApplication(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(getApplication(), "Error de servidor", Toast.LENGTH_SHORT).show();
                }

            });
        }
    private void guardarToken(String token){
        ApiClient.guardarToken(token,getApplication());
    }



    }


