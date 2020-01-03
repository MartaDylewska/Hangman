
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DesktopIconUI;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

class MyFrame extends JFrame {


    private static final String musicName = "sound.wav";
    private static final String hangmanIconName = "Hangman-game.Png";

    private LoginPanel loginPanel;
    private CreatePanel createPanel;
    private ResultPanel resultPanel;
    private HangJPanel hangJPanel;

    private int pointsPlayer1, pointsPlayer2, option;
    private Dictionary dictionary;

    MyFrame() {

        playMusic();

//        "javax.swing.plaf.metal.MetalLookAndFeel"
//        "javax.swing.plaf.nimbus.NimbusLookAndFeel"

/*        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }*/

        try {
            URL iconURL = getClass().getResource(hangmanIconName);
            ImageIcon icon = new ImageIcon(iconURL);
            //setIconImage(icon.getImage());
            setIconImage(Toolkit.getDefaultToolkit().getImage(iconURL));
        }
        catch (Exception ex){
            System.out.println("Problem with picture: Hangman-game.png");
        }

        setSize(700,530);
        setTitle("Hangman / Szubienica");
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 2 - getWidth() / 2;
        int posY = height / 2 - getHeight() / 2;

        setLocation(posX, posY);

        dictionary = Dictionary.getInstance();

        loginPanel = new LoginPanel();

        getContentPane().add(loginPanel);

        loginPanel.getPlayer1().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getConfirm().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        loginPanel.getPlayer2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getConfirm().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        loginPanel.getConfirm().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getConfirm().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        loginPanel.getReset().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginPanel.getReset().doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        loginPanel.getConfirm().addActionListener(e -> {

            createPanel = new CreatePanel(loginPanel);
            option = loginPanel.getOption();
            if (loginPanel.check()) {

                createPanel.getLabel().setText(loginPanel.getPlayer1().getText() + dictionary.get("guess")[option]);
                getContentPane().add(createPanel);
                getContentPane().remove(loginPanel);
                repaint();
                revalidate();

            } else {
                JOptionPane.showMessageDialog(null, loginPanel.getMessage());
            }

            createPanel.getWord().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        createPanel.getConfirm().doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            createPanel.getConfirm().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        createPanel.getConfirm().doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            createPanel.getReset().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        createPanel.getReset().doClick();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });

            createPanel.getConfirm().addActionListener(e12 -> {

                hangJPanel = new HangJPanel(createPanel, loginPanel);

                if (createPanel.check()) {
                    hangJPanel.setCurrentPlayers();
                    getContentPane().add(hangJPanel);
                    getContentPane().remove(createPanel);
                    repaint();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, createPanel.getMessage());
                }

                hangJPanel.getConfirm().addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER)
                            hangJPanel.getConfirm().doClick();}

                    @Override
                    public void keyReleased(KeyEvent e) {}
                });

                hangJPanel.getConfirm().addActionListener(e1 -> {

                    if (HangJPanel.totalGamesCounter % 2 == 0) {
                        resultPanel = new ResultPanel(loginPanel, hangJPanel);
                        pointsPlayer1 += resultPanel.getPointPlayer1();
                        pointsPlayer2 += resultPanel.getPointPlayer2();
                        resultPanel.getResult().setText(loginPanel.getPlayer1().getText() + " " + pointsPlayer1 + " : "
                                + pointsPlayer2 + " " + loginPanel.getPlayer2().getText());
                        getContentPane().add(resultPanel);
                        getContentPane().remove(hangJPanel);
                        repaint();
                        revalidate();

                        resultPanel.getConfirm().addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {

                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    resultPanel.getConfirm().doClick();
                                }
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {

                            }
                        });

                        resultPanel.getConfirm().addActionListener(e2 ->
                                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

                        resultPanel.getPlay().addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {

                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    resultPanel.getConfirm().doClick();
                                }
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {

                            }
                        });

                        resultPanel.getPlay().addActionListener(e2 -> {

                            createPanel.getLabel().setText(loginPanel.getPlayer1().getText() + dictionary.get("guess")[option]);
                            createPanel.getCategories().setSelectedIndex(0);
                            createPanel.getWord().setText("");

                            getContentPane().add(createPanel);
                            getContentPane().remove(resultPanel);
                            repaint();
                            revalidate();
                        });

                        resultPanel.getNewGame().addKeyListener(new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {

                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                    resultPanel.getConfirm().doClick();
                                }
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {

                            }
                        });

                        resultPanel.getNewGame().addActionListener(e2 ->{
                            pointsPlayer1 = 0;
                            pointsPlayer2 = 0;
                            getContentPane().add(loginPanel);
                            getContentPane().remove(resultPanel);
                            repaint();
                            revalidate();
                        });

                    } else {
                        createPanel.getLabel().setText(loginPanel.getPlayer2().getText() + dictionary.get("guess")[option]);
                        createPanel.getCategories().setSelectedIndex(0);
                        createPanel.getWord().setText("");

                        getContentPane().add(createPanel);
                        getContentPane().remove(hangJPanel);
                        repaint();
                        revalidate();
                    }
                });
            });
        });
    }

    private void playMusic(){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(musicName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e){
            System.out.println("Problem with music.");
        }
    }

    static Border blackBorder(){
        return BorderFactory.createLineBorder(Color.black, 1);
    }

}