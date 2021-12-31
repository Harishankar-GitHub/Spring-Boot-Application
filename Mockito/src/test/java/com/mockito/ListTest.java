package com.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ListTest {

	@Mock
	private List listMock;

	@AfterEach
	void tearDown() {
		reset(listMock);
	}

	@Test
	void testSize() {
		when(listMock.size()).thenReturn(10);
		assertEquals(10, listMock.size());
	}

	@Test
	void testSize_MultipleReturns() {
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
	void testGet() {
		when(listMock.get(0)).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertNull(listMock.get(1));
	}

	@Test
	void testGet_GenericParameter() {
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeString");
		assertEquals("SomeString", listMock.get(0));
		assertEquals("SomeString", listMock.get(1));
		assertEquals("SomeString", listMock.get(5));

		// Similar to Mockito.anyInt(), there are many methods like anyList, anyLong, anyMap etc.
	}
}
