package mohawk.co858.metricmodeller.core.db.factor;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mohawk.co858.metricmodeller.core.factor.Factor;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@BindingAnnotation(BindFactor.MyBinderFactory.class)
public @interface BindFactor {

    class MyBinderFactory implements BinderFactory {

        @Override
        public Binder<BindFactor, Factor> build(final Annotation annotation){
            return (q, bind, factor) -> {
                q.bind("id", factor.id());
                q.bind("title", factor.title());
            };
        }
    }
}
