import java.math.BigDecimal;

public class Pineapple extends PizzaDecorator {

	private static final BigDecimal PRICE = new BigDecimal("0.50");
	
	public Pineapple(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getDescription() {
		return "Pineapple";
	}

	@Override
	public BigDecimal getPrice() {
		return pizza.getPrice().add(PRICE);
	}

}
