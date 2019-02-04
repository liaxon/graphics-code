package objects;

/**
 * This class is for intersections, and stores the information that the main program needs
 * to decide what to do next. Returned by getIntersection()
 * 
 * If any of the fields are null, that's fine.
 */
public class Intersection {
	public static final double BUFFER = 0.01; // this is the minimum distance away something needs to be to be seen
	
	public double distance;
	public Vector3 position;
	public Vector3 direction; //normalized
	public Vector3 normal; //normalized
	public boolean usesColorer;
	private Vector3 colorVector; //in range
	private IColorer colorer;
	
	public Intersection(double distance, Vector3 colorVector) { // the minimal
		this.distance = distance;
		this.colorVector = colorVector;
		usesColorer = false;
	}
	public Intersection(double distance, IColorer colorer) { // the minimal
		this.distance = distance;
		this.colorer = colorer;
		usesColorer = true;
	}
	public Intersection(double distance, Vector3 position, Vector3 direction, Vector3 normal, Vector3 colorVector) {
		this.distance = distance;
		this.position = position;
		this.direction = direction;
		this.normal = normal;
		this.colorVector = colorVector;
		usesColorer = false;
	}
	public Intersection(double distance, Vector3 position, Vector3 direction, Vector3 normal, IColorer colorer) {
		this.distance = distance;
		this.position = position;
		this.direction = direction;
		this.normal = normal;
		this.colorer = colorer;
		usesColorer = true;
	}
	
	public static Intersection make(double distance, Ray ray, Vector3 normal, Vector3 colorVector) {
		// below ensures you're reflecting off the right side -- no need to do this in the IIntersector object
		Vector3 normal2 = Vector3.dot(ray.dir, normal) < 0 ? normal : normal.scaled(-1);
		return new Intersection(distance, ray.extrapolate(distance), normal2.reflected(ray.dir), normal2, colorVector);
	}
	public static Intersection make(double distance, Ray ray, Vector3 normal, IColorer colorer) {
		// below ensures you're reflecting off the right side -- no need to do this in the IIntersector object
		Vector3 normal2 = Vector3.dot(ray.dir, normal) < 0 ? normal : normal.scaled(-1);
		return new Intersection(distance, ray.extrapolate(distance), normal2.reflected(ray.dir), normal2, colorer);
	}
	
	public Ray toRay() {
		return new Ray(position, direction);
	}
	public Vector3 getColor() {
		if(usesColorer)
			return colorer.getColor();
		else
			return colorVector;
	}
	public void setColor(Vector3 colorVector) {
		usesColorer = false;
		this.colorVector = colorVector;
	}
	public void setColor(IColorer colorer) {
		usesColorer = true;
		this.colorer = colorer;
	}
}