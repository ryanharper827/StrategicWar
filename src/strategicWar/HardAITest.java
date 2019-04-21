package strategicwar;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

public class HardAITest {
	@Rule
	public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
	
    @Test
    public void testSelectBattleCard()
    {
        Hand hand = new Hand();
        hand.addCard(new Card(0, 12));
        hand.addCard(new Card(2, 3));
        hand.addCard(new Card(2, 9));
        hand.addCard(new Card(3, 6));
        hand.addCard(new Card(1, 2));
        HardAI hardAI = new HardAI();
        Card selection = hardAI.selectBattleCard(hand);
        assertTrue(selection.getValue() == 12);
    }

    @Test
    public void testSelectPrizeCards()
    {
        Hand hand = new Hand();
        hand.addCard(new Card(0, 12));
        hand.addCard(new Card(2, 3));
        hand.addCard(new Card(2, 9));
        hand.addCard(new Card(3, 6));
        hand.addCard(new Card(1, 2));
        HardAI hardAI = new HardAI();
        int count = 2;
        Card[] selection = hardAI.selectPrizeCards(hand, count);
        assertTrue(selection.length == count);
        assertTrue(selection[0] != selection[1]);
        assertTrue(selection[0].getValue() == 2);
        assertTrue(selection[1].getValue() == 3);
    }

}
