package mohawk.co858.metricmodeller.core.metric;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import mohawk.co858.metricmodeller.core.weighting.Weighting;

public class MetricMeasurements {

    public static class Entry {

        private final Metric metric;
        private final SimpleObjectProperty<Integer> count;
        private final SimpleObjectProperty<Weighting> weighting;

        public Entry(final Metric metric, final int count, final Weighting weighting){
            this.metric = metric;
            this.count = new SimpleObjectProperty<>(count);
            this.weighting = new SimpleObjectProperty<>(weighting);
        }

        public Metric metric(){
            return metric;
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
            return metric == e.metric
                    && count.get() == e.count.get()
                    && weighting.get() == e.weighting.get();
        }
    }

    private final Map<Metric, Entry> map;

    public MetricMeasurements(){
        map = new HashMap<>();
    }

    public Map<Metric, Entry> map(){
        return map;
    }

    public Set<Metric> keys(){
        return map.keySet();
    }

    public Collection<Entry> values(){
        return map.values();
    }

    public void clear(){
        map.clear();
    }

    public boolean contains(final Metric metric){
        return map.containsKey(metric);
    }

    public boolean remove(final Metric metric){
        return map.remove(metric) != null;
    }

    public boolean remove(final Entry entry){
        return remove(entry.metric);
    }

    public void set(final Metric metric, final int count, final Weighting weighting){
        if(map.containsKey(metric)){
            final Entry e = get(metric);
            e.count.set(count);
            e.weighting.set(weighting);
        }else
            map.put(metric, new Entry(metric, count, weighting));
    }

    public void add(final Entry entry){
        map.put(entry.metric, entry);
    }

    public Entry get(final Metric metric){
        return map.getOrDefault(metric, new Entry(metric, 0, null));
    }
}
