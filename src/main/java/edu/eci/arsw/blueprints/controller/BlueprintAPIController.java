package edu.eci.arsw.blueprints.controller;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@RestController
@RequestMapping("/blueprints")
public class BlueprintAPIController {

    private static final Logger LOGGER = Logger.getLogger(BlueprintAPIController.class.getName());

    private final BlueprintsServices services;

    public BlueprintAPIController(BlueprintsServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            Set<Blueprint> data = services.getAllBlueprints(); 
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED); 
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error obteniendo blueprints", ex);
            return new ResponseEntity<>("Error obteniendo blueprints", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{author}")
    public ResponseEntity<?> getByAuthor(@PathVariable String author) {
        try {
            Set<Blueprint> data = services.getBlueprintsByAuthor(author);
            if (data == null || data.isEmpty()) {
                return new ResponseEntity<>("Autor no encontrado: " + author, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error consultando autor: " + author, ex);
            return new ResponseEntity<>("Error consultando autor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{author}/{bpname}")
    public ResponseEntity<?> getOne(@PathVariable String author, @PathVariable String bpname) {
        try {
            Blueprint bp = services.getBlueprint(author, bpname);
            if (bp == null) {
                return new ResponseEntity<>("Blueprint no encontrado: " + author + "/" + bpname,
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bp, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error consultando blueprint: " + author + "/" + bpname, ex);
            return new ResponseEntity<>("Error consultando blueprint", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Blueprint bp) {
        try {
            if (bp == null || bp.getAuthor() == null || bp.getName() == null) {
                return new ResponseEntity<>("Body inválido: requiere author y name",
                        HttpStatus.BAD_REQUEST);
            }
            services.createBlueprint(bp);               
            return new ResponseEntity<>(bp, HttpStatus.CREATED);
        } catch (BlueprintPersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error de persistencia al crear", e);
            
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creando blueprint", e);
            return new ResponseEntity<>("Error creando blueprint", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{author}/{bpname}")
    public ResponseEntity<?> updatePoints(@PathVariable String author,
                                          @PathVariable String bpname,
                                          @RequestBody Blueprint payload) {
        try {
            if (payload == null || payload.getPoints() == null) {
                return new ResponseEntity<>("Body debe incluir 'points'", HttpStatus.BAD_REQUEST);
            }

            services.updateBlueprintPoints(author, bpname, payload.getPoints());
            Blueprint updated = services.getBlueprint(author, bpname); // ya filtrado
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (BlueprintPersistenceException e) {
            LOGGER.log(Level.WARNING, "No se pudo actualizar (no existe?): " + author + "/" + bpname, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error actualizando blueprint", e);
            return new ResponseEntity<>("Error actualizando blueprint", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
