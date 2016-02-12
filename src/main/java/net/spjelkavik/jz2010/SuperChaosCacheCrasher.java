package net.spjelkavik.jz2010;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hennings
 * Date: 03.sep.2010
 */
public class SuperChaosCacheCrasher {

    public SuperChaosCacheCrasher(SuperCache c) {
        this.superChaosCache = c;
    }

    private final SuperCache superChaosCache;

    public void havoc() {
        List<ChaosThread> ct = new ArrayList<ChaosThread>();
//        Thread t0 = new ChaosThread(true);
        Thread t1 = new ChaosThread(false);
        Thread t2 = new ChaosGetterThread();
        Thread t3 = new ChaosGetterThread();

        t1.setName("Chaos-1");
        t2.setName("Chaos-2");
        t3.setName("Chaos-3");

        //t0.start();
        t1.start(); t2.start(); t3.start();

        try {
//            t0.join();
            t1.join(); t2.join();  t3.join();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("try to get #1: ");
        System.out.println(superChaosCache.get("1"));

    }

    public static final int TOTAL = 10 * 1000 * 1000;

    class ChaosThread extends Thread {

        final boolean x;

        public ChaosThread(boolean x) {
            this.x=x;
        }

        @Override
        public void run() {
            for (int i = 0; i < TOTAL; i++) {
                String key = "" + ((int)i);
                try {
                    superChaosCache.add("1", new Integer(i));
                    superChaosCache.add("" + Math.random()*TOTAL, new Integer(i));
                    if (i%500==0) { System.out.println("adding thread: " + i + " / " + TOTAL);}
                }catch (Exception e) {
                    System.out.println("Concurrency issue! " + e);
                }
            }
            System.out.println("end of add");
        }
    }

    class ChaosGetterThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < TOTAL*2; i++) {
                try {
                    superChaosCache.get("1");
                    superChaosCache.get("1");
                    superChaosCache.get("1");
                    superChaosCache.remove("1");
                    superChaosCache.add("1", new Integer(i));
                    Thread.sleep((long) (Math.random()*2));
                    if (i%15000==0) { System.out.println("getting thread " + getName()+": " + i + " / " + TOTAL);}

                }catch (Exception e) {
                    System.out.println("Concurrency issue! " +e);
                }

            }
            System.out.println("end of get");
        }
    }


}