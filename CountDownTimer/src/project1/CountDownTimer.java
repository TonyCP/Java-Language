package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**********************************************************************
 * Project 1 - Count Down Timer (01/11/2018).
 * write a countdown timer class to help out the city of GR!
 * 
 * @version 1.0
 * @author TonaeChanelle_
 *********************************************************************/

public class CountDownTimer {

	/** Represents hours of timer **/
	private int hours;

	/** Represents minutes of timer **/
	private int minutes;

	/** Represents seconds of timer **/
	private int seconds;

	/** on/off switch for methods **/
	private static boolean suspend = false;

	/** Default constructor - sets timer to zero **/
	public CountDownTimer() {
		super();
	}

	/******************************************************************
	 * Constructor initializing instance variables.
	 * @param hours - hours on timer.
	 * @param minutes - minutes on timer.
	 * @param seconds -  seconds on timer.
	 * @exception - Illegal (# out of bounds).
	 *****************************************************************/
	public CountDownTimer(int hours, int minutes, int seconds) {
		super();
		if(hours < 0 || minutes < 0 ||seconds < 0  ||minutes > 59 
				|| seconds > 59) {
			throw new IllegalArgumentException("Input not valid");
		}
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		overLoad(); 
	}

	/******************************************************************
	 * Constructor initializing variables - sets hours to 0.
	 * @param minutes - minutes on timer.
	 * @param seconds - seconds on timer.
	 * @exception - Illegal (# out of bounds).
	 *****************************************************************/
	public CountDownTimer(int minutes, int seconds) {
		super();
		if(minutes < 0 || seconds < 0 || minutes > 59 || seconds > 59)
		{	throw new IllegalArgumentException("Input not valid");
		}
		this.hours = 0;
		this.minutes = minutes;
		this.seconds = seconds;
		overLoad(); 
	}

	/******************************************************************
	 * Constructor initializing variables - sets hours & minutes to 0.
	 * @param seconds - seconds on timer.
	 *  @exception - Illegal (# out of bounds).
	 *****************************************************************/
	public CountDownTimer(int seconds) {
		super();
		if(seconds < 0 || seconds > 59){
			throw new IllegalArgumentException("Input not valid");
		}
		this.hours = 0;
		this.minutes = 0;
		this.seconds = seconds;
		overLoad();
	}

	/******************************************************************
	 * Constructor initializing "this" timer with "other" timer param.
	 * @param other - another timer.
	 * @exception - Null (CountDownTimer null).
	 *****************************************************************/
	public CountDownTimer(CountDownTimer other) {
		if(other == null) {
			throw new NullPointerException("Null input. Try again.");
		}
		this.hours = other.hours;
		this.minutes = other.minutes;
		this.seconds = other.seconds;
		overLoad();
	}

	/******************************************************************
	 * Constructor accepts a String as param - 01:21:30.
	 * @param time - string input of time.
	 * @throws Exception -  Illegal(empty string).
	 * @exception - Illegal (empty string).
	 *****************************************************************/
	public CountDownTimer(String time)  {
		String t[] = time.split(":");

		//must have input- no empty strings.
		if(time.length() == 0) {
			throw new IllegalArgumentException("No input. Try again.");
		} 

		if(t.length == 1) {
			hours = 0;
			minutes = 0;
			seconds = Integer.parseInt(t[0]);
			overLoad();
		}if(t.length == 2) {
			hours = 0;
			minutes = Integer.parseInt(t[0]);
			seconds = Integer.parseInt(t[1]);
			overLoad();
		}if(t.length == 3) {
			hours = Integer.parseInt(t[0]);
			minutes = Integer.parseInt(t[1]);
			seconds = Integer.parseInt(t[2]);
			overLoad();
		}
	} 

	/******************************************************************
	 * Method returns true if "this" timer matches "other" timer.
	 * @param other -  object entered for comparison.
	 * @exception - Null (object null).
	 *****************************************************************/
	public boolean equals(Object other) {
		if(other == null) {
			throw new NullPointerException("Null input. Try again.");
		}
		if(other instanceof CountDownTimer) 
			if(this.hours == ((CountDownTimer)other).hours
			&& this.minutes == ((CountDownTimer)other).minutes 
			&& this.seconds == ((CountDownTimer)other).seconds) {
				return true;
			}
		return false;
	}

