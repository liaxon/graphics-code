package objects;


/**
 * This is designed with the equation:
 * (XYZ - point) is in the vector-space <u, v>.
 * In other words, (XYZ-point) . (uxv) = 0
 * 
 * TODO: add a way to specify a plane with 3 points
 */

public class Plane implements IIntersector {
	public Vector3 point;
	public Vector3 u, v; // these are not assumed to be normalized or orthogonal, or to have normalized cross product.
	public Vector3 n; // this is set at the beginning, and not changed.
	public boolean usesTexture;
	public IShape shape;
	private ITexture texture;
	private Vector3 colorVec; 
	
	@Override
	public Intersection getIntersection(Ray incoming){
		// NOTE: works even if "dir" is not normalized
		// also NOTE: works b/c math
		double dist = Vector3.dot(n, Vector3.sub(point, incoming.start)) / Vector3.dot(n, incoming.dir);
		if(dist >= Intersection.BUFFER) {
			Intersection hit =  Intersection.make(dist, incoming, n.normalized(), colorVec);
			Vector2 UVPoint = toUV(incoming.extrapolate(dist));
			if(shape.getInPlane(UVPoint)) {
				if(usesTexture)
					hit.setColor(texture.getColor(UVPoint));
				return hit;
			}
			else
				return null;
		}
		else
			return null;
	}
	
	public Plane(Vector3 point, Vector3 u, Vector3 v, IShape shape, Vector3 colorVec) {
		this.point = point;
		this.u = u;
		this.v = v;
		this.n = Vector3.cross(u,v);
		this.shape = shape;
		this.setColor(colorVec);
	}
	
	public Plane(Vector3 point, Vector3 u, Vector3 v, IShape shape, ITexture colorer) {
		this.point = point;
		this.u = u;
		this.v = v;
		this.n = Vector3.cross(u,v);
		this.shape = shape;
		this.setColor(colorer);
	}
	
	public Vector3 fromUV(Vector2 inside) {
		return Vector3.add(point, Vector3.add(u.scaled(inside.x), v.scaled(inside.y)));
	}
	// this below first projects onto the plane, then scales to U/V.
	// if just UVO were returned, this would also contain the projection to the orthogonal axis (n).
	// if this gets called a lot, it might make sense to make Matrix3(u,v,n).inverted() a property
	public Vector2 toUV(Vector3 outside) {
		Matrix3 characteristic = new Matrix3(u, v, n).inverted();
		Vector3 UVO = characteristic.applied(Vector3.sub(outside, point));
		return new Vector2(UVO.x, UVO.y);
	}
//	// this is much faster, but only works if the u, v are orthogonal unit vectors:
//	public Vector2 toUV(Vector3 outside) {
//		Vector3 diff = Vector3.sub(outside, point);
//		return new Vector2(Vector3.dot(diff, u), Vector3.dot(diff, v));
//	}
	
	public double getSide(Vector3 outside) { // Returns the side of the plane a point is on
		return Vector3.dot(Vector3.sub(outside, point), n) > 0 ? 1 : -1;
		// use with: n.scaled(getSide(outside))
	}
	
	// gives this a simple Vector3 color;
	public void setColor(Vector3 colorVec) {
		usesTexture = false;
		this.colorVec = colorVec;
	}
	public void setColor(ITexture texture) {
		usesTexture = true;
		this.texture = texture;
	}
}