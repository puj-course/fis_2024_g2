package org.musify.service;

import org.musify.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaServiceImpl implements ArtistaService{
    @Autowired
    private ArtistaRepository artistaRepository;

}