	/******************************************************************
	 * Static method returns true if timer t1 matches timer t2.
	 * @param t1 - first timer for comparison.
	 * @param t2 - second timer for comparison.
	 * @return - returns true (t1 = t2)/ false (t1 != t2).
	 * @exception - Null (CountDownTimer null).
	 *****************************************************************/
	public static boolean equals(CountDownTimer t1, CountDownTimer t2) {
		if(t1 == null || t2 == null) {
			throw new NullPointerException();
		}
		if((t1.hours == t2.hours) 
				&& (t1.minutes == t2.minutes)
				&& (t1.seconds == t2.seconds)) {
			return true; 
		}
		return false;
	}

	/**********************************************************************
	 * Method: 
	 * @param other - timer for comparison.
	 * @return - returns 1 if "this" timer is (greater) than "other" timer .
	 * @return - returns -1 if "this" timer is (less) than "other" timer.
	 * @return - returns 0 if "this" timer is (equal) to "other" timer.
	 * @exception - Null (CountDownTimer null).
	 **********************************************************************/

	public int compareTo(CountDownTimer other)  {
		if(other == null) {
			throw new NullPointerException("Null input. Try again.");
		}

		if(this.convertSec() > other.convertSec()) {
			return 1;
		}else if(this.convertSec() < other.convertSec()) {
			return -1;
		}
		return 0;
	}



	/**********************************************************************
	 * Static method: 
	 * @param t1 - first timer for comparison.
	 * @param t2 - seconds timer for comparison.
	 * @return - returns 1 if timer t1 is (greater) than timer t2.
	 * @return - returns -1 if timer t1  is (less) than timer t2. 
	 * @return -  returns 0 if timer t1  is (equal) to timer t2.
	 * @exception - Null (CountDownTimer null).
	 *********************************************************************/
	public static int compareTo(CountDownTimer t1, CountDownTimer t2) {
		if(t1 == null || t2 == null) {
			throw new NullPointerException("Null input. Try again.");
		}

		if(t1.convertSec() > t2.convertSec()) {
			return 1;
		}else if(t1.convertSec() < t2.convertSec()) {
			return -1;
		}

		return 0;
	}

	/**********************************************************************
	 * Method subtracts seconds from "this" timer.
	 * @param seconds - seconds subtracted from timer.
	 * @exception - Illegal ( # seconds less than 0).
	 *********************************************************************/
	public void sub(int seconds) {
		if(seconds < 0) {
			throw new IllegalArgumentException("No negative input. Try again.");
		}
		if(!suspend)
			for(int i = 0; i < seconds; i++) {
				dec();
			}
		overLoad();
	} 


	/**********************************************************************
	 * Method subtracts "other" timer from "this" timer.
	 * @param other - another timer used to mutate "this" timer.
	 * @exception - Null (CountDownTimer null).
	 *********************************************************************/
	public void sub(CountDownTimer other) {
		if(other == null) {
			throw new NullPointerException("Null input. Try again.");
		}
		if(!suspend)
			this.hours -= other.hours;
		this.minutes -= other.minutes;
		this.seconds -= other.seconds;
		overLoad();

	}

	/** Method decrements "this" timer by 1 second **/
	public void dec() {
		if(!suspend)
			if(convertSec() > 0) {
				if(seconds > 0) {
					seconds -= 1;	
				}else{	
					seconds = 59;
					if(minutes > 0) {
						minutes -= 1;
					}else{
						minutes = 59;
						hours -= 1;
					}
				}
			}
	}

	/**********************************************************************
	 * Method adds seconds to "this" timer.
	 * @param seconds - seconds added to timer.
	 * @exception - Illegal ( # seconds less than 0).
	 *********************************************************************/
	public void add(int seconds) { 
		if(seconds < 0) {
			throw new IllegalArgumentException("No negative input. Try again.");
		}
		if(!suspend)
			this.seconds += seconds;
		overLoad();
	}

	/**********************************************************************
	 * Method adds "other" timer to "this" timer .
	 * @param other - other timer used to mutate "this" timer.
	 * @exception - Null (CountDownTimer null).
	 *********************************************************************/
	public void add(CountDownTimer other) {
		if(other == null) {
			throw new NullPointerException("Null input. Try again.");
		}
		if(!suspend)
			this.hours += other.hours;
		this.minutes += other.minutes;
		this.seconds += other.seconds;
		overLoad();
	}

