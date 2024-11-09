package com.softulp.imnoapp.ui.perfil;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.imnoapp.model.Propietario;
import com.softulp.imnoapp.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> mPropietario ;
    private MutableLiveData<String> mGuardarPerfil ;
    private MutableLiveData<Boolean> mHabilitar ;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario>getMPropietario(){
        if(mPropietario == null){
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
    }

    public LiveData<String>getMGuardarPerfil(){
        if(mGuardarPerfil == null){
            mGuardarPerfil = new MutableLiveData<>();
        }
        return mGuardarPerfil;
    }

    public LiveData<Boolean>getMHabilitar(){
        if(mHabilitar == null){
            mHabilitar = new MutableLiveData<>();
        }
        return mHabilitar;
    }

    public void miPerfil(){
        String token = ApiClient.leerToken(getApplication());
        if (token != null){
            ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliariaService();
            Call<Propietario> call = api.get(token);
            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("PerfilViewModel", "Propietario: " + response.body().toString());
                        mPropietario.setValue(response.body());
                    } else {
                        Log.d("PerfilViewModel", "Error en la respuesta. Código: " + response.code() + ", Mensaje: " + response.message());
                    }
                }


                @Override
                public void onFailure(Call<Propietario> call, Throwable throwable) {
                    Log.e("PerfilViewModel", "Error al obtener perfil: " + throwable.getMessage());
                    Toast.makeText(getApplication(), "Error al cargar el perfil", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    public void editarDatos(String boton, Propietario propietario) {
        if ("Editar".equals(boton)) {
            mGuardarPerfil.setValue("Guardar Perfil");
            mHabilitar.setValue(true);
        } else if ("Guardar Perfil".equals(boton)) {
            mGuardarPerfil.setValue("Editar");
            mHabilitar.setValue(false);

            String token = ApiClient.leerToken(getApplication());
            if (token != null) {
                ApiClient.InmobiliariaService api = ApiClient.getApiInmobiliariaService();
                Call<Propietario> call = api.editar("Bearer " +token, propietario);
                call.enqueue(new Callback<Propietario>() {
                    @Override
                    public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mPropietario.setValue(response.body());
                            Toast.makeText(getApplication(), "Propietario actualizado!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("PerfilViewModel", "Error en la respuesta: " + response.code() + ", " + response.message());
                            Toast.makeText(getApplication(), "Error al actualizar Propietario!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Propietario> call, Throwable throwable) {
                        Log.e("PerfilViewModel", "Error al actualizar propietario: " + throwable.getMessage());
                        Toast.makeText(getApplication(), "Error al actualizar propietario!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

}
