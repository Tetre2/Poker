public enum PossibleHands {

    highCard{
        @Override
        public String toString() {
            return "Highest Card";
        }
    }, pair{
        @Override
        public String toString() {
            return "Pair";
        }
    }, twoPair{
        @Override
        public String toString() {
            return "Two Pairs";
        }
    }, three_of_a_kind{
        @Override
        public String toString() {
            return "Three of a kind";
        }
    }, straight{
        @Override
        public String toString() {
            return "Straight";
        }
    }, flush{
        @Override
        public String toString() {
            return "Flush";
        }
    }, fullHouse{
        @Override
        public String toString() {
            return "Full House";
        }
    }, four_of_a_kind{
        @Override
        public String toString() {
            return "Four of a kind";
        }
    }, straitFlush{
        @Override
        public String toString() {
            return "Straight Flush";
        }
    }, royalFlush{
        @Override
        public String toString() {
            return "Royal Flush";
        }
    }



    /*
    (bästa (1) till sämsta (10))
    * 1. Royal Flush        - A,K,Q,J,T utav samma färg tex Hjärter
    * 2. Straight Flush     - 5 kort av samma färg och stege
    * 3. Four of a Kind     - 4 kort av samma vallör
    * 4. Full House         - 3 kort av samma vallör + 2 av sammma vallör
    * 5. Flush              - 5 kort av samma färg tex Hjärter
    * 6. Straight           - Stege alla färger tillåtna
    * 7. Three of a Kind    - 3 kort av samma vallör
    * 8. Two Pair           - 2 olika par
    * 9. Pair               - par
    * 10. High Card         - det högsta kortet
    *
    * */


}
