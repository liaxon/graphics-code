package objects;

public class Grouping implements IIntersector{
	public IIntersector[] intersectors;
	public Vector3 origin;
	
	public Grouping(Vector3 origin, IIntersector... intersectors) {
		this.origin = origin;
		this.intersectors = intersectors;
	}

	@Override
	public Intersection getIntersection(Ray incoming) {
		Ray modelIncoming = incoming.translate(origin.scaled(-1)); // move to model space
		Intersection bestInter = null;
		for(IIntersector intsec : intersectors) {
			Intersection newInter = intsec.getIntersection(modelIncoming);
			if(newInter != null && (bestInter==null || newInter.distance < bestInter.distance))
				bestInter = newInter;
		}
		if(bestInter!=null)
			bestInter.position = Vector3.add(bestInter.position, origin); // back from model space
		return bestInter;
	}
}
