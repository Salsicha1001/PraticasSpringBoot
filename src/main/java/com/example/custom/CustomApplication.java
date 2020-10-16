package com.example.custom;

import com.example.custom.domain.Categoria;
import com.example.custom.domain.Cidade;
import com.example.custom.domain.Estado;
import com.example.custom.domain.Produto;
import com.example.custom.repositories.CategoriaRepository;
import com.example.custom.repositories.CidadeRepository;
import com.example.custom.repositories.EstadoRepository;
import com.example.custom.repositories.ProdutoRepository;
import com.example.custom.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CustomApplication implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");

        Produto p1 = new Produto(null,"impressora",90.00);
        Produto p2 = new Produto(null,"mouse",902.00);
        Produto p3 = new Produto(null,"PC",920.00);
        Estado e1 = new Estado(null,"Minas Gerais");
        Estado e2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia" ,e1);
        Cidade c2 =new Cidade(null, "Sao Paulo", e2);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2,p3));
        cat2.getProdutos().addAll(Arrays.asList( p2));
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        e1.getCidades().addAll(Arrays.asList(c1));
        e2.getCidades().addAll(Arrays.asList(c2));

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
        estadoRepository.saveAll(Arrays.asList(e1,e2));
        cidadeRepository.saveAll(Arrays.asList(c1,c2));
    }
}
