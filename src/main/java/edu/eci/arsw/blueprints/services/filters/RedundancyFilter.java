package edu.eci.arsw.blueprints.services.filters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component
@Primary
public class RedundancyFilter implements BlueprintFilter {

    @Override
    public Blueprint applyOne(Blueprint bp) {
        List<Point> filteredPoints = new ArrayList<>();
        Point prev = null;
        for (Point p : bp.getPoints()) {
            if (prev == null || !(p.getX() == prev.getX() && p.getY() == prev.getY())) {
                filteredPoints.add(p);
            }
            prev = p;
        }
        return new Blueprint(bp.getAuthor(), bp.getName(), filteredPoints.toArray(new Point[0]));
    }

    @Override
    public Set<Blueprint> apply(Set<Blueprint> blueprints) {
        Set<Blueprint> filtered = new HashSet<>();
        for (Blueprint bp : blueprints) {
            filtered.add(applyOne(bp));
        }
        return filtered;
    }
}
