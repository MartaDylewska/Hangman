import javax.swing.*;
import java.awt.*;

class LoginPanel extends JPanel{

    private JLabel playersLabel, imageLabel;
    private ImageIcon headImage;
    private JTextField player1, player2;
    private JButton reset, confirm;
    private String message;
    private int option;
    private JRadioButton polish, english;
    private Dictionary dictionary;
    private ButtonGroup group;

    static Font panelFont = new Font("Comic Sans MS", Font.PLAIN, 18);
    static Font hintFont = new Font("Comic Sans MS", Font.ITALIC, 18);

    LoginPanel(){ //panel wpisywania graczy

        setLayout(null);

        setBackground(new Color(215,216,218));

        dictionary = Dictionary.getInstance();

        createFields();
        createButtons();
        createRadios();
        setImage();
        setTexts(option);
        add();
        actions();

    }

    private void createFields(){

        imageLabel = new JLabel();
        imageLabel.setBounds(20,400,200, 100);

        playersLabel = new JLabel();
        playersLabel.setBounds(200, 150, 300, 30);
        playersLabel.setFont(panelFont);

        player1 = new HintTextField(dictionary.get("play1")[option]);
        player1.setBounds(200, 200, 300, 30);
        player1.setFont(hintFont);
        player1.setForeground(Color.gray);
        player1.setBorder(MyFrame.blackBorder());

        player2 = new HintTextField(dictionary.get("play2")[option]);
        player2.setBounds(200, 250, 300, 30);
        player2.setFont(hintFont);
        player2.setForeground(Color.gray);
        player2.setBorder(MyFrame.blackBorder());
    }

    private void setTexts(int option){
        playersLabel.setText(dictionary.get("plays")[option]);
        player1.setText(dictionary.get("play1")[option]);
        player2.setText(dictionary.get("play2")[option]);
        reset.setText(dictionary.get("reset")[option]);
    }

    private void createButtons(){

        reset = new JButton();
        reset.setBounds(200, 300, 150, 30);
        reset.setFont(panelFont);

        confirm = new JButton();
        confirm.setText("OK");
        confirm.setBounds(350, 300, 150, 30);
        confirm.setFont(panelFont);
    }

    private void createRadios(){

        english = new JRadioButton(new ImageIcon(this.getClass().getResource("uk20.png")));
        english.setBounds(570,20,30,30);
        english.setBackground(new Color(215,216,218));

        polish = new JRadioButton(new ImageIcon(this.getClass().getResource("pl20.png")));
        polish.setBounds(600,20,30,30);
        polish.setBackground(new Color(215,216,218));

        group = new ButtonGroup();
        group.add(english);
        group.add(polish);
    }

    private void add(){

        add(imageLabel);
        add(playersLabel);
        add(player1);
        add(player2);
        add(reset);
        add(confirm);
        add(polish);
        add(english);
    }

    private void actions(){

        reset.addActionListener(e -> {
            player1.setText("");
            player2.setText("");
        });

        english.addActionListener(e -> {
            option = 1;
            setTexts(option);
        });

        polish.addActionListener(e -> {
            option = 0;
            setTexts(option);
        });
    }

    private void setImage(){
        try {
            headImage = new ImageIcon(this.getClass().getResource("head.png"));
        } catch (Exception e) {
            System.out.println("Problem with picture: head.png");
        }

        imageLabel.setIcon(headImage);
    }

    boolean check(){
        boolean isNotEmpty = player1.getText().length() != 0 && player2.getText().length() != 0;
        boolean isDifferent = !(player1.getText().equals(player2.getText()));

        if(!isNotEmpty)
            message = dictionary.get("message1")[option];
        else if(!isDifferent)
            message = dictionary.get("message2")[option];

        return isNotEmpty && isDifferent;
    }

    JButton getConfirm() {
        return confirm;
    }
    JButton getReset() {
        return reset;
    }

    JTextField getPlayer1(){
        return player1;
    }

    JTextField getPlayer2(){
        return player2;
    }

    String getMessage(){
        return message;
    }

    int getOption() {
        return option;
    }
}