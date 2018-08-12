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
public class DisconnectMessage implements Message{
    public void sendMessage(MultiThreadClient c)
    {
        c.getPw().println("All/A client has disconnected.");
    }
}
