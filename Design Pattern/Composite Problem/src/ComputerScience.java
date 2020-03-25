/**
 * A computer science department leaf.
 * 
 * @author ripke1tj
 *
 */
public class ComputerScience implements Department {

	private Integer id;
	private String name;

	@Override
	public void printDepartmentName() {
		System.out.println(name);
	}

}
