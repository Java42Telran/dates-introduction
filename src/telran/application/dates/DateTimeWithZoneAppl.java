package telran.application.dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeWithZoneAppl {
	static ZoneId zone;
public static void main(String[] args) throws Exception {
	//args[0] - optional substring of time Zone (default - local time zone)
	try {
		setZone(args);
		System.out.println(ZonedDateTime.now(zone));
	} catch (RuntimeException e) {
		
		e.printStackTrace();
	}
catch (Exception e) {
		
		System.out.println(e.getMessage());
	}
}
private static void setZone(String[] args) throws Exception {
	if (args.length == 0) {
		zone = ZoneId.systemDefault();
	} else {
		String zoneStr = args[0].toUpperCase();
		if (zoneStr.matches("GMT[-+]\\d\\d?")) {
			zone = ZoneId.of(zoneStr);
		} else {
			zone = getZone(zoneStr);
		}
	}
	
}
private static ZoneId getZone(String zoneContent) throws Exception {
	for (String strZone: ZoneId.getAvailableZoneIds()) {
		if (strZone.toUpperCase().contains(zoneContent)) {
			return ZoneId.of(strZone);
		}
	}
	throw new Exception("Wrong Zone content");
}
}
