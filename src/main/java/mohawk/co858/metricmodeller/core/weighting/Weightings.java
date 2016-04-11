package mohawk.co858.metricmodeller.core.weighting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import mohawk.co858.metricmodeller.core.db.Database;

public final class Weightings {

    private static final Map<Object, Weighting> MAP = new HashMap<>();

    private Weightings(){}

    public static Weighting forIdOrName(final Object idOrName){
        return MAP.get(idOrName);
    }

    public static Collection<Weighting> values(){
        return MAP.values().stream().distinct().collect(Collectors.toList());
    }

    public static void clear(){
        MAP.clear();
    }

    public static void load(){
        for(final Weighting weighting : Database.weightings().all()){
            MAP.put(weighting.name(), weighting);
            MAP.put(weighting.id(), weighting);
        }
    }
}
