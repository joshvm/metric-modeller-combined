package mohawk.co858.metricmodeller.core.team;

import javafx.beans.property.SimpleObjectProperty;

public class Team {

    public enum Coordination {

        NONE("None", 1),
        LOW("Low", 2),
        MEDIUM("Medium", 3),
        HIGH("High", 4);

        public static final Coordination[] VALUES = values();

        private final String title;
        private final double value;

        Coordination(final String title, final double value){
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

    public enum Leadership {

        POOR("Poor", .9),
        AVERAGE("Average", .95),
        STRONG("Strong", .98),
        EXCEPTIONAL("Exceptional", 1);

        public static final Leadership[] VALUES = values();

        private final String title;
        private final double value;

        Leadership(final String title, final double value){
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

    private final SimpleObjectProperty<Boolean> enabled;
    private final SimpleObjectProperty<Coordination> coordination;
    private final SimpleObjectProperty<Leadership> leadership;

    public Team(final boolean enabled, final Coordination coordination, final Leadership leadership){
        this.enabled = new SimpleObjectProperty<>(enabled);
        this.coordination = new SimpleObjectProperty<>(coordination);
        this.leadership = new SimpleObjectProperty<>(leadership);
    }

    public SimpleObjectProperty<Boolean> enabled(){
        return enabled;
    }

    public SimpleObjectProperty<Coordination> coordination(){
        return coordination;
    }

    public SimpleObjectProperty<Leadership> leadership(){
        return leadership;
    }
}
