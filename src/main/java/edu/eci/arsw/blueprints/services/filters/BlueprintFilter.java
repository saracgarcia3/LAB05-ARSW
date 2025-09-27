package edu.eci.arsw.blueprints.services.filters;

import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface BlueprintFilter {
    Blueprint applyOne(Blueprint bp);

    Set<Blueprint> apply(Set<Blueprint> blueprints);
}
