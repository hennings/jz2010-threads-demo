package net.spjelkavik.jz2010;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * User: hennings
 * Date: 01.sep.2010
 */
@Component
public class RiceCookerSearcher {

    final static AtomicInteger statusId = new AtomicInteger(0);
    final static Logger log = Logger.getLogger(RiceCookerSearcher.class);

    Map<String, HugeObject> superCache = new ConcurrentHashMap<String, HugeObject>();

    public List<RiceCooker> getAll() {

        updateStatus();

        FutureTask<List<RiceCooker>> task = new FutureTask<List<RiceCooker>>(new CookerSearcher());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(task);
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    StatusThread st;

    private void updateStatus() {
        if (st == null) {
            st = new StatusThread(statusId.getAndIncrement());
        }
        st.start();
    }

    class StatusThread extends Thread {
        final int id;

        StatusThread(int id) {
            this.id = id;
            this.setDaemon(true);
        }

        volatile String status;
        public void setStatus(String st) {
            this.status = st;
        }

        URL u;

        @Override
        public synchronized void run() {
            try {
                this.setName("Status-" + id);
                u = new URL("http://www.bofh.no/?status=javazone");
                log.info("Running thread " + this.getName());
                while (true) {
                    URLConnection c = u.openConnection();
                    c.connect();
                    Thread.sleep(1000 * 4);
                }

            }catch (Exception e) {
                log.info("Trouble in status thread: " + e);

            }


        }
    }

    class CookerSearcher implements Callable<List<RiceCooker>> {

        @Override
        public List<RiceCooker> call() throws Exception {
            List<RiceCooker> r = new ArrayList<RiceCooker>();
            r.add(new RiceCooker(1, "Yong Ma magic.com", 100+Math.random()*320));
            r.add(new RiceCooker(2, "Tefal Comfort", 200+Math.random()*50));
            r.add(new RiceCooker(3, "Tefal 4-in-1", 500+ Math.random()*50));
            r.add(new RiceCooker(4, "Aroma ARC838TC", 1000 + Math.random()*100));

            superCache.put("rice", new HugeObject());

            return r;
        }

    }

    public RiceCooker findByPk(int id) {
        List<RiceCooker> l = getAll();
        for (RiceCooker r : l) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}
