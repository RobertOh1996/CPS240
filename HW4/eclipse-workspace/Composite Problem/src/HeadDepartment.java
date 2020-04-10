import java.util.ArrayList;
import java.util.List;

public class HeadDepartment implements Department {

	private Integer id;
	private String name;

	private List<Department> childDepartments;

	public HeadDepartment(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.childDepartments = new ArrayList<Department>();
	}

	@Override
	public void printDepartmentName() {
		for (Department department : childDepartments) {
			department.printDepartmentName();
		}
	}

	public void addDepartment(Department department) {
		if (!childDepartments.contains(department)) {
			childDepartments.add(department);
		}
	}

	public void removeDepartment(Department department) {
		if (childDepartments.contains(department)) {
			childDepartments.remove(department);
		}
	}

}
