package telran.application.dates;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ReminderAppl {
private static final long DEFAULT_START_WAITING_MILLISECONDS = 0;
private static final long DEFAULT_BEEPS_DURATION = 3_600_000; //in milliseconds
static long intervalOfBeeps;
static ChronoUnit unit;
static long beepsDuration;
static long beepsStartWaiting;
	public static void main(String[] args) {
		
		//args[0] - mandatory interval of beeps 
		//args[1] - mandatory ChronoUnit (according to time units of ChronoUnit)
		//args[2] - optional in ChronoUnit values when to end up beeps (default) 1 hour
		//args[3] - optional in ChronoUnit values when to start beeping (default immediately )
		//beep - System.out.println("\007")
		try {
			setIntervalOfBeeps(args);
			setUnit(args);
			intervalToMillis();
			setBeepsDuration(args);
			setBeepsStartWaiting(args);
			runBeeps();
		}catch(RuntimeException e) {
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	private static void runBeeps() {
		waitingFor(beepsStartWaiting);
		Instant start = Instant.now();
		
		do {
			waitingFor(intervalOfBeeps);
			System.out.println("\007\007\007");
		}while(ChronoUnit.MILLIS.between(start, Instant.now()) < beepsDuration);
		
	}
	private static void setBeepsStartWaiting(String[] args) throws Exception {
		if (args.length < 4) {
			beepsStartWaiting = DEFAULT_START_WAITING_MILLISECONDS; 
		} else {
			try {
				beepsStartWaiting = Long.parseLong(args[3]);
			} catch (NumberFormatException e) {
				throw new Exception("Beeps start waiting time should be a number");
			}
			if (beepsDuration < 0) {
				throw new Exception("Beeps start waiting time can't be a negative number");
			}
			beepsStartWaiting = Duration.of(beepsStartWaiting, unit).toMillis();
			
		}
		
	}
	private static void intervalToMillis() {
		intervalOfBeeps = Duration.of(intervalOfBeeps, unit).toMillis();//conversion to milliseconds from unit
		
	}
	private static void setBeepsDuration(String[] args) throws Exception {
		if (args.length < 3) {
			beepsDuration = DEFAULT_BEEPS_DURATION; //one hour to milliseconds
		} else {
			try {
				beepsDuration = Long.parseLong(args[2]);
			} catch (NumberFormatException e) {
				throw new Exception("Beeps duration should be a number");
			}
			if (beepsDuration < 0) {
				throw new Exception("Beeps duration can't be a negative number");
			}
			beepsDuration = Duration.of(beepsDuration, unit).toMillis();
			
		}
		
	}
	private static void setUnit(String[] args) throws Exception {
		if (args.length < 2) {
			throw new Exception("Chrono Unit should be specified as the second argument");
		}
		try {
			unit = ChronoUnit.valueOf(args[1].toUpperCase());
			
		} catch (IllegalArgumentException e) {
			throw new Exception("Wrong Chrono Unit");
		}
		
	}
	private static void setIntervalOfBeeps(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Interval between beeps should be specified as the first argument");
		}
		try {
			intervalOfBeeps = Long.parseLong(args[0]);
		}catch (NumberFormatException e) {
			throw new Exception("Interval between beeps should be a number");
		}
		if (intervalOfBeeps < 0) {
			throw new Exception("interval can't be a negative number");
		}
		
	}
	/**
	 * waiting for the given time period in milliseconds
	 * @param periodInMillis
	 */
	static void waitingFor(long periodInMillis) {
		Instant start = Instant.now();
		do {
			
		}while(ChronoUnit.MILLIS.between(start, Instant.now()) < periodInMillis);
	}

}
