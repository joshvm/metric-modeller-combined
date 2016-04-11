package mohawk.co858.metricmodeller.core.db.factor;

import java.util.Collection;
import mohawk.co858.metricmodeller.core.factor.Factor;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(FactorMapper.class)
public interface FactorDao {

    @SqlUpdate("INSERT INTO factors (title) VALUES (:title)")
    int insert(@BindFactor final Factor factor);

    @SqlBatch("INSERT INTO factors (title) VALUES (:title)")
    int[] insert(@BindFactor final Iterable<Factor> factors);

    @SqlQuery("SELECT * FROM factors")
    Collection<Factor> all();
}
