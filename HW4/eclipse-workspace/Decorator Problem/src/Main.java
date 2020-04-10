/**
 * Entry point for the application.
 * 
 * @author
 *
 */
public class Main {

	public static void main(String[] args) {
		Pizza pizza = new CheesePizza();
		String cheesepizza = pizza.getDescription();
		pizza = new Anchovies(pizza);
		System.out.println("Menu 1: " + pizza.getDescription() + " " +  cheesepizza);
		System.out.println("Menu 1 price: " + pizza.getPrice());
		pizza = new Pineapple(pizza);
		System.out.println("Menu 2: " + pizza.getDescription() + " Anchovies " + cheesepizza);
		System.out.println("Menu 2 price: " + pizza.getPrice());
		Pizza pizza2 = new VeggiePizza();
		String veggie = pizza2.getDescription();
		pizza2 = new Pineapple(pizza2);
		pizza2 = new Olive(pizza2);
		pizza2 = new Sausage(pizza2);
		System.out.println("Menu 3: " + pizza2.getDescription() + " Olive " + "Pineapple " + veggie);
		System.out.println("Menu 3 price: " + pizza2.getPrice());
	}

}
