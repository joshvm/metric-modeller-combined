package mohawk.co858.metricmodeller.core.db;

import mohawk.co858.metricmodeller.core.config.Config;
import mohawk.co858.metricmodeller.core.db.factor.FactorDao;
import mohawk.co858.metricmodeller.core.db.history.ProjectHistoryDao;
import mohawk.co858.metricmodeller.core.db.lang.LanguageDao;
import mohawk.co858.metricmodeller.core.db.metric.MetricDao;
import mohawk.co858.metricmodeller.core.db.weighting.WeightingDao;
import org.skife.jdbi.v2.DBI;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

public final class Database {

    private static DBI dbi;

    private static LanguageDao languages;
    private static FactorDao factors;
    private static WeightingDao weightings;
    private static MetricDao metrics;

    private static ProjectHistoryDao projectHistory;

    private Database(){}

    public static void init() {
        final SQLiteConnectionPoolDataSource ds = new SQLiteConnectionPoolDataSource();
        ds.setUrl(Config.dbPath());
        dbi = new DBI(ds);

        languages = dbi.open(LanguageDao.class);
        factors = dbi.open(FactorDao.class);
        weightings = dbi.open(WeightingDao.class);
        metrics = dbi.open(MetricDao.class);
        projectHistory = dbi.open(ProjectHistoryDao.class);
    }

    public static LanguageDao languages(){
        return languages;
    }

    public static FactorDao factors(){
        return factors;
    }

    public static WeightingDao weightings(){
        return weightings;
    }

    public static MetricDao metrics(){
        return metrics;
    }

    public static ProjectHistoryDao projectHistory(){
        return projectHistory;
    }
}
