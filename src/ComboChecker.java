import java.util.ArrayList;

public class ComboChecker {

    private ArrayList<Hand> bestHands = new ArrayList<>();

    public ComboChecker() {
    }

    public Hand getBestHand() {
        Hand bestHand = bestHands.get(0);

        for (Hand h : bestHands) {
            if (h.getHandRating() > bestHand.getHandRating()) {
                bestHand = h;
            }
        }

        return bestHand;
    }

    public void updateBestHand(ArrayList<Card> playerCards, ArrayList<Card> displayedCards) {

        switch (displayedCards.size()){
            case 3: whenDisplaiedIsThree(playerCards, displayedCards); break;
            case 4: whenDisplaiedIsFore(playerCards, displayedCards); break;
            case 5: whenDisplaiedIsFive(playerCards, displayedCards); break;
            default:
                System.out.println("ERROR!!!!! - ComboChecker, updateBestHand");
        }

    }

    private void whenDisplaiedIsThree(ArrayList<Card> playerCards, ArrayList<Card> displayedCards) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card c : playerCards) {
            cards.add(c);
        }
        for (Card c : displayedCards) {
            cards.add(c);
        }

        matchAgainstBestHand(cards);

    }

    private void whenDisplaiedIsFore(ArrayList<Card> playerCards, ArrayList<Card> displayedCards) {
        /*
        P: K 3
        D: 1  4  6  J
        1.    KJ641 KJ641
        2.    KJ643 KJ643-
        3.    KJ631 KJ631--
        4.    KJ341 KJ431---
        5.    K3641 K6431----
        6.    3J641 J6431
              3J64K KJ643-
              3J6K1 KJ631--
              3JK41 KJ431---
              3K641 K6431----
         */
        ArrayList<Card> cards = new ArrayList<>();
        for (Card c : displayedCards) {
            cards.add(c);
        }
        cards.add(playerCards.get(0));

        //matchar motsvarande 1.
        matchAgainstBestHand(cards);

        //matchar motsvarande 2. - 6.
        for (int i = 0; i < cards.size(); i++) {

            ArrayList<Card> combos = new ArrayList<>();

            for (int j = 0; j < cards.size(); j++) {
                //byter ut ett av de 5 korten mot det andra playerCard
                if (i == j) {
                    combos.add(playerCards.get(1));
                } else {
                    combos.add(cards.get(j));
                }
            }
            matchAgainstBestHand(combos);
        }
    }

    private void whenDisplaiedIsFive(ArrayList<Card> playerCards, ArrayList<Card> displayedCards) {

        for (int i = 0; i < displayedCards.size(); i++) {

            ArrayList<Card> cards = new ArrayList<>();
            for (Card c : displayedCards) {
                cards.add(c);
            }
            cards.set(i, playerCards.get(0));

            //matchar motsvarande 1.
            matchAgainstBestHand(cards);

            //matchar motsvarande 2. - 6.
            for (int j = 0; j < cards.size(); j++) {

                ArrayList<Card> combos = new ArrayList<>();

                for (int k = 0; k < cards.size(); k++) {
                    //byter ut ett av de 5 korten mot det andra playerCard
                    if (j == k) {
                        combos.add(playerCards.get(1));
                    } else {
                        combos.add(cards.get(k));
                    }
                }
                matchAgainstBestHand(combos);
            }

        }

    }


    private void matchAgainstBestHand(ArrayList<Card> combo) {
        HandConverter handConverter = new HandConverter();
        Hand hand = handConverter.getHand(combo);

        if (bestHands.size() == 0) {
            bestHands.add(hand);
        } else {
            if (hand.getHandRating() > bestHands.get(0).getHandRating()) {
                //ifall den nya handen är bättre än den gamla
                bestHands.clear();
                bestHands.add(hand);

            } else if (hand.getHandRating() == bestHands.get(0).getHandRating()) {
                //ifall de är lika bra läggs den till i listan för att sedan välga den högsta av samma "tier"
                bestHands.add(hand);
            }
        }
    }


    /*
    Old comboChecker
     //går igenom båda korten spelaren har
        for (Card playerCard : playerCards) {

            //går igenom alla displayedCards och byter ut ett av dem i teget mot ett av playerCard
            for (Card card : displayedCards) {

                ArrayList<Card> combo = new ArrayList<>();

                for (Card c : displayedCards) {
                    if (card.equals(c)) {
                        combo.add(playerCard);
                    } else {
                        combo.add(c);
                    }
                }

                matchAgainstBestHand(combo);

            }
        }

        //för att byta ut två kort väljs tre kort ut utav desplyedCards och sedan läggs playerCards till
        for (int i = 0; i < displayedCards.size() - 2; i++) {
            for (int j = i + 1; j < displayedCards.size() - 1; j++) {
                for (int k = j + 1; k < displayedCards.size(); k++) {
                    ArrayList<Card> combo = new ArrayList<>();
                    combo.add(displayedCards.get(i));
                    combo.add(displayedCards.get(j));
                    combo.add(displayedCards.get(k));
                    combo.add(playerCards.get(0));
                    combo.add(playerCards.get(1));
                    matchAgainstBestHand(combo);
                }
            }
        }

     */

}
