package com.hoteljpj.service;

import com.hoteljpj.model.entity.Hotel;
import com.hoteljpj.model.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (!hotel.isPresent()) {
            throw new Exception("Hotel não encontrado");
        }
        return hotel.get();
    }

    public Hotel save(Hotel hotel) throws Exception {
        if (hotel.getClassificacao() == null || hotel.getClassificacao() > 5 || hotel.getClassificacao() < 0) {
            throw new Exception("Classificação não pode ser menor que 0 ou maior que 5");
        }
        if (hotel.getNome() == null || hotel.getNome().length() < 3) {
            throw new Exception("Nome deve ter pelo menos 3 caracteres.");
        }
        // TESTAR NA FACULDADE
        // O DA DATA EU ACHO Q VAI DAR ERRADO
        if (hotel.getQtdQuartos() <= 0 || hotel.getQtdQuartos() == null){
            throw new Exception("Quantidade Quartos não pode ser menor que 1 ou nulo");
        }
        Date hoje = new Date();
        if (hotel.getDataFundacao().before(hoje)) {
            throw new IllegalStateException("A DataFundacao do Hotel não pode ser menor que a data do dia do cadastro");
        }
        if (hotel.getCafe() == null) {
            throw new Exception("Cafe do Hotel não pode ser nulo.");
        }

        if (hotel.getAlmoco() == null) {
            throw new Exception("Almoco do Hotel não pode ser nulo.");
        }

        if (hotel.getJanta() == null) {
            throw new Exception("Janta do Hotel não pode ser nula.");
        }

        return hotelRepository.save(hotel);
    }

    public Hotel delete(Long id) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(id);

        if (!hotel.isPresent()) {
            throw new Exception("Hotel não encontrado");
        }

        hotelRepository.delete(hotel.get());
        return hotel.get();
    }

    public Long count() {
        return hotelRepository.count();
    }

}
//   JEAN E PEDRO, ESSE CODIGO AQUI E SÓ PRA LEMBRA DE COLOCAR NO QUARTO,
//   PARA VERIFICA SE ELE TA COMEÇANDO COM LETRA QUE NEM NAS RF
//
//                  Nova validação para Identificacao do Quarto
//            if (hotel.getId() == null || !hotel.getIdentificacao().matches("^[a-zA-Z]\\d{4,}$")) {
//                throw new Exception("A Identificacao do Quarto deve conter no mínimo 5 dígitos, começando com uma letra.");
//            }