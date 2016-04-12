package mohawk.co858.metricmodeller.core.metric;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import mohawk.co858.metricmodeller.core.weighting.Weighting;

public class MetricMeasurements {

    public static class Entry {

        private final Parameter parameter;
        private final SimpleObjectProperty<Integer> count;
        private final SimpleObjectProperty<Weighting> weighting;

        public Entry(final Parameter parameter, final int count, final Weighting weighting){
            this.parameter = parameter;
            this.count = new SimpleObjectProperty<>(count);
            this.weighting = new SimpleObjectProperty<>(weighting);
        }

        public Parameter metric(){
            return parameter;
        }

        public SimpleObjectProperty<Integer> count(){
            return count;
        }

        public SimpleObjectProperty<Weighting> weighting(){
            return weighting;
        }

        @Override
        public boolean equals(final Object o){
            if(!(o instanceof Entry))
                return false;
            if(o == this)
                return true;
            final Entry e = (Entry) o;
            return parameter == e.parameter
                    && count.get() == e.count.get()
                    && weighting.get() == e.weighting.get();
        }
    }

    private final Map<Parameter, Entry> map;

    public MetricMeasurements(){
        map = new HashMap<>();
    }

    public Map<Parameter, Entry> map(){
        return map;
    }

    public Set<Parameter> keys(){
        return map.keySet();
    }

    public Collection<Entry> values(){
        return map.values();
    }

    public void clear(){
        map.clear();
    }

    public boolean contains(final Parameter parameter){
        return map.containsKey(parameter);
    }

    public boolean remove(final Parameter parameter){
        return map.remove(parameter) != null;
    }

    public boolean remove(final Entry entry){
        return remove(entry.parameter);
    }

    public void set(final Parameter parameter, final int count, final Weighting weighting){
        if(map.containsKey(parameter)){
            final Entry e = get(parameter);
            e.count.set(count);
            e.weighting.set(weighting);
        }else
            map.put(parameter, new Entry(parameter, count, weighting));
    }

    public void add(final Entry entry){
        map.put(entry.parameter, entry);
    }

    public Entry get(final Parameter parameter){
        return map.getOrDefault(parameter, new Entry(parameter, 0, null));
    }
}
