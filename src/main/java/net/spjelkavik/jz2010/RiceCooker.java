package net.spjelkavik.jz2010;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class RiceCooker {
    private final int id;
    private final String name;
    private final int price;

    public RiceCooker(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = (int) price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        RiceCooker o = (RiceCooker) obj;
        return new EqualsBuilder().append(id, o.id).equals(obj);
    }
}
