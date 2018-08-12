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
public class BroadcastMessage implements Message{
    
    public void sendMessage(String s, MultiThreadClient c) {
        c.getPw().println("All/" + s);
    }
    
}
