/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatApp;

/**
 *
 * @author nicky
 */
public class MessageTo implements Message{

    public void sendMessage(String msg, String receiver, MultiThreadClient c) {
        c.getPw().println("SendTo/" + receiver + "/" + msg);
    }
    
}
