package com.thalesmonteiro.backendfirebase.Controller;

import com.thalesmonteiro.backendfirebase.entity.Memoria;
import com.thalesmonteiro.backendfirebase.repository.MemoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memorias")
public class MemoriaController {

    @Autowired
    private MemoriaRepository memoriaRepository;

    @GetMapping
    public List<Memoria> listar() {
        return memoriaRepository.findAll(Sort.by("id"));
    }

    @PostMapping
    public Memoria salvar(@RequestBody Memoria memoria) {
        return memoriaRepository.save(memoria);
    }

    @PutMapping("/{id}")
    public Memoria atualizar(@PathVariable Long id, @RequestBody Memoria novaMemoria) {
        Memoria memoria = memoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memória não encontrada com id: " + id));
        memoria.setTitulo(novaMemoria.getTitulo());
        memoria.setDescricao(novaMemoria.getDescricao());
        memoria.setData(novaMemoria.getData());
        memoria.setCategoria(novaMemoria.getCategoria());
        memoria.setFavorito(novaMemoria.isFavorito());
        return memoriaRepository.save(memoria);
    }
}
