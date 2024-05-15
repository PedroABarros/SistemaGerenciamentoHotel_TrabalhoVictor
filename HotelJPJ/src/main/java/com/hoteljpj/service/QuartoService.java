package com.hoteljpj.service;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.entity.Quarto;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.model.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuartoService {

    private QuartoRepository quartoRepository;

    private HotelRepository hotelRepository;

    public QuartoService(QuartoRepository quartoRepository, HotelRepository hotelRepository) {
        this.quartoRepository = quartoRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<Quarto> findAll() {
        return quartoRepository.findAll();
    }

    public Quarto findById(Long id) throws Exception {
        Optional<Quarto> quarto = quartoRepository.findById(id);
        if (!quarto.isPresent()) {
            throw new Exception("Quarto não encontrado");
        }
        return quarto.get();
    }

    public Quarto delete(Long id) throws Exception {
        Optional<Quarto> quarto = quartoRepository.findById(id);

        if (!quarto.isPresent()) {
            throw new Exception("Quarto não encontrado");
        }

       quartoRepository.delete(quarto.get());
        return quarto.get();
    }
//   JEAN E PEDRO, ESSE CODIGO AQUI E SÓ PRA LEMBRA DE COLOCAR NO QUARTO,
//   PARA VERIFICA SE ELE TA COMEÇANDO COM LETRA QUE NEM NAS RF
//
//                  Nova validação para Identificacao do Quarto
//            if (hotel.getId() == null || !hotel.getIdentificacao().matches("^[a-zA-Z]\\d{4,}$")) {
//                throw new Exception("A Identificacao do Quarto deve conter no mínimo 5 dígitos, começando com uma letra.");
//            }


    public void save(Long hotelId, Quarto quarto) {
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

    public void edit(Quarto quarto) {
        if (quarto.getId() == null) {
            throw new IllegalArgumentException("ID do quarto não pode ser nulo para atualização");
        }

        Optional<Quarto> existingQuarto = quartoRepository.findById(quarto.getId());
        if (existingQuarto.isPresent()) {
            try {
                quartoRepository.save(quarto);
            } catch (Exception e) {
                throw new RuntimeException("Falha ao atualizar o quarto", e);
            }
        } else {
            throw new IllegalArgumentException("Quarto não encontrado para o ID: " + quarto.getId());
        }
    }
}
