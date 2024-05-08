package com.hoteljpj.service;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.repository.HotelRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Long id) throws Exception {
        Optional<Hotel> aluno = hotelRepository.findById(id);
        if (!aluno.isPresent()) {
            throw new Exception("Aluno não encontrado");
        }
        return aluno.get();
    }

    public Hotel save(Hotel hotel) throws Exception {
        if (hotel.getClassificacao() == null || hotel.getClassificacao() > 5 || hotel.getClassificacao() < 0) {
            System.out.println("Valor da classificação: " + hotel.getClassificacao());
            throw new Exception("Classificação não pode ser menor que 0 ou maior que 5");
        }

        if (hotel.getNome() == null || hotel.getNome().length() < 3) {
            throw new Exception("Nome deve ter pelo menos 3 caracteres.");
        }
        return hotelRepository.save(hotel);
    }

    public Hotel delete(Long id) throws Exception {
        Optional<Hotel> aluno = hotelRepository.findById(id);

        if (!aluno.isPresent()) {
            throw new Exception("Aluno não encontrado");
        }

        hotelRepository.delete(aluno.get());
        return aluno.get();
    }

    public Long count() {
        return hotelRepository.count();
    }

}