package eecs2030.assignment;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Md Omor Faruque
 * @version 1.0
 * @since 2017-06-12
 *        <h1>GeoLocation</h1> 
 *        The GeoLocation class implements an application
 *        that takes longitude and latitude and calculates the distance between
 *        two points and GMT offset of each point in Eastern Standard Time
 */
public final class GeoLocation implements Comparable<GeoLocation> {
	/**
	 * The value of longitude in degree
	 */
	final private double longitude;
	final private double lattitude;

	private static final Map<String, GeoLocation> instances = new TreeMap<String, GeoLocation>();
	private static final DecimalFormat df = new DecimalFormat("+#,#00.0000;-#");

	private GeoLocation(double longitude, double lattitude) {
		this.longitude = longitude;
		this.lattitude = lattitude;
	}

	/**
	 * l This method is used to check if the two parameters are in range and
	 * valid. After that it checks if the similar object exists and if doesn't
	 * then creates a new object
	 * 
	 * @param longitude
	 *            a degree value between -180 to 180
	 * @param lattitude
	 *            a degree value between -90 to 90
	 * @return new object if the object doesn't exist already
	 * @pre longitude is greater than or equal to -180 and less than or equal to
	 *      180, latitude is greater than or equal to -90 and less than or equal
	 *      to 90
	 * @throw IllegalArgumentExceptionif the parameters are not valid
	 * 
	 */
	public static GeoLocation getInstance(double longitude, double lattitude) {
		if (longitude >= -180 && longitude <= 180 && lattitude >= -90 && lattitude <= 90) {

			String key = "" + df.format(longitude) + "," + df.format(lattitude);
			GeoLocation n = GeoLocation.instances.get(key);
			if (n == null) {
				n = new GeoLocation(longitude, lattitude);
				GeoLocation.instances.put(key, n);
			}
			return n;
		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * This factory method is used to create a robust object that will work for
	 * any numbers passed as arguments and will be converted to valid arguments
	 * 
	 * @param longitude
	 *            a degree value between -180 to 180
	 * @param lattitude
	 *            a degree value between -90 to 90
	 * @pre Parameters should be double value type
	 * @return new object if the object doesn't exist already
	 */
	public static GeoLocation generate(double longitude, double latitude) {
		double lon = longitude;
		double lat = latitude;
		if (longitude < -180) {
			lon = -180;
		} else if (longitude > 180) {
			lon = 180;
		}

		if (latitude < -90) {
			lat = -90;
		} else if (lat > 90) {
			lat = 90;
		}

		GeoLocation gl = getInstance(lon, lat);
		return gl;

	}

	/**
	 * Returns the GMT offset in Eastern Standard Time
	 * 
	 * @return GMT offset of given GeoLocation
	 */
	public int getGMTHourOffset() {

		return (int) (this.longitude * 24) / 360;
	}

	/**
	 * Returns the no of GeoLocation object created
	 * 
	 * @return number of created object
	 */
	public static int getCount() {
		return instances.size();
	}

	/**
	 * Returns the shortest distance between two GeoLocation
	 * 
	 * @param location1
	 *            a GeoLocation object
	 * @param location2
	 *            a GeoLocation object
	 * @return shortest distance between two GeoLocation points
	 */
	public static double distance(GeoLocation location1, GeoLocation location2) {
		double lat1 = location1.lattitude;
		double lon1 = location1.longitude;
		double lat2 = location2.lattitude;
		double lon2 = location2.longitude;
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1.609344;
		return dist;

	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	@Override
	public String toString() {

		return df.format(this.longitude) + "," + df.format(this.lattitude);
	}

	@Override
	public boolean equals(Object obj) {
		GeoLocation gl = (GeoLocation) obj;

		if (Math.abs(this.longitude) == Math.abs(gl.longitude) && this.lattitude == gl.lattitude) {
			return true;
		}
		return false;

	}

	@Override
	public int compareTo(GeoLocation o) {

		int gmtOffsetOne = this.getGMTHourOffset();
		int gmtOffsetTwo = o.getGMTHourOffset();
		if (gmtOffsetOne > gmtOffsetTwo) {
			return 1;
		} else if (gmtOffsetOne < gmtOffsetTwo) {
			return -1;
		} else {
			return Double.compare(this.lattitude, o.lattitude);
		}
	}

}
