package com.example.custom.resource;

import com.example.custom.domain.Categoria;
import com.example.custom.dto.CategoriaDTO;
import com.example.custom.service.CategoriaService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {


    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Object obj =  categoriaService.buscar(id);

        return ResponseEntity.ok().body(obj);
    } @GetMapping("/")
    public ResponseEntity<List<CategoriaDTO>> findAll() {

      List<Categoria> obj = categoriaService.findAll();
      List<CategoriaDTO> list = obj.stream().map(o -> new CategoriaDTO(o)).collect(Collectors.toList());

        return ResponseEntity.ok().body(list);
    }


    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> insert(@RequestBody CategoriaDTO i){
        Categoria obj = categoriaService.fromDto(i);
        obj =categoriaService.insert(obj);
        //importante
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> update(@RequestBody CategoriaDTO objt, @PathVariable Integer id){
       Categoria obj = categoriaService.fromDto(objt);
        obj.setId(id);
        obj = categoriaService.update(obj);
        return  ResponseEntity.noContent().build();
    }
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
