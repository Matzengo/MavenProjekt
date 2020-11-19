package de.hfu;

import org.junit.*;
import static org.junit.Assert.*;

public class QueueTest {
	
	private int para1, para2, para3, para4;
	private Queue que;
	
	@Before
	public void testIntit() {
		que = new Queue(3);
		this.para1=1;
		this.para2=2;
		this.para3=3;
		this.para4=4;
	}
	
	@Test
	public void testQueue() {
		que.enqueue(para1);
		que.enqueue(para2);
		que.enqueue(para3);
		que.enqueue(para4);
		assertEquals(para1, que.dequeue());
		assertEquals(para2, que.dequeue());
		assertEquals(para4, que.dequeue());
	}
	
	@Test(expected=IllegalStateException.class, timeout=1000)
	public void testistErstesHalbjahrExexp2() {
		que.dequeue();
	}
	@Test
	public void testHeadTail() {
		que.enqueue(para1);
		assertEquals(0, que.head);
		assertEquals(0, que.tail);
		que.enqueue(para2);
		assertEquals(0, que.head);
		assertEquals(1, que.tail);
		que.enqueue(para3);
		assertEquals(0, que.head);
		assertEquals(2, que.tail);
		que.enqueue(para4);
		assertEquals(0, que.head);
		assertEquals(2, que.tail);
	}
}
