import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class scannerGUI extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JButton startScan;
    private JLabel welcomeText, hostText, startText, stopText;
    private static JTextArea results;
    private JTextField host, startPort, stopPort;
    private static int lines = 0;
    private final String START = "Start Scanner";
    private final String STOP = "Stop Scanner";
    private scannerClass scanner;

    public scannerGUI() {
        super("Port Scanner v1 by Tim H.");
        setSize(200, 300);
        setLocation(400, 400);
        startScan = new JButton(START);
        startScan.addActionListener(this);
        welcomeText = new JLabel("Welcome to Port Scanner V1");
        hostText = new JLabel("Host IP");
        startText = new JLabel("Starting Port");
        stopText = new JLabel("Stopping Port");
        results = new JTextArea(15, 20);
        startPort = new JTextField("80", 5);
        stopPort = new JTextField("81", 5);
        host = new JTextField("127.0.0.1", 10);
        FlowLayout flo = new FlowLayout(FlowLayout.LEFT);
        setLayout(flo);
        add(welcomeText);
        add(hostText);
        add(host);
        add(startText);
        add(startPort);
        add(stopText);
        add(stopPort);
        add(startScan);
        add(results);
        setVisible(true);
    }

    public static void main(String args[]) {
        scannerGUI sg = new scannerGUI();
    }

    public static void addResult(String result) {
        results.insert(result, 0);
    }

    public void actionPerformed(ActionEvent avt) {
        Object o = avt.getSource();
        if (o == startScan) {
            if (startScan.getText().compareTo(START) == 0) {
                int portStart = Integer.parseInt(startPort.getText());
                int portStop = Integer.parseInt(stopPort.getText());
                addResult("\nScanner has been started");
                String hostName = host.getText();
                startScan.setText(STOP);
                scanner = new scannerClass(hostName, portStart, portStop);
                scanner.startScan();
            } else {
                scanner.stopScan();
                startScan.setText(START);
            }
        }
    }
}