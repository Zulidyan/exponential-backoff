package networkshw3;
import java.util.Random;

public class Station {
	Station(){
		collisionCount = 1;
	}
	private int backoff; //next time to send
	private int collisionCount;
	private int id;
	Random r = new Random();
	
	public int getBackoff(){
		return this.backoff;
	}
	
	public int getCollisionCount(){
		return this.collisionCount;
	}
	
	public void setCollisionCount(int c){
		this.collisionCount = c;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setID(int i){
		this.id = i;
	}
	
	public int findNextSend(){
		int next = (int)Math.pow(2, this.collisionCount); //ex: if one collision, next is 2. if 2 collisions, next is 4
		return this.r.nextInt(next); //nextInt returns a value from 0 to next-1
	}
	
	public void setBackoff(int b){
		this.backoff = b;
	}
	
	public boolean send(){
		return (this.backoff == 0);
	}

}
