package com.example.custom.service;

import com.example.custom.domain.Categoria;
import com.example.custom.repositories.CategoriaRepository;
import com.example.custom.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Object buscar(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
            return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));


    }
}
