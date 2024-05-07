package com.hoteljpj.controller;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/ola")
    public String ola(){
        return "Olá mundo";
    }

    @GetMapping("/list")
    public List<Hotel> list(){
        return hotelRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Hotel> getById(@PathVariable("id") long id) {
        return hotelRepository.findById(id);
    }

    @GetMapping("/total")
    public Long getTotal() {
        return hotelRepository.count();
    }

    @PostMapping("/create")
    public Hotel create(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("/edit")
    public Hotel edit(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/delete/{id}")
    public Hotel delete(@PathVariable("id") long id) {
        Optional<Hotel> hotelemover = hotelRepository.findById(id);
        if (!hotelemover.isPresent()) return null;

        hotelRepository.delete(hotelemover.get());
        return hotelemover.get();


    }
    }


