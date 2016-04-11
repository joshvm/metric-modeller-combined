package mohawk.co858.metricmodeller.core.db.lang;

import java.sql.ResultSet;
import java.sql.SQLException;
import mohawk.co858.metricmodeller.core.lang.Language;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class LanguageMapper implements ResultSetMapper<Language> {

    @Override
    public Language map(final int index, final ResultSet rs, final StatementContext ctx) throws SQLException{
        return new Language(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("productivity_level"),
                rs.getDouble("loc_per_fp")
        );
    }
}
