import java.util.List;

public class MyUtil {

	public static <E extends Comparable<E>> E max(List<E> list) {
		E max = Collections.max(list);
		return max;
	}
}