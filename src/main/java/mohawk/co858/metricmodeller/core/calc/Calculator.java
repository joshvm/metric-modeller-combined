package mohawk.co858.metricmodeller.core.calc;

import mohawk.co858.metricmodeller.core.project.Project;

public interface Calculator {

    OutputParam outputParam();

    double calculate(final Project project);

}
