package com.j2ee.j2eetdspring.controllers;

import com.j2ee.j2eetdspring.entities.Sortie;
import com.j2ee.j2eetdspring.entities.User;
import com.j2ee.j2eetdspring.services.SortieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sorties")
public class SortieController {

    @Autowired
    private SortieService sortieService;

    @RequestMapping(path = "/{nom}", method = RequestMethod.GET)
    public Sortie get(@PathVariable(name = "nom") String nom) {

        return sortieService.getSortieById(nom);

    }

    @RequestMapping(method = RequestMethod.PUT)
    public Sortie createOrUpdate(@RequestBody @Valid Sortie sortie) {

        return sortieService.createOrUpdate(sortie);

    }

    @Operation(summary = "Récupération de toutes les sorties")
    @RequestMapping(path = "/_all", method = RequestMethod.GET)
    public List<Sortie> getAllSorties() {

        return sortieService.getAllSorties();

    }

    @RolesAllowed("ADMIN")
    @Operation(summary = "Suppression d'une sortie à partir de son identifiant")
    @RequestMapping(path = "/{nom}", method = RequestMethod.DELETE)
    public void deleteSortie(@PathVariable(value = "nom") String nom) {

        sortieService.deleteSortie(nom);

    }

}
