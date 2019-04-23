package strategicwar;

import static org.junit.Assert.*;

//import org.junit.Rule;
import org.junit.Test;

/**
 * Designed to test the RandomAI class.
 */
public class RandomAITest {
	//@Rule
	//public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();
	
    @Test
    public void testSelectBattleCard()
    {
        Hand hand = new Hand();
        hand.addCard(new Card(0, 12));
        hand.addCard(new Card(2, 3));
        hand.addCard(new Card(2, 9));
        hand.addCard(new Card(3, 6));
        hand.addCard(new Card(1, 2));
        RandomAI easyAI = new RandomAI();
        Card selection = easyAI.selectBattleCard(hand);
        assertTrue(selection != null);
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
        RandomAI easyAI = new RandomAI();
        int count = 2;
        Card[] selection = easyAI.selectPrizeCards(hand, count);
        assertTrue(selection.length == count);
        assertTrue(selection[0] != selection[1]);
    }

}
