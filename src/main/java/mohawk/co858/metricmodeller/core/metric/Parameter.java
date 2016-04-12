package mohawk.co858.metricmodeller.core.metric;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import mohawk.co858.metricmodeller.core.weighting.Weighting;

public class Parameter {

    private final int id;
    private final String name;
    private final String title;
    private final Map<Weighting, Integer> levels;

    public Parameter(final int id, final String name, final String title){
        this.id = id;
        this.name = name;
        this.title = title;

        levels = new HashMap<>();
    }

    public int id(){
        return id;
    }

    public String name(){
        return name;
    }

    public String title(){
        return title;
    }

    public Map<Weighting, Integer> levels(){
        return levels;
    }

    public Set<Weighting> weightings(){
        return levels.keySet();
    }

    public void weight(final Weighting weighting, final int level){
        levels.put(weighting, level);
    }

    public int weightingLevel(final Weighting weighting){
        return levels.getOrDefault(weighting, 0);
    }

    @Override
    public boolean equals(final Object o){
        if(o == null)
            return false;
        if(!(o instanceof Parameter))
            return false;
        final Parameter m = (Parameter) o;
        return id == m.id;
    }
}
