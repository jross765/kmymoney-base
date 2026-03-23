package org.kmymoney.base.basetypes.complex;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

public class TestKMMPriceID {

	private static KMMPriceID prcID = null;

	// -----------------------------------------------------------------

	public static void main(String[] args) throws Exception {
		junit.textui.TestRunner.run(suite());
	}

	@SuppressWarnings("exports")
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TestKMMPriceID.class);
	}

	@Before
	public void initialize() throws Exception {
//		qualifID = new KMMPriceID();
	}

	// -----------------------------------------------------------------

	@Test
	public void test01() throws Exception {
		prcID = new KMMPriceID("USD", "EUR", "2023-10-13");
		assertEquals("USD", prcID.getFromSecCurr().toString());
		assertEquals("EUR", prcID.getToCurr().toString());
		assertEquals(LocalDate.parse("2023-10-13"), prcID.getDate());
		assertEquals("2023-10-13", prcID.getDateStr());
		
		prcID = new KMMPriceID("E000012", "EUR", "2023-10-13");
		assertEquals("E000012", prcID.getFromSecCurr().toString());
		assertEquals("EUR", prcID.getToCurr().toString());
		assertEquals(LocalDate.parse("2023-10-13"), prcID.getDate());
		assertEquals("2023-10-13", prcID.getDateStr());
	}

	@Test
	public void test02() throws Exception {
		prcID = KMMPriceID.parse("USD:EUR:2023-10-13");
		assertEquals("USD", prcID.getFromSecCurr().toString());
		assertEquals("EUR", prcID.getToCurr().toString());
		assertEquals(LocalDate.parse("2023-10-13"), prcID.getDate());
		assertEquals("2023-10-13", prcID.getDateStr());
		
		prcID = KMMPriceID.parse("E000012:EUR:2023-10-13");
		assertEquals("E000012", prcID.getFromSecCurr().toString());
		assertEquals("EUR", prcID.getToCurr().toString());
		assertEquals(LocalDate.parse("2023-10-13"), prcID.getDate());
		assertEquals("2023-10-13", prcID.getDateStr());
	}

}
