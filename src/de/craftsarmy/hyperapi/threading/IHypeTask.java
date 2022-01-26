package de.craftsarmy.hyperapi.threading;

import de.craftsarmy.hyperapi.threading.HypeThread;

import java.io.IOException;

public interface IHypeTask {

    void run(HypeThread hypeThread) throws Exception;

}
