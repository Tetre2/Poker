public class Card implements Comparable<Card>{

    private Color color;
    private Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public int compareTo(Card card) {//TODO testa så att det gör det jag tror att den gör, det bör vara störst först
        return card.getValue().ordinal() - value.ordinal();
    }

    @Override
    public String toString() {
        return value.name() + " of " + color.name();
    }
}
