import java.util.TimerTask;

public class ExitTask extends TimerTask {
     @Override
        public void run() {
            System.out.println("System will exit after 300 seconds.");
            System.exit(0);
        }
}


