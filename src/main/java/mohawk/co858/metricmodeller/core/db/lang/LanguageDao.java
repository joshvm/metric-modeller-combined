package mohawk.co858.metricmodeller.core.db.lang;

import java.util.Collection;
import mohawk.co858.metricmodeller.core.lang.Language;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(LanguageMapper.class)
public interface LanguageDao {

    @SqlUpdate("INSERT INTO languages (name, productivity_level, loc_per_fp) VALUES (:name, :productivity_level, :loc_per_fp)")
    int insert(@BindLanguage final Language language);

    @SqlBatch("INSERT INTO languages (name, productivity_level, loc_per_fp) VALUES (:name, :productivity_level, :loc_per_fp)")
    int[] insert(@BindLanguage final Iterable<Language> languages);

    @SqlQuery("SELECT * FROM languages")
    Collection<Language> all();
}
