
public class Case {
	Proprio occupant;
	Vision vision;
	Bateaux bat;
	
	public Case() {
		occupant = Proprio.Libre;
		vision = Vision.Brouillard;
	}
	public Case(Proprio occupant,Vision vision) {
		this.occupant = occupant;
		this.vision = vision;
	}
	
}
