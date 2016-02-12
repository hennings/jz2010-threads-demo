package net.spjelkavik.jz2010;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: hennings
 * Date: 02.sep.2010
 */
public class HugeObject {
    static AtomicInteger globalId = new AtomicInteger(0);
    private static final int SIZE = (int)( 1024 * 1024 * 0.9);

    private final int id;
    private final String xmlContent;

    public HugeObject() {
        id = globalId.getAndIncrement();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i<SIZE; i++) {
            sb.append(64+Math.random()*26);
        }
        xmlContent = sb.toString();
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        HugeObject ho = (HugeObject) o;
        if (ho.getId()==this.getId()) { return true;}
        return false;
    }

}
