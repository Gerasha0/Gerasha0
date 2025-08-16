package com.lukyanov.spring_db.service;

import com.lukyanov.spring_db.entity.MusicalInstrument;
import java.util.List;

public interface MusicalInstrumentService {
    List<MusicalInstrument> findAll();
}
