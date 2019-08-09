import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private ArrayList<Card> cards;
    private PossibleHands hand;
    private ArrayList<Card> importantCards;

    public Hand(ArrayList<Card> cards, PossibleHands hand, ArrayList<Card> importantCards) {
        this.cards = cards;
        this.hand = hand;
        this.importantCards = importantCards;
        Collections.sort(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public PossibleHands getHand() {
        return hand;
    }

    public int getHandRating(){
        return hand.ordinal();
    }

    public int getHighestCardRating(){
        return cards.get(0).getValue().ordinal();
    }

    public ArrayList<Card> getImportantCards() {
        return importantCards;
    }

    public int getImportentCardsRating(){
        return importantCards.get(0).getValue().ordinal();
    }

    @Override
    public String toString() {
        String s = "";
        for (Card c: cards) {
            s += c.toString() + ", ";
        }
        return s;
    }
}
