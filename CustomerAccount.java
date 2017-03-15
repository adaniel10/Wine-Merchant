/**Abin Daniel
 * The following customer account class is used to store the name of the customer
 * and their initial balance. It is also used to perform certain operations 
 * such as sale or return on the initial balance.
 */

public class CustomerAccount 
{
	//instance variables
	private double initBal, transCost;
	private String custName;
	
	private Wine wm1;
	private final double SERVICE_CHARGE = 0.8; //for 20% service charge => 100%-20% = 80% (0.8 in decimal)
	
	//constructor for initializing the instance variables
	public CustomerAccount(String name, double initBalance)
	{
		custName = name;
		initBal = initBalance;
		
	}
	
	/**This method is used to process the sales of the quantity of bottles multiplied
	 * by the price of one bottle. The initial balance is then updated and the 
	 * transaction is returned as a double.
	 */
	public void saleProcess(Wine w)
	{
		wm1 = w;
		
		//transaction is calculated by: (one bottle price) x (no. of wines) 
		transCost = wm1.getBottPrice() * wm1.getWineQuant();
		initBal += transCost;		//the transaction is added to the current balance
	}
	
	/**This method will return the transaction when the process return button
	 * is pressed. The refund is calculated as the:
	 * Refund: quantity of bottles x the price of one bottle x (20% service charge) 
	 **/
	public void returnProcess(Wine w)
	{
		wm1 = w;
		
		//transaction is calculated by: (one bottle price) x (no. of wines) x (service charge) 
		transCost = wm1.getBottPrice() * wm1.getWineQuant() * SERVICE_CHARGE;
		initBal -= transCost; 		//current balance is deducted by the transaction 
	}
	
	//accessor methods
	public String getName() { return custName; }
	public double getTrans() { return transCost; }
	public double getBal() { return initBal; }
	

}
