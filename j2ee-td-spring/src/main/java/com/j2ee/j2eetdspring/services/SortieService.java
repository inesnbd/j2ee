package com.j2ee.j2eetdspring.services;

import com.j2ee.j2eetdspring.entities.Sortie;
import com.j2ee.j2eetdspring.repositories.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SortieService {

    @Autowired
    private SortieRepository sortieRepository;

    public Sortie getSortieById(String nom) {

        return sortieRepository.findById(nom).orElse(null);

    }

    public Sortie createOrUpdate(Sortie sortie) {

        return sortieRepository.save(sortie);

    }

    public List<Sortie> getAllSorties() {

        return sortieRepository.findAll();

    }

    public void deleteSortie(String nom) {

        sortieRepository.deleteById(nom);

    }
}
