package module1;

public class SimpleLocation {

	public double lat, lon;
	
	public SimpleLocation(double lat, double lon)
	{
		this.lat = lat;
				this.lon = lon;
	}
	//overloading by creating a secondary constructor!
	public SimpleLocation(){
		this.lat = 43.44f;
		this.lon=33.3f;
	}
	
}
