package mohawk.co858.metricmodeller.core.factor;

import java.util.ArrayList;
import java.util.List;
import mohawk.co858.metricmodeller.core.db.Database;

public final class Factors {

    private static final List<Factor> LIST = new ArrayList<>();

    private Factors(){}

    public static List<Factor> values(){
        return LIST;
    }

    public static void load() {
        Database.factors().all().forEach(LIST::add);
    }
}
