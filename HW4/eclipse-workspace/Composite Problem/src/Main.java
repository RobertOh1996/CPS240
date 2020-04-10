/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		Department cs = new ComputerScience(1, "Computer Science");
		HeadDepartment dp = new HeadDepartment(3, "Head Department");
		dp.addDepartment(cs);
		System.out.println("Step 1:");
		dp.printDepartmentName();
		Department mt = new Mathmatics(2, "Mathmatics");
		dp.addDepartment(mt);
		System.out.println("Step 2:");
		dp.printDepartmentName();
		System.out.println("Step 3:");
		dp.removeDepartment(cs);
		dp.printDepartmentName();		
	}

}
