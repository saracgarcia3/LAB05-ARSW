package edu.eci.arsw.blueprints.persistence;

import java.util.List;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public interface BlueprintsPersistence {

    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    Blueprint getBlueprint(String author, String bprintname);

    Set<Blueprint> getBlueprintsByAuthor(String author);

    Set<Blueprint> getAllBlueprints();

    void updatePoints(String author, String bprintname, List<Point> newPoints)
            throws BlueprintPersistenceException;

    boolean deleteBlueprint(String author, String bprintname);
}

