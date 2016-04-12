package mohawk.co858.metricmodeller.core.database;

import javafx.beans.property.SimpleObjectProperty;

public class DatabaseComplexity {

    public enum DatabaseComplexityFactor {

        SIMPLE("Ordinary", .8),
        ORDINARY("Low", .9),
        MIDWAY("Midway", 1),
        GREAT("Great", 1.1),
        COMPLEX("Complex", 1.2);

        public static final DatabaseComplexityFactor[] VALUES = values();

        private final String title;
        private final double value;

        DatabaseComplexityFactor(final String title, final double value){
            this.title = title;
            this.value = value;
        }

        public String title(){
            return title;
        }

        public double value(){
            return value;
        }

        @Override
        public String toString(){
            return title;
        }
    }


    private final SimpleObjectProperty<DatabaseComplexityFactor> dbComplexityFactor;

    public DatabaseComplexity(final DatabaseComplexityFactor coordination){
        this.dbComplexityFactor = new SimpleObjectProperty<>(coordination);
    }


    public SimpleObjectProperty<DatabaseComplexityFactor> dbComplexityFactor(){
        return dbComplexityFactor;
    }

}
