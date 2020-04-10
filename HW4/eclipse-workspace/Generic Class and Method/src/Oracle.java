
public class Oracle<T extends Comparable<T>> {

	T v1, v2;

	public void set(T v1, T v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	public T getLarger(){
		if(v1.compareTo(v2) > 0){
			return v1;
		} else return v2;
	}
}
