package de.craftsarmy.hyperapi.file;

import java.io.File;
import java.net.URI;

public class HypeFile extends File {

    public HypeFile(String pathname) {
        super(pathname);
    }

    public HypeFile(String parent, String child) {
        super(parent, child);
    }

    public HypeFile(File parent, String child) {
        super(parent, child);
    }

    public HypeFile(URI uri) {
        super(uri);
    }

}
