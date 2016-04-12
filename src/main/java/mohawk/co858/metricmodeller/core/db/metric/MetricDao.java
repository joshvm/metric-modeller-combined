package mohawk.co858.metricmodeller.core.db.metric;

import java.util.Collection;
import mohawk.co858.metricmodeller.core.metric.Parameter;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(MetricMapper.class)
public interface MetricDao {

    @SqlQuery("SELECT * FROM metrics")
    Collection<Parameter> all();

    @SqlQuery("SELECT level FROM metric_weighting_levels WHERE metric_id = :metricId AND weighting_id = :weightingId")
    int weightingLevel(@Bind("metricId") final int metricId, @Bind("weightingId") final int weightingId);

}
