package ConnectFour;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    Game game;

    String[] AI;
    String[] players;

    int numPlayers;
    String AItype;


    JButton btn0;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JButton btn5;
    JButton btn6;


    public GUI() {
        game = new Game();
        AI = new String[]{"Random", "MiniMax"};
        players = new String[]{"1 Player", "2 Players"};
        numPlayers = 1;
        AItype = "Random";

        btn0 = new ColumnButton(game.grid, 0);
        btn1 = new ColumnButton(game.grid, 1);
        btn2 = new ColumnButton(game.grid, 2);
        btn3 = new ColumnButton(game.grid, 3);
        btn4 = new ColumnButton(game.grid, 4);
        btn5 = new ColumnButton(game.grid, 5);
        btn6 = new ColumnButton(game.grid, 6);
    }


    public void selection() {

        JFrame startFrame = new JFrame("Selection");
        JPanel startPanel = new JPanel();
        JComboBox dropDownAI = new JComboBox(AI);
        JComboBox dropDownPlayers = new JComboBox(players);
        JButton playbtn = new JButton("play!");

//        startPanel.setLayout(new GridLayout(1, 4, 0, 0));
        startPanel.setLayout(new FlowLayout());
        JLabel playerLabel = new JLabel("Number of Players");
        playerLabel.setBounds(10, 20, 80, 25);
        JLabel AILabel = new JLabel("AI type");
        AILabel.setBounds(10, 20, 80, 25);

        dropDownAI.addItemListener(
                itemEvent -> {
                    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                        AItype = AI[dropDownAI.getSelectedIndex()];
                    }
                }
        );

        dropDownPlayers.addItemListener(
                itemEvent -> {
                    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                        numPlayers = dropDownPlayers.getSelectedIndex() + 1;
                    }
                }
        );

        playbtn.addActionListener(
                actionEvent -> {
                    startFrame.dispose();
                    if (numPlayers == 2) {
                        AItype = null;
                    }
                    this.play();
                }
        );

        startPanel.add(playerLabel);
        startPanel.add(dropDownPlayers);
        startPanel.add(AILabel);
        startPanel.add(dropDownAI);
        startPanel.add(playbtn);

        startFrame.add(startPanel);
        startFrame.pack();
        startFrame.setSize(600, 80);
        startFrame.setResizable(false);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
    }

    public void play() {

        JFrame frame = new JFrame("Connect Four");
        JPanel panel = new JPanel();


        ImageIcon icon = new ImageIcon("images/C4empty.png");
        btn0.addActionListener(new Action(0));
        btn0.setIcon(icon);
        btn1.addActionListener(new Action(1));
        btn1.setIcon(icon);
        btn2.addActionListener(new Action(2));
        btn2.setIcon(icon);
        btn3.addActionListener(new Action(3));
        btn3.setIcon(icon);
        btn4.addActionListener(new Action(4));
        btn4.setIcon(icon);
        btn5.addActionListener(new Action(5));
        btn5.setIcon(icon);
        btn6.addActionListener(new Action(6));
        btn6.setIcon(icon);

        panel.setLayout(new GridLayout(1, 7));
        panel.add(btn0);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);

        frame.add(panel);
        frame.pack();
        frame.setSize(700, 635);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class Action implements ActionListener {

        private int column;

        public Action(int column) {
            this.column = column;
        }

        public void setColumn(int column) {
         this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!game.play(AItype, column)) {
                System.out.println("Open new Window");
            } else {
                btn0.repaint();
                btn1.repaint();
                btn2.repaint();
                btn3.repaint();
                btn4.repaint();
                btn5.repaint();
                btn6.repaint();
            }
        }
    }

}
