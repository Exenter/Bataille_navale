import java.util.*;

public class Position {
	Point p1;
	Point centre;
	Point p2;
	String orientation;
	
	
	public Position(Point p1, Point centre, Point p2 ) {
		
		this.p1 = p1;
		this.centre = centre;
		this.p2 = p2;
		if(p1.x == p2.x && p1.x== centre.x) {
			orientation = "vert";
		}
		else {
			orientation = "horiz";
		}
	}
	
	public void deplacement(String sens) {
		switch (sens) {
		case "avant":
			if (orientation == "vert") {
				p1.depX(sens);
				centre.depX(sens);
				p2.depX(sens);
			}
			else {
				p1.depY(sens);
				centre.depY(sens);
				p2.depY(sens);
			}
			
			break;
		case "arriere":
			if (orientation == "vert") {
				p1.depX(sens);
				centre.depX(sens);
				p2.depX(sens);
			}
			else {
				p1.depY(sens);
				centre.depY(sens);
				p2.depY(sens);
			}
			
			break;
			
		case "rotation":
			if (orientation == "vert") {
				p1.rotationX(centre, "+");
				p2.rotationX(centre, "-");
				orientation = "horiz";
			}
			else {
				p1.rotationY(centre, "+");
				p2.rotationY(centre, "-");
				orientation = "vert";
			}
			
			break;
			
		default:
			System.exit(0);
			break;
		}
	}
}
