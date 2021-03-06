package org.javamoney.tck.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;

import org.javamoney.tck.ClassTester;
import org.javamoney.tck.TCKTestSetup;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.junit.Test;

@SpecVersion(spec = "JSR 354", version = "0.8")
public class CurrencyUnitTest {

	@SpecAssertion(section = "4.2.1", id = "421-A1")
	@Test
	public void testEnsureCurrencyUnit() {
		assertTrue("TCK Configuration not available.",
				TCKTestSetup.getTestConfiguration() != null);
		assertTrue(TCKTestSetup.getTestConfiguration().getCurrencyClasses()
				.size() > 0);
	}

	@SpecAssertion(section = "4.2.1", id = "421-A2")
	@Test
	public void testEqualISOCurrencies() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			for (Currency currency : Currency.getAvailableCurrencies()) {
				CurrencyUnit unit = MonetaryCurrencies.getCurrency(currency
						.getCurrencyCode());
				assertNotNull(unit);
				CurrencyUnit unit2 = MonetaryCurrencies.getCurrency(currency
						.getCurrencyCode());
				assertNotNull(unit2);
				assertEquals(unit, unit2);
			}
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-A3")
	@Test
	public void testEnforce3LetterCode4ISO() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			for (Currency currency : Currency.getAvailableCurrencies()) {
				CurrencyUnit unit = MonetaryCurrencies.getCurrency(currency
						.getCurrencyCode());
				assertNotNull(unit);
				assertEquals(currency.getCurrencyCode(), unit.getCurrencyCode());
			}
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-A4")
	@Test
	public void testISOCodes() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			for (Currency currency : Currency.getAvailableCurrencies()) {
				CurrencyUnit unit = MonetaryCurrencies.getCurrency(currency
						.getCurrencyCode());
				assertEquals(currency.getCurrencyCode(), unit.getCurrencyCode());
				assertEquals(currency.getDefaultFractionDigits(),
						unit.getDefaultFractionDigits());
				assertEquals(currency.getNumericCode(), unit.getNumericCode());
			}
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-B1")
	@Test
	public void testCurrencyClassesEqualsHashcode() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			ClassTester.testHasPublicMethod(type, int.class, "hashCode");
		}
		for (String code : new String[] { "CHF", "USD", "EUR", "GBP", "USS" }) {
			CurrencyUnit unit = MonetaryCurrencies.getCurrency(code);
			ClassTester.testHasPublicMethod(unit.getClass(), int.class,
					"hashCode");
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-B2")
	@Test
	public void testImplementsEquals() {
		List<CurrencyUnit> firstUnits = new ArrayList<CurrencyUnit>();
		List<CurrencyUnit> secondUnits = new ArrayList<CurrencyUnit>();
		for (String code : new String[] { "CHF", "USD", "EUR", "GBP", "USS" }) {
			CurrencyUnit unit = MonetaryCurrencies.getCurrency(code);
			assertNotNull(unit);
			ClassTester.testHasPublicMethod(unit.getClass(), boolean.class,
					"equals", Object.class);
			firstUnits.add(unit);
			CurrencyUnit unit2 = MonetaryCurrencies.getCurrency(code);
			assertNotNull(unit);
			secondUnits.add(unit);
		}
		for (String code : new String[] { "CHF", "USD", "EUR", "GBP", "USS" }) {
			CurrencyUnit unit = MonetaryCurrencies.getCurrency(code);
			ClassTester.testHasPublicMethod(unit.getClass(), boolean.class,
					"equals", Object.class);
		}
		for (int i = 0; i < firstUnits.size(); i++) {
			assertEquals(firstUnits.get(i), secondUnits.get(i));
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-B3")
	@Test
	public void testCurrencyClassesComparable() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			ClassTester.testComparable(type);
		}
		for (String code : new String[] { "CHF", "USD", "EUR", "GBP", "USS" }) {
			CurrencyUnit unit = MonetaryCurrencies.getCurrency(code);
			ClassTester.testComparable(unit.getClass());
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-B4")
	@Test
	public void testIsImmutable() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			ClassTester.testImmutable(type);
		}
		for (String code : new String[] { "CHF", "USD", "EUR", "GBP", "USS" }) {
			CurrencyUnit unit = MonetaryCurrencies.getCurrency(code);
			ClassTester.testImmutable(unit.getClass());
		}
	}

	@SpecAssertion(section = "4.2.1", id = "421-B6")
	@Test
	public void testImplementsSerializable() {
		for (Class type : TCKTestSetup.getTestConfiguration()
				.getCurrencyClasses()) {
			ClassTester.testSerializable(type);
		}
		for (String code : new String[] { "CHF", "USD", "EUR", "GBP", "USS" }) {
			CurrencyUnit unit = MonetaryCurrencies.getCurrency(code);
			ClassTester.testSerializable(unit);
		}
	}

}
