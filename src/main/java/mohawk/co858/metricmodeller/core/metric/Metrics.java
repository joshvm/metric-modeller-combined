package mohawk.co858.metricmodeller.core.metric;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import mohawk.co858.metricmodeller.core.db.Database;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import mohawk.co858.metricmodeller.core.weighting.Weightings;

public final class Metrics {

    private static final Map<Object, Metric> MAP = new LinkedHashMap<>();

    private Metrics(){}

    public static Metric forIdOrName(final Object idOrName){
        return MAP.get(idOrName);
    }

    public static Collection<Metric> values(){
        return MAP.values().stream().distinct().collect(Collectors.toList());
    }

    public static void clear(){
        MAP.clear();
    }

    public static void load() {
        for(final Metric metric : Database.metrics().all()){
            for(final Weighting weighting : Weightings.values())
                metric.weight(weighting, Database.metrics().weightingLevel(metric.id(), weighting.id()));
            MAP.put(metric.name(), metric);
            MAP.put(metric.id(), metric);
        }
    }
}
