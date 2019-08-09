import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private static final Scanner sc = new Scanner(System.in);
    private ComboChecker comboChecker = new ComboChecker();
    private ArrayList<Card> cards = new ArrayList<>();
    private String name;
    private Hand bestHand;
    private int money;
    private boolean small_blind;
    private boolean big_blind;
    private boolean folded = false;
    private Board board;
    private int bet = 0;

    public Player(String name, int money, Board board) {
        this.name = name;
        this.money = money;
        this.board = board;
    }

    private void call(){// lägg ut lika mycket pängar som den förra spelaren lagt ut

    }

    private void raise(){// lägg ut mer än den förre spelaren

    }

    private void fold(){// ge upp.
        folded = true;
    }

    private void check(){// kan ändast göras om ingen har bettat innan (i nuvarande omgång)

    }

    public void giveCard(Card card){
            cards.add(card);
    }

    public boolean isSmall_blind() {
        return small_blind;
    }

    public void setSmall_blind(boolean small_blind) {
        this.small_blind = small_blind;
    }

    public boolean isBig_blind() {
        return big_blind;
    }

    public void setBig_blind(boolean big_blind) {
        this.big_blind = big_blind;
    }

    public Hand getbestHand() {
        return comboChecker.getBestHand();

    }

    public boolean isFolded() {
        return folded;
    }

    public String getName() {
        return name;
    }

    //---------- IO -----------
    public void playerTurn(){

        System.out.println(name + "'s turn!");

        printPlayerCards();

        if(board.getDisplayedCards().size() > 0) {

            printDisplayedCards();

            comboChecker.updateBestHand(cards, board.getDisplayedCards());
            bestHand = getbestHand();
            printBestHand();
        }

        String command = sc.nextLine();
        inputDecode(command);
    }

    private void inputDecode(String command){
        command.toLowerCase();
        switch (command){
            case "call": call(); break;
            case "raise": raise(); break;
            case "fold": fold(); break;
            case "check": check(); break;
            case "help":
                System.out.println("You can use call, raise, fold and check!");
                playerTurn();
                break;
            default:
                System.out.println("Not a valid choice!" +
                        " Please see \"help\" for available commands ");
                playerTurn();
                break;
        }
    }

    private void printPlayerCards (){
        System.out.println("You have: ");
        for (Card card: cards) {
            System.out.println("   " + card.toString());
        }
        System.out.println();
    }

    private void printDisplayedCards(){
            System.out.println("The displayed cards are: ");
            for (Card c : board.getDisplayedCards()) {
                System.out.println("   " + c.toString());
            }
            System.out.println();

    }

    private void printBestHand(){
        System.out.println("Your best hand is: ");
        System.out.println(getbestHand().getHand().toString());
        System.out.println("With: " + getbestHand().getImportantCards());
        System.out.println();
    }

}
