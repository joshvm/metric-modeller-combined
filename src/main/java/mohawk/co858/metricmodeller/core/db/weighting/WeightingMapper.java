package mohawk.co858.metricmodeller.core.db.weighting;

import java.sql.ResultSet;
import java.sql.SQLException;
import mohawk.co858.metricmodeller.core.weighting.Weighting;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class WeightingMapper implements ResultSetMapper<Weighting> {

    @Override
    public Weighting map(final int index, final ResultSet rs, final StatementContext ctx) throws SQLException{
        return new Weighting(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("title")
        );
    }
}
