package lifeexpectancy;

import java.util.HashMap;
import java.util.List;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
public class LifeExpectancy extends PApplet {

	UnfoldingMap map; 
	HashMap<String,Float> lifeExpbyCountry;
	?List<Feature> countries;
	?List<Marker> countryMarkers;
	
	public void setup(){
		
		/* Canvas Setup*/
		size(1200,1000,OPENGL);
		map = new UnfoldingMap(this, 50,50,1000,900, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpbyCountry = loadLFECSV("LifeExpectancyWorldBankModule3.csv");
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		?countryMarkers = MapUtils.createSimpleMarkers(countries);
		?map.addMarkers(countryMarkers);
		shader();
	}
	public void draw(){
		map.draw();
		
	}
	
	private void shader(){
		for (Marker marker : countryMarkers) {
			// Find data for country of the current marker
			String countryId = marker.getId();
			System.out.println(countryId);
			if (lifeExpbyCountry.containsKey(countryId)) {
				float lifeExp = lifeExpbyCountry.get(countryId);
				// Encode value as brightness (values range: 40-90)
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				
				System.out.println("ugh");
				marker.setColor(color(150,150,150));
			}}
	}
	
	private ?HashMap<String,Float> loadLFECSV(String fileName){
		HashMap<String, Float> lifeExpbyCountry = new HashMap<String,Float>();
		String[] rows = loadStrings(fileName);
		for (String row : rows) {
			// Reads country name and population density value from CSV row
			// NOTE: Splitting on just a comma is not a great idea here, because
			// the csv file might have commas in their entries, as this one does.  
			// We do a smarter thing in ParseFeed, but for simplicity, 
			// we just use a comma here, and ignore the fact that the first field is split.
			String[] columns = row.split(",");
			if (columns.length == 6 && !columns[5].equals("..")) {
				lifeExpbyCountry.put(columns[4], Float.parseFloat(columns[5]));
				
			}
		}
		return lifeExpbyCountry;
	}
}
