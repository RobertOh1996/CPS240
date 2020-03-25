import java.math.BigDecimal;

public class VeggiePizza implements Pizza {

	private static final BigDecimal PRICE = new BigDecimal("8.00");

	@Override
	public String getDescription() {
		return "Veggie Pizza";
	}

	@Override
	public BigDecimal getPrice() {
		return PRICE;
	}

}
