package loggers;

import beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    public void logEvent(Event event){
        cache.add(event);
        if (cache.size() == cacheSize){
            writeEventsFromeCache();
            cache.clear();
        }
    }

    private void writeEventsFromeCache() {
        for (Event event : cache){
            super.logEvent(event);
        }
    }

    private void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromeCache();
        }
    }
}
