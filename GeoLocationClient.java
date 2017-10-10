package eecs2030.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GeoLocationClient {
	public static void main(String[] args) {

		GeoLocation one = GeoLocation.getInstance(79.3832, 43.6532);
		GeoLocation two = GeoLocation.getInstance(130.1234568, -7);
		GeoLocation three = GeoLocation.getInstance(62.3832, 49.76);
		GeoLocation four = GeoLocation.getInstance(170.1234568, 72);
		System.out.println(GeoLocation.getCount());
		// System.out.println(one.toString());
		List<GeoLocation> list = new ArrayList<GeoLocation>();
		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);

		for (GeoLocation geoLocation : list) {

			System.out.println(geoLocation);
		}
		Collections.sort(list);
		System.out.println();
		

		for (GeoLocation geoLocation : list) {

			System.out.println(geoLocation);
		}
		System.out.println(GeoLocation.distance(one, two)+"km");
	

	}

}
