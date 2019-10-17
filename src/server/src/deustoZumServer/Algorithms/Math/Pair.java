package deustoZumServer.Algorithms.Math;

public class Pair implements Comparable<Pair>{
	public final int index;
	public final double value;
	public Pair(int index,double value) {
		this.index =index;
		this.value = value;
	}
	@Override
	public int compareTo(Pair o) {
		return Double.valueOf(this.value).compareTo(o.value);
	}
	public int getIndex() {
		return index;
	}
	public double getValue() {
		return value;
	}
	
	
	
}
