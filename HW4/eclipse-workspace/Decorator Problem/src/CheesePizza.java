import java.math.BigDecimal;

public class CheesePizza implements Pizza {

	private static final BigDecimal PRICE = new BigDecimal("5.00");

	@Override
	public String getDescription() {
		return "Cheese Pizza";
	}

	@Override
	public BigDecimal getPrice() {
		return PRICE;
	}

}
