import java.awt.EventQueue;
import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
//            MyFrame.setDefaultLookAndFeelDecorated(true);
            MyFrame window;
            window = new MyFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            SwingUtilities.updateComponentTreeUI(window);
        });

        LoginPanel loginPanel = new LoginPanel();
        CreatePanel createPanel = new CreatePanel(loginPanel);
        HangJPanel hangJPanel = new HangJPanel(createPanel, loginPanel);
        ResultPanel resultPanel = new ResultPanel(loginPanel, hangJPanel);

        ResultPanel.setLocal();
        resultPanel.animation();

    }
}

