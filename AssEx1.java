/**Abin Daniel
 * This class contains the main method where it will get the information of the 
 * customers name and their initial balance via two input dialog boxes. 
 */

import javax.swing.*;

public class AssEx1 
{
	public static void main(String[] arg)
	{
		String name = "";	//initializing name to an empty string

		try
		{
			//Creates the name input dialog box
			name = JOptionPane.showInputDialog(null, "Enter Name:", "Customer Information", 
					JOptionPane.INFORMATION_MESSAGE);

			//Exit the program if name is empty
			if (name.isEmpty())
			{
				System.exit(0);			//terminates program
			}
		}
		catch(NullPointerException e)	
		{
			System.exit(0);				
		}


		/*The following block of code creates the initial balance input dialog 
		 * box and catches if an non-number is entered into the dialog box.
		 */
		double initBalance = 0;
		boolean result = true;							//Sets result to true

		do{
			try 
			{
				initBalance = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Initial Balance: £", 
						"Current Balance", JOptionPane.INFORMATION_MESSAGE));
				result = false;							//Sets result to false
			}

			catch(NumberFormatException nFE) 
			{
				JOptionPane.showMessageDialog(null, "Not a Valid Number", 
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (result);

		CustomerAccount cu = new CustomerAccount(name, initBalance); //customer account object
		
		//Create and set up the window
		LWMGUI lwm = new LWMGUI(cu);	//passing customerAccount object as a parameter							 
	}

}
