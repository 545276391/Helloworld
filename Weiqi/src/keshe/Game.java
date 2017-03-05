/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keshe;

import javax.swing.JFrame;

/**
 *
 * @author hpl
 */

public class Game {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("13081214 尹睿尧");
        frame.add(new Yugi());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
