package pt.ulisboa.tecnico.softeng.broker.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import pt.ulisboa.tecnico.softeng.broker.exception.BrokerException;
import pt.ulisboa.tecnico.softeng.broker.interfaces.BankInterface;

public class BrokerConstructorMethodTest {

	@Test
	public void success() {
		Broker broker = new Broker("BR01", "WeExplore", new BankInterface());

		Assert.assertEquals("BR01", broker.getCode());
		Assert.assertEquals("WeExplore", broker.getName());
		Assert.assertEquals(0, broker.getNumberOfAdventures());
		Assert.assertTrue(Broker.brokers.contains(broker));
	}

	@Test
	public void nullCode() {
		try {
			new Broker(null, "WeExplore", new BankInterface());
			Assert.fail();
		} catch (BrokerException be) {
			Assert.assertEquals(0, Broker.brokers.size());
		}
	}

	@Test
	public void emptyCode() {
		try {
			new Broker("  ", "WeExplore", new BankInterface());
			Assert.fail();
		} catch (BrokerException be) {
			Assert.assertEquals(0, Broker.brokers.size());
		}
	}

	@Test
	public void uniqueCode() {
		Broker broker = new Broker("BR01", "WeExplore", new BankInterface());

		try {
			new Broker("BR01", "WeExploreX", new BankInterface());
			Assert.fail();
		} catch (BrokerException be) {
			Assert.assertEquals(1, Broker.brokers.size());
			Assert.assertTrue(Broker.brokers.contains(broker));
		}
	}

	@Test
	public void nullName() {
		try {
			new Broker("BR01", null, new BankInterface());
			Assert.fail();
		} catch (BrokerException be) {
			Assert.assertEquals(0, Broker.brokers.size());
		}
	}

	@Test
	public void emptyName() {
		try {
			new Broker("BR01", "    ", new BankInterface());
			Assert.fail();
		} catch (BrokerException be) {
			Assert.assertEquals(0, Broker.brokers.size());
		}
	}

	@After
	public void tearDown() {
		Broker.brokers.clear();
	}

}
