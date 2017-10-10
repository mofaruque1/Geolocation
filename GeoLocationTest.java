package eecs2030.assignment;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoLocationTest {

	@Test
	public void testGetInstanceOne() {
		double lon = 150;
		double lat = 65;
		boolean check = false;
		GeoLocation gLocation = GeoLocation.getInstance(lon, lat);
		if(gLocation!=null){
			check = true;
		}
		assertTrue(check);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetInstanceTwo() {
		double lon = -190;
		double lat = 65;
		boolean check = false;
		GeoLocation gLocation = GeoLocation.getInstance(lon, lat);
		if(gLocation!=null){
			check = true;
		}
		assertTrue(check);
	}
	
	
	@Test
	public void testGetInstanceThree() {
		double lon = 180;
		double lat = -90;
		boolean check = false;
		GeoLocation gLocation = GeoLocation.getInstance(lon, lat);
		if(gLocation!=null){
			check = true;
		}
		assertTrue(check);
	}
	
	
	@Test
	public void testGetInstanceFour() {
		double lon = -180;
		double lat = -90;
		boolean check = false;
		GeoLocation gLocation = GeoLocation.getInstance(lon, lat);
		if(gLocation!=null){
			check = true;
		}
		assertTrue(check);
	}
	
	
	@Test
	public void testGenerate() {
		double lon = -2180;
		double lat = -900;
		boolean check = false;
		GeoLocation gLocation = GeoLocation.generate(lon, lat);
		if(gLocation!=null){
			check = true;
		}
		assertTrue(check);
	}

	@Test
	public void testGetGMTHourOffsetOne() {
		GeoLocation geoLocation = GeoLocation.getInstance(130.11, -7);
		int expected = (int)(130.11*24)/360;
		int actual = geoLocation.getGMTHourOffset();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetGMTHourOffsetTwo() {
		GeoLocation geoLocation = GeoLocation.getInstance(-180.00, -90);
		int expected = (int)(-180.00*24)/360;
		int actual = geoLocation.getGMTHourOffset();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetGMTHourOffsetToronto() {
		GeoLocation geoLocation = GeoLocation.getInstance(-79.24, 43.40);
		int expected = -5;
		int actual = geoLocation.getGMTHourOffset();
		assertEquals(expected, actual);
	}

	
	@Test
	public void testDistance() {
		GeoLocation one = GeoLocation.getInstance(79.3832, 43.6532);
		GeoLocation two = GeoLocation.getInstance(130.1234568, -7);
		double expected = 7590.186839640372;
		double actual = GeoLocation.distance(one, two);
		double delta= 1e-9;
		assertEquals(expected, actual, delta);
		
	}

	@Test
	public void testToString() {
		GeoLocation one = GeoLocation.getInstance(79.3832, 43.6532);
		String expected = "+79.3832,+43.6532";
		String actual = one.toString();
		assertEquals(expected, actual);
	}
	

	@Test
	public void testGetCount() {
		GeoLocation glOne = GeoLocation.getInstance(-79.24, 43.40);
		GeoLocation glTwo = GeoLocation.getInstance(-180.00, -90);
		int expected = 2;
		int actual = GeoLocation.getCount();
		assertEquals(expected, actual);
	}
	



}
