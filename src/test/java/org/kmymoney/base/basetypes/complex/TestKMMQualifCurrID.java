package org.kmymoney.base.basetypes.complex;

import static org.junit.Assert.assertEquals;

import java.util.Currency;

import org.junit.Test;
import org.kmymoney.base.basetypes.simple.KMMCurrID;

import junit.framework.JUnit4TestAdapter;

public class TestKMMQualifCurrID {
	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestKMMQualifCurrID.class);
	}

	// -----------------------------------------------------------------

	@Test
	public void test01() throws Exception {
		KMMQualifCurrID currID = new KMMQualifCurrID(Currency.getInstance("EUR"));

		assertEquals(KMMQualifCurrID.Type.CURRENCY, currID.getType());
		assertEquals(new KMMCurrID("EUR"), currID.getCurrID());
		assertEquals(Currency.getInstance("EUR"), currID.getCurrID().get());
		assertEquals("EUR", currID.getCurrID().get().getCurrencyCode());
		assertEquals("EUR", currID.getCode());
		assertEquals("CURRENCY:EUR", currID.toString());

		// ---

		currID = new KMMQualifCurrID(Currency.getInstance("USD"));

		assertEquals(KMMQualifCurrID.Type.CURRENCY, currID.getType());
		assertEquals(new KMMCurrID("USD"), currID.getCurrID());
		assertEquals(Currency.getInstance("USD"), currID.getCurrID().get());
		assertEquals("USD", currID.getCurrID().get().getCurrencyCode());
		assertEquals("USD", currID.getCode());
		assertEquals("CURRENCY:USD", currID.toString());

		// ---

		try {
			currID = new KMMQualifCurrID(Currency.getInstance("XYZ")); // invalid code
			assertEquals(1, 0);
		} catch (Exception exc) {
			// correct behaviour: Throw exception
			assertEquals(0, 0);
		}
	}

	@Test
	public void test04_1() throws Exception {
		KMMQualifCurrID secCurrPrs = KMMQualifCurrID.parse("CURRENCY:EUR");
		KMMQualifCurrID secCurrRef = new KMMQualifCurrID(Currency.getInstance("EUR"));

		assertEquals(KMMQualifCurrID.Type.CURRENCY, secCurrPrs.getType());
		assertEquals("CURRENCY:EUR", secCurrPrs.toString());
		assertEquals(secCurrRef, secCurrPrs);

		// ---

		secCurrPrs = KMMQualifCurrID.parse("CURRENCY:USD");
		secCurrRef = new KMMQualifCurrID(Currency.getInstance("USD"));

		assertEquals(KMMQualifCurrID.Type.CURRENCY, secCurrPrs.getType());
		assertEquals("CURRENCY:USD", secCurrPrs.toString());
		assertEquals(secCurrRef, secCurrPrs);
	}

	@Test
	public void test04_2() throws Exception {
		try {
			KMMQualifCurrID secCurr = KMMQualifCurrID.parse("EURONEXT:SAP");
			assertEquals(1, 0);
		} catch (Exception exc) {
			assertEquals(0, 0);
		}
	}

	@Test
	public void test04_3() throws Exception {
		try {
			KMMQualifCurrID secCurr = KMMQualifCurrID.parse("FUXNSTUELL:BURP");
			assertEquals(1, 0);
		} catch (Exception exc) {
			assertEquals(0, 0);
		}
	}
}
