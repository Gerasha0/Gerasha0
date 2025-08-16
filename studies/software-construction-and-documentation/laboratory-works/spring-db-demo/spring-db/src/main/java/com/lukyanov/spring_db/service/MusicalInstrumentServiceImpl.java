package com.lukyanov.spring_db.service;

import com.lukyanov.spring_db.entity.MusicalInstrument;
import com.lukyanov.spring_db.repository.MusicalInstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicalInstrumentServiceImpl implements MusicalInstrumentService {
    private final MusicalInstrumentRepository musicalInstrumentRepository;

    @Override
    public List<MusicalInstrument> findAll() {
        return musicalInstrumentRepository.findAll();
    }
}
