package mohawk.co858.metricmodeller.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class Config {

    private static final Properties PROPS = new Properties();

    private static final String CORE_FILE_PROPS = "core.properties";

    private static final String DB_PATH_KEY = "db.path";

    private Config(){}

    public static String get(final String key){
        return PROPS.getProperty(key);
    }
    
    private static Path path(final String key){
        return Paths.get(get(key));
    }

    public static String dbPath(){
        return get(DB_PATH_KEY);
    }

    public static void init() throws IOException{
        try(final InputStream in = Files.newInputStream(Paths.get(CORE_FILE_PROPS))){
            PROPS.load(in);
        }
    }
}
