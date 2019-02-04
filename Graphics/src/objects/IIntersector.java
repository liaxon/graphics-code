package objects;

/**
 * @author Liam
 * This is the class for a real object that can intersect a ray of light. It returns how far along
 * the intersection occurred.
 * 
 * TODO: make a separate "getIntersection" method which doesn't require evaluating color and such.
 * TOMAKE: cone, cube
 */
public interface IIntersector {
	// Returns the closest intersection to an object. If no intersection occurs, returns null.
	public Intersection getIntersection(Ray incoming);
}
