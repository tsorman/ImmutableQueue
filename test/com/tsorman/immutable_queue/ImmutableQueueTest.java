package com.tsorman.immutable_queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ImmutableQueueTest {
	
	private static Queue<String> testQueue ;
	private static final String FIRST_QUEUEE = "First" ;
	private static final String SECOND_QUEUEE = "Second" ;
	
	private static Queue<Integer> testQueueNum ;
	private static final Integer FIRST_QUEUEE_NUM = 1 ;
	private static final Integer SECOND_QUEUEE_NUM = 2 ;
	
	@BeforeAll
    static void beforeAll() {
		
		testQueue = new ImmutableQueue<String>() ;
		testQueueNum = new ImmutableQueue<Integer>() ;
    }
	
	@Test
	public void testIsEmpty () {
		boolean isQueueEmpty = testQueue.isEmpty() ;
		assertTrue(isQueueEmpty);
	}
	
	@Test
	public void testHead () {
		Queue<String> queueddOne = testQueue.enQueue(FIRST_QUEUEE) ;
		assertEquals( FIRST_QUEUEE,  queueddOne.head());
	}

	@Test
	public void testEnQueue () {
		
		Queue<String> queueddOne = testQueue.enQueue(FIRST_QUEUEE) ;
		
		assertTrue(testQueue.isEmpty());
		assertFalse (queueddOne.isEmpty()) ;
		
	}
	
	@Test
	public void testDequeue () 
	{
		Queue<String> queueddOne = testQueue.enQueue(FIRST_QUEUEE) ;
		Queue<String> deQueued = queueddOne.deQueue() ;
		
		assertTrue ( deQueued.isEmpty()) ;
		
		assertNotEquals ( testQueue , deQueued) ;
	}
	
	@Test
	public void testDeQueueEmpty () 
	{
		assertThrows(NoSuchElementException.class, () -> {
			testQueue.head();
	    });
		
	}
	
	@Test
	public void queueTwo () {
		
		Queue<String> queueddOne = testQueue.enQueue(FIRST_QUEUEE) ;
		Queue<String> queueddTwo = queueddOne.enQueue(SECOND_QUEUEE) ;
		
		assertEquals(FIRST_QUEUEE, queueddTwo.head());
		
	}
	
	@Test
	public void queueTwoDequeueUntilEmpty () {
		Queue<String> queueddOne = testQueue.enQueue(FIRST_QUEUEE) ;
		Queue<String> queueddTwo = queueddOne.enQueue(SECOND_QUEUEE) ;
		
		assertEquals ( FIRST_QUEUEE, queueddTwo.head()) ;
		
		Queue<String> firstDequeue = queueddTwo.deQueue() ;
		
		assertEquals ( SECOND_QUEUEE , firstDequeue.head()) ;
		
		Queue<String> secondDeQueue = firstDequeue.deQueue() ;
		
		assertTrue( secondDeQueue.isEmpty()); 
		assertNotEquals(testQueue, secondDeQueue);
		
		assertThrows(NoSuchElementException.class, () -> {
			secondDeQueue.head();
	    });
	}
	
	@Test
	public void queueTwoDequeueUntilEmptyWithNumbers () {
		Queue<Integer> queueddOne = testQueueNum.enQueue(FIRST_QUEUEE_NUM) ;
		Queue<Integer> queueddTwo = queueddOne.enQueue(SECOND_QUEUEE_NUM) ;
		
		assertEquals ( FIRST_QUEUEE_NUM, queueddTwo.head()) ;
		
		Queue<Integer> firstDequeue = queueddTwo.deQueue() ;
		
		assertEquals ( SECOND_QUEUEE_NUM , firstDequeue.head()) ;
		
		Queue<Integer> secondDeQueue = firstDequeue.deQueue() ;
		
		assertTrue( secondDeQueue.isEmpty());
		assertNotEquals(testQueueNum, secondDeQueue);
		
		assertThrows(NoSuchElementException.class, () -> {
			secondDeQueue.head();
	    });
	}
	
	

}
