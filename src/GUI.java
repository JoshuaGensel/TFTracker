import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashSet;

public class GUI {

    Playermanager playermanager = new Playermanager();
    private HashSet<Player> allPlayersBackup = new HashSet<Player>();
    private HashSet<PlayerKiller> allPlayerKillersBackup = new HashSet<PlayerKiller>();


    /**
     * method for gettin first.
     */
    public static void issaFirst(){
        JOptionPane.showMessageDialog(null,"ISSA FIRST, LETS GOOOOO!!!");
        System.exit(0);
    }

    /**
     * method for gettin first.
     */
    public void iDied(){
        if(playermanager.getNumberDeadPlayers() <= 3){
            JOptionPane.showMessageDialog(null,"its all good. ull get your LP back , King <3");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null,"Top 4 = LP go up :)");
            System.exit(0);
        }
    }

    

    public GUI(){

        /**
         * Sets up Frame and Layout for GUI.
         */
        JFrame frame = new JFrame("TFTracker");
        JPanel panel = new JPanel(new BorderLayout());
        JButton resetButton = new JButton("Reset");
        JButton iDiedButton = new JButton("I died.");
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.add(resetButton, BorderLayout.NORTH);
        panel.add(iDiedButton, BorderLayout.SOUTH);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPanel.setLayout(new GridLayout(8,1));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setLayout(new GridLayout(8,1));
        leftPanel.add(new JLabel("Players you could fight next:        "));
        rightPanel.add(new JLabel("Click their name when a player dies."));

        /**
         * Adds Playerbuttons to GUI.
         */
        for(int i = 0; i<7;i++){
            String playerName = JOptionPane.showInputDialog(String.format("Player %d name?", i+1));
            Player player = new Player(playerName,this.playermanager);
            PlayerKiller playerKiller = new PlayerKiller(player,this.playermanager);
            leftPanel.add(player);
            playermanager.addPlayer(player);
            rightPanel.add(playerKiller);
            playermanager.playerKillerSet.add(playerKiller);
            allPlayersBackup.add(player);
            allPlayerKillersBackup.add(playerKiller);
        }

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(410,400);
        frame.setVisible(true);

        /**
         * Function for Reset Button
         */
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playermanager.reset();
            }
        });

        /**
         * Function for "I died."-Button
         */
        iDiedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iDied();;
            }
        });
    }
}