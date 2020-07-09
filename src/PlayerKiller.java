import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class PlayerKiller extends JButton {

    private Player player;
    private Playermanager playermanager;

    public PlayerKiller(Player player, Playermanager playermanager) {
        this.player = player;
        setText(this.player.getName() + " died.");
        addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.setAlive(false);
                setEnabled(false);
                player.setEnabled(false);
                playermanager.numberDeadPlayers++;
                if(playermanager.getNumberDeadPlayers() == 7) {
                    GUI.issaFirst();
                }
                playermanager.removeDead();
            }
        });
    }
}