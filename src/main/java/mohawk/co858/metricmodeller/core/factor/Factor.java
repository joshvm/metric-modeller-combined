package mohawk.co858.metricmodeller.core.factor;

public class Factor {

    private final int id;
    private final String title;

    public Factor(final int id, final String title){
        this.id = id;
        this.title = title;
    }

    public int id(){
        return id;
    }

    public String title(){
        return title;
    }

    @Override
    public boolean equals(final Object o){
        if(o == null)
            return false;
        if(!(o instanceof Factor))
            return false;
        final Factor f = (Factor) o;
        return id == f.id;
    }
}
