package org.example.common.domain;

public class PositiveIntegerCount {
    private int count;

    public PositiveIntegerCount() {
        this.count = 0;
    }

    public PositiveIntegerCount(int count) {
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if(count <= 0) {
            return;
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
