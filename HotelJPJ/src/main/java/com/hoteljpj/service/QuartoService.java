package com.hoteljpj.service;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.entity.Quarto;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.model.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuartoService {

    private QuartoRepository quartoRepository;

    private HotelRepository hotelRepository;

    public QuartoService(QuartoRepository quartoRepository, HotelRepository hotelRepository) {
        this.quartoRepository = quartoRepository;
        this.hotelRepository = hotelRepository;
    }

    public void criarQuarto(Long hotelId, Quarto quarto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            quarto.setHotel(hotel);

            // Adicionar validação do objeto quarto aqui, se necessário

            try {
                quartoRepository.save(quarto);
            } catch (Exception e) {
                // Tratar falha ao salvar o quarto, como registrar o erro ou lançar uma exceção personalizada
                throw new RuntimeException("Falha ao salvar o quarto", e);
            }
        } else {
            throw new IllegalArgumentException("Hotel não encontrado para o ID: " + hotelId);
        }
    }

}
