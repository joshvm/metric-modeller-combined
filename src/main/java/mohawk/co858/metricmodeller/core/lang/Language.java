package mohawk.co858.metricmodeller.core.lang;

public class Language {

    private final int id;
    private final String name;
    private final double productivityLevel;
    private final double locPerFp;

    public Language(final int id, final String name, final double productivityLevel, final double locPerFp){
        this.id = id;
        this.name = name;
        this.productivityLevel = productivityLevel;
        this.locPerFp = locPerFp;
    }

    public int id(){
        return id;
    }

    public String name(){
        return name;
    }

    public double productivityLevel(){
        return productivityLevel;
    }

    public double locPerFp(){
        return locPerFp;
    }

    @Override
    public boolean equals(final Object o){
        if(o == null)
            return false;
        if(!(o instanceof Language))
            return false;
        final Language l = (Language) o;
        return id == l.id;
    }
}
