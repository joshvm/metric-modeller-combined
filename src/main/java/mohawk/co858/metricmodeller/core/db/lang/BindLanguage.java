package mohawk.co858.metricmodeller.core.db.lang;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import mohawk.co858.metricmodeller.core.lang.Language;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@BindingAnnotation(BindLanguage.MyBinderFactory.class)
public @interface BindLanguage {

    class MyBinderFactory implements BinderFactory {

        @Override
        public Binder<BindLanguage, Language> build(final Annotation annotation){
            return (q, bind, lang) -> {
                q.bind("id", lang.id());
                q.bind("name", lang.name());
                q.bind("productivity_level", lang.productivityLevel());
                q.bind("loc_per_fp", lang.locPerFp());
            };
        }
    }
}
