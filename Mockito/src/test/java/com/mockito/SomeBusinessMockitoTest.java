package com.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SomeBusinessMockitoTest {

	@Test
	void testFindTheGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
		// If we cannot import mock then follow below steps :
		// Window > Preferences > Java > Editor > Content Assist > Favorites
		
//		dataServiceMock.retrieveAllData() => new int[] {24, 15, 3};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		
		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = someBusinessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
	
	@Test
	void testFindTheGreatestFromAllData_ForOneValue() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		
		SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceMock);
		int result = someBusinessImpl.findTheGreatestFromAllData();
		assertEquals(24, result);
	}
}
