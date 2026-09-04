package edu.eci.arsw.math;

public class BBPThread extends Thread {
    private int start;
    private int count;
    private byte[] digits;

    private static final Object lock = new Object();
    private static boolean paused = false;

    public BBPThread(int start, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public void run() {
        digits = new byte[count];
        double sum = 0;
        long lastChecked = System.currentTimeMillis();
        int localStart = start;

        for (int i = 0; i < count; i++) {
            if (i % PiDigits.DigitsPerSum == 0) {
                sum = 4 * PiDigits.sum(1, start)
                        - 2 * PiDigits.sum(4, start)
                        - PiDigits.sum(5, start)
                        - PiDigits.sum(6, start);

                start += PiDigitsDigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }
    }
}