package com.hoteljpj.service;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.entity.Quarto;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.model.repository.QuartoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private QuartoRepository quartoRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Long id) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (!hotel.isPresent()) {
            throw new Exception("Hotel não encontrado");
        }
        return hotel.get();
    }

    public Hotel save(@NotNull Hotel hotel) throws Exception {
        if (hotel.getNome() == null || hotel.getNome().length() < 3) {
            throw new Exception("Nome deve ter pelo menos 3 caracteres.");

        }
        // nome
// !quartoRepository.findByIdentificacaoAndHotelId(quarto.getIdentificacao(), quarto.getHotel().getId()).getId().equals(quarto.getId())

        // nome - id
        if (hotelRepository.existsByNome(hotel.getNome()) && !hotelRepository.findByNomeAndId(hotel.getNome(), hotel.getId()).getId().equals(hotel.getId())) {
            throw new IllegalArgumentException("O nome do hotel deve ser único");
        }

        //DATA
        Date hoje = new Date();
        if (!hotel.getDataFundacao().before(hoje)) {
            throw new IllegalStateException("A DataFundacao do Hotel não pode ser maior que a data do dia do cadastro");
        }
        //CLASSIFICAÇÃO
        if (hotel.getClassificacao() == null || hotel.getClassificacao() > 5 || hotel.getClassificacao() < 0) {
            throw new Exception("Classificação não pode ser menor que 0 ou maior que 5");
        }
        //CAFE
        if (hotel.getCafe() == null) {
            throw new Exception("Cafe do Hotel não pode ser nulo.");
        }
        //ALMOCO
        if (hotel.getAlmoco() == null) {
            throw new Exception("Almoco do Hotel não pode ser nulo.");
        }
        //JANTA
        if (hotel.getJanta() == null) {
            throw new Exception("Janta do Hotel não pode ser nula.");
        }

        return hotelRepository.save(hotel);

    }


    @Transactional
    public void deleteHotel(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new IllegalArgumentException("Hotel não encontrado");
        }

        List<Quarto> quartos = quartoRepository.findByHotelId(hotelId);
        if (!quartos.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir o hotel pois ele ainda possui quartos associados.");
        }

        hotelRepository.deleteById(hotelId);
    }

    public Long count() {
        return hotelRepository.count();
    }

}
