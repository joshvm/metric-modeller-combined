package mohawk.co858.metricmodeller.core.expertise;

import javafx.beans.property.SimpleObjectProperty;

public class Expertise {

    public enum Level {

        TRAINEE("Trainee", 30, 1.2),
        AMATEUR("Amateur", 50, 1.1),
        AVERAGE("Average", 80, 1),
        PROFESSIONAL("Professional", 100, .9);

        public static final Level[] VALUES = values();

        private final String title;
        private final double defaultCost;
        private final double factor;

        Level(final String title, final double defaultCost, final double factor){
            this.title = title;
            this.defaultCost = defaultCost;
            this.factor = factor;
        }

        public String title(){
            return title;
        }

        public double defaultCost(){
            return defaultCost;
        }

        public double factor(){
            return factor;
        }

        @Override
        public String toString(){
            return title;
        }
    }

    private final SimpleObjectProperty<Level> level;
    private final SimpleObjectProperty<Double> cost;
    private final SimpleObjectProperty<Integer> count;

    public Expertise(final Level level, final double cost, final int count){
        this.level = new SimpleObjectProperty<>(level);
        this.cost = new SimpleObjectProperty<>(cost);
        this.count = new SimpleObjectProperty<>(count);
    }

    public SimpleObjectProperty<Level> level(){
        return level;
    }

    public SimpleObjectProperty<Double> cost(){
        return cost;
    }

    public SimpleObjectProperty<Integer> count(){
        return count;
    }
}
