package com.onarinskyi.environment;

public class Timeout {

    private long implicitWait;
    private long explicitWait;

    public Timeout(long implicitWait, long explicitWait) {
        this.implicitWait = implicitWait;
        this.explicitWait = explicitWait;
    }

    public long implicitWait() {
        return implicitWait;
    }

    public long explicitWait() {
        return explicitWait;
    }
}
