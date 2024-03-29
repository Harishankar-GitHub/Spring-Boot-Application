package com.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeBusinessStubTest {

	@Test
	void testFindTheGreatestFromAllData() {
		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(new DataServiceStub());
		int result = someBusinessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
	
	class DataServiceStub implements DataService	 {
		// Using a Stub is difficult when the DataService Interface is updated.
		// We need to create many Stubs to test different scenarios.
		// We can use a Mockito instead of this.
		@Override
		public int[] retrieveAllData()
		{
			return new int[] {24, 6, 15};
		}
	}
}
