package org.mulinlab.variantsampler.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimeMetric {
    public static final NumberFormat timeFmt = new DecimalFormat("00");
    protected String name;
    protected long start;
    protected long count;
    protected int maxCount;

    public TimeMetric(String name) {
        this.maxCount = 10000;
        this.name = name;
        this.start = System.currentTimeMillis();
        this.count = 0L;
    }

    public void addRecord() {
        ++this.count;
        this.printCount(false);
    }

    public void addRecord(long count) {
        this.count = count;
        this.printCount(false);
    }

    public void doEnd() {
        this.printCount(true);
    }

    public void doEnd(long count) {
        this.count = count;
        this.printCount(true);
    }

    private void printCount(boolean isEnd) {
        if (isEnd || this.count % (long)this.maxCount == 0L) {
            long seconds = (System.currentTimeMillis() - this.start) / 1000L;
            String elapsed = formatElapseTime(seconds);
            this.info(this.name + " processed " + this.count + " records, elapsed time: " + elapsed + "s");
        }

    }

    protected void info(String info) {
        System.out.println(info);
    }

    public static String formatElapseTime(long seconds) {
        long s = seconds % 60L;
        long allMinutes = seconds / 60L;
        long m = allMinutes % 60L;
        long h = allMinutes / 60L;
        return timeFmt.format(h) + ":" + timeFmt.format(m) + ":" + timeFmt.format(s);
    }

    public long getCount() {
        return this.count;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}
