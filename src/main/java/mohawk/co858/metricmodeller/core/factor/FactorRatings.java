package mohawk.co858.metricmodeller.core.factor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;

public class FactorRatings {

    public static class Entry {

        private final SimpleObjectProperty<Factor> factor;
        private final SimpleObjectProperty<Rating> rating;

        private Entry(final Factor factor, final Rating rating){
            this.factor = new SimpleObjectProperty<>(factor);
            this.rating = new SimpleObjectProperty<>(rating);
        }

        public SimpleObjectProperty<Factor> factor(){
            return factor;
        }

        public SimpleObjectProperty<Rating> rating(){
            return rating;
        }
    }

    private final Map<Factor, Entry> map;

    public FactorRatings(){
        map = new HashMap<>();

        clear();
    }

    public Map<Factor, Entry> map(){
        return map;
    }

    public void clear(){
        for(final Factor f : Factors.values())
            set(f, Rating.NO_INFLUENCE);
    }

    public Set<Factor> keys(){
        return map.keySet();
    }

    public Collection<Entry> values(){
        return map.values();
    }

    public void set(final Factor factor, final Rating rating){
        if(!map.containsKey(factor))
            map.put(factor, new Entry(factor, rating));
        else
            map.get(factor).rating.set(rating);
    }

    public Entry get(final Factor factor){
        return map.get(factor);
    }
}
