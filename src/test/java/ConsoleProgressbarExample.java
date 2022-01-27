import de.craftsarmy.hyperapi.console.HypeConsoleProgressbar;

public class ConsoleProgressbarExample {

    public static void main(String[] args) {
        HypeConsoleProgressbar progressbar = new HypeConsoleProgressbar("Progress Bar Example");
        for(int i = 0; i <= 100; i++) {
            try {
                progressbar.update(i);
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        progressbar.stop();
    }

}
