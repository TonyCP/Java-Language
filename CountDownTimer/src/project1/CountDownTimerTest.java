package project1;

import static org.junit.Assert.*;

import org.junit.Test;

/**********************************************************************
 * JUNIT TEST CLASS
 * Project 1 - Count Down Timer (01/11/2018).
 * 
 * @version 1.0
 * @author TonaeChanelle_
 *********************************************************************/
public class CountDownTimerTest { 

	/** Tests for String toString() methods in all **/
	/**********************************************************************
	 * Tests for 1st timer constructor (h,m,s).
	 * 
	 *********************************************************************/
	@Test
	public void TCPconstr() {
		CountDownTimer t = new CountDownTimer(5,20,14);
		assertEquals(t.toString(), "05:20:14");
	}

	//makes sure format is correct
	@Test 
	public void TCPconstr1() {
		CountDownTimer t = new CountDownTimer(1,2,3);
		assertEquals(t.toString(), "01:02:03");
	}

	@Test
	public void TCPconstr2() { 
		CountDownTimer t = new CountDownTimer(100,14,1);
		assertEquals(t.toString(), "100:14:01");
	}

	@Test
	public void TCPconstr3() {
		CountDownTimer t = new CountDownTimer(0,0,0);
		assertEquals(t.toString(), "00:00:00");
	}

