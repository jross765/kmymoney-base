package org.kmymoney.base.basetypes.simple;

import static org.junit.Assert.assertEquals;

import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestKMMCurrID {

	private static KMMCurrID kmmID = null;

	// -----------------------------------------------------------------

	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestKMMCurrID.class);
	}

	@Before
	public void initialize() throws Exception {
		kmmID = new KMMCurrID();
	}

	// -----------------------------------------------------------------

	@Test
	public void test01_1() throws Exception {
		kmmID.set("EUR");
		assertEquals(Currency.getInstance("EUR"), kmmID.get());
		assertEquals("EUR", kmmID.get().getCurrencyCode());
		assertEquals("EUR", kmmID.toString());
		assertEquals("EUR", kmmID.toStringShort());
		assertEquals("KMMCurrID [curr=EUR]", kmmID.toStringLong());

		kmmID.set("USD");
		assertEquals(Currency.getInstance("USD"), kmmID.get());
		assertEquals("USD", kmmID.get().getCurrencyCode());
		assertEquals("USD", kmmID.toString());
		assertEquals("USD", kmmID.toStringShort());
		assertEquals("KMMCurrID [curr=USD]", kmmID.toStringLong());
	}

	@Test
	public void test01_2() throws Exception {
		kmmID.set(Currency.getInstance("EUR"));
		assertEquals(Currency.getInstance("EUR"), kmmID.get());
		assertEquals("EUR", kmmID.get().getCurrencyCode());
		assertEquals("EUR", kmmID.toString());
		assertEquals("EUR", kmmID.toStringShort());
		assertEquals("KMMCurrID [curr=EUR]", kmmID.toStringLong());

		kmmID.set(Currency.getInstance("USD"));
		assertEquals(Currency.getInstance("USD"), kmmID.get());
		assertEquals("USD", kmmID.get().getCurrencyCode());
		assertEquals("USD", kmmID.toString());
		assertEquals("USD", kmmID.toStringShort());
		assertEquals("KMMCurrID [curr=USD]", kmmID.toStringLong());
	}

	@Test
	public void test02() throws Exception {
		try {
			kmmID.set("ABC");
			assertEquals(2, 1);
		} catch (Exception InvalidKMMCurrIDException) {
			// Muss Exception werfen, wenn er hier landet, ist es richtig
			assertEquals(1, 1);
		}

		try {
			kmmID.set("USDB");
			assertEquals(2, 1);
		} catch (Exception InvalidKMMCurrIDException) {
			// Muss Exception werfen, wenn er hier landet, ist es richtig
			assertEquals(1, 1);
		}
	}

}
