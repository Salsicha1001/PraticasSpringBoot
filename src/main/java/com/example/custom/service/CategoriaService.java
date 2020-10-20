package com.example.custom.service;

import com.example.custom.domain.Categoria;
import com.example.custom.dto.CategoriaDTO;
import com.example.custom.repositories.CategoriaRepository;
import com.example.custom.resource.exception.DataIntegrityException;
import com.example.custom.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
            return obj.orElseThrow(() -> new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));


    }

    public List<Categoria> findAll() {
      return categoriaRepository.findAll();

    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }
    public Categoria update(Categoria obj){
        Categoria newObj = buscar(obj.getId());
        updateData(newObj, obj);
        return categoriaRepository.save(newObj);
    }
    public void delete(Integer id) {
        buscar(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }
    public Categoria fromDto(CategoriaDTO obj){
        return new Categoria(obj.getId(),obj.getName());
    }
    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setName(obj.getName());
    }
}
