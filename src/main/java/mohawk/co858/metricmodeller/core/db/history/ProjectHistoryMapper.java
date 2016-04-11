package mohawk.co858.metricmodeller.core.db.history;

import java.sql.ResultSet;
import java.sql.SQLException;
import mohawk.co858.metricmodeller.core.project.history.ProjectHistory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ProjectHistoryMapper implements ResultSetMapper<ProjectHistory> {

    @Override
    public ProjectHistory map(final int index, final ResultSet rs, final StatementContext ctx) throws SQLException{
        return new ProjectHistory(
                rs.getInt("id"),
                rs.getInt("ActualEffort"),
                rs.getInt("EstimatedFP")
        );
    }
}
