import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		//Problem One
		Oracle<Integer> oracle1 = new Oracle<Integer>();
		oracle1.set(10, 20);
		System.out.println(oracle1.getLarger());

		Oracle<String> oracle2 = new Oracle<String>();
		oracle2.set("Hi", "Hello");
		System.out.println(oracle2.getLarger());

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(MyUtil.max(list));

		}
	}
}
