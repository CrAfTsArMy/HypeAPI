package de.craftsarmy.hyperapi;

import de.craftsarmy.hyperapi.console.HypeConsoleProzessbar;
import de.craftsarmy.hyperapi.download.HypeDownloadTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HypeDownloader {

    public static void main(String[] args) {
        HypeDownloader downloader = new HypeDownloader();
        downloader.download("https://web1.craftsblock.de/cloud/index.php/s/6YTnLXKbsYawAnq/download", new File("C:\\Users\\sve_m\\Desktop\\test.mov"));
    }

    private HypeConsoleProzessbar prozessbar;
    private final ConcurrentLinkedQueue<HypeDownloadTask> tasks = new ConcurrentLinkedQueue<>();

    public void download(String url, File file) {
        try {
            download(new URL(url), file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void download(URL url, File file) {
        HypeDownloadTask task = new HypeDownloadTask(url, file);
        if (prozessbar == null)
            download(task);
        else
            tasks.add(task);
    }

    private void next() {
        if (!tasks.isEmpty()) {
            HypeDownloadTask task = (HypeDownloadTask) tasks.toArray()[0];
            tasks.remove(task);
            download(task);
        }
    }

    private void download(HypeDownloadTask task) {
        if(prozessbar == null)
            prozessbar = new HypeConsoleProzessbar(task.getUrl().toString());
        prozessbar.update(0);
        BufferedInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            URLConnection connection = task.getUrl().openConnection();
            connection.setRequestProperty("User-Agent", "hypeDonwloader/v2.5");
            int size = connection.getContentLength();
            if (size < 0) {
                System.err.println("Could not get the file size");
            } else {
                System.out.println("File size: " + size);
            }
            connection.connect();
            inputStream = new BufferedInputStream(connection.getInputStream());
            outputStream = new FileOutputStream(task.getFile());
            byte[] data = new byte[1024];
            double sumCount = 0.0D;
            int count;
            while ((count = inputStream.read(data, 0, 1024)) != -1) {
                outputStream.write(data, 0, count);
                sumCount += count;
                if (size > 0)
                    prozessbar.update(sumCount / size * 100.0D);
            }
        } catch (IOException exception) {
            throw new Error(exception);
        } finally {
            prozessbar.update(100.0D);
            prozessbar.stop();
            prozessbar = null;
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException exception) {
                throw new Error(exception);
            }
        }
    }

}
