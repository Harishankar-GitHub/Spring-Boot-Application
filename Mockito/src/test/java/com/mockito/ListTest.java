package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	void testSize()
	{
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(10);
		assertEquals(10, listMock.size());
	}
	
	@Test
	void testSize_MultipleReturns()
	{
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(10).thenReturn(20);
		// When it is called 1st time, it returns 10, next time it returns 20.
		// 3rd time it returns the last thenReturn() Value i.e., 20.
		
		int firstTimeCalled = listMock.size();
		int secondTimeCalled = listMock.size();
		
//		System.out.println(firstTimeCalled);
//		System.out.println(secondTimeCalled);
		
		assertEquals(10, firstTimeCalled);
		assertEquals(20, secondTimeCalled);
	}
	
	@Test
	void testGet()
	{
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}
	
	@Test
	void testGet_GenericParameter()
	{
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertEquals("SomeString", listMock.get(1));
		assertEquals("SomeString", listMock.get(5));
		
		// Similar to Mockito.anyInt(), there are many methods like anyList, anyLong, anyMap etc.		
	}
}
