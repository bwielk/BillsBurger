package burgers;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ChipsTest {
	
	private Chips chips;
	private Till till;

	@Before
	public void before(){
		chips = new Chips("Portion of chips", ChipsSize.MEDIUM, 1.50);
		till = new Till();
	}
	
	@Test
	public void chipsHasName(){
		assertEquals("Portion of chips", chips.getName());
	}
	
	@Test
	public void chipsHaveSize(){
		assertEquals(ChipsSize.MEDIUM, chips.getSize());
		assertEquals(0.5, chips.getSize().getPriceProportion(), 0.1);
	}
	
	@Test
	public void chipsHaveBasePrice(){
		assertEquals(1.50, chips.getPrice(), 0.1);
	}
	
	@Test
	public void tillsCanCalculateChipsPropoerPrice(){
		till.newTransaction();
		till.addProduct(chips);
		BigDecimal result = new BigDecimal("2.25");
		assertEquals(result, till.calculateTransaction());
	}

}
