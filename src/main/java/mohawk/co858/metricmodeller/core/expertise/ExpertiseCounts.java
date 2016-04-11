package mohawk.co858.metricmodeller.core.expertise;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExpertiseCounts {

    private final Map<Expertise.Level, Expertise> map;

    public ExpertiseCounts(){
        map = new LinkedHashMap<>();

        for(final Expertise.Level level : Expertise.Level.VALUES)
            map.put(level, new Expertise(level, level.defaultCost(), 0));
    }

    public void clear(){
        for(final Expertise.Level level : Expertise.Level.VALUES){
            set(level, 0);
            map.get(level).cost().set(level.defaultCost());
        }
    }

    public Collection<Expertise> values(){
        return map.values();
    }

    public void set(final Expertise.Level level, final int count){
        map.get(level).count().set(count);
    }

    public Expertise get(final Expertise.Level level){
        return map.get(level);
    }

    public int peopleCount(){
        return values().stream()
                .mapToInt(e -> e.count().get())
                .sum();
    }
}
