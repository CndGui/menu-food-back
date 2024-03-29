package com.guilhermerodrigues.menu.food;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Transactional
    void deleteByName(String name);

    @Transactional
    FoodResponseDTO findByName(String name);
}
