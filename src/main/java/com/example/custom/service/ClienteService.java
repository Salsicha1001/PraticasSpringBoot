package com.example.custom.service;

import com.example.custom.domain.Categoria;
import com.example.custom.domain.Cliente;
import com.example.custom.repositories.CategoriaRepository;
import com.example.custom.repositories.ClienteRepository;
import com.example.custom.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Object buscarCliente(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));


    }
}
