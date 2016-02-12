package net.spjelkavik.jz2010;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.*;
import  static org.junit.Assert.*;


/**
 * User: hennings
 * Date: 03.sep.2010
 */
public class SuperChaosCacheCrasherTest {

    public static void main(String[] a) throws Exception {
        if (a.length>0 && "slow".equals(a[0])) {
            new SuperChaosCacheCrasherTest().testSlow();
        } else {
            new SuperChaosCacheCrasherTest().testHavoc();
        }
    }

    @Test
    public void doNothin() {
        assertTrue(true);
        // all ok
    }

    public void testHavoc() throws Exception {
        SuperChaosCacheCrasher crasher = new SuperChaosCacheCrasher(new SuperChaosCache());

        System.out.println("Wait!");
        System.out.println("Start!");
        crasher.havoc();
    }

    public void testSlow() throws Exception {
        SuperChaosCacheCrasher crasher = new SuperChaosCacheCrasher(new SuperSlowCache());
        System.out.println("Wait!");
        System.out.println("Start!");
        crasher.havoc();
    }


}
