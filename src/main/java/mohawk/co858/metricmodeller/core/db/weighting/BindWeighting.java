package mohawk.co858.metricmodeller.core.db.weighting;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@BindingAnnotation(BindWeighting.MyBinderFactory.class)
public @interface BindWeighting {

    class MyBinderFactory implements BinderFactory {

        @Override
        public Binder<BindWeighting, Weighting> build(final Annotation annotation){
            return (q, bind, weighting) -> {
                q.bind("id", weighting.id());
                q.bind("name", weighting.name());
                q.bind("title", weighting.title());
            };
        }
    }
}
