package objects;

public class TexSmooth implements ITexture{
	public Vector3 c1, c2, c3;
	
	public TexSmooth(Vector3 c1, Vector3 c2, Vector3 c3) {
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
	}
	
	@Override
	public Vector3 getColor(Vector2 UVPoint) {
		// return u * (c2-c1) + v*(c3-c1) + c1
		// return (1-u-v) * c1 + u * c2 + v * c3
		return Vector3.add(c1.scaled(1-UVPoint.x-UVPoint.y), 
				Vector3.add(c2.scaled(UVPoint.x), c3.scaled(UVPoint.y)));
	}
}
