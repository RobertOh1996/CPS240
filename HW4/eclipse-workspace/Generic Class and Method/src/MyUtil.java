import java.util.Collections;
import java.util.List;

public class MyUtil {

	public static <E extends Comparable<E>> E min(List<E> list) {
		E min = Collections.min(list);
		return min;	
	}
	
}