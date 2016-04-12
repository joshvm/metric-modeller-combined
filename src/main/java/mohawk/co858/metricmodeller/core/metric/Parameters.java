package mohawk.co858.metricmodeller.core.metric;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import mohawk.co858.metricmodeller.core.db.Database;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import mohawk.co858.metricmodeller.core.weighting.Weightings;

public final class Parameters {

    private static final Map<Object, Parameter> MAP = new LinkedHashMap<>();

    private Parameters(){}

    public static Parameter forIdOrName(final Object idOrName){
        return MAP.get(idOrName);
    }

    public static Collection<Parameter> values(){
        return MAP.values().stream().distinct().collect(Collectors.toList());
    }

    public static void clear(){
        MAP.clear();
    }

    public static void load() {
        for(final Parameter parameter : Database.metrics().all()){
            for(final Weighting weighting : Weightings.values())
                parameter.weight(weighting, Database.metrics().weightingLevel(parameter.id(), weighting.id()));
            MAP.put(parameter.name(), parameter);
            MAP.put(parameter.id(), parameter);
        }
    }
}
