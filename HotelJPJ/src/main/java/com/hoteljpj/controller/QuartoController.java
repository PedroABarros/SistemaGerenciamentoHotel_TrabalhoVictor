package com.hoteljpj.controller;

import com.hoteljpj.model.entity.Quarto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hoteljpj.model.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/quarto")
public class QuartoController {
    @Autowired
    private QuartoRepository quartoRepository;

    @GetMapping("/ola")
    public String ola(){
        return "Olá mundo";
    }

    @GetMapping("/list")
    public List<Quarto> list(){
        return quartoRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Quarto> getById(@PathVariable("id") long id) {
        return quartoRepository.findById(id);
    }

    @GetMapping("/total")
    public Long getTotal() {
        return quartoRepository.count();
    }

    @PostMapping("/create")
    public Quarto create(@RequestBody Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    @PutMapping("/edit")
    public Quarto edit(@RequestBody Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    @DeleteMapping("/delete/{id}")
    public Quarto delete(@PathVariable("id") long id) {
        Optional<Quarto> quartoemover = quartoRepository.findById(id);
        if (!quartoemover.isPresent()) return null;

        quartoRepository.delete(quartoemover.get());
        return quartoemover.get();


    }

}
