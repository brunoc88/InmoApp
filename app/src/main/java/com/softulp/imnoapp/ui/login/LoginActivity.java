package com.softulp.imnoapp.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.softulp.imnoapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        setContentView(binding.getRoot());

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.log(binding.etMail.getText().toString(),binding.etClave.getText().toString());
            }
        });

    }
}