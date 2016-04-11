package mohawk.co858.metricmodeller.core.db.metric;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mohawk.co858.metricmodeller.core.metric.Metric;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@BindingAnnotation(BindMetric.MyBinderFactory.class)
public @interface BindMetric {

    class MyBinderFactory implements BinderFactory {

        @Override
        public Binder<BindMetric, Metric> build(final Annotation annotation){
            return (q, bind, metric) -> {
                q.bind("id", metric.id());
                q.bind("name", metric.name());
                q.bind("title", metric.title());
            };
        }
    }
}
