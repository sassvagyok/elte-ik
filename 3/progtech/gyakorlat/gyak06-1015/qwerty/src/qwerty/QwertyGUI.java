/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qwerty;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author matte
 */
public class QwertyGUI {
    private JFrame frame;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JTextField textField;

    public QwertyGUI(int fieldWidth) {
        frame = new JFrame("Qwerty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textField = new JTextField(fieldWidth);
        textPanel.add(textField);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 8));
        
        for (Key key : Key.values()) {
            JButton button = new JButton(key.name());
            button.addActionListener(new QwertyActionListener(key));
            buttonPanel.add(button);
        }
        
        frame.getContentPane().add(BorderLayout.NORTH, textPanel);
        frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
        
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    class QwertyActionListener implements ActionListener {
        private final Key key;

        public QwertyActionListener(Key key) {
            this.key = key;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String currentText = textField.getText();
            
            currentText = switch(key) {
                case Q, W, E, R, T, Y -> currentText + key.name();
                case BS -> currentText.length() > 0 ? currentText.substring(0, currentText.length() - 1) : "";
                case CLR -> "";
            };
            
            textField.setText(currentText);
        }
    }
}
