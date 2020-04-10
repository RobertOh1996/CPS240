import java.math.BigDecimal;

public class Olive extends PizzaDecorator {

	private static final BigDecimal PRICE = new BigDecimal("3.00");
	
	public Olive(Pizza pizza) {
		super(pizza);
	}
	
	@Override
	public String getDescription() {
		return "Olive";
	}

	@Override
	public BigDecimal getPrice() {
		return pizza.getPrice().add(PRICE);
	}

}
