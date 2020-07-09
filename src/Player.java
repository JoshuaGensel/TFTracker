import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Player extends JButton {

    private String name;
    private boolean alive;
    private Playermanager playermanager;

    public String getName(){
        return this.name;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }


    public Player(String name, Playermanager playermanager){
        this.name = name;
        this.playermanager = playermanager;
        setText(this.name);
        Player player = this;
        addActionListener((ActionListener) new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				playermanager.roundLoop(player);
			}
        });
    }
}