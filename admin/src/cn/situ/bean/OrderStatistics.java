package cn.situ.bean;

import java.util.List;

/**
 * ¶©µ¥Í³¼Æ
 */
public class OrderStatistics {
    private List<String> date;
    private List<Integer> number;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Integer> getNumber() {
        return number;
    }

    public void setNumber(List<Integer> number) {
        this.number = number;
    }
}
