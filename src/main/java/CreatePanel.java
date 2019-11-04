import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CreatePanel extends JPanel {

    static JLabel label;
    private JComboBox<String> categories;
    private JTextField word;
    private JButton reset, confirm;
    private String message, player1, player2;
    LoginPanel loginPanel;

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    CreatePanel(LoginPanel loginPanel){

        this.loginPanel = loginPanel;
        this.player1 = loginPanel.getPlayer1().getText();
        this.player2 = loginPanel.getPlayer2().getText();


        setLayout(null);

        createFields();
        createButtons();
        add();
        actions();
    }

    private void createFields(){

        label = new JLabel();
        label.setBounds(100, 100, 300, 30);
        label.setText(player1 + " wymyśla hasło:");

        String[] categoriesOptions = {"--wybierz--", "zwierzę", "roślina", "pierwiastek", "przedmiot", "pojazd", "państwo", "miasto", "inne"};

        categories = new JComboBox<>(categoriesOptions);
        categories.setBounds(100,150,300,30);

        word = new JTextField();
        word.setBounds(100, 200, 300, 30);
        word.setText("Hasło");
        word.setForeground(Color.gray);
        word.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                word.setText("");
                word.setForeground(Color.black);
            }
        });
    }

    private void createButtons(){

        reset = new JButton();
        reset.setText("Wyczyść");
        reset.setBounds(100, 250, 150, 30);

        confirm = new JButton();
        confirm.setText("OK");
        confirm.setBounds(250, 250, 150, 30);
    }

    private void add(){

        add(label);
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

        confirm.addActionListener(e -> {
            if(!check()) {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    boolean check(){
        boolean isEmpty = categories.getSelectedIndex() != 0 && word.getText().length() != 0;
        Pattern pattern = Pattern.compile("[A-ZĆŁÓŚŻŹa-zćłóśżź][a-ząćęłńóśżź]+");
        Matcher matcher = pattern.matcher(word.getText());
        boolean isIncorrect = matcher.matches();
        if(!isEmpty)
            message = "Uzupełnij dane.";
        else if(!isIncorrect)
            message = "Niedozwolone hasło.";
        return isEmpty && isIncorrect;
    }

    JButton getConfirm() {
        return confirm;
    }

//    public JLabel getLabel(){
//        return label;
//    }

    JTextField getWord(){
        return word;
    }

    JComboBox getCategories() {
        return this.categories;
    }

    // public static void setLabelText(String textForLabel){
    //   label.setText(textForLabel);
    //}
}