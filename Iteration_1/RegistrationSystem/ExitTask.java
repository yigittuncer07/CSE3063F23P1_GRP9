import java.util.TimerTask;

public class ExitTask extends TimerTask {
     @Override
        public void run() {
            System.out.println("The system has timed out, please log in again.");
            System.exit(0);
        }
}


