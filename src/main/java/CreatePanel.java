import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreatePanel extends JPanel {

    private JLabel playerLabel, imageLabel;
    private ImageIcon shhImage;
    private JComboBox<String> categories;
    private JTextField word;
    private JButton reset, confirm;
    private String message;
    private int option;
    private Dictionary dictionary;

    CreatePanel(LoginPanel loginPanel){ //panel do wymyślania hasła

        this.option = loginPanel.getOption();

        setLayout(null);
        setBackground(new Color(215,216,218));

        dictionary = Dictionary.getInstance();

        createFields();
        createButtons();
        setImage();
        add();
        actions();
    }

    private void createFields(){

        imageLabel = new JLabel();
        imageLabel.setBounds(500,150,200, 260);

        playerLabel = new JLabel();
        playerLabel.setBounds(200, 150, 300, 30);
        playerLabel.setFont(LoginPanel.panelFont);

        String[] categoriesOptions = {dictionary.get("categories0")[option],dictionary.get("categories1")[option],
                dictionary.get("categories2")[option],dictionary.get("categories3")[option],dictionary.get("categories4")[option],
                dictionary.get("categories5")[option],dictionary.get("categories6")[option],dictionary.get("categories7")[option],
                dictionary.get("categories8")[option],dictionary.get("categories9")[option],dictionary.get("categories10")[option],
                dictionary.get("categories11")[option],dictionary.get("categories12")[option],dictionary.get("categories13")[option],
                dictionary.get("categories14")[option],dictionary.get("categories15")[option],dictionary.get("categories16")[option]};


        categories = new JComboBox<>(categoriesOptions);
        categories.setBounds(200,200,300,30);
        categories.setFont(LoginPanel.panelFont);
        categories.setBorder(MyFrame.blackBorder());

        word = new HintTextField(dictionary.get("word")[option]);
        word.setFont(LoginPanel.hintFont);
        word.setForeground(Color.gray);
        word.setBounds(200, 250, 300, 30);
        word.setToolTipText(dictionary.get("tip")[option]);
        word.setBorder(MyFrame.blackBorder());
    }

    private void createButtons(){

        reset = new JButton();
        reset.setText(dictionary.get("reset")[option]);
        reset.setBounds(200, 300, 150, 30);
        reset.setFont(LoginPanel.panelFont);

        confirm = new JButton();
        confirm.setText("OK");
        confirm.setBounds(350, 300, 150, 30);
        confirm.setFont(LoginPanel.panelFont);
    }

    private void add(){

        add(imageLabel);
        add(playerLabel);
        add(categories);
        add(word);
        add(reset);
        add(confirm);
    }

    private void actions(){
        reset.addActionListener(e -> {
            word.setText("");
            categories.setSelectedIndex(0);
        });
    }

    boolean check(){
        boolean isNotEmpty = categories.getSelectedIndex() != 0 && word.getText().length() != 0;

        Pattern pattern = Pattern.compile(dictionary.get("wordPattern")[option]);
        Matcher matcher = pattern.matcher(word.getText());
        boolean isCorrect = matcher.matches();

        boolean isProperLength = word.getText().length()<=32;

        if(!isNotEmpty)
            message = dictionary.get("message1")[option];
        else if(!isCorrect)
            message = dictionary.get("message3")[option];
        else if(!isProperLength)
            message = dictionary.get("message4")[option];
        return isNotEmpty && isCorrect && isProperLength;
    }

    private void setImage(){
        try {
            shhImage = new ImageIcon(this.getClass().getResource("shh.png"));
        } catch (Exception e) {
            System.out.println("Error");
        }

        imageLabel.setIcon(shhImage);
    }

    String getMessage(){
        return message;
    }

    JButton getConfirm() {
        return confirm;
    }

    JButton getReset() {
        return reset;
    }

    JLabel getLabel(){
        return playerLabel;
    }

    JTextField getWord(){
        return word;
    }

    JComboBox getCategories() {
        return this.categories;
    }
}