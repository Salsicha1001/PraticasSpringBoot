package com.example.custom.service;

import com.example.custom.domain.Cliente;
import com.example.custom.domain.Pedido;
import com.example.custom.repositories.ClienteRepository;
import com.example.custom.repositories.PedidoRepository;
import com.example.custom.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
