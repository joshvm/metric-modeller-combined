package mohawk.co858.metricmodeller.core.util;

public final class Utils {

    public interface Task {

        void run() throws Exception;
    }

    private Utils(){
    }

    public static boolean tryRun(final Task task){
        try{
            task.run();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
