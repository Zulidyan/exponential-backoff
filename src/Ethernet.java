package networkshw3;
import java.util.ArrayList;

public class Ethernet {
	Ethernet(int n){
		timeline = 0;
		numStations = n;
		stationArray = new ArrayList<Station>(numStations);
		for(int i = 1; i <= numStations; i++){ //fill the array with stations
			Station s = new Station();
			stationArray.add(s);
			s.setID(i);
		}
	}
	private int timeline;
	private int numStations;
	private ArrayList<Station> stationArray;
	
	public ArrayList<Station> getStationArray(){
		return this.stationArray;
	}
	
	public int getTimeline(){
		return this.timeline;
	}

}
