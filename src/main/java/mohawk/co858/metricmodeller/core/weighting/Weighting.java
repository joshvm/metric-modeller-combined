package mohawk.co858.metricmodeller.core.weighting;

public class Weighting {

    private final int id;
    private final String name;
    private final String title;

    public Weighting(final int id, final String name, final String title){
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public int id(){
        return id;
    }

    public String name(){
        return name;
    }

    public String title(){
        return title;
    }

    @Override
    public boolean equals(final Object o){
        if(o == null)
            return false;
        if(!(o instanceof Weighting))
            return false;
        final Weighting w = (Weighting) o;
        return id == w.id;
    }

    @Override
    public String toString(){
        return title;
    }
}
