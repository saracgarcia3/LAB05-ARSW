package edu.eci.arsw.blueprints.services.filters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component("subsamplingFilter")
public class SubsamplingFilter implements BlueprintFilter {

    @Override
    public Blueprint applyOne(Blueprint bp) {
        List<Point> pts = bp.getPoints();
        if (pts == null || pts.isEmpty()) {
            return new Blueprint(bp.getAuthor(), bp.getName(), new Point[]{});
        }
        
        List<Point> out = new ArrayList<>();
        for (int i = 0; i < pts.size(); i += 2) {
            out.add(pts.get(i));
        }
        
        return new Blueprint(bp.getAuthor(), bp.getName(), out.toArray(Point[]::new));

        
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

