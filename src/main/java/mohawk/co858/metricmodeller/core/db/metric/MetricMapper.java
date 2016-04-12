package mohawk.co858.metricmodeller.core.db.metric;

import java.sql.ResultSet;
import java.sql.SQLException;
import mohawk.co858.metricmodeller.core.metric.Parameter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class MetricMapper implements ResultSetMapper<Parameter> {

    @Override
    public Parameter map(final int index, final ResultSet rs, final StatementContext ctx) throws SQLException{
        return new Parameter(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("title")
        );
    }
}
