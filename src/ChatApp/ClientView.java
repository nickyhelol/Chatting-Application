/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatApp;

import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author nicky
 */
public class ClientView extends JPanel{

    private JLabel label;
    private JLabel chatLabel;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendBtn;
    private JTextField receiver;

    public ClientView() {
        setLayout(null);

        label = new JLabel("Let's start to chat!");
        label.setLocation(180, 20);
        label.setSize(150, 20);
        label.setFont(new Font("Times", Font.BOLD, 16));
        this.add(label);

        chatLabel = new JLabel("Chating Room");
        chatLabel.setLocation(20, 50);
        chatLabel.setSize(150, 20);
        chatLabel.setFont(new Font("Times", Font.ITALIC, 16));
        this.add(chatLabel);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setLocation(20, 80);
        scrollPane.setSize(430, 400);
        this.add(scrollPane);

        inputField = new JTextField();
        inputField.setLocation(20, 520);
        inputField.setSize(230, 30);
        this.add(inputField);

        sendBtn = new JButton("SendTo");
        sendBtn.setLocation(260, 520);
        sendBtn.setSize(90, 30);
        this.add(sendBtn);
        
        receiver = new JTextField();
        receiver.setLocation(360, 520);
        receiver.setSize(100, 30);
        this.add(receiver);

        this.setSize(500, 620);

    }

    public JTextField getReceiver() {
        return receiver;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setChatLabel(JLabel chatLabel) {
        this.chatLabel = chatLabel;
    }


    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }

    public void setSendBtn(JButton sendBtn) {
        this.sendBtn = sendBtn;
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getChatLabel() {
        return chatLabel;
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public JButton getSendBtn() {
        return sendBtn;
    }
}
