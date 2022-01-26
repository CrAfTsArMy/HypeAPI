package de.craftsarmy.hyperapi.download;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class HypeDownloadTask {

    private final URL url;
    private final File file;

    @Deprecated(since = "2.5")
    public HypeDownloadTask(String url, File file) throws MalformedURLException {
        this(new URL(url), file);
    }

    public HypeDownloadTask(URL url, File file) {
        this.url = url;
        this.file = file;
    }

    public URL getUrl() {
        return url;
    }

    public File getFile() {
        return file;
    }

}
