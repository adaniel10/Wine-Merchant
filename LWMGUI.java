/**Abin Daniel
 * This LWMGUI class is used to provide the interface for the user to process the
 * sales and the return. It is also used to handle certain events. This class
 * is the View and Controller class in the model-view-controller style.
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LWMGUI extends JFrame implements ActionListener
{
	private JButton saleButton, returnButton; 												//declaring two buttons
	private JTextField setName, setQuantity, setPrice, setTrans, setBal;					//declaring five text fields
	private JLabel nameLabel, quantityLabel, priceLabel, wineLabel, transLabel, balLabel;	//declaring six labels
	private CustomerAccount mma;
	private int sQuant;
	private double sPrice;
	private String sName, i ,j;


	public LWMGUI(CustomerAccount m)
	{	
		mma = m;

		//Creating the frame
		setTitle("Lilybank Wine Merchants: "+ mma.getName());	//sets the title
		setSize(500, 180);										//sets the size of the frame
		setLocation(700, 400);									//sets the location of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//terminates the program if window is closed

		//Creating the buttons
		saleButton = new JButton("Process Sale");
		returnButton = new JButton("Process Return");

		//Creating the text fields
		setName = new JTextField(15);
		setQuantity = new JTextField(5);
		setPrice = new JTextField(5);
		setTrans = new JTextField("� ", 8);
		setBal = new JTextField(8);

		//check if initial balance is negative or not
		double sBal = mma.getBal();		//gets the balance from the customerAccount

		if(sBal < 0)
		{
			//if negative then => use 'CR' to display negative money
			setBal.setText(String.format("�%.2f CR", mma.getBal()*-1));
		}
		else
			setBal.setText(String.format("�%.2f", mma.getBal()));

		//Creating the labels
		nameLabel = new JLabel("Name:");
		quantityLabel = new JLabel("Quantity:");
		priceLabel = new JLabel("Price: �");
		wineLabel = new JLabel("Wine Purchased: ");
		transLabel = new JLabel("Amount of Transaction:");
		balLabel = new JLabel("Current Balance:");

		//Adding buttons to the frame
		setLayout(new GridLayout(4,1));		//Sets the grid layout to 4 rows and 1 column			

		JPanel pan = new JPanel();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();

		pan.add(nameLabel);
		pan.add(setName);
		pan.add(quantityLabel);
		pan.add(setQuantity);
		pan.add(priceLabel);
		pan.add(setPrice);

		pan1.add(saleButton);
		pan1.add(returnButton);

		pan2.add(wineLabel);

		pan3.add(transLabel);
		pan3.add(setTrans);
		pan3.add(balLabel);
		pan3.add(setBal);

		//Add labels/text-field/buttons to the frame
		add(pan);
		add(pan1);
		add(pan2);
		add(pan3);

		//Setting up un-editable fields
		setTrans.setEditable(false);
		setBal.setEditable(false);

		//Adding listener to each button
		saleButton.addActionListener(this);	
		returnButton.addActionListener(this);

		setVisible(true);						//displays the window by setting it to true
	}
	
	//a method to print the sale
	private void printSale()
	{
		Wine win = new Wine(sName, sQuant, sPrice);					//creates a wine object
		mma.saleProcess(win);										//passing wine object as a parameter
		wineLabel.setText("Wine Purchased: " + win.getWineName());	//setting the wine name
		
		checkBalance();
	}
	
	//method to print the return
	private void printReturn()
	{
		Wine win = new Wine(sName, sQuant, sPrice);					//creates a wine object
		mma.returnProcess(win);										//passing wine object as a parameter
		wineLabel.setText("Wine Purchased: " + win.getWineName());	//setting the wine name
		
		checkBalance();
		
	}
	
	//checks the balance if it is whether negative or positive
	private void checkBalance()
	{
		if (mma.getBal() < 0)	//if the current balance is less than zero
		{
			//print the details onto the transaction and balance field
			setTrans.setText(String.format("�%.2f", mma.getTrans()));	//prints to the transaction field
			setBal.setText(String.format("�%.2f CR", mma.getBal()*-1));	//displays 'CR' for negative money and prints it to balance field
		}
		else
		{
			setTrans.setText(String.format("�%.2f", mma.getTrans()));	//prints to the transaction field
			setBal.setText(String.format("�%.2f", mma.getBal()));
		}
	}


	/**This method is used to clear the text fields of name, quantity and wine
	 * whenever it is called. The fields are cleared using a white space. 
	 */
	private void clear()
	{
		//clearing the fields
		setName.setText("");
		setQuantity.setText("");
		setPrice.setText("");
	}

	//This block of code checks which buttons were pressed: sale / return process
	public void actionPerformed(ActionEvent e)
	{
		sName = setName.getText();			//gets info from name field then converts to string

		if(setName.getText().isEmpty() || setPrice.getText().isEmpty() || 
				setQuantity.getText().isEmpty())	//checks text field/price field/quantity field if its empty
		{
			//displays a message
			JOptionPane.showMessageDialog(null, "Please Enter All Details", 
					"Alert: Missing Information", JOptionPane.WARNING_MESSAGE);
			clear();								//clear the fields
			wineLabel.setText("Wine Purchased: ");	//clear the wine field
			setTrans.setText("�");					//resets transaction field
			setName.grabFocus(); 					//takes the cursor back to the name field
		}
		else
		{
			try
			{
				i = setQuantity.getText();			//gets info. from quantity field then converts to string
				sQuant = Integer.parseInt(i);		//converts string to integer

				if (sQuant < 0)						//checks if the quantity is negative
				{
					//displays quantity error message
					JOptionPane.showMessageDialog(null, "Please Enter A Positive Number", 
							"Quantity Error", JOptionPane.ERROR_MESSAGE);
					wineLabel.setText("Wine Purchased: ");
					clear();
					setTrans.setText("�");
				}
				else
				{
					try
					{
						j = setPrice.getText();				//gets info from price field then converts to string
						sPrice = Double.parseDouble(j);		//converts string to double

						if (sPrice < 0)						//checks if the price is negative
						{
							//displays price error message
							JOptionPane.showMessageDialog(null, "Please Enter A Positive Number", 
									"Price Error", JOptionPane.ERROR_MESSAGE);
							wineLabel.setText("Wine Purchased: ");
							clear();
							setTrans.setText("�");
						}
						else
						{
							Object s = e.getSource();

							if (s == saleButton)				//checks if the sale button is pressed
							{									
								printSale();					//go to the printSale method
							}
							else if (s == returnButton)			//checks if the return button is pressed
							{
								printReturn();					//go to the printReturn method
							}

							clear();							//clears the wine name/price/quantity field
						}
					}
					catch(NumberFormatException nFE)
					{
						//displays price error message
						JOptionPane.showMessageDialog(null, "You Have Entered an Invalid Number", 
								"Price Error", JOptionPane.ERROR_MESSAGE);
						wineLabel.setText("Wine Purchased: ");
						clear();
						setTrans.setText("�");
					}
				}
			}
			catch(NumberFormatException nFE)
			{
				//displays price error message
				JOptionPane.showMessageDialog(null, "You Have Entered an Invalid Number", 
						"Quantity Error", JOptionPane.ERROR_MESSAGE);
				wineLabel.setText("Wine Purchased: ");
				clear();
				setTrans.setText("�");
			}
		}
	}

}
