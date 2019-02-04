package objects;

/**
 * This class is for real, physical objects.
 * They must include what are typically material properties,
 * such as color, transparency, reflectivity,
 * as well as physical properties like a "mesh"
 * 
 * All of these objects are stored in a list in the master class.
 */
public class RealThing
{
	public IIntersector intersector;
	
	public boolean active = true;
	
	// right now, transparent should be false
	public boolean transparent;
	public double transparency; // 0.0 to 1.0
	// right now, "reflective" should always be false
	public boolean reflective;
	public double reflectivity; // 0.0 to 1.0
	
	//TODO: test all this
}
