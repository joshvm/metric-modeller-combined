package mohawk.co858.metricmodeller.core.db.weighting;

import java.util.Collection;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(WeightingMapper.class)
public interface WeightingDao {

    @SqlUpdate("INSERT INTO weightings (name, title) VALUES (:name, :title)")
    int insert(@BindWeighting final Weighting weighting);

    @SqlBatch("INSERT INTO weightings (name, title) VALUES (:name, :title)")
    int[] insert(@BindWeighting final Iterable<Weighting> weightings);

    @SqlQuery("SELECT * FROM weightings")
    Collection<Weighting> all();
}
