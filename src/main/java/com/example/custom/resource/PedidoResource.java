package com.example.custom.resource;

import com.example.custom.domain.Cliente;
import com.example.custom.domain.Pedido;
import com.example.custom.service.ClienteService;
import com.example.custom.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
        @GetMapping
        public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
