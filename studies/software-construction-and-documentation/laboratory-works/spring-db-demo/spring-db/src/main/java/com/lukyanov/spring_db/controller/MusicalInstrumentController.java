package com.lukyanov.spring_db.controller;

import com.lukyanov.spring_db.entity.MusicalInstrument;
import com.lukyanov.spring_db.service.MusicalInstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MusicalInstrumentController {
    private final MusicalInstrumentService musicalInstrumentService;

    @GetMapping("/instruments")
    public List<MusicalInstrument> getAllInstruments() {
        return musicalInstrumentService.findAll();
    }
}
