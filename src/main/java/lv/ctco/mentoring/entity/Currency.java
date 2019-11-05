package lv.ctco.mentoring.entity;

import java.math.BigDecimal;

public class Currency {

    private String name;
    private BigDecimal rate;

    public Currency(String name, BigDecimal rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String toString() {
        return this.getClass().getName() + "{name: " + name + ", rate: " + rate + "}";
    }
}
