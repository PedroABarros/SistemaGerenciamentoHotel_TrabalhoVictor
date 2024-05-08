package com.hoteljpj.controller;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
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
    // MAIS PARA ISSO TEM Q SER PASSADO O ID
    // EX: http://localhost:8080/api/hotel/{id}
    // NESSE EXEMPLO ELE IRA EDITAR
    // EX: http://localhost:8080/api/hotel/
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
    @GetMapping("/getById/{id}")
    public Optional<Hotel> getById(@PathVariable("id") long id) {
        return hotelRepository.findById(id);
    }

    @GetMapping("/total")
    public Long getTotal() {
        return hotelRepository.count();
    }

    @DeleteMapping("/delete/{id}")
    public Hotel delete(@PathVariable("id") long id) {
        Optional<Hotel> hotelemover = hotelRepository.findById(id);
        if (!hotelemover.isPresent()) return null;

        hotelRepository.delete(hotelemover.get());
        return hotelemover.get();


    }


    }


