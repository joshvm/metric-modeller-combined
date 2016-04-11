package mohawk.co858.metricmodeller.core.project;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Projects {

    private final Map<String, Project> map;

    public Projects(){
        map = new LinkedHashMap<>();
    }

    public Collection<Project> values(){
        return map.values();
    }

    public void add(final Project project){
        map.put(project.name().get(), project);
    }

    public Project get(final String name){
        return map.get(name);
    }
}
