package edu.eci.arsw.blueprints.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.services.filters.BlueprintFilter;


@Service
public class BlueprintsServices {

    private final BlueprintsPersistence persistence;
    private final BlueprintFilter filter;

 
    public BlueprintsServices(BlueprintsPersistence persistence, BlueprintFilter filter) {
        this.persistence = persistence;
        this.filter = filter;
    }


    public Set<Blueprint> getAllBlueprints() {
        return filter.apply(persistence.getAllBlueprints());
    }

    public Set<Blueprint> getBlueprintsByAuthor(String author) {
        return filter.apply(persistence.getBlueprintsByAuthor(author));
    }


    public Blueprint getBlueprint(String author, String name) {
        Blueprint raw = persistence.getBlueprint(author, name);
        return (raw == null) ? null : filter.applyOne(raw);
    }


    public void createBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        persistence.saveBlueprint(bp);
    }

    
    public void updateBlueprintPoints(String author, String name, List<Point> newPoints)
            throws BlueprintPersistenceException {
        persistence.updatePoints(author, name, newPoints);
    }


    public boolean deleteBlueprint(String author, String name) {
        return persistence.deleteBlueprint(author, name);
    }
}
