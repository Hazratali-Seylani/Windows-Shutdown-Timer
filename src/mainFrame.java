import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class mainFrame extends JFrame{
    private JButton a15MinutesButton;
    private JButton a30MinutesButton;
    private JButton a45MinutesButton;
    private JButton a60MinutesButton;
    private JButton confirmButton;
    private JTextField timeField;
    private JRadioButton minutesRadioButton;
    private JRadioButton secondsRadioButton;
    private JButton cancelShutdownButton;
    private JPanel mainPanel;
    private JRadioButton hoursRadioButton;

    public mainFrame() {

        ButtonGroup group = new ButtonGroup();  // We added every radio buttons in the group,because in each group only one can be selected at a time
        group.add(secondsRadioButton);
        group.add(minutesRadioButton);
        group.add(hoursRadioButton);
        secondsRadioButton.setSelected(true);   // This code will make sure that seconds radio button is default selected button

        a15MinutesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Process runtime = Runtime.getRuntime().exec("cmd /c shutdown -s -t 900");
                    a30MinutesButton.setEnabled(false);
                    a45MinutesButton.setEnabled(false);
                    a60MinutesButton.setEnabled(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        a30MinutesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Process runtime = Runtime.getRuntime().exec("cmd /c shutdown -s -t 1800");
                    a15MinutesButton.setEnabled(false);
                    a45MinutesButton.setEnabled(false);
                    a60MinutesButton.setEnabled(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        a45MinutesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Process runtime = Runtime.getRuntime().exec("cmd /c shutdown -s -t 2700");
                    a15MinutesButton.setEnabled(false);
                    a30MinutesButton.setEnabled(false);
                    a60MinutesButton.setEnabled(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        a60MinutesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Process runtime = Runtime.getRuntime().exec("cmd /c shutdown -s -t 3600");
                    a15MinutesButton.setEnabled(false);
                    a30MinutesButton.setEnabled(false);
                    a45MinutesButton.setEnabled(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        cancelShutdownButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Process runtime = Runtime.getRuntime().exec("cmd /c shutdown -a");
                    a15MinutesButton.setEnabled(true);
                    a30MinutesButton.setEnabled(true);
                    a45MinutesButton.setEnabled(true);
                    a60MinutesButton.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    int userDefinedTime = 0;
                    if (secondsRadioButton.isSelected()){
                        userDefinedTime = Integer.parseInt(timeField.getText());
                    }
                    else if (minutesRadioButton.isSelected()){
                        userDefinedTime = Integer.parseInt(timeField.getText())*60;
                    }
                    else if (hoursRadioButton.isSelected()){
                        userDefinedTime = Integer.parseInt(timeField.getText())*3600;
                    }

                    Process runtime = Runtime.getRuntime().exec("cmd /c shutdown -s -t " + userDefinedTime);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        mainFrame timerWindow = new mainFrame();
        timerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // For stopping the project when clicking on X
        timerWindow.setVisible(true);                               // For making the frame visible
        timerWindow.setTitle("Windows Shutdown Timer");             // Title
        timerWindow.setSize(500, 200);                // Setting default windows size
        timerWindow.setContentPane(timerWindow.mainPanel);          // Setting something, I don't know why, just write it
        timerWindow.setResizable(false);                            // To disable the window resizing
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ali\\Desktop\\Code\\Window Shutdown Timer 2\\imgs\\timer-60.png");
        timerWindow.setIconImage(icon);
        centreWindow(timerWindow);
    }

    public static void centreWindow(Window frame) {                 // This function makes sure that, every time program is start, it will start in the center
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}
