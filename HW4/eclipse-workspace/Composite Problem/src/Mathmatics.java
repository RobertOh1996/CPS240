/**
 * A computer science department leaf.
 * 
 * @author Jaemin Oh
 *
 */
public class Mathmatics implements Department {

	private Integer id;
	private String name;

	public Mathmatics(int i, String string) {
		this.id = i;
		this.name = string;
	}
	
	@Override
	public void printDepartmentName() {
		System.out.println(name);
	}

}
