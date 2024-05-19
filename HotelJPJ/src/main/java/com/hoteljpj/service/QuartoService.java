package com.hoteljpj.service;

import com.hoteljpj.model.entity.Quarto;
import com.hoteljpj.model.repository.HotelRepository;
import com.hoteljpj.model.repository.QuartoRepository;
import org.springframework.stereotype.Service;

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

    public Quarto save(Quarto quarto) {

        // identificação
        if (quarto.getIdentificacao() == null || !quarto.getIdentificacao().matches("^[a-zA-Z]\\d{4}$")) {
            throw new IllegalArgumentException("A IDENTIFICAÇÃO do Quarto deve conter exatamente 5 caracteres, começando com uma letra seguida de quatro dígitos.");
        }
        // Hotel
        if (quarto.getHotel() == null) {
            throw new IllegalArgumentException("O quarto deve estar associado a um HOTEL");
        }
        // identificação no Hotel
        if (quartoRepository.findByIdentificacaoAndHotelId(quarto.getIdentificacao(), quarto.getHotel().getId()) != null
                && !quartoRepository.findByIdentificacaoAndHotelId(quarto.getIdentificacao(), quarto.getHotel().getId()).getId().equals(quarto.getId())) {

            throw new IllegalArgumentException("O HOTEL já possui um quarto com essa IDENTIFICAÇÃO");

        }
        // vista
        if (quarto.getVista() == null || quarto.getVista().trim().isEmpty()) {
            throw new IllegalArgumentException("A VISTA do quarto não pode ser vazia");
        }
        if (quarto.getVista().length() > 100) {
            throw new IllegalArgumentException("A VISTA do quarto não pode ter mais de 100 caracteres");
        }
        // comodidades
        if (quarto.getComodidades() == null || quarto.getComodidades().trim().isEmpty()) {
            throw new IllegalArgumentException("COMODIDADES do quarto não pode ser vazia");
        }
        if (quarto.getComodidades().length() > 100) {
            throw new IllegalArgumentException("COMODIDADES do quarto não pode ter mais de 100 caracteres");
        }
        // Descrição
        if (quarto.getDescricao() == null || quarto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A DESCRIÇÃO do quarto não pode ser vazia");
        }
        if (quarto.getDescricao().length() > 500) {
            throw new IllegalArgumentException("A DESCRIÇÃO do quarto não pode ter mais de 100 caracteres");
        }
        // Tamanho
        if (quarto.getTamanho() <= 0) {
            throw new IllegalArgumentException("O TAMANHO do quarto deve ser maior do que 0");
        }

        return quartoRepository.save(quarto);

        }


}

