//Exponential backoff simulation
//Christian Brading 
//CS 4310
// 3/18/15
package networkshw3;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		int numStations = 20;
		boolean canSend = false;
		Ethernet ethernet = new Ethernet(numStations);
		//ArrayList<Station> stations = ethernet.getStationArray();
		ArrayList<Station> sendArray = new ArrayList<Station>();
		int timeline = ethernet.getTimeline();
		
		for(Station s : ethernet.getStationArray()){//get initial backoff times at random (0 or 1)
			s.setBackoff(s.findNextSend());
		}
		int loops = 20; //the number of times to run the whole simulation. Higher number will result in better accuracy
						//but will take longer
		int[] times = new int[loops]; //hold finishing times in an array

		for(int i = 0; i < loops; i++){
		while(!canSend){
			for(Station s : ethernet.getStationArray()){
				//uncomment next two lines to see Station # and backoff choices
				//System.out.print("Station "+s.getID()+ "\n");
				//System.out.println(s.getBackoff());
				if(s.send()){ //if a station tries to transmit this time slot
					sendArray.add(s);
					int c = s.getCollisionCount();
					c++;
					s.setCollisionCount(c); //increment collisions
					s.setBackoff(s.findNextSend());//get new backoff time
					
				}
				else{
					int b = s.getBackoff();
					b--;
					s.setBackoff(b); //count down one
					//System.out.println("Station "+s.getID()+ " new backoff: "+ b);
				}
				//end for
			}
			if(sendArray.size() == 1)canSend = true; //if only one station sends, there is no collision and we are done
			timeline++;
			//uncomment to see collision count and finishing time T
//			for(Station t: sendArray ){
//				System.out.println("Updated. Station "+t.getID()+" has "+t.getCollisionCount()+" collisions");
//			}
			//System.out.println("T: "+timeline+"\n");
			sendArray.clear();
		}
		//reset collision count, backoff, canSend, and timeline in between loops
		for(Station q: ethernet.getStationArray()){
			q.setCollisionCount(0);
			q.setBackoff(0);
		}
		canSend = false;
		times[i] = timeline;
		timeline = 0;
		}
		//get average slot time
		double sum = 0;
		double avg = 0.0;
		for(int i: times){
			sum += i;
//			System.out.println(i);
		}
		avg = sum / loops;
		System.out.println("N = "+ numStations);
		System.out.println("Average is: "+ avg);

	}

}
