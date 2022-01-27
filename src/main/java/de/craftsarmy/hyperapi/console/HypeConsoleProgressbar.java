package de.craftsarmy.hyperapi.console;

import de.craftsarmy.hyperapi.runtime.HypeTimeConverter;
import de.craftsarmy.hyperapi.threading.HypeRunnable;
import de.craftsarmy.hyperapi.threading.IHypeTask;
import de.craftsarmy.hyperapi.threading.HypeThread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HypeConsoleProgressbar {

    private final String name;
    private final HypeThread hypeThread;
    private final IHypeTask hypeTask;
    private final PrintStream stream;
    private final ConcurrentLinkedQueue<Integer> missed = new ConcurrentLinkedQueue<>();

    private int current = 0;
    private long timestamp = System.currentTimeMillis();
    private long start = System.currentTimeMillis();


    public HypeConsoleProgressbar(String name) {
        this.name = name;
        this.hypeThread = new HypeThread(new HypeRunnable(), "HypeConsoleProgress-" + UUID.randomUUID(), -1);
        stream = System.out;
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                missed.add(b);
            }
        };
        System.setOut(new PrintStream(outputStream));
        this.hypeTask = hypeThread -> {
            if (timestamp <= System.currentTimeMillis()) {
                try {
                    final double megaBytes = 10241024;
                    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
                    double used = memoryMXBean.getHeapMemoryUsage().getUsed() / megaBytes;
                    double max = memoryMXBean.getHeapMemoryUsage().getMax() / megaBytes;
                    String bar = "\r\u001B[93m" + name + "  " + (current < 100 ? (current <= 9 ? "00" : "0") : "") + current + "%  [%percent%]  Memory: [%memory%]  Time: [%time%]\u001B[0m";
                    String percent = "=".repeat(Math.max(0, current / 5 + 1)) +
                            ">" +
                            " ".repeat(Math.max(0, 20 - (current / 5)));
                    bar = bar.replace("%percent%", percent);
                    bar = bar.replace("%memory%", (used < 1000 ? (used <= 99 ? (used <= 9 ? "000" : "00") : "0") : "") + (int) used + "/" + (max < 1000 ? (max <= 99 ? (max <= 9 ? "000" : "00") : "0") : "") + (int) max);
                    bar = bar.replace("%time%", HypeTimeConverter.toString(System.currentTimeMillis() - start));
                    stream.write(bar.getBytes());
                    timestamp = System.currentTimeMillis() + 100;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.hypeThread.registerTask(hypeTask);
        this.hypeThread.start();
    }

    public void update(int percentage) {
        current = percentage;
    }

    public void update(double percentage) {
        update((int) percentage);
    }

    public void stop() {
        try {
            Thread.sleep(50);
            System.setOut(stream);
            if (current >= 100) {
                System.out.print("\r\u001B[92m" + name + "  Task Completed (Time: " + HypeTimeConverter.toString(System.currentTimeMillis() - start) + ")\u001B[0m");
            } else
                System.out.print("\r\u001B[91m" + name + "  Task Cancelled\u001B[0m");
            System.out.println("\n\u001B[91mMissed console outputs:\u001B[0m");
            this.hypeThread.unregisterTask(hypeTask);
            hypeThread.stop();
            if (missed.isEmpty())
                System.out.println("\u001B[92mNo console outputs missing!\u001B[0m");
            else
                for (int b : missed)
                    System.out.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HypeConsoleProgressbar processbar = new HypeConsoleProgressbar("Test");
        for (int i = 0; i < 101; i++) {
            processbar.update(i);
            Thread.sleep(1000);
        }
        Thread.sleep(5000);
        processbar.stop();
    }

}
