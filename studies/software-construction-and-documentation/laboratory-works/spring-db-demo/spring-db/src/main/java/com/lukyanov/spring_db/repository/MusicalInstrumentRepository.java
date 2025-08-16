package com.lukyanov.spring_db.repository;

import com.lukyanov.spring_db.entity.MusicalInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalInstrumentRepository extends JpaRepository<MusicalInstrument, Integer> {
}
