package mohawk.co858.metricmodeller.core.util;

import java.awt.Desktop;
import java.net.URI;

public final class Utils {

    private Utils(){
    }

    public static boolean browse(final String url){
        try{
            Desktop.getDesktop().browse(URI.create(url));
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
