import java.math.BigDecimal;

public class Anchovies extends PizzaDecorator {

	private static final BigDecimal PRICE = new BigDecimal("2.00");
	
	public Anchovies(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getDescription() {
		return "Anchovies";
	}

	@Override
	public BigDecimal getPrice() {
		return pizza.getPrice().add(PRICE);
	}

}
