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

    public Quarto save(Long hotelId, Quarto quarto) {
        if (hotelRepository.findById(hotelId).isPresent()) {

            // identificação
            if (quartoRepository.existsByHotelAndIdentificacao(hotelRepository.findById(hotelId), quarto.getIdentificacao())) {
                throw new IllegalArgumentException("Já existe um quarto com essa identificação para o hotel");
            }
            if (quarto.getIdentificacao() == null){
                throw new IllegalArgumentException("A Identificacao do Quarto esta NULA.");
            }
            if (!quarto.getIdentificacao().matches("^[a-zA-Z]\\d{4,}$")) {
                throw new IllegalArgumentException("A Identificacao do Quarto deve conter no mínimo 5 dígitos, começando com uma letra.");
            }
            // vista
            if (quarto.getVista() == null || quarto.getVista().trim().isEmpty()) {
                throw new IllegalArgumentException("A vista do quarto não pode ser vazia");
            } else if (quarto.getVista().length() > 100) {
                throw new IllegalArgumentException("A vista do quarto não pode ter mais de 100 caracteres");
            }
            // comodidades
            if (quarto.getComodidades() == null || quarto.getComodidades().trim().isEmpty()) {
                throw new IllegalArgumentException("A vista do quarto não pode ser vazia");
            } else if (quarto.getComodidades().length() > 100) {
                throw new IllegalArgumentException("A vista do quarto não pode ter mais de 100 caracteres");
            }
            // comodidades
            if (quarto.getDescricao() == null || quarto.getDescricao().trim().isEmpty()) {
                throw new IllegalArgumentException("A vista do quarto não pode ser vazia");
            } else if (quarto.getDescricao().length() > 500) {
                throw new IllegalArgumentException("A vista do quarto não pode ter mais de 100 caracteres");
            }

            // Tamanho
            if (quarto.getTamanho() <= 0) {
                throw new IllegalArgumentException("O tamanho do quarto deve ser maior do que 0");
            }

            quarto.setHotel(hotelRepository.findById(hotelId).get());

            try {
                return quartoRepository.save(quarto);

            } catch (Exception e) {
                throw new RuntimeException("Falha ao salvar o quarto", e);
            }
        }else {
            throw new IllegalArgumentException("Hotel não encontrado para o ID: " + hotelId);
        }
    }

    public void edit(Quarto quarto) {
        if (hotelRepository.findById(quarto.getId()).isPresent()) {

            try {
                quartoRepository.save(quarto);

            } catch (Exception e) {

                throw new RuntimeException("Falha ao atualizar o quarto", e);

            }

        } else {
            throw new IllegalArgumentException("Quarto não editado para o ID: " + quarto.getId());
        }
    }
}
