package com.hoteljpj.controller;

import com.hoteljpj.model.entity.Quarto;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.service.QuartoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hoteljpj.model.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quarto")
public class QuartoController {
    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private QuartoService quartoService;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/{hotelId}")
    public ResponseEntity<String> save(@PathVariable Long hotelId, @RequestBody Quarto quarto) {
        try {
            quartoService.save(hotelId, quarto);

            return ResponseEntity.ok("Quarto associado ao hotel com sucesso");
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@RequestBody Quarto id) {
        try {
            quartoService.edit(id);

            return ResponseEntity.ok("Quarto editado com sucesso");
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ola")
    public String ola(){
        return "Olá mundo";
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(quartoService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(quartoService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(quartoService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
