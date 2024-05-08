package com.hoteljpj.service;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.repository.HotelRepository;

import java.util.Optional;
import java.util.List;

public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Long> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Long id) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (!hotel.isPresent()) {
            throw new Exception("Aluno não encontrado");
        }
        return hotel.get();
    }

}
