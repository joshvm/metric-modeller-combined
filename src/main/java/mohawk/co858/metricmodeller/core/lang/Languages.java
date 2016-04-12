package mohawk.co858.metricmodeller.core.lang;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import mohawk.co858.metricmodeller.core.db.Database;

public final class Languages {

    private static final Map<Object, Language> MAP = new LinkedHashMap<>();

    private Languages(){
    }

    public static Collection<Language> values(){
        return MAP.values().stream().distinct().collect(Collectors.toList());
    }

    public static Language forIdOrName(final Object idOrName){
        return MAP.get(idOrName);
    }

    public static void load(){
        for(final Language lang : Database.languages().all()){
            MAP.put(lang.name().toLowerCase(), lang);
            MAP.put(lang.id(), lang);
        }
    }

}
