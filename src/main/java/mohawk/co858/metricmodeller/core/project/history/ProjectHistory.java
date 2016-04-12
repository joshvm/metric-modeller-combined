package mohawk.co858.metricmodeller.core.project.history;

public class ProjectHistory {

    private final int id;
    private final double actualEffort;
    private final double sourceLinesOfCode;

    public ProjectHistory(final int id, final double actualEffort, final double sourceLinesOfCode){
        this.id = id;
        this.actualEffort = actualEffort;
        this.sourceLinesOfCode = sourceLinesOfCode;
    }

    public int id(){
        return id;
    }

    public double actualEffort(){
        return actualEffort;
    }

    public double sourceLinesOfCode(){
        return sourceLinesOfCode;
    }
}
