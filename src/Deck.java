import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();
    private static final Random random = new Random();

    public Deck() {
        //lägger till alla kort i leken
        for (int i = 0; i < Color.values().length; i++) {
            for (int j = 0; j < Value.values().length; j++) {

                deck.add(new Card( Color.values()[i], Value.values()[j]) );

            }
        }

    }

    public Card takeCard(){
        int i = random.nextInt(deck.size() - 1);
        Card card = deck.get(i);
        deck.remove(card);
        return card;
    }

    public ArrayList<Card> takeTwoCards(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(takeCard());
        cards.add(takeCard());
        return cards;
    }

}
