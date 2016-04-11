package mohawk.co858.metricmodeller.core.db.factor;

import java.sql.ResultSet;
import java.sql.SQLException;
import mohawk.co858.metricmodeller.core.factor.Factor;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class FactorMapper implements ResultSetMapper<Factor> {

    @Override
    public Factor map(final int index, final ResultSet rs, final StatementContext ctx) throws SQLException{
        return new Factor(
                rs.getInt("id"),
                rs.getString("title")
        );
    }
}
