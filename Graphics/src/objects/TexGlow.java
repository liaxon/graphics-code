package objects;

public class TexGlow implements ITexture{
	public Vector3 mainColor, secColor; // center, background
	
	public TexGlow(Vector3 mainColor, Vector3 secColor) {
		this.mainColor = mainColor;
		this.secColor = secColor;
	}

	@Override
	public Vector3 getColor(Vector2 UVPoint) {
		double dist = UVPoint.x*UVPoint.x + UVPoint.y*UVPoint.y;
		return Vector3.weightSum(secColor, mainColor, Math.atan(dist));
	}
}
