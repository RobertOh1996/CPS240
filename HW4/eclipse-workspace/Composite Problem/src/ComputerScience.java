/**
 * A computer science department leaf.
 * 
 * @author Jaemin Oh
 *
 */
public class ComputerScience implements Department {

	private Integer id;
	private String name;

	public ComputerScience(int i, String string) {
		this.id = i;
		this.name = string;
	}

	@Override
	public void printDepartmentName() {
		System.out.println(name);
	}

}
