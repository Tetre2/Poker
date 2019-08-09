import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Board {

    private Deck deck;
    private int small_blind;
    private int big_blind;
    private int startingMoney;
    private int betTotal = 0;
    private ArrayList<Player> players;
    private ArrayList<Card> displayedCards = new ArrayList<>();
    private ArrayList<Player> winners = new ArrayList<>();
    private WinChecker winChecker = new WinChecker();
    private Player currentPlayer;

    public Board(int small_blind, int big_blind, int startingMoney, ArrayList<Player> players) {
        this.small_blind = small_blind;
        this.big_blind = big_blind;
        this.startingMoney = startingMoney;
        this.players = players;

    }

    private void initGame() {

        System.out.print("Game starting ");
        try {
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
            System.out.print(".");
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

        currentPlayer = players.get(0);
        setSmallBlind(currentPlayer);
        currentPlayer = players.get(1);
        setBigBlind(currentPlayer);

        for (Player p : players) {
            p.giveCard(deck.takeCard());
            p.giveCard(deck.takeCard());
        }

        setNextCurrentPlayer();

    }

    public void start() {

        deck = new Deck();

        initGame();

        //alla har fått sina kort och ska bestämma vad de vill göra
        for (Player p : players) {
            if (!p.isFolded()) {
                p.playerTurn();
            }
        }

        checkForFoldWin();

        //tre kort visas
        for (int i = 0; i < 3; i++) {
            displayedCards.add(deck.takeCard());
        }
        Collections.sort(displayedCards);


        for (int i = 0; i < 2; i++) {
            //spelarna får välja vad de ska göra
            for (Player p : players) {
                if (!p.isFolded()) {
                    p.playerTurn();
                }
            }

            checkForFoldWin();

            //visa ytterligare ett kort
            displayedCards.add(deck.takeCard());
            Collections.sort(displayedCards);

        }

        //spelarna får välja vad de ska göra en sista gång
        for (Player p : players) {
            if (!p.isFolded()) {
                p.playerTurn();
            }
        }


        //spelarna visar sina kort

        displayWinners();

    }

    public void replay() {

    }

    //--------------- logic -----------------

    private void checkForFoldWin(){
        int i = 0;

        for (Player p : players) {
            if (p.isFolded()) {
                i++;
            }
        }

        if(players.size() - i == 1){
            displayWinners();
        }

    }

    private void setSmallBlind(Player player) {
        for (Player p : players) {
            if (p.equals(player)) {
                p.setSmall_blind(true);
            } else {
                p.setSmall_blind(false);
            }
        }
    }

    private void setBigBlind(Player player) {
        for (Player p : players) {
            if (p.equals(player)) {
                p.setBig_blind(true);
            } else {
                p.setBig_blind(false);
            }
        }
    }



    private void setNextCurrentPlayer() {
        currentPlayer = players.get(players.indexOf(currentPlayer) + 1 % players.size());
    }

    //----------------- getters / setters -------------


    public ArrayList<Card> getDisplayedCards() {
        return displayedCards;
    }

    //------------------- IO ---------------

    private void displayWinners(){
        ArrayList<Player> activePlayers = new ArrayList<>();
        for (Player p : players) {
            if (!p.isFolded()) {
                activePlayers.add(p);
            }
        }
        winners = winChecker.checkWin(activePlayers);

        for (Player player : winners) {
            System.out.println(player.getName() + " Won with:");
            System.out.println(player.getbestHand().getImportantCards());
        }
    }

}
