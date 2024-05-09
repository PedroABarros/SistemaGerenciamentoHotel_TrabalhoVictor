package com.hoteljpj.controller;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    //APAGAR DEPOIS QUE TIVER FEITOS TODOS SERVICE
    @Autowired
    private HotelRepository hotelRepository;

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    // NÃO APAGAR O OLA
    @GetMapping("/ola")
    public String ola(){
        return "Olá mundo";
    }

    // CRIA UM NOVO HOTEL
    // QUANDO SE USA O METODO POST
    // QUANDO SE USA O PUT ELE VAI EDITAR UMA JÁ EXISTENTE
    // EX: PUT
    // http://localhost:8080/api/hotel
    // NESSE EXEMPLO ELE IRA EDITAR
    // EX: POST
    //     http://localhost:8080/api/hotel
    // NESSE OUTRO ELE IRA CRIAR UM NOVO

    @PostMapping()
    public ResponseEntity save(@RequestBody Hotel hotel) {
        try {
            return ResponseEntity.ok(hotelService.save(hotel));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity edit(@RequestBody Hotel hotel) {
        try {
            return ResponseEntity.ok(hotelService.save(hotel));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // NO findById ELE IRA ACHAR UM HOTEL
    // É APENAS NECESSARIO PASSAR UM ID
    // CASO NÃO PASSE ELE IRA RETORNAR
    // UMA LISTA COM OS HOTEIS CADASTRADO
    // EX: GET
    //     http://localhost:8080/api/hotel/1

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(hotelService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // AQUI RETORNA A LISTA DOS HOTEIS
    // EX: GET
    //     http://localhost:8080/api/hotel
    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(hotelService.findAll());
    }
    // NO METODO DELTE ELE  APENAS
    // VAI DELETAR OS OBJETOS
    // EX: DELETE
    //     http://localhost:8080/api/hotel
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(hotelService.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    // APENAS RETORNA O NUMERO TOTAL DE HOTEIS
    // EX : GET
    //      http://localhost:8080/api/hotel/total
    @GetMapping("/total")
    public Long getTotal() {
        return hotelRepository.count();
    }
}





