package com.hoteljpj.model.repository;

import com.hoteljpj.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    boolean existsByNome(String nome);
}
