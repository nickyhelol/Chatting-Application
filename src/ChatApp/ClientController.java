/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatApp;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author nicky
 */
public class ClientController extends JFrame {

    private MultiThreadClient model;
    private ClientView view;
    BroadcastMessage broadcast;
    MessageTo messageTo;
    DisconnectMessage disconnectMessage;

    public ClientController() {
        view = new ClientView();
        model = new MultiThreadClient(view);
        broadcast = new BroadcastMessage();
        messageTo = new MessageTo();
        disconnectMessage = new DisconnectMessage();

        model.getView().getSendBtn().addActionListener((ActionEvent e) -> {
            sendBtnEventHandler();
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                eventHandleWindowClosed();
            }
        });

        getContentPane().add(model.getView());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 650);
        setResizable(false);

    }

    public static void main(String[] args) {
        ClientController JFrame = new ClientController();
        JFrame.setVisible(true);
    }

    private void sendBtnEventHandler() {

        String msg = model.getView().getInputField().getText();
        String receiver = model.getView().getReceiver().getText();
        if (receiver.compareToIgnoreCase("all") == 0) {
            broadcast.sendMessage(msg, model);
        } else {
            messageTo.sendMessage(msg, receiver, model);
        }
    }

    private void eventHandleWindowClosed(){
        disconnectMessage.sendMessage(model);
        model.finish();
    }
}
