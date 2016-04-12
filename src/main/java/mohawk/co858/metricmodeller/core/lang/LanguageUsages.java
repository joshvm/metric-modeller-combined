package mohawk.co858.metricmodeller.core.lang;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;

public class LanguageUsages {

    public static class Entry {

        private final ReadOnlyObjectProperty<Language> language;
        private final SimpleObjectProperty<Usage> usage;

        public Entry(final Language language, final Usage usage){
            this.language = new ReadOnlyObjectWrapper<>(language);
            this.usage = new SimpleObjectProperty<>(usage);
        }

        public ReadOnlyObjectProperty<Language> language(){
            return language;
        }

        public SimpleObjectProperty<Usage> usage(){
            return usage;
        }

        @Override
        public boolean equals(final Object o){
            if(!(o instanceof Entry))
                return false;
            if(this == o)
                return true;
            final Entry e = (Entry) o;
            return language.get().equals(e.language.get())
                    && usage.get().equals(e.usage.get());
        }
    }

    private final Map<Language, Entry> map;

    public LanguageUsages(){
        map = new TreeMap<>(Comparator.comparing(Language::name));
    }

    public Map<Language, Entry> map(){
        return map;
    }

    public Set<Language> keys(){
        return map.keySet();
    }

    public Collection<Entry> values(){
        return map.values();
    }

    public void clear(){
        map.clear();
    }

    public boolean contains(final Language lang){
        return map.containsKey(lang);
    }

    public boolean remove(final Language lang){
        return map.remove(lang) != null;
    }

    public boolean remove(final Entry entry){
        return remove(entry.language.get());
    }

    public void set(final Language lang, final Usage usage){
        if(map.containsKey(lang))
            get(lang).usage().set(usage);
        else
            map.put(lang, new Entry(lang, usage));
    }

    public void add(final Entry entry){
        set(entry.language.get(), entry.usage.get());
    }

    public Entry get(final Language lang){
        return map.getOrDefault(lang, new Entry(lang, null));
    }
}
