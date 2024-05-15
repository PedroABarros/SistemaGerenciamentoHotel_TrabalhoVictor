package com.hoteljpj.model.repository;

import com.hoteljpj.model.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findByHotelId(Long hotelId);
}
