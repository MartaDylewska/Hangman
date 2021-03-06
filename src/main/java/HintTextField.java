import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JTextField implements FocusListener {

    private String hint;
    private boolean showingHint;

    HintTextField(String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
            super.setFont(LoginPanel.panelFont);
            super.setForeground(Color.black);
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
            super.setFont(LoginPanel.hintFont);
            super.setForeground(Color.gray);
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
