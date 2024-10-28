package com.softulp.imnoapp.ui.perfil;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.imnoapp.databinding.FragmentPerfilBinding;
import com.softulp.imnoapp.model.Propietario;


public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(requireActivity()).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                if (propietario != null) {
                    Log.d("PerfilFragment", "Propietario actualizado: " + propietario.toString());
                    binding.etMail.setText(propietario.getEmail());
                    binding.etTelefono.setText(propietario.getTelefono());
                    binding.etNombre.setText(propietario.getNombre());
                    binding.etApellido.setText(propietario.getApellido());
                    binding.etClave.setText(propietario.getClave());
                    String mail = binding.etMail.getText().toString();
                    Log.d("PerfilFragment", "Email obtenido del propietario: " + propietario.getEmail());

                } else {
                    Log.d("PerfilFragment", "Propietario es null");
                }
            }
        });

        vm.getMGuardarPerfil().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btEditar.setText(s);
            }
        });

        vm.getMHabilitar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.etClave.setEnabled(aBoolean);
                binding.etMail.setEnabled(aBoolean);
                binding.etApellido.setEnabled(aBoolean);
                binding.etNombre.setEnabled(aBoolean);
                binding.etTelefono.setEnabled(aBoolean);
            }
        });

        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario = new Propietario();
                propietario.setApellido(binding.etApellido.getText().toString());
                propietario.setNombre(binding.etNombre.getText().toString());
                propietario.setEmail(binding.etMail.getText().toString());
                propietario.setClave(binding.etClave.getText().toString());
                propietario.setTelefono(binding.etTelefono.getText().toString());
                vm.editarDatos(binding.btEditar.getText().toString(),propietario);
            }
        });

        vm.miPerfil();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
