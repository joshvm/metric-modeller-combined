package mohawk.co858.metricmodeller.core.project.history;

public class ProjectHistory {

    private final int id;
    private final double actualEffort;
    private final double functionPoints;

    public ProjectHistory(final int id, final double actualEffort, final double functionPoints){
        this.id = id;
        this.actualEffort = actualEffort;
        this.functionPoints = functionPoints;
    }

    public int id(){
        return id;
    }

    public double actualEffort(){
        return actualEffort;
    }

    public double estimatedFunctionPoints(){
        return functionPoints;
    }
}
