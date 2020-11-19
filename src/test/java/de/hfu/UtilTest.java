package de.hfu;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {
	
	@Test
	public void testistErstesHalbjahr() {
		assertTrue(Util.istErstesHalbjahr(1));
		assertTrue(Util.istErstesHalbjahr(2));
		assertTrue(Util.istErstesHalbjahr(3));
		assertTrue(Util.istErstesHalbjahr(4));
		assertTrue(Util.istErstesHalbjahr(5));
		assertTrue(Util.istErstesHalbjahr(6));
		
		assertFalse(Util.istErstesHalbjahr(7));
		assertFalse(Util.istErstesHalbjahr(8));
		assertFalse(Util.istErstesHalbjahr(9));
		assertFalse(Util.istErstesHalbjahr(10));
		assertFalse(Util.istErstesHalbjahr(11));
		assertFalse(Util.istErstesHalbjahr(12));
	}
	
	@Test(expected=IllegalArgumentException.class, timeout=1000)
	public void testistErstesHalbjahrExexp1() {
		Util.istErstesHalbjahr(0);
	}
	@Test(expected=IllegalArgumentException.class, timeout=1000)
	public void testistErstesHalbjahrExexp2() {
		Util.istErstesHalbjahr(13);
	}
}
