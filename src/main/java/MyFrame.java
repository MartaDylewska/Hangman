import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.net.URL;

class MyFrame extends JFrame {

    MyFrame() {
        try {
            URL iconURL = getClass().getResource("Hangman-game.png");
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }
        catch (Exception ex){
            System.out.println("Problem with picture: Hangman-game.png");
        }

        setSize(700,530);
        setTitle("Szubienica");
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 2 - getWidth() / 2;
        int posY = height / 2 - getHeight() / 2;

        setLocation(posX, posY);
        LoginPanel loginPanel = new LoginPanel();

        add(loginPanel);

        loginPanel.getConfirm().addActionListener(e -> {

            CreatePanel createPanel = new CreatePanel();
            if (loginPanel.check()) {

                createPanel.getLabel().setText(loginPanel.getPlayer1().getText() + " wymyśla hasło");
                add(createPanel);
                remove(loginPanel);
                repaint();
                revalidate();

            } else {
                JOptionPane.showMessageDialog(null, loginPanel.getMessage());
            }

            createPanel.getConfirm().addActionListener(e12 -> {

                HangJPanel hangJPanel = new HangJPanel(createPanel, loginPanel);

                if (createPanel.check()) {
                    hangJPanel.setCurrentPlayers();
                    add(hangJPanel);
                    remove(createPanel);
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, createPanel.getMessage());
                }

                hangJPanel.getConfirm().addActionListener(e1 -> {

                    if (HangJPanel.totalGamesCounter == 2) {
                        ResultPanel resultPanel = new ResultPanel(hangJPanel);
                        add(resultPanel);
                        remove(hangJPanel);
                        repaint();
                        revalidate();

                        resultPanel.getConfirm().addActionListener(e2 ->
                                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
                    } else {
                        createPanel.getLabel().setText(loginPanel.getPlayer2().getText() + " wymyśla hasło");
                        createPanel.getCategories().setSelectedIndex(0);
                        createPanel.setHint();

                        add(createPanel);
                        remove(hangJPanel);
                        repaint();
                        revalidate();
                    }
                });
            });
        });
    }

    static Border blackBorder(){
        return BorderFactory.createLineBorder(Color.black, 1);
    }
}