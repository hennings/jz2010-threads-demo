package net.spjelkavik.jz2010;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * User: hennings
 * Date: 01.sep.2010
 */
public class RiceCookerSearcher {

    Map<String, HugeObject> superCache = new ConcurrentHashMap<String,HugeObject>();
    
    public List<RiceCooker> getAll() {
        FutureTask<List<RiceCooker>> task = new FutureTask<List<RiceCooker>>(new CookerSearcher ());
        ExecutorService es = Executors.newSingleThreadExecutor ();
        es.submit (task);
        try {
            return task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    class CookerSearcher implements Callable<List<RiceCooker>> {

        @Override
        public List<RiceCooker> call() throws Exception {
            List<RiceCooker> r = new ArrayList<RiceCooker>();
            r.add(new RiceCooker(1, "Tefal Simple", 100));
            r.add(new RiceCooker(2, "Tefal Medium", 300));
            r.add(new RiceCooker(3, "Tefal Plus", 500));
            r.add(new RiceCooker(4, "Tefal Platinum", 1000));

            superCache.put("rice", new HugeObject());

            return r;
        }
    }

    public RiceCooker findByPk(int id) {
        List<RiceCooker> l = getAll();
        for (RiceCooker r : l) {
            if (r.getId()==id) { return r;}
        }
        return null;
    }
}
