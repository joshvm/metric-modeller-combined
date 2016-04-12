package mohawk.co858.metricmodeller.core.project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import mohawk.co858.metricmodeller.core.database.DatabaseComplexity;
import mohawk.co858.metricmodeller.core.expertise.ExpertiseCounts;
import mohawk.co858.metricmodeller.core.factor.FactorRatings;
import mohawk.co858.metricmodeller.core.lang.LanguageUsages;
import mohawk.co858.metricmodeller.core.metric.MetricMeasurements;
import mohawk.co858.metricmodeller.core.team.Team;

public class Project {

    private static int counter = 1;

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final LanguageUsages languageUsages;
    private final MetricMeasurements metricMeasurements;
    private final FactorRatings factorRatings;
    private final ExpertiseCounts expertiseCounts;
    private final DatabaseComplexity databaseComplexity;
    private final Team team;

    public Project(final int id, final String name,
                   final LanguageUsages languageUsages,
                   final MetricMeasurements metricMeasurements,
                   final FactorRatings factorRatings,
                   final ExpertiseCounts expertiseCounts,
                   final DatabaseComplexity databaseComplexity,
                   final Team team){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.languageUsages = languageUsages;
        this.metricMeasurements = metricMeasurements;
        this.factorRatings = factorRatings;
        this.expertiseCounts = expertiseCounts;
        this.databaseComplexity = databaseComplexity;
        this.team = team;
    }

    public SimpleIntegerProperty id(){
        return id;
    }

    public SimpleStringProperty name(){
        return name;
    }

    public LanguageUsages languageUsages(){
        return languageUsages;
    }

    public MetricMeasurements metricMeasurements(){
        return metricMeasurements;
    }

    public FactorRatings factorRatings(){
        return factorRatings;
    }

    public ExpertiseCounts expertiseCounts(){
        return expertiseCounts;
    }

    public DatabaseComplexity dbComplexity(){
        return databaseComplexity;
    }

    public Team team(){
        return team;
    }

    public static Project create(final int id, final String name){
        return new Project(id, name,
                new LanguageUsages(),
                new MetricMeasurements(),
                new FactorRatings(),
                new ExpertiseCounts(),
                new DatabaseComplexity(DatabaseComplexity.DatabaseComplexityFactor.MIDDLE),
                new Team(false, Team.Coordination.MEDIUM, Team.Leadership.EXCEPTIONAL)
        );
    }

    public static Project create(final String name){
        return create(counter++, name);
    }

    public static int counter(){
        return counter;
    }
}
