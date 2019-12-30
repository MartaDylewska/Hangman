import javax.swing.*;
import java.awt.*;
import java.util.Random;

class ResultPanel extends JPanel {

    private JButton finish, play, newGame;
    private JLabel picture, result;
    private ImageIcon myPicture;
    private int pointPlayer1, pointPlayer2;

    //animacja
    private static Random random = new Random();
    private static int dimension = 30;
    private static Balloons[] balloons = new Balloons[9];
    private static Rain[] rain = new Rain[500];

    static void setLocal(){

        for (int i = 0; i < balloons.length; i++) {
            balloons[i] = new Balloons();
        }

        for (Balloons balloon : balloons) {
            balloon.setLocationX(random.nextInt(700));
            balloon.setLocationY(400);
            balloon.setDirectionX(random.nextInt(2) + 1);
            balloon.setDirectionY(random.nextInt(5) + 1);
            balloon.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        }

        for (int i = 0; i < rain.length; i++) {
            rain[i] = new Rain();
        }

        for (Rain aRain : rain) {
            aRain.setStartX(random.nextInt(700));
            aRain.setStartY(random.nextInt(400));
            aRain.setEndX(aRain.getStartX() + 2);
            aRain.setEndY(aRain.getStartY() + 4);
        }
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        if(pointPlayer1 == 1 && pointPlayer2 == 1) {
            //niebo
            graphics2D.setColor(new Color(220,235,255));
            graphics2D.fillRect(0, 0, 700, 400);
            for (Balloons balloon : balloons) {
                graphics2D.setColor(balloon.getColor());
                graphics2D.fillOval(balloon.getLocationX(), balloon.getLocationY(), dimension, dimension);
            }
        } else {
            //niebo
            graphics2D.setColor(new Color(200,200,200));
            graphics2D.fillRect(0, 0, 700, 400);
            for (Rain aRain : rain) {
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawLine(aRain.getStartX(), aRain.getStartY(), aRain.getEndX(), aRain.getEndY());
            }
        }
    }

    void animation() {

        while (true) {
            for (Balloons balloon : balloons) {
                if (balloon.getLocationY() + dimension < 0)
                    balloon.setLocationY(400);
                if (balloon.getLocationX() + dimension < 0) {
                    balloon.setLocationX(700);
                }
                balloon.setLocationX(balloon.getLocationX() - balloon.getDirectionX());
                balloon.setLocationY(balloon.getLocationY() - balloon.getDirectionY());
            }

            for (Rain aRain : rain) {
                if (aRain.getStartY() > 400) {
                    aRain.setStartY(0);
                    aRain.setEndY(4);
                }
                if (aRain.getStartX() > 700) {
                    aRain.setStartX(0);
                    aRain.setEndX(2);
                }
                aRain.setStartX(aRain.getStartX() + 2);
                aRain.setStartY(aRain.getStartY() + 2);
                aRain.setEndX(aRain.getEndX() + 2);
                aRain.setEndY(aRain.getEndY() + 2);
            }

            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    ResultPanel(HangJPanel hangJPanel) { //ostatnie okno z końcowymi wynikami gry

        this.pointPlayer1 = hangJPanel.getPointPlayer1();
        this.pointPlayer2 = hangJPanel.getPointPlayer2();

        setLayout(null);
        setBackground(new Color(215,216,218));

        createLabels();
        createButtons();
        setPicture();
        add();
    }

    private void createLabels() {

        picture = new JLabel();
        picture.setBounds(0, 0, 700, 450);

        result = new JLabel();
        result.setFont(LoginPanel.panelFont);
        result.setBounds(62,400,200,40);

    }

    private void createButtons() {

        play = new JButton();
        play.setText("Dalej");
        play.setFont(LoginPanel.panelFont);
        play.setBounds(274,450,150,40);

        newGame = new JButton();
        newGame.setText("Od nowa");
        newGame.setFont(LoginPanel.panelFont);
        newGame.setBounds(62,450,150,40);

        finish = new JButton();
        finish.setText("Koniec");
        finish.setFont(LoginPanel.panelFont);
        finish.setBounds(486, 450, 150, 40);
    }

    private void setPicture() {
        if (pointPlayer1 == 1 && pointPlayer2 == 1) {
            try {
                myPicture = new ImageIcon(this.getClass().getResource("doublewon.gif"));
            } catch (Exception e) {
                System.out.println("Problem with picture: doublewon.gif");
            }
        } else if (pointPlayer1 == 1 || pointPlayer2 == 1){
            try {
                myPicture = new ImageIcon(this.getClass().getResource("wonlost.gif"));
            } catch (Exception e) {
                System.out.println("Problem with picture: wonlost.gif");
            }
        } else {
            try {
                myPicture = new ImageIcon(this.getClass().getResource("doublelost.gif"));
            } catch (Exception e) {
                System.out.println("Problem with picture: doublelost.gif");
            }
        }
        picture.setIcon((myPicture));
    }

    private void add() {

        add(finish);
        add(play);
        add(newGame);
        add(result);
        add(picture);
    }

    JButton getConfirm() {
        return finish;
    }

    JButton getPlay() {
        return play;
    }

    JButton getNewGame() {
        return newGame;
    }

    JLabel getResult() {
        return result;
    }

    int getPointPlayer1() {
        return pointPlayer1;
    }

    int getPointPlayer2() {
        return pointPlayer2;
    }
}