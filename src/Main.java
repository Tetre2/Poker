import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static Board board;

    private static int small_blind;
    private static int big_blind;
    private static int startingMoney;
    private static ArrayList<Player> players;


    public static void main(String[] args) {

        //setup();
        testingWithPreMadePlayers();

        board.start();

    }

    private static void setup() {

        System.out.println("Choose small blind: ");
        String s = sc.nextLine();
        isValidNumber(s);
        small_blind = Integer.parseInt(s);

        System.out.println("Choose big blind: ");
        s = sc.nextLine();
        isValidNumber(s);
        big_blind = Integer.parseInt(s);

        System.out.println("Choose Starting money: ");
        s = sc.nextLine();
        isValidNumber(s);
        startingMoney = Integer.parseInt(s);


        System.out.println("Create new Players!");
        System.out.println("There have to be at least 3!");
        players = new ArrayList<>();
        board = new Board(small_blind, big_blind, startingMoney, players);
        addPlayer();


    }

    private static boolean isValidNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void addPlayer() {
        System.out.println("Player name: ");
        String s = sc.nextLine(); //TODO

        Player p = new Player(s, startingMoney, board);

        players.add(p);

        if (players.size() < 3) {
            addPlayer();
        } else if (askYesNo("Add another player? (y/n)")) {
            addPlayer();
        }
    }

    /**
     * @param question frågan som ska svaras ja / nej på
     * @return false = nej. true = ja
     */
    private static boolean askYesNo(String question) {
        System.out.println(question);
        String s = sc.nextLine(); //TODO
        s.toLowerCase();
        switch (s) {
            case "n":
            case "no":
                return false;
            case "y":
            case "yes":
                return true;
            default:
                System.out.println("Not a valid answer!");
                return askYesNo(question);
        }
    }

    //------ tests ------------

    private static void testingWithPreMadePlayers(){

        small_blind = 2;
        big_blind = 4;
        startingMoney = 10000;
        players = new ArrayList<>();
        board = new Board(small_blind, big_blind, startingMoney, players);
        players.add(new Player("Mattias", startingMoney, board));
        players.add(new Player("Frida", startingMoney, board));
        players.add(new Player("Martin", startingMoney, board));


    }

    private static void testingComboChecker(){
        ComboChecker comboChecker = new ComboChecker();
        Deck deck = new Deck();

        ArrayList<Card> displayedCards = new ArrayList<>();
        ArrayList<Card> playerCards = new ArrayList<>();

        /*cards.add(new Card( Color.Clover, Value.Jack));
        cards.add(new Card( Color.Clover, Value.King));
        cards.add(new Card( Color.Clover, Value.Queen));
        cards.add(new Card( Color.Clover, Value.Ten));
        cards.add(new Card( Color.Clover, Value.Ace));*/


        playerCards.add(deck.takeCard());
        playerCards.add(deck.takeCard());

        for (int i = 0; i < 5; i++) {
            displayedCards.add(deck.takeCard());
        }

        Collections.sort(displayedCards);
        Collections.sort(playerCards);

        System.out.println("PlayerCards: ");
        for (Card c : playerCards) {
            System.out.println(c.toString());
        }
        System.out.println();

        System.out.println("DisplayedCards");
        for (Card c : displayedCards) {
            System.out.println(c.toString());
        }
        System.out.println();


        System.out.println("That is: ");
        comboChecker.updateBestHand(playerCards, displayedCards);
        System.out.println(comboChecker.getBestHand().getHand());

    }

    private static void testingHandConverter(){
        HandConverter handConverter = new HandConverter();
        Deck deck = new Deck();


        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new Card( Color.Spades, Value.Jack));
        cards.add(new Card( Color.Clover, Value.King));
        cards.add(new Card( Color.Clover, Value.Queen));
        cards.add(new Card( Color.Clover, Value.Jack));
        cards.add(new Card( Color.Clover, Value.Fiv));


        /*for (int i = 0; i < 5; i++) {
            cards.add(deck.takeCard());
        }*/

        Collections.sort(cards);

        for (Card c : cards) {
            System.out.println(c.toString());
        }
        System.out.println();


        Hand hand = handConverter.getHand(cards);
        System.out.println();
        System.out.println("That is: ");
        System.out.println(hand.getHand());

    }
}
