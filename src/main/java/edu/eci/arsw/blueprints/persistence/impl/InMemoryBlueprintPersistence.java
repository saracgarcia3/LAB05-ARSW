package edu.eci.arsw.blueprints.persistence.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.Tuple;

@Repository
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    
    private final ConcurrentMap<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

    private Tuple<String,String> keyOf(String author, String name) {
        return new Tuple<>(
                author == null ? null : author.toLowerCase(Locale.ROOT),
                name == null ? null : name.toLowerCase(Locale.ROOT)
        );
    }

    public InMemoryBlueprintPersistence() {

        putSeed(new Blueprint("autor1", "plano1", new Point[]{ new Point(40,40), new Point(15,15) }));
        putSeed(new Blueprint("autor1", "plano2", new Point[]{ new Point(10,10), new Point(20,20) }));
        putSeed(new Blueprint("autor2", "plano3", new Point[]{ new Point(30,30), new Point(60,60) }));
    }

    private void putSeed(Blueprint bp) {
        blueprints.put(keyOf(bp.getAuthor(), bp.getName()), bp);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
    
        Blueprint prev = blueprints.putIfAbsent(keyOf(bp.getAuthor(), bp.getName()), bp);
        if (prev != null) {
            throw new BlueprintPersistenceException("Blueprint already exists: " + bp.getAuthor() + "/" + bp.getName());
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) {
        return blueprints.get(keyOf(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) {
        String a = author == null ? null : author.toLowerCase(Locale.ROOT);
        return blueprints.entrySet().stream()
                .filter(e -> Objects.equals(e.getKey().getElem1(), a))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        return new HashSet<>(blueprints.values());
    }

    @Override
    public void updatePoints(String author, String bprintname, List<Point> newPoints)
            throws BlueprintPersistenceException {
        Tuple<String,String> k = keyOf(author, bprintname);
        Blueprint res = blueprints.computeIfPresent(k, (kk, original) -> {
            Point[] pts = (newPoints == null) ? new Point[]{} : newPoints.toArray(new Point[0]);
            return new Blueprint(original.getAuthor(), original.getName(), pts);
        });
        if (res == null) {
            throw new BlueprintPersistenceException("Blueprint not found: " + author + "/" + bprintname);
        }
    }

    @Override
    public boolean deleteBlueprint(String author, String bprintname) {
        return blueprints.remove(keyOf(author, bprintname)) != null;
    }
}

