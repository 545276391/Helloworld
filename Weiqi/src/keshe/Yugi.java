/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keshe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author hpl
 */

public class Yugi extends JPanel {

    JButton b[][] = new JButton[19][19];
    int[][] a = new int[19][19];

    JButton play;  
    boolean start;
    Timer timer;
    int hour = 0;
    int minute = 0;
    int second = 0;
    String time;
    JLabel timeLabel;
    String[] s = {"先手", "后手"};
    JComboBox combo;

    public Yugi() {
        setPreferredSize(new Dimension(500, 500));
        setLayout(new BorderLayout());
        JPanel up = new JPanel();
        up.setLayout(new GridLayout(19, 19));
        JPanel down = new JPanel();
        start = false;
        play = new JButton("PLAY");
        play.addActionListener(new Listener());
        down.add(play);
        timeLabel = new JLabel("00:00:00");
        Font f = new Font("黑体", 1, 50);
        timeLabel.setFont(f);
        down.add(timeLabel);
        timer = new Timer(1000, new Listener2());
        combo = new JComboBox(s);
        combo.addActionListener(new Listener());
        down.add(combo);

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                a[i][j] = 0;
                b[i][j] = new JButton();
                up.add(b[i][j]);
                b[i][j].addActionListener(new Listener());
            }
        }

        add(down, BorderLayout.SOUTH);
        add(up, BorderLayout.CENTER);

    }

    private class Listener implements ActionListener {

        int i, j, k, m, l;
        Random r = new Random();

        void AI() {

            if (i > 0 && i < 18 && j > 0 && j < 18) {
                l = r.nextInt(4);
                switch (l) {
                    case 0:
                        k = i;
                        m = j - 1;
                        break;
                    case 1:
                        k = i - 1;
                        m = j;
                        break;
                    case 2:
                        k = i;
                        m = j + 1;
                        break;
                    case 3:
                        k = i + 1;
                        m = j;
                        break;
                }
            } else if (i == 18 && j > 0 && j < 18) {
                l = r.nextInt(3);
                switch (l) {
                    case 0:
                        k = i;
                        m = j - 1;
                        break;
                    case 1:
                        k = i - 1;
                        m = j;
                        break;
                    case 2:
                        k = i;
                        m = j + 1;
                        break;
                }
            } else if (i == 0 && j > 0 && j < 18) {
                l = r.nextInt(3);
                switch (l) {
                    case 0:
                        k = i;
                        m = j - 1;
                        break;
                    case 1:
                        k = i + 1;
                        m = j;
                        break;
                    case 2:
                        k = i;
                        m = j + 1;
                        break;
                }
            } else if (j == 0 && i > 0 && i < 18) {
                l = r.nextInt(3);
                switch (l) {
                    case 0:
                        k = i - 1;
                        m = j;
                        break;
                    case 1:
                        k = i + 1;
                        m = j;
                        break;
                    case 2:
                        k = i;
                        m = j + 1;
                        break;
                }
            } else if (j == 18 && i > 0 && i < 18) {
                l = r.nextInt(3);
                switch (l) {
                    case 0:
                        k = i - 1;
                        m = j;
                        break;
                    case 1:
                        k = i + 1;
                        m = j;
                        break;
                    case 2:
                        k = i;
                        m = j - 1;
                        break;
                }
            } else if (i == 18 && j == 0) {
                k = i - 1;
                m = j + 1;
            } else if (j == 18 && i == 0) {
                k = i + 1;
                m = j - 1;
            } else if (i == 0 && j == 0) {
                k = i + 1;
                m = j + 1;
            } else if (i == 18 && j == 18) {
                k = i - 1;
                m = j - 1;
            }

        }

        boolean check() {
            if (i > 1 && i < 17 && j > 1 && j < 17) {

                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 1 && j > 1 && j < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 1 && i > 1 && i < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 17 && j > 1 && j < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 17 && i > 1 && i < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 0 && j > 1 && j < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 0 && i > 1 && i < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 18 && j > 1 && j < 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 18 && i > 1 && i < 17) {
                //左边旗子是否快被包围
                if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 1 && i == 0) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 1 && i == 18) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i][j + 2] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 1 && j == 1) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 1 && j == 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 1 && j == 0) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 1 && j == 18) {
                //左边旗子是否快被包围
                if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 17 && i == 0) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (j == 17 && i == 18) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 17 && j == 1) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 17 && j == 17) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //左边旗子是否快被包围
                else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0 && a[i - 1][j + 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j + 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 17 && j == 0) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i - 1][j + 1] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0 && a[i + 1][j + 1] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 17 && j == 18) {
                //左边旗子是否快被包围
                if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i + 1][j - 1] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i][j - 2] != 0 && a[i - 1][j - 1] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 0 && j == 0) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i + 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j + 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 0 && j == 18) {
                //左边旗子是否快被包围
                if (a[i][j - 1] == 2 && a[i][j - 2] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i + 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } //下面旗子是否快被包围
                else if (a[i + 1][j] == 2 && a[i + 1][j - 1] != 0) {
                    k = i + 2;
                    m = j;
                    return false;
                } else if (a[i + 1][j] == 2 && a[i + 2][j] != 0) {
                    k = i + 1;
                    m = j - 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 18 && j == 0) {
                //右边旗子是否快被包围
                if (a[i][j + 1] == 2 && a[i - 1][j + 1] != 0) {
                    k = i;
                    m = j + 2;
                    return false;
                } else if (a[i][j + 1] == 2 && a[i][j + 2] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j + 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j + 1;
                    return false;
                } else {
                    return true;
                }

            } else if (i == 18 && j == 18) {
                //左边旗子是否快被包围
                if (a[i][j - 1] == 2 && a[i][j - 2] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else if (a[i][j - 1] == 2 && a[i - 1][j - 1] != 0) {
                    k = i;
                    m = j - 2;
                    return false;
                } //上面旗子是否快被包围
                else if (a[i - 1][j] == 2 && a[i - 1][j - 1] != 0) {
                    k = i - 2;
                    m = j;
                    return false;
                } else if (a[i - 1][j] == 2 && a[i - 2][j] != 0) {
                    k = i - 1;
                    m = j - 1;
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }

        void conflict() {
            while (a[k][m] != 0) {

                if (i > 0 && i < 18 && j > 0 && j < 18) {

                    if (a[i][j + 1] != 0 && a[i][j - 1] != 0 && a[i + 1][j] != 0 && a[i - 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                } else if (i == 0 && j != 0 && j != 18) {
                    if (a[i][j + 1] != 0 && a[i][j - 1] != 0 && a[i + 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }

                } else if (j == 0 && i != 0 && i != 18) {
                    if (a[i][j + 1] != 0 && a[i + 1][j] != 0 && a[i - 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                } else if (i == 18 && j != 18 && j != 0) {
                    if (a[i][j + 1] != 0 && a[i][j - 1] != 0 && a[i - 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }

                } else if (j == 18 && i != 18 && i != 0) {
                    if (a[i][j - 1] != 0 && a[i + 1][j] != 0 && a[i - 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                } else if (i == 0 && j == 0) {
                    if (a[i][j + 1] != 0 && a[i + 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                } else if (i == 0 && j == 18) {
                    if (a[i][j - 1] != 0 && a[i + 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                } else if (i == 18 && j == 0) {
                    if (a[i][j + 1] != 0 && a[i - 1][j] != 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                } else if (i == 18 && j == 18) {
                    if (a[i][j - 1] == 1 && a[i - 1][j] == 0) {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                    } else {
                        k = r.nextInt(19);
                        m = r.nextInt(19);
                        AI();
                    }
                }

            }
        }

        void win() {

            if (i > 1 && i < 17 && j > 1 && j < 17) {
                if ((a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 1 && j > 1 && j < 17) {
                if ((a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 1 && i > 1 && i < 17) {
                if ((a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1)//左边旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 1 && i == 0) {
                if ((a[i][j - 1] == 2 && a[i + 1][j - 1] == 1)//左边旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 1 && i == 18) {
                if ((a[i][j - 1] == 2 && a[i - 1][j - 1] == 1)//左边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i][j + 2] == 1))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 1 && j == 1) {
                if ((a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 1 && j == 17) {
                if ((a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 1 && j == 0) {
                if ((a[i - 1][j] == 2 && a[i - 1][j + 1] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 1 && j == 18) {
                if ((a[i - 1][j] == 2 && a[i - 1][j - 1] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 17 && j > 1 && j < 17) {
                if ((a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 17 && i > 1 && i < 17) {
                if ((a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1)//右边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 17 && i == 0) {
                if ((a[i][j + 1] == 2 && a[i + 1][j + 1] == 1)//右边旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j - 1] == 2 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 17 && i == 18) {
                if ((a[i][j + 1] == 2 && a[i - 1][j + 1] == 1)//右边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 17 && j == 1) {
                if ((a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 17 && j == 17) {
                if ((a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 17 && j == 0) {
                if ((a[i + 1][j] == 2 && a[i + 1][j + 1] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 17 && j == 18) {
                if ((a[i + 1][j] == 2 && a[i + 1][j - 1] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 0 && j > 1 && j < 17) {
                if ((a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i][j + 1] == 2 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 0 && i > 1 && i < 17) {
                if ((a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 2][j] == 1))//下面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 18 && j > 1 && j < 17) {
                if ((a[i - 1][j] == 2 && a[i - 1][j - 1] == 1 && a[i - 1][j + 1] == 1 && a[i - 2][j] == 1)//上面旗子被围
                        || (a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i][j - 2] == 1))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (j == 18 && i > 1 && i < 17) {
                if ((a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1)//左边旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1)//下面旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 0 && j == 0) {
                if ((a[i][j + 1] == 2 && a[i + 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j + 1] == 1 && a[i + 2][j] == 1))//下面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 0 && j == 18) {
                if ((a[i][j - 1] == 2 && a[i + 1][j - 1] == 1 && a[i][j - 2] == 1)//左边旗子被围
                        || (a[i + 1][j] == 2 && a[i + 1][j - 1] == 1 && a[i + 2][j] == 1))//下面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 18 && j == 0) {
                if ((a[i][j + 1] == 2 && a[i - 1][j + 1] == 1 && a[i][j + 2] == 1)//右边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j + 1] == 1 && a[i - 2][j] == 1))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            } else if (i == 18 && j == 18) {
                if ((a[i][j - 1] == 2 && a[i - 1][j - 1] == 1 && a[i][j - 2] == 1)//左边旗子被围
                        || (a[i - 1][j] == 2 && a[i - 1][j - 1] == 1 && a[i - 2][j] == 1))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你赢了！");
                    System.exit(0);
                }
            }

        }
        
        void lose() {

            if (k > 1 && k < 17 && m > 1 && m < 17) {
                if ((a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 1 && m > 1 && m < 17) {
                if ((a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 1 && k > 1 && k < 17) {
                if ((a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2)//左边旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 1 && k == 0) {
                if ((a[k][m - 1] == 1 && a[k + 1][m - 1] == 2)//左边旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 1 && k == 18) {
                if ((a[k][m - 1] == 1 && a[k - 1][m - 1] == 2)//左边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k][m + 2] == 2))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 1 && m == 1) {
                if ((a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 1 && m == 17) {
                if ((a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 1 && m == 0) {
                if ((a[k - 1][m] == 1 && a[k - 1][m + 1] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 1 && m == 18) {
                if ((a[k - 1][m] == 1 && a[k - 1][m - 1] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 17 && m > 1 && m < 17) {
                if ((a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 17 && k > 1 && k < 17) {
                if ((a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2)//右边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 17 && k == 0) {
                if ((a[k][m + 1] == 1 && a[k + 1][m + 1] == 2)//右边旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m - 1] == 1 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 17 && k == 18) {
                if ((a[k][m + 1] == 1 && a[k - 1][m + 1] == 2)//右边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 17 && m == 1) {
                if ((a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 17 && m == 17) {
                if ((a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 17 && m == 0) {
                if ((a[k + 1][m] == 1 && a[k + 1][m + 1] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2))//右边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 17 && m == 18) {
                if ((a[k + 1][m] == 1 && a[k + 1][m - 1] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 0 && m > 1 && m < 17) {
                if ((a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k][m + 1] == 1 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 0 && k > 1 && k < 17) {
                if ((a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 2][m] == 2))//下面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 18 && m > 1 && m < 17) {
                if ((a[k - 1][m] == 1 && a[k - 1][m - 1] == 2 && a[k - 1][m + 1] == 2 && a[k - 2][m] == 2)//上面旗子被围
                        || (a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k][m - 2] == 2))//左边旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (m == 18 && k > 1 && k < 17) {
                if ((a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2)//左边旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2)//下面旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 0 && m == 0) {
                if ((a[k][m + 1] == 1 && a[k + 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m + 1] == 2 && a[k + 2][m] == 2))//下面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 0 && m == 18) {
                if ((a[k][m - 1] == 1 && a[k + 1][m - 1] == 2 && a[k][m - 2] == 2)//左边旗子被围
                        || (a[k + 1][m] == 1 && a[k + 1][m - 1] == 2 && a[k + 2][m] == 2))//下面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 18 && m == 0) {
                if ((a[k][m + 1] == 1 && a[k - 1][m + 1] == 2 && a[k][m + 2] == 2)//右边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m + 1] == 2 && a[k - 2][m] == 2))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            } else if (k == 18 && m == 18) {
                if ((a[k][m - 1] == 1 && a[k - 1][m - 1] == 2 && a[k][m - 2] == 2)//左边旗子被围
                        || (a[k - 1][m] == 1 && a[k - 1][m - 1] == 2 && a[k - 2][m] == 2))//上面旗子被围
                {
                    start = false;
                    JOptionPane.showMessageDialog(getParent(), "你输了！");
                    System.exit(0);
                }
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (combo.getSelectedItem() == "先手") {
                
                combo.setEnabled(false);
                if (e.getSource() == play) {
                    start = true;
                    timer.start();
                }
            } else {
                combo.setEnabled(false);
                if (e.getSource() == play) {
                    start = true;
                    timer.start();
                    k = r.nextInt(19);
                    m = r.nextInt(19);
                    b[k][m].setIcon(new ImageIcon("bg2.jpg"));
                    a[k][m] = 2;    
                }
            }

            if (start) {

                for (i = 0; i < 19; i++) {
                    for (j = 0; j < 19; j++) {

                        if (e.getSource() == b[i][j]) {

                            if (a[i][j] == 0) {
                                b[i][j].setIcon(new ImageIcon("bg.jpg"));
                                a[i][j] = 1;
                                win();

                            } else {
                                break;
                            }

                            if (check()) {
                                AI();
                            }
                            conflict();

                            if (a[k][m] == 0) {

                                b[k][m].setIcon(new ImageIcon("bg2.jpg"));
                                a[k][m] = 2;
                                lose();

                            }

                        }

                    }
                }
            }

        }
    }

    private class Listener2 implements ActionListener {

        void time() {
            time = "";
            second++;
            if (second == 60) {
                minute++;
                second = 0;
            }
            if (minute == 60) {
                hour++;
                minute = 0;
            }
            if (hour == 24) {
                hour = 0;
            }
            if (hour < 10) {
                time = time + "0" + hour + ":";
            } else {
                time = time + hour + ":";
            }
            if (minute < 10) {
                time = time + "0" + minute + ":";
            } else {
                time = time + minute + ":";
            }
            if (second < 10) {
                time = time + "0" + second;
            } else {
                time = time + second;
            }
            timeLabel.setText(time);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (start) {
                time();
            }
        }
    }
}
