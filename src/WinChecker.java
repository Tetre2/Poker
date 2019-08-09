import java.util.ArrayList;

public class WinChecker {

    private ArrayList<Player> players;

    public WinChecker() {

    }


    public ArrayList<Player> checkWin(ArrayList<Player> players) {
        this.players = players;

        if(players.size() < 0){
            return new ArrayList<Player>();
        }

        //sorterar ut de spelare med högsta handen (kan vara flera)
        ArrayList<Player> winningPlayers = new ArrayList<>();
        winningPlayers.add(players.get(0));
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i).getbestHand().getHand().ordinal() > winningPlayers.get(0).getbestHand().getHand().ordinal()) {
                //ifall den nya spelaren har bättre hand än den gamla
                winningPlayers.clear();
                winningPlayers.add(players.get(i));

            } else if (players.get(i).getbestHand().getHand().ordinal() == winningPlayers.get(0).getbestHand().getHand().ordinal()) {
                //ifall de är lika bra läggs den till i listan för att sedan välga den högsta av samma typ av hand
                winningPlayers.add(players.get(i));
            }
        }

        //kollar igenom de utsorterade vinnarna och kolla på kortens värden, och väljer där efter ut vinnaren/vinnarna
        if (winningPlayers.size() > 1) { // GÖR OM - använd ej getHandRating eftersom det inte funkar åp kort mitt i handen
            ArrayList<Player> winners = new ArrayList<>();

            winners.add(winningPlayers.get(0));
            for (int i = 1; i < winningPlayers.size(); i++) {
                if (winningPlayers.get(i).getbestHand().getImportentCardsRating() > winners.get(0).getbestHand().getImportentCardsRating()) {
                    //ifall den nya spelaren har bättre hand än den gamla
                    winners.clear();
                    winners.add(winningPlayers.get(i));

                } else if (winningPlayers.get(i).getbestHand().getImportentCardsRating() == winners.get(0).getbestHand().getImportentCardsRating()) {
                    //ifall de är lika bra läggs den till i listan för att sedan välga den högsta av samma typ av hand
                    winners.add(winningPlayers.get(i));
                }
            }

        }


        return winningPlayers;
    }

    }
