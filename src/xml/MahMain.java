package xml;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MahMain {
	public static void main(String args[]) {
		ZonedDateTime test = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		System.out.println(test.toString());
	}
}
