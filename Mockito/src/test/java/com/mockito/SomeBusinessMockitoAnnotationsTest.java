package com.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@RunWith(MockitoJUnitRunner.class)	// This @RunWith annotation is in JUnit 4 and it doesn't work.
@ExtendWith(MockitoExtension.class)		// This @RunWith is replaced by @ExtendWith annotation is in JUnit 5 and it works
public class SomeBusinessMockitoAnnotationsTest {

	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	SomeBusinessImpl someBusinessImpl;
	
	@Test
	void testFindTheGreatestFromAllData() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 24, 15, 3 });
		assertEquals(24, someBusinessImpl.findTheGreatestFromAllData());
	}
	
	@Test
	void testFindTheGreatestFromAllData_ForOneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 15 });
		assertEquals(15, someBusinessImpl.findTheGreatestFromAllData());
	}
	
	@Test
	void testFindTheGreatestFromAllData_NoValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE, someBusinessImpl.findTheGreatestFromAllData());
	}
}
