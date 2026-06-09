package com.prueba.futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.futbol.entity.Player;

@Repository
public interface PlayerRepository  extends JpaRepository<Player, Long>{
    
}
