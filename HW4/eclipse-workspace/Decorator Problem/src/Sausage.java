import java.math.BigDecimal;

public class Sausage extends PizzaDecorator {

	private static final BigDecimal PRICE = new BigDecimal("4.00");
	
	public Sausage(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getDescription() {
		return "Sausage";
	}

	@Override
	public BigDecimal getPrice() {
		return pizza.getPrice().add(PRICE);
	}

}
