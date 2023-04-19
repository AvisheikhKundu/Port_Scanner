import java.io.IOException;
import java.net.*;

public class scannerClass implements Runnable {
    private Socket sock;
    public String hostname;
    public int startPort, endPort;
    private Thread scanner = null;
    private int counter = 0;

    public scannerClass() {
        hostname = "127.0.0.1";
        startPort = 80;
        endPort = 81;
    }
    public scannerClass(String hostName, int portStart, int portStop) {
        hostname = hostName;
        startPort = portStart;
        endPort = portStop;
    }
    public void stopScan() {
        if (scanner != null) {
            scanner.stop();
            scannerGUI.addResult("\nScanner has been Stopped");
        }
    }
    public void startScan() {
        if (scanner == null) {
            scanner = new Thread(this);
            scanner.start();
        }
    }
    public void run() {
        if (endPort > startPort) {
            counter = this.startPort;
            while (counter <= endPort) {
                try {
                    sock = new Socket(hostname, counter);
                    sock.close();
                    scannerGUI.addResult("\nPort " + counter + " is Open");
                } catch (IOException e) {
                    scannerGUI.addResult("\nPort " + counter + " is Closed");
                }
                counter++;
            }

        }
        else {
            scannerGUI.addResult("\nStart port is greater then endPort");
        }

    }

}