	@Test
	public void TCPconstr4() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer(0,0,0);
		assertTrue(t.equals(t2));
	}

	@Test
	public void TCPconstr5() {
		CountDownTimer t = new CountDownTimer(20,2,34);
		CountDownTimer t2 = new CountDownTimer("20:02:34");
		CountDownTimer t3 = new CountDownTimer();
		assertTrue(t.equals(t2));
		assertFalse(t2.equals(t3));
	}

	@Test
	public void TCPconstr6() {
		CountDownTimer t = new CountDownTimer("01:10:01");
		CountDownTimer t2 = new CountDownTimer(1,10,1);
		assertTrue(t.equals(t2));
	}
	//Illegal Exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr7() {
		new CountDownTimer(7,2,-50);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr8() {
		new CountDownTimer(0,-30,26);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr9() {
		new CountDownTimer(10,200,500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr10() {
		new CountDownTimer(0,1,1000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr11() {
		new CountDownTimer(-1,3,59);
	}

	/**********************************************************************
	 * Tests for 2nd timer constructor (m,s).
	 * 
	 *********************************************************************/
	@Test
	public void TCPconstr12() {
		CountDownTimer t = new CountDownTimer(10,32);
		assertEquals(t.toString(), "00:10:32");
	}

	//makes sure format is correct
	@Test
	public void TCPconstr13() {
		CountDownTimer t = new CountDownTimer(5,3);
		assertEquals(t.toString(), "00:05:03");
	}

	@Test
	public void TCPconstr14() {
		CountDownTimer t = new CountDownTimer(27,2);
		assertEquals(t.toString(), "00:27:02");
	}

	@Test
	public void TCPconstr15() {
		CountDownTimer t = new CountDownTimer(27,2);
		assertEquals(t.toString(), "00:27:02");
	}

	@Test
	public void TCPconstr16() {
		CountDownTimer t = new CountDownTimer(0,0);
		assertEquals(t.toString(), "00:00:00");
	}

	@Test
	public void TCPconstr17() {
		CountDownTimer t = new CountDownTimer(14,9);
		CountDownTimer t2 = new CountDownTimer(50,59);
		assertFalse(t.equals(t2));
		assertEquals(t.toString(), "00:14:09");
		assertEquals(t2.toString(), "00:50:59");
	}

	@Test
	public void TCPconstr18() {
		CountDownTimer t = new CountDownTimer(0,0);
		CountDownTimer t2 = new CountDownTimer();
		CountDownTimer t3 = new CountDownTimer(41,0);
		assertTrue(t.equals(t2));
		assertEquals(t.toString(), "00:00:00");
		assertEquals(t2.toString(), "00:00:00");
		assertFalse(t3.equals(t));
	}
	//Illegal Exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr19() {
		new CountDownTimer(-10,3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr20() {
		new CountDownTimer(0,-36);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr21() {
		new CountDownTimer(500,3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr22() {
		new CountDownTimer(18,100000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr23() {
		new CountDownTimer(0,-36);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr24() {
		new CountDownTimer(-200,-400);
	}

	/**********************************************************************
	 * Tests for 3rd timer constructor (s).
	 * 
	 *********************************************************************/
	@Test
	public void TCPconstr25() {
		CountDownTimer t = new CountDownTimer();
		assertEquals(t.toString(), "00:00:00");
	}

	@Test
	public void TCPconstr26() {
		CountDownTimer t = new CountDownTimer(59);
		assertEquals(t.toString(), "00:00:59");
	}

	@Test
	public void TCPconstr27() {
		CountDownTimer t = new CountDownTimer(18);
		assertEquals(t.toString(), "00:00:18");
	}

	@Test
	public void TCPconstr28() {
		CountDownTimer t = new CountDownTimer(4);
		CountDownTimer t2 = new CountDownTimer(04);
		assertTrue(t.equals(t2));
	}

	@Test
	public void TCPconstr29() {
		CountDownTimer t = new CountDownTimer(51);
		CountDownTimer t2 = new CountDownTimer(50);
		CountDownTimer t3 = new CountDownTimer(0);
		assertFalse(t.equals(t2));
		assertEquals(t3.toString(), "00:00:00");
	}

	//Illegal Exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr30() {
		new CountDownTimer(60);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr31() {
		new CountDownTimer(1000,20);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr32() {
		new CountDownTimer(-12,1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr33() {
		new CountDownTimer(-400);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCPconstr33pt2() {
		new CountDownTimer(-40,20);
	}

	/**********************************************************************
	 * Tests for CountDownTimer(CountDownTimer other).
	 * 
	 *********************************************************************/
	@Test
	public void TCP() {
		CountDownTimer t = new CountDownTimer(1,2,3);
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "01:02:03");
	}

	@Test
	public void TCP2() {
		CountDownTimer t = new CountDownTimer(0,14);
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "00:00:14");
	}

	@Test
	public void TCP3() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "00:00:00");
		assertTrue(t2.equals(t));
	}

	@Test
	public void TCP4() {
		CountDownTimer t = new CountDownTimer(1);
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "00:00:01");
	}

	@Test
	public void TCP5() {
		CountDownTimer t = new CountDownTimer(10,59);
		CountDownTimer t2 = new CountDownTimer(t);
		CountDownTimer t3 = new CountDownTimer(t2);
		assertEquals(t.toString(), "00:10:59");
		assertTrue(t2.equals(t));
		assertTrue(t3.equals(t));
	}

	@Test
	public void TCP6() {
		CountDownTimer t = new CountDownTimer(100,59,59);
		CountDownTimer t2 = new CountDownTimer(t);
		CountDownTimer t3 = new CountDownTimer(t2);
		assertEquals(t3.toString(), "100:59:59");
		assertTrue(t3.equals(t));
	}

	@Test
	public void TCP7() {
		CountDownTimer t = new CountDownTimer(40);
		CountDownTimer t2 = new CountDownTimer(t);
		CountDownTimer t3 = new CountDownTimer(t2);
		assertEquals(t2.toString(), "00:00:40");
		assertTrue(t3.equals(t2));
	}

	//null exceptions
	@Test(expected = NullPointerException.class)
	public void TCPpt2() {
		CountDownTimer t = null;
		new CountDownTimer(t);
	}

	/**********************************************************************
	 * Tests for CountDownTimer(String time).
	 * 
	 *********************************************************************/	
	@Test
	public void TCP8() {
		CountDownTimer t = new CountDownTimer("04:10:32");
		assertEquals(t.toString(), "04:10:32");
	}

	@Test
	public void TCP9() {
		CountDownTimer t = new CountDownTimer("10:32");
		assertEquals(t.toString(), "00:10:32");
	}

	@Test
	public void TCP10() {
		CountDownTimer t = new CountDownTimer("32");
		assertEquals(t.toString(), "00:00:32");
	}

	@Test
	public void TCP11() {
		CountDownTimer t = new CountDownTimer("00:00:00");
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "00:00:00");
		assertTrue(t2.equals(t));
	}

	@Test
	public void TCP12() {
		CountDownTimer t = new CountDownTimer("59:59");
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "00:59:59");
		assertTrue(t2.equals(t));
	}

	@Test
	public void TCP13() {
		CountDownTimer t = new CountDownTimer("1");
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.toString(), "00:00:01");
		assertTrue(t2.equals(t));
	}

	@Test
	public void TCP14() {
		CountDownTimer t = new CountDownTimer("10:20:30");
		CountDownTimer t2 = new CountDownTimer("17");
		CountDownTimer t3 = new CountDownTimer(t2);
		assertEquals(t2.toString(), "00:00:17");
		assertTrue(t3.equals(t2));
		assertFalse(t.equals(t3));
	}

	@Test
	public void TCP15() {
		CountDownTimer t = new CountDownTimer("400:04:04");
		CountDownTimer t2 = new CountDownTimer(t);
		CountDownTimer t3 = new CountDownTimer(t2);
		assertEquals(t2.toString(), "400:04:04");
		assertTrue(t3.equals(t2));
		assertTrue(t.equals(t3));
	}

	//Null Exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCP16() {
		new CountDownTimer("");
	}

	/**********************************************************************
	 * Tests for boolean equals(Object other).
	 * 
	 *********************************************************************/
	@Test
	public void TCP22() {
		CountDownTimer t = new CountDownTimer(1);
		CountDownTimer t2 = new CountDownTimer();
		assertFalse(t2.equals(t));
	}

	@Test
	public void TCP23() {
		Object t = new Object();
		CountDownTimer t2 = new CountDownTimer();
		assertFalse(t2.equals(t));
	} 

	@Test
	public void TCP17() {
		CountDownTimer t = new CountDownTimer("2000:59:01");
		CountDownTimer t2 = new CountDownTimer("200:59:01");
		assertFalse(t.equals(t2));
	}

	@Test
	public void TCP18() {
		CountDownTimer t = new CountDownTimer("01:01");
		CountDownTimer t2 = new CountDownTimer("01:1");
		assertTrue(t.equals(t2));
	}

	@Test
	public void TCP19() {
		CountDownTimer t = new CountDownTimer("01");
		CountDownTimer t2 = new CountDownTimer("1");
		assertTrue(t.equals(t2));
	}

	@Test
	public void TCP20() {
		CountDownTimer t = new CountDownTimer("01:02:03");
		CountDownTimer t2 = new CountDownTimer(1,2,3);
		assertTrue(t.equals(t2));
	}

	@Test
	public void TCP21(){
		CountDownTimer t = new CountDownTimer(50,6,7);
		CountDownTimer t2 = new CountDownTimer(50,06,07);
		CountDownTimer t3 = new CountDownTimer("50:06:07");
		assertTrue(t2.equals(t));
		assertTrue(t3.equals(t));
	} 

	//null exceptions
	@Test(expected = NullPointerException.class)
	public void TCP21p2() {
		CountDownTimer t = null;
		CountDownTimer t2 = new CountDownTimer();
		assertFalse(t2.equals(t));
	}

	/**********************************************************************
	 * static boolean equals(CountDownTimer t1, CountDownTimer t2).
	 * 
	 *********************************************************************/
	@Test
	public void TCP24() {
		CountDownTimer t = new CountDownTimer("08");
		CountDownTimer t2 = new CountDownTimer(8);
		assertTrue(CountDownTimer.equals(t, t2));
	}

	@Test
	public void TCP26() {
		CountDownTimer t = new CountDownTimer("02:24");
		CountDownTimer t2 = new CountDownTimer(2,25);
		assertFalse(CountDownTimer.equals(t, t2));
	}

	@Test
	public void TCP27() {
		CountDownTimer t = new CountDownTimer("10:03:32");
		CountDownTimer t2 = new CountDownTimer(10,3,32);
		assertTrue(CountDownTimer.equals(t, t2));
		assertEquals(t.getHours(), t2.getHours());
	}

	@Test
	public void TCP28() {
		CountDownTimer t = new CountDownTimer(59,1);
		CountDownTimer t2 = new CountDownTimer(t);
		assertTrue(CountDownTimer.equals(t, t2));
	}

	@Test
	public void TCP29() {
		CountDownTimer t = new CountDownTimer("100:50:07");
		CountDownTimer t2 = new CountDownTimer(102,5,7);
		assertFalse(CountDownTimer.equals(t, t2));
	}

	@Test
	public void TCP30() {
		CountDownTimer t = new CountDownTimer(1,2,3);
		CountDownTimer t2 = new CountDownTimer(t); 
		CountDownTimer t3 = new CountDownTimer(4); 
		assertTrue(CountDownTimer.equals(t, t2));
		assertFalse(CountDownTimer.equals(t, t3));
	}

	@Test
	public void TCP35() {
		CountDownTimer t = new CountDownTimer("00");
		CountDownTimer t2 = new CountDownTimer("10:10:10");
		t.setHours(t2.getHours());

		assertFalse(CountDownTimer.equals(t, t2));
	}

	@Test
	public void TCP31() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer(t);
		CountDownTimer t3 = new CountDownTimer(t2);
		assertTrue(CountDownTimer.equals(t3, t));
		assertTrue(CountDownTimer.equals(t3, t2));
	}

	//null
	@Test(expected = NullPointerException.class)
	public void TCP32() {
		CountDownTimer t = null;
		CountDownTimer t2 = new CountDownTimer(29);
		assertFalse(CountDownTimer.equals(t2, t));
	}

	@Test(expected = NullPointerException.class)
	public void TCP33() {
		CountDownTimer t = new CountDownTimer(29);
		CountDownTimer t2 = null;
		assertFalse(CountDownTimer.equals(t2, t));
	}

	@Test(expected = NullPointerException.class)
	public void TCP34() {
		CountDownTimer t = null;
		CountDownTimer t2 = null;
		assertFalse(CountDownTimer.equals(t, t2));
	}


	/**********************************************************************
	 * Tests for compareTo(CountDownTimer other).
	 * 
	 * COMEBACK - FIX 42
	 *********************************************************************/
	@Test
	public void TCP39() {
		CountDownTimer t = new CountDownTimer("50");
		CountDownTimer t2 = new CountDownTimer("05");
		assertEquals(t.compareTo(t2), 1);
	}

	@Test
	public void TCP38() {
		CountDownTimer t = new CountDownTimer("10");
		CountDownTimer t2 = new CountDownTimer(11);
		assertEquals(t.compareTo(t2), -1);
	}

	@Test
	public void TCP40() {
		CountDownTimer t = new CountDownTimer(1);
		CountDownTimer t2 = new CountDownTimer(1);
		assertEquals(t.compareTo(t2), 0);
	}

	@Test
	public void TCP41() {
		CountDownTimer t = new CountDownTimer("01:59");
		CountDownTimer t2 = new CountDownTimer("01:14");
		assertEquals(t.compareTo(t2), 1);
	}

	@Test
	public void TCP42() {
		CountDownTimer t = new CountDownTimer("20:40");
		CountDownTimer t2 = new CountDownTimer(21,0);
		assertEquals(t.compareTo(t2), -1);
		assertEquals(t.toString(), "00:20:40");
		assertEquals(t2.toString(), "00:21:00");
	}

	@Test
	public void TCP43() {
		CountDownTimer t = new CountDownTimer(30,30);
		CountDownTimer t2 = new CountDownTimer(30,30);
		assertEquals(t.compareTo(t2), 0);
	}

	@Test
	public void TCP44() {
		CountDownTimer t = new CountDownTimer("101:10:01");
		CountDownTimer t2 = new CountDownTimer("101:01:01");
		assertEquals(t.compareTo(t2), 1);
	}

	@Test
	public void TCP45() {
		CountDownTimer t = new CountDownTimer("29:04:18");
		CountDownTimer t2 = new CountDownTimer(209,4,18);
		assertEquals(t.compareTo(t2), -1);
	}

	@Test
	public void TCP46() {
		CountDownTimer t = new CountDownTimer(1,2,3);
		CountDownTimer t2 = new CountDownTimer(1,2,3);
		assertEquals(t.compareTo(t2), 0);
	}

	@Test
	public void TCP46pt2() {
		CountDownTimer t = new CountDownTimer(122,2,3);
		CountDownTimer t2 = new CountDownTimer(1,2,3);
		assertEquals(t.compareTo(t2), 1);
	}

	@Test
	public void TCP47() {
		CountDownTimer t = new CountDownTimer("10:0");
		CountDownTimer t2 = new CountDownTimer(10,0);
		CountDownTimer t3 = new CountDownTimer();
		assertEquals(t.compareTo(t3), 1);
		assertEquals(t3.compareTo(t2), -1);
		assertEquals(t.compareTo(t2), 0);
	}

	// null exception
	@Test(expected = NullPointerException.class)
	public void TCP48() {
		CountDownTimer t = null;
		CountDownTimer t2 = new CountDownTimer();
		assertEquals(t2.compareTo(t), 0);
	}

	/**********************************************************************
	 *Tests for- static int compareTo(CountDownTimer t1, CountDownTimer t2)
	 *
	 *FIX-COMEBACK 56
	 *********************************************************************/
	@Test
	public void TCP49() {
		CountDownTimer t = new CountDownTimer("4");
		CountDownTimer t2 = new CountDownTimer("3");
		assertEquals(CountDownTimer.compareTo(t,t2), 1);
	}

	@Test
	public void TCP50() {
		CountDownTimer t = new CountDownTimer("4");
		CountDownTimer t2 = new CountDownTimer(12);
		assertEquals(CountDownTimer.compareTo(t,t2), -1);
	}

	@Test
	public void TCP51() {
		CountDownTimer t = new CountDownTimer(17);
		CountDownTimer t2 = new CountDownTimer(17);
		assertEquals(CountDownTimer.compareTo(t,t2), 0);
	}

	@Test
	public void TCP52() {
		CountDownTimer t = new CountDownTimer("59:59");
		CountDownTimer t2 = new CountDownTimer("59:50");
		assertEquals(CountDownTimer.compareTo(t,t2), 1);
	}

	@Test
	public void TCP53() {
		CountDownTimer t = new CountDownTimer("10:20");
		CountDownTimer t2 = new CountDownTimer(30,20);
		assertEquals(CountDownTimer.compareTo(t,t2), -1);
	}

	@Test
	public void TCP54() {
		CountDownTimer t = new CountDownTimer(47,47);
		CountDownTimer t2 = new CountDownTimer(47,47);
		assertEquals(CountDownTimer.compareTo(t,t2), 0);
	}

	@Test
	public void TCP55() {
		CountDownTimer t = new CountDownTimer("25:43:43");
		CountDownTimer t2 = new CountDownTimer("12:10:12");
		assertEquals(CountDownTimer.compareTo(t,t2), 1);
	}

	@Test
	public void TCP55pt2() {
		CountDownTimer t = new CountDownTimer("02:43:43");
		CountDownTimer t2 = new CountDownTimer("12:10:12");
		assertEquals(CountDownTimer.compareTo(t,t2), -1);
	}

	@Test
	public void TCP56() {
		CountDownTimer t = new CountDownTimer("25:43:43");
		CountDownTimer t2 = new CountDownTimer(50,12,10);
		assertEquals(CountDownTimer.compareTo(t,t2), -1);
	}

	@Test
	public void TCP56pt2() {
		CountDownTimer t = new CountDownTimer(100,10,1);
		CountDownTimer t2 = new CountDownTimer(51,27,1);
		assertEquals(CountDownTimer.compareTo(t,t2), 1);
	}

	@Test
	public void TCP57() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer();
		assertEquals(CountDownTimer.compareTo(t,t2), 0);
	}

	//null exceptions
	@Test(expected = NullPointerException.class)
	public void TCP58() {
		CountDownTimer t = null;
		CountDownTimer t2 = new CountDownTimer();
		assertEquals(CountDownTimer.compareTo(t,t2), 1);
	}

	@Test(expected = NullPointerException.class)
	public void TCP59() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = null;
		assertEquals(CountDownTimer.compareTo(t,t2), 1);
	}

	/**********************************************************************
	 * Tests for void sub(int seconds).
	 * 
	 *********************************************************************/
	@Test
	public void TCP60() {
		CountDownTimer.suspend(false);
		CountDownTimer t = new CountDownTimer("10");
		t.sub(10);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:00:00");
	}

	@Test
	public void TCP61() {
		CountDownTimer t = new CountDownTimer(20);
		t.sub(10);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:00:10");
	}

	@Test
	public void TCP62() {
		CountDownTimer t = new CountDownTimer("1:20");
		t.sub(21);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:00:59");
	}

	@Test
	public void TCP63() {
		CountDownTimer t = new CountDownTimer(2,12);
		t.sub(5);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:02:07");
	}

	@Test
	public void TCP64() {
		CountDownTimer t = new CountDownTimer("18:02:00");
		t.sub(100);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "18:00:20");
	}

	@Test
	public void TCP65() {
		CountDownTimer t = new CountDownTimer(30,15,6);
		t.sub(1000);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "29:58:26");

		CountDownTimer.suspend(true);
		t.sub(10);
		CountDownTimer.suspend(false);
	}

	//Illegal exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCP66() {
		CountDownTimer t = new CountDownTimer("10");
		t.sub(-700);
	}

	/**********************************************************************
	 * Tests for sub(CountDownTimer other).
	 * 
	 *********************************************************************/
	@Test
	public void TCP67() {
		CountDownTimer.suspend(false);
		CountDownTimer t = new CountDownTimer("5");
		CountDownTimer t2 = new CountDownTimer("10");
		t2.sub(t);
		assertEquals(t2.toString(), "00:00:05");
	}

	@Test
	public void TCP68() {
		CountDownTimer t = new CountDownTimer("19");
		CountDownTimer t2 = new CountDownTimer(59);
		t2.sub(t);
		assertEquals(t2.toString(), "00:00:40");
	}

	@Test
	public void TCP69() {
		CountDownTimer t = new CountDownTimer(1);
		CountDownTimer t2 = new CountDownTimer(1);
		t2.sub(t);
		assertEquals(t2.toString(), "00:00:00");
	}

	@Test
	public void TCP70() {
		CountDownTimer t = new CountDownTimer("20:02");
		CountDownTimer t2 = new CountDownTimer("30:02");
		t2.sub(t);
		assertEquals(t2.toString(), "00:10:00");
	}

	@Test
	public void TCP71() {
		CountDownTimer t = new CountDownTimer("17:11");
		CountDownTimer t2 = new CountDownTimer(17,22);
		t2.sub(t);
		assertEquals(t2.toString(), "00:00:11");
	}

	@Test
	public void TCP72() {
		CountDownTimer t = new CountDownTimer(59,59);
		CountDownTimer t2 = new CountDownTimer(50,50);
		t.sub(t2);
		assertEquals(t.toString(), "00:09:09");
	}

	@Test
	public void TCP73() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer("01:02:03");
		t2.sub(t);
		assertEquals(t2.toString(), "01:02:03");
	}

	@Test
	public void TCP74() {
		CountDownTimer t = new CountDownTimer("1000:07:02");
		CountDownTimer t2 = new CountDownTimer(2000,07,02);
		t2.sub(t);
		assertEquals(t2.toString(), "1000:00:00");
	}

	@Test
	public void TCP75() {
		CountDownTimer t = new CountDownTimer(5,1,1);
		CountDownTimer t2 = new CountDownTimer(14,1,1);
		t2.sub(t);
		assertEquals(t2.toString(), "09:00:00");
	}

	@Test
	public void TCP76() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer("01:02:03");
		CountDownTimer t3 = new CountDownTimer("02:50:07");
		t2.sub(t);
		t3.sub(t2);
		assertEquals(t2.toString(), "01:02:03");
		assertEquals(t3.toString(), "01:48:04");

		CountDownTimer.suspend(true);
		t2.sub(t);
		CountDownTimer.suspend(false);
	}

	//Null exceptions
	@Test(expected = NullPointerException.class)
	public void TCP77() {
		CountDownTimer t = null;
		CountDownTimer t2 = new CountDownTimer(51);
		t2.sub(t);
	}

	/**********************************************************************
	 * Tests for dec().
	 * 
	 *********************************************************************/
	@Test
	public void TCP78() {
		CountDownTimer.suspend(false);
		CountDownTimer t = new CountDownTimer("4");
		t.dec();
		assertEquals(t.toString(), "00:00:03");
	}

	@Test
	public void TCP79() {
		CountDownTimer t = new CountDownTimer(10);
		t.dec();
		assertEquals(t.toString(), "00:00:09");
	}

	@Test
	public void TCP80() {
		CountDownTimer t = new CountDownTimer("12:12");
		t.dec();
		assertEquals(t.toString(), "00:12:11");
	}

	@Test
	public void TCP81() {
		CountDownTimer t = new CountDownTimer(52,7);
		t.dec();
		t.dec();
		t.convertSec();
		assertEquals(t.toString(), "00:52:05");
	}

	@Test
	public void TCP82() {
		CountDownTimer t = new CountDownTimer("50000:42:43");
		t.dec();
		assertEquals(t.toString(), "50000:42:42");
		assertEquals(t.convertSec(), 180002562);
	}

	@Test
	public void TCP83() {
		CountDownTimer t = new CountDownTimer(1000,59,14);
		t.dec();
		assertEquals(t.toString(), "1000:59:13");
		t.dec();
		assertEquals(t.toString(), "1000:59:12");

		CountDownTimer.suspend(true);
		t.dec();
		CountDownTimer.suspend(false);
	}


	/**********************************************************************
	 * Tests for add(int seconds).
	 * 
	 *********************************************************************/
	@Test
	public void TCP84() {
		CountDownTimer.suspend(false);
		CountDownTimer t = new CountDownTimer("20");
		t.add(5);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:00:25");
	}

	@Test
	public void TCP85() {
		CountDownTimer t = new CountDownTimer(17);
		t.add(2);
		t.add(2);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:00:21");
	}

	@Test
	public void TCP86() {
		CountDownTimer t = new CountDownTimer("04:01");
		CountDownTimer t2 = new CountDownTimer(t);
		t2.add(14);
		assertEquals(t2.toString(), "00:04:15");
	}

	@Test
	public void TCP87() {
		CountDownTimer t = new CountDownTimer(21,9);
		t.add(40);
		CountDownTimer t2 = new CountDownTimer(t);

		assertEquals(t2.toString(), "00:21:49");
	}

	@Test
	public void TCP88() {
		CountDownTimer t = new CountDownTimer("34:25:06");
		t.add(2);
		CountDownTimer t2 = new CountDownTimer(t);
		t2.add(18);

		assertEquals(t2.toString(), "34:25:26");
		assertFalse(t2.equals(t));
	}

	@Test
	public void TCP89() {
		CountDownTimer t = new CountDownTimer();
		t.add(2);
		t.add(2);
		CountDownTimer t2 = new CountDownTimer(t);
		t.equals(t2);

		assertEquals(t2.toString(), "00:00:04");
		assertTrue(t2.equals(t));

		CountDownTimer.suspend(true);
		t.add(0);
		CountDownTimer.suspend(false);
	}

	//Illegal exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCP90() {
		CountDownTimer t = new CountDownTimer(17);
		t.add(-1);
	}

	/**********************************************************************
	 * Tests for add(CountDownTimer other).
	 * 
	 **********************************************************************/
	@Test
	public void TCP91() {
		CountDownTimer.suspend(false);
		CountDownTimer t = new CountDownTimer("2");
		CountDownTimer t2 = new CountDownTimer("10");
		t2.add(t);
		assertEquals(t2.toString(), "00:00:12");
	}

	@Test
	public void TCP92() {
		CountDownTimer t = new CountDownTimer("9");
		CountDownTimer t2 = new CountDownTimer(11);
		t2.add(t);
		assertEquals(t2.toString(), "00:00:20");
	}

	@Test
	public void TCP93() {
		CountDownTimer t = new CountDownTimer(37);
		CountDownTimer t2 = new CountDownTimer(4);
		t2.add(t);
		assertEquals(t2.toString(), "00:00:41");
	}

	@Test
	public void TCP94() {
		CountDownTimer t = new CountDownTimer("01:01");
		CountDownTimer t2 = new CountDownTimer("02:02");
		t2.add(t);
		assertEquals(t2.toString(), "00:03:03");
	}

	@Test
	public void TCP95() {
		CountDownTimer t = new CountDownTimer("20:05");
		CountDownTimer t2 = new CountDownTimer(12,9);
		t2.add(t);
		assertEquals(t2.toString(), "00:32:14");
	}

	@Test
	public void TCP96() {
		CountDownTimer t = new CountDownTimer(2,2);
		CountDownTimer t2 = new CountDownTimer(2,2);
		t2.add(t);
		assertEquals(t2.toString(), "00:04:04");
		t2.add(t);
		assertEquals(t2.toString(), "00:06:06");
	}

	@Test
	public void TCP97() {
		CountDownTimer t = new CountDownTimer("60:01:20");
		CountDownTimer t2 = new CountDownTimer("02:04:20");
		t2.add(t);
		assertEquals(t2.toString(), "62:05:40");
	}

	@Test
	public void TCP98() {
		CountDownTimer t = new CountDownTimer("19:23:07");
		CountDownTimer t2 = new CountDownTimer(1,2,3);
		t2.add(t);
		assertEquals(t2.toString(), "20:25:10");
	}

	@Test
	public void TCP99() {
		CountDownTimer t = new CountDownTimer();
		CountDownTimer t2 = new CountDownTimer(100,1,1);
		t2.add(t);
		assertEquals(t2.toString(), "100:01:01");

		CountDownTimer.suspend(true);
		t2.add(t);
		CountDownTimer.suspend(false);
	}

	//Null exceptions
	@Test(expected = NullPointerException.class)
	public void TCP100() {
		CountDownTimer t = null;
		CountDownTimer t2 = new CountDownTimer(102,8,11);
		t2.add(t);
	}

	/**********************************************************************
	 * Tests for void inc().
	 * 
	 *********************************************************************/
	@Test
	public void TCP101() {
		CountDownTimer.suspend(false);
		CountDownTimer t = new CountDownTimer("10");
		t.inc();
		assertEquals(t.toString(), "00:00:11");
	}

	@Test
	public void TCP102() {
		CountDownTimer t = new CountDownTimer(8);
		t.inc();
		t.inc();
		assertEquals(t.toString(), "00:00:10");
	}

	@Test
	public void TCP103() {
		CountDownTimer t = new CountDownTimer("41:28");
		t.inc();
		assertEquals(t.toString(), "00:41:29");
	}

	@Test
	public void TCP104() {
		CountDownTimer t = new CountDownTimer(21,21);
		t.inc();
		assertEquals(t.toString(), "00:21:22");
	}

	@Test
	public void TCP105() {
		CountDownTimer t = new CountDownTimer("12:04:25");
		t.inc();
		assertEquals(t.toString(), "12:04:26");
		t.inc();
		t.inc();
		assertEquals(t.toString(), "12:04:28");

		CountDownTimer.suspend(true);
		t.inc();
		CountDownTimer.suspend(false);
	}

	@Test
	public void TCP106() {
		CountDownTimer t = new CountDownTimer(1200,59,59);
		t.inc();
		assertEquals(t.toString(), "1201:00:00");
	}

	/**********************************************************************
	 * Tests for save/load(String fileName).
	 * 
	 *********************************************************************/
	@Test
	public void TCP107() {
		CountDownTimer t = new CountDownTimer("1");
		t.save("timer");
		t.add(20);
		assertEquals(t.toString(), "00:00:21");
		t.load("timer");
		assertEquals(t.toString(), "00:00:01");
	}

	@Test
	public void TCP108() {
		CountDownTimer t = new CountDownTimer(15);
		t.save("timer");
		t.add(28);
		assertEquals(t.toString(), "00:00:43");
		t.load("timer");
		assertEquals(t.toString(), "00:00:15");
	}

	@Test
	public void TCP109() {
		CountDownTimer t = new CountDownTimer("39:40");
		t.save("timer");
		t.sub(35);
		assertEquals(t.toString(), "00:39:05");
		t.load("timer");
		assertEquals(t.toString(), "00:39:40");
	}

	@Test
	public void TCP110() {
		CountDownTimer t = new CountDownTimer(53,2);
		t.save("timer");
		t.sub(2);
		assertEquals(t.toString(), "00:53:00");
		t.load("timer");
		assertEquals(t.toString(), "00:53:02");
	}

	@Test
	public void TCP111() {
		CountDownTimer t = new CountDownTimer("4203:21:50");
		CountDownTimer t2 = new CountDownTimer("03:01:00");
		t2.save("timer");
		t2.add(20);
		assertEquals(t2.toString(), "03:01:20");
		t.sub(10);
		t.load("timer");
		assertEquals(t.toString(), "03:01:00");
	}

	@Test
	public void TCP112() {
		CountDownTimer t = new CountDownTimer(21,2,4);
		CountDownTimer t2 = new CountDownTimer();
		t2.save("timer");
		t2.add(20);
		assertEquals(t2.toString(), "00:00:20");
		t.add(t2);
		t.sub(4);
		t.load("timer");
		assertEquals(t.toString(), "00:00:00");
	}

	//Illegal exceptions
	@Test(expected = IllegalArgumentException.class)
	public void TCP113() {
		CountDownTimer t = new CountDownTimer();
		t.save("***timer");
	}

	@Test(expected = IllegalArgumentException.class)
	public void TCP114() {
		CountDownTimer t = new CountDownTimer(4);
		t.save("timer");
		t.add(20);
		assertEquals(t.toString(), "00:00:24");
		t.load("timr");
	}

	/**********************************************************************
	 * Tests for suspend(boolean flag). 
	 * Tests for getters and setters.
	 *
	 *********************************************************************/
	//set seconds
	@Test
	public void TCP116() {
		CountDownTimer t = new CountDownTimer("1");
		CountDownTimer.suspend(true);

		t.setSeconds(18);
		assertEquals(t.toString(), "00:00:01");
		CountDownTimer.suspend(false);

		t.setSeconds(18);
		assertEquals(t.toString(), "00:00:18");
	}

	@Test
	public void TCP119() {
		CountDownTimer t = new CountDownTimer(24,3);

		assertEquals(t.toString(), "00:24:03");

		CountDownTimer.suspend(true);
		t.setSeconds(41);

		assertEquals(t.toString(), "00:24:03");
	}

	@Test
	public void TCP120() {
		CountDownTimer t = new CountDownTimer("1500:59:10");
		CountDownTimer.suspend(false);
		t.setSeconds(18);
		assertEquals(t.toString(), "1500:59:18");
	}

	//get seconds
	@Test
	public void TCP117() {
		CountDownTimer t = new CountDownTimer(21);
		assertEquals(t.getSeconds(), 21);
	}

	@Test
	public void TCP118() {
		CountDownTimer t = new CountDownTimer("31:03");
		CountDownTimer t2 = new CountDownTimer(t);
		assertEquals(t2.getSeconds(), 3);
	}

	@Test
	public void TCP121() {
		CountDownTimer t = new CountDownTimer(1,2,3);
		CountDownTimer t2 = new CountDownTimer(3,2,1);
		assertEquals(t.getSeconds(), 3);
		assertEquals(t2.getSeconds(), 1);
	}

	//set minutes
	@Test
	public void TCP122() {
		CountDownTimer t = new CountDownTimer(0);

		t.setMinutes(12);
		CountDownTimer.suspend(true);
		t.setMinutes(30);

		assertEquals(t.toString(), "00:12:00");
	}

	@Test
	public void TCP123() {
		CountDownTimer t = new CountDownTimer("49:02");

		CountDownTimer.suspend(true);
		t.setMinutes(1);

		assertEquals(t.toString(), "00:49:02");

		CountDownTimer.suspend(false);
		t.setMinutes(20);

		assertEquals(t.toString(), "00:20:02");
	}

	@Test
	public void TCP124() {
		CountDownTimer t = new CountDownTimer(567,4,50);
		CountDownTimer t2 = new CountDownTimer(t);
		t2.setMinutes(0);
		CountDownTimer.suspend(true);
		assertEquals(t2.toString(), "567:00:50");
		t2.setMinutes(50);
		assertFalse(t.equals(t2));

		CountDownTimer.suspend(false);
	}

	//get minutes
	@Test
	public void TCP125() {
		CountDownTimer t = new CountDownTimer("9");
		CountDownTimer t2 = new CountDownTimer(t);
		t.getMinutes();

		assertEquals(t2.getMinutes(), 0);
	}

	@Test
	public void TCP126() {
		CountDownTimer t = new CountDownTimer(19,12);
		assertEquals(t.getMinutes(), 19);
	}

	@Test
	public void TCP127() {
		CountDownTimer t = new CountDownTimer("143:12:21");
		assertEquals(t.getMinutes(), 12);
	}

	//set hours
	@Test
	public void TCP128() {
		CountDownTimer t = new CountDownTimer("32");

		t.setHours(2678);
		CountDownTimer.suspend(true);
		t.setHours(3);
		CountDownTimer.suspend(false);

		assertEquals(t.toString(), "2678:00:32");	
	}

	@Test
	public void TCP129() {
		CountDownTimer t = new CountDownTimer(27,15);
		CountDownTimer t2 = new CountDownTimer("20:02");

		t.setHours(50);
		assertEquals(t.toString(), "50:27:15");	

		CountDownTimer.suspend(true);
		t2.setHours(3);
		CountDownTimer.suspend(false);
		t.setHours(36);

		assertEquals(t.toString(), "36:27:15");	
		assertEquals(t2.toString(), "00:20:02");	
	}

	@Test
	public void TCP130() {
		CountDownTimer t = new CountDownTimer(5,16,50);
		CountDownTimer.suspend(true);
		t.setHours(3);

		assertEquals(t.toString(), "05:16:50");	

		CountDownTimer.suspend(false);
	}

	//get hours
	@Test
	public void TCP131() {
		CountDownTimer t = new CountDownTimer();
		assertEquals(t.getHours(), 0);
	}

	@Test
	public void TCP132() {
		CountDownTimer t = new CountDownTimer("34:29");
		CountDownTimer t2 = new CountDownTimer(1,14,54);

		assertEquals(t.getHours(), 0);
		assertEquals(t2.getHours(), 1);
	}

	@Test
	public void TCP133() {
		CountDownTimer t = new CountDownTimer(4300,1,0);
		assertEquals(t.getHours(), 4300);
	}


}
