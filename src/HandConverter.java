import java.util.ArrayList;
import java.util.Collections;

public class HandConverter {

    private ArrayList<Card> cards;

    public HandConverter() {

    }

    public Hand getHand(ArrayList<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
        PossibleHands pHand = getHand();
        ArrayList<Card> importantCards = getImportantCards(pHand);
        Collections.sort(importantCards);
        return new Hand(cards, pHand, importantCards);
    }

    private boolean contains(Value value) {
        return containsXOf(value, 1);
    }

    private boolean containsXOf(Value value, int number) {
        int sum = 0;

        for (Card c : cards) {
            if (c.getValue() == value) {
                sum++;
            }
        }
        return (sum == number);
    }


    /**
     * finns det numb kort av samma valör
     *
     * @param numb antalet kort man letar efter
     * @return om det finns numb kort av någon valfri valör
     */
    private boolean isXSameValues(int numb) {
        return isXSameValues(numb, cards);
    }

    /**
     * finns det numb kort av samma valör
     *
     * @param numb antalet kort man letar efter
     * @return om det finns numb kort av någon valfri valör
     */
    private boolean isXSameValues(int numb, ArrayList<Card> cards) {
        //behöver inte kolla numb-1 sista eftersom de alldrig uppfyller vilkoret om att det ska finnas numb lika
        for (int i = 0; i < cards.size() - (numb - 1); i++) {
            if (containsXOf(cards.get(i).getValue(), numb)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Bygger på samma strucktur som isXSameValues.
     *
     * @param numb antalet kort som eftersökes
     * @return Valören på det numb styck kort som finns i handen
     */
    private Value getValueOfXSame(int numb) {
        return getValueOfXSame(numb, cards);
    }

    /**
     * Bygger på samma strucktur som isXSameValues.
     *
     * @param numb antalet kort som eftersökes
     * @return Valören på det numb styck kort som finns i handen
     */
    private Value getValueOfXSame(int numb, ArrayList<Card> cards) {
        //behöver inte kolla numb-1 sista eftersom de alldrig uppfyller vilkoret om att det ska finnas numb lika
        for (int i = 0; i < cards.size() - (numb - 1); i++) {
            if (containsXOf(cards.get(i).getValue(), numb)) {
                return cards.get(i).getValue();
            }
        }
        return null;
    }

    private int indexOf(Value value, ArrayList<Card> cards) {

        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getValue() == value) {
                return i;
            }
        }
        return -1;
    }

    private int indexOf(Value value) {
        return indexOf(value, cards);
    }

    private boolean allSameColor() {

        Color color = null;

        for (Card c : cards) {

            if (color == null) {
                color = c.getColor();
            } else {
                if (!color.equals(c.getColor())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isStraight() { // stege

        int valueOfFirstCard = cards.get(0).getValue().ordinal();

        for (int i = 1; i < cards.size(); i++) {
            // för att det ska vara en stege ska värdet på kortet efter vara ett mindre (eftersom korten ordnas enligt störst först)
            if (valueOfFirstCard != cards.get(i).getValue().ordinal() + 1) {
                return false;
            }
        }

        return true;
    }

    private PossibleHands getHand() {
        if (checkForRoyalFlush())
            return PossibleHands.royalFlush;
        if (checkForStraightFlush())
            return PossibleHands.straitFlush;
        if (checkForFourOfAKind())
            return PossibleHands.four_of_a_kind;
        if (checkForFullHouse())
            return PossibleHands.fullHouse;
        if (checkForFlush())
            return PossibleHands.flush;
        if (checkForStraight())
            return PossibleHands.straight;
        if (checkForThreeOfAKind())
            return PossibleHands.three_of_a_kind;
        if (checkForTwoPairs())
            return PossibleHands.twoPair;
        if (checkForPair())
            return PossibleHands.pair;

        return PossibleHands.highCard;

    }


    private boolean checkForRoyalFlush() {

        return (contains(Value.values()[Value.values().length-1])
                && contains(Value.values()[Value.values().length-2])
                && contains(Value.values()[Value.values().length-3])
                && contains(Value.values()[Value.values().length-4])
                && contains(Value.values()[Value.values().length-5])
                && allSameColor());
    }

    private boolean checkForStraightFlush() {
        return (isStraight() && allSameColor());
    }

    private boolean checkForFourOfAKind() {
        //kollar om första eller andra kortets valör finns fyra gånger. eftersom det endast kommer att finnas 5 kort, så kan antingen första vara samma som de anndra tre eller så är de fyra sista samma
        return (containsXOf(cards.get(0).getValue(), 4) || containsXOf(cards.get(0).getValue(), 4));
    }

    private boolean checkForFullHouse() {
        if (isXSameValues(3) && isXSameValues(2)) {

            Value pairOfThree = getValueOfXSame(3);
            Value pairOfTwo = getValueOfXSame(2);
            return (pairOfThree != pairOfTwo);
        }
        return false;
    }

    private boolean checkForFlush() {
        return (allSameColor());
    }

    private boolean checkForStraight() {
        return (isStraight());
    }

    private boolean checkForThreeOfAKind() {
        return isXSameValues(3);
    }

    private boolean checkForTwoPairs() {
        if (isXSameValues(2)) {
            ArrayList<Card> cards = new ArrayList<>();
            for (Card c : this.cards) {
                cards.add(c);
            }
            Collections.sort(cards);
            Value value = getValueOfXSame(2);

            cards.remove(indexOf(value));
            cards.remove(indexOf(value));

            if (isXSameValues(2, cards)) {
                return true;
            }

        }
        return false;
    }

    private boolean checkForPair() {
        return isXSameValues(2);
    }


    private ArrayList<Card> getImportantCards(PossibleHands possibleHands){
        switch (possibleHands){
            case royalFlush:
            case straitFlush:
            case fullHouse:
            case flush:
            case highCard:
            case straight: return getFiveNumbHandsCards();

            case four_of_a_kind: return getFourOfAKindCards();
            case three_of_a_kind: return getThreeOfAKindCards();
            case twoPair: return getTowPairCards();
            case pair: return getPairCards();
            default: return null;
        }
    }

    /**
     * @return hella handen för de gånger man har en hand där alla 5 kort bildar handen. tex. royalFlush, StraightFlush...
     */
    private ArrayList<Card> getFiveNumbHandsCards(){
        return cards;
    }

    /**
     * @return de kort som ingår i "Fore of a kind" handen
     */
    private ArrayList<Card> getFourOfAKindCards() {
        ArrayList<Card> cards = new ArrayList<>();

        Value value = getValueOfXSame(4);
        for (Card c : cards) {
            if (c.getValue() == value) {
                cards.add(c);
            }
        }
        return cards;
    }

    /**
     * @return de kort som ingår i "Three of a kind" handen
     */
    private ArrayList<Card> getThreeOfAKindCards() {
        ArrayList<Card> cards = new ArrayList<>();

        Value value = getValueOfXSame(3);
        for (Card c : this.cards) {
            if (c.getValue() == value) {
                cards.add(c);
            }
        }
        return cards;
    }

    /**
     * @return de kort som ingår i "Two pair" handen
     */
    private ArrayList<Card> getTowPairCards() {
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Card> temp = new ArrayList<>();

        for (Card c : this.cards) {
            cards.add(c);
            temp.add(c);
        }

        Value value = getValueOfXSame(2, temp);
        temp.remove(indexOf(value, temp));
        temp.remove(indexOf(value, temp));

        value = getValueOfXSame(2);
        temp.remove(indexOf(value));
        temp.remove(indexOf(value));

        cards.remove(temp.get(0));

        return cards;

    }

    /**
     * @return de kort som ingår i "Pair" handen
     */
    private ArrayList<Card> getPairCards() {
        ArrayList<Card> cards = new ArrayList<>();
        Value value = getValueOfXSame(2);
        for (Card c : this.cards) {
            if(value == c.getValue()){
                cards.add(c);
            }
        }
        return cards;
    }

}
