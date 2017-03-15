/**Abin Daniel
 * This wine class is a model class. It is used to produce information 
 * regarding the type of wine which was purchased or returned as well as the price
 * of the bottle and its quantity.
 */

public class Wine 
{
	//initializing the instance variables
	private String wineName;		//representing the wine name
	private double bottPrice;		//representing the price of one bottle
	private int wineQuant;			//representing the quantity of a wine
	
	//constructor of the 'Wine' class with three parameters
	public Wine(String n, int q, double p)
	{
		wineName = n;
		wineQuant = q;
		bottPrice = p;
	}
	
	//accessor methods
	public String getWineName() { return wineName; }
	public double getBottPrice() { return bottPrice; }
	public int getWineQuant() { return wineQuant; }

}
