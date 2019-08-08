package com.cofig;

/**
 * @author lw
 * @date 2019-08-01
 */
public enum PathCofig {
    TARGET("d:\\tj\\", 1);
    private String name;
    private int index;

    PathCofig(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
