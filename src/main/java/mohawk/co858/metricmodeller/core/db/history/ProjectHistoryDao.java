package mohawk.co858.metricmodeller.core.db.history;

import java.util.Collection;
import mohawk.co858.metricmodeller.core.project.history.ProjectHistory;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ProjectHistoryMapper.class)
public interface ProjectHistoryDao {

    @SqlQuery("SELECT * FROM history")
    Collection<ProjectHistory> all();
}
