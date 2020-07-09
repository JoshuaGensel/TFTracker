import java.util.HashSet;
import java.util.Iterator;


public class Playermanager {

    private int queuesize = 4;
    public int numberDeadPlayers = 0;
    private HashSet<Player> allPlayers = new HashSet<Player>();
    public HashSet<PlayerKiller> playerKillerSet = new HashSet<PlayerKiller>();
    private HashSet<Player> playablePlayers = new  HashSet<Player>();
    private HashSet<Player> deadPlayers = new HashSet<Player>();
    private LimitedQueue<Player> nonPlayablePlayers = new LimitedQueue<Player>(queuesize);

    public void addPlayer(Player player){
        allPlayers.add(player);
        playablePlayers.add(player);
        player.setAlive(true);
    }

    public void killPlayer(Player player){
        playablePlayers.addAll(nonPlayablePlayers);
        this.nonPlayablePlayers = new LimitedQueue<Player>(queuesize);
        deadPlayers.add(player);
    }

    public void reset(){
        queuesize = 4;
        numberDeadPlayers = 0;
        playablePlayers.addAll(deadPlayers);
        playablePlayers.addAll(nonPlayablePlayers);
        this.deadPlayers = new HashSet<Player>();
        this.nonPlayablePlayers = new LimitedQueue<Player>(queuesize);

        for(Player p:playablePlayers){
            p.setAlive(true);
            p.setEnabled(true);
        }
        for(PlayerKiller pk:playerKillerSet){
            pk.setEnabled(true);
        }
    }

    public void resizeQueue(){

        /**
         * Adjust Queuesize according to players alive.
         */
        if(deadPlayers.size()==0){
            this.queuesize=3;
        }
        if(deadPlayers.size()==1){
            this.queuesize=2;
        }
        if(deadPlayers.size()==2){
            this.queuesize=2;
        }
        if(deadPlayers.size()==3){
            this.queuesize=1;
        }
        if(deadPlayers.size()==4){
            this.queuesize=0;
        }
        if(deadPlayers.size()==5){
            this.queuesize=0;
        }
        if(deadPlayers.size()==6){
            this.queuesize=0;
        }
        if(deadPlayers.size()==7){
            this.queuesize=0;
        }
    }

    public int getNumberDeadPlayers(){
        return this.numberDeadPlayers;
    }

    public void removeDead(){

        /**
         * Necessary Iterators
         */
        Iterator<Player> itrPlayablePlayers = playablePlayers.iterator();
        Iterator<Player> itrNonPlayablePlayers = nonPlayablePlayers.iterator();

        /**
         * Checks for dead players and removes them.
         */
        while(itrPlayablePlayers.hasNext()){
            Player p = itrPlayablePlayers.next();
            if(p.getAlive()==false){
                killPlayer(p);
                itrPlayablePlayers.remove();
            }
        }
        while(itrNonPlayablePlayers.hasNext()){
            Player p = itrNonPlayablePlayers.next();
            if(p.getAlive()==false){
                killPlayer(p);
                itrNonPlayablePlayers.remove();
            }
        }

        resizeQueue();

        /**
         * Disables Buttons for all non-playable Opponents and activates Buttons of playable Opponents.
         */
        for(Player p:playablePlayers){
            p.setEnabled(true);
        }
        for(Player p:nonPlayablePlayers){
            p.setEnabled(false);
        }
    }

    public void roundLoop(Player player){

        resizeQueue();

        /**
         * Adds Latest Player to the Queue and new possible Opponent.
         */
        try{
            nonPlayablePlayers.add(player);
        } catch(IllegalStateException full){
                playablePlayers.add(nonPlayablePlayers.remove());
                nonPlayablePlayers.add(player);
        }

        /**
         * Disables Buttons for all non-playable Opponents and activates Buttons of playable Opponents.
         */
        for(Player p:playablePlayers){
            p.setEnabled(true);
        }
        for(Player p:nonPlayablePlayers){
            p.setEnabled(false);
        }
    }
}