	/** Method increments "this" timer by 1 second **/
	public void inc() {
		if(!suspend)
			this.seconds += 1;
		overLoad();
	}

	/** Method returns string representation of timer **/
	public String toString() {
		String h = Integer.toString(hours);
		String m = Integer.toString(minutes);
		String s = Integer.toString(seconds);

		if(h.length() == 1) {
			h = "0" + h;
		}
		if(m.length() == 1) {
			m = "0" + m;	
		}
		if(s.length() == 1) {
			s = "0" + s;	
		}
		overLoad();
		return h + ":" + m + ":" + s;
	}

	/**********************************************************************
	 * Method saves "this" CountDownTimer to a file.
	 * @param fileName - name of file "saved" to location.
	 *********************************************************************/
	public void save(String fileName) {
		PrintWriter out = null;
		try {
			//save fileName
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		}
		//problem saving fileName
		catch (Exception e) {
			throw new IllegalArgumentException("Error: " + e);
		}

		//save time to fileName
		out.println(this.hours + ":" + this.minutes + ":" + this.seconds);
		out.close();

	}

	/**********************************************************************
	 * Method loads "this" CountDownTimer from a file.
	 * @param fileName - name of file "loaded" from location.
	 *********************************************************************/
	public void load(String fileName) {
		String time;
		try {
			//open the data file
			Scanner fileReader = new Scanner(new File(fileName));

			//read the data file
			time = fileReader.next();

			String t[] = time.split(":");
			this.hours = Integer.parseInt(t[0]);
			this.minutes = Integer.parseInt(t[1]);
			this.seconds = Integer.parseInt(t[2]);

			System.out.println(t);
			fileReader.close();
		}

		//problem reading file
		catch(Exception e) {
			throw new IllegalArgumentException("Error: " + e);
		}

	}

	/**********************************************************************
	 * Method turns timer methods "on"(false) and "off"(true).
	 * @param flag - boolean controlling methods.
	 *********************************************************************/
	public static void suspend(boolean flag) {
		suspend = flag;
	}

	/**Method for timer overload **/
	public void overLoad() {
		//Formats seconds and minutes
		if(seconds > 59) {
			this.minutes += this.seconds / 60;
			this.seconds = this.seconds % 60;
		}
		if(minutes > 59) {
			this.hours += this.minutes / 60;
			this.minutes = this.minutes % 60;
		}
		//Extra
		if(hours < 0 || minutes < 0 || seconds < 0) {
			throw new IllegalArgumentException();
		}
		//		if(seconds < 0 ) {
		//			this.minutes -= 1;
		//			this.seconds += 60;
		//		}
		//		if(minutes < 0) {
		//			this.hours -= 1;
		//			this.minutes += 60;
		//		}
	}
	/** Method to convert timer to seconds **/
	int convertSec() {
		int totalSec = (3600 * hours) + (60 * minutes) + seconds;
		return totalSec;
	}

	//GETTERS AND SETTERS
	/**********************************************************************
	 * Get the hours of "this" timer.
	 * @return - returns hours.
	 *********************************************************************/
	public int getHours() {
		return hours;
	}

	/**********************************************************************
	 * Set hours of "this" timer.
	 * @param hours - hours of timer.
	 *********************************************************************/
	public void setHours(int hours) {
		if(!suspend)
			this.hours = hours;
	}

	/**********************************************************************
	 * Get the minutes of "this" timer.
	 * @return - returns minutes. 
	 *********************************************************************/
	public int getMinutes() {
		return minutes;
	}

	/**********************************************************************
	 * Set minutes of "this" timer.
	 * @param minutes - minutes of timer.
	 *********************************************************************/
	public void setMinutes(int minutes) {
		if(!suspend)
			this.minutes = minutes;
	}

	/**********************************************************************
	 * Get the seconds of "this" timer.
	 * @return - returns seconds.
	 *********************************************************************/
	public int getSeconds() {
		return seconds;
	}

	/**********************************************************************
	 * Set seconds of "this" timer.
	 * @param seconds - seconds on timer.
	`*********************************************************************/
	public void setSeconds(int seconds) {
		if(!suspend)
			this.seconds = seconds;
	}
}
