//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Class          : vendingMachine.Model
//
// Author         : Richard E. Pattis
//                  Computer Science Department
//                  Carnegie Mellon University
//                  5000 Forbes Avenue
//                  Pittsburgh, PA 15213-3891
//                  e-mail: pattis@cs.cmu.edu
//
// Maintainer     : Author
//
//
// Description:
//
//   The Model for the VendingMachine package implements the guts of the
// vending machine: it responds to presses of buttons created by the
// Conroller (deposit, cancel, buy), and tells the View when it needs
// to update its display (calling the update in view, which calls the
// accessor methods in this classes)
// 
//   Note that "no access modifier" means that the method is package
// friendly: this means the member is public to all other classes in
// the calculator package, but private elsewhere.
//
// Future Plans   : More Comments
//                  Increase price as stock goes down
//                  Decrease price if being outsold by competition
//                  Allow option to purchase even if full change cannot 
//                    be returned (purchaser pays a premium to quench thirst)
//                  Allow user to enter 2 x money and gamble: 1/2 time
//                    all money returned with product; 1/2 time no money and
//                    no product returned
//
// Program History:
//   9/20/01: R. Pattis - Operational for 15-100
//   2/10/02: R. Pattis - Fixed Bug in change-making method
//
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
package vendingMachine;


import java.lang.Math;


public class Model {
	//Define fields (all instance variables)
	
	private View view;         // Model must tell View when to update itself
	
	private int cokeLeft = 100;
	private int pepsiLeft = 100;
	private int cokePrice = 200;
	private int pepsiPrice = 200;
	
	private int quartersLeft, dimesLeft, nickelsLeft;

	private String lastOrder;

	
	//Refer to the view (used to call update after each button press)
	public void addView(View v){
		view = v;
	}
	
	//Define required methods: mutators (setters) and accessors (getters)
	
	//Represent "interesting" state of vending machine
	public String toString()
	{
		return "Vending Machine State: \n" +
			"  Coke     Left      = " + cokeLeft     + "\n" +
			"  Pepsi    Left      = " + pepsiLeft    + "\n" +
			"  Quarters Left      = " + quartersLeft + "\n" +
			"  Dimes    Left      = " + dimesLeft    + "\n" +
			"  Nickels  Left      = " + nickelsLeft  + "\n";
		//Display any other instance variables that you declare too
	}
	
	public void cancel(){
		int amount=100;
		if (lastOrder.equals("coke")) {
			cokeLeft--;
			amount = cokePrice;

		}
		if (lastOrder.equals("pepsi")){
			pepsiLeft--;
			amount=pepsiPrice;
		}
		int q = 0;
		while(amount-25 >=0){
			amount-=25;
			q++;
		}
		int d = 0;
		while (amount-10>=0){
			amount-=10;
			d++;
		}
		int n = 0;
		while (amount-5>=0){
			amount-=5;
			n++;
		}
		quartersLeft -=q;
		dimesLeft -= d;
		nickelsLeft -= n;

		System.out.println(this.toString());


	}
	public void deposit(int amount){
		int q = 0;
		while(amount-25 >=0){
			amount-=25;
			q++;
		}
		int d = 0;
		while (amount-10>=0){
			amount-=10;
			d++;
		}
		int n = 0;
		while (amount-5>=0){
			amount-=5;
			n++;
		}
		quartersLeft +=q;
		dimesLeft += d;
		nickelsLeft += n;
		System.out.println(this.toString());


	}


	public void buy(String product){
		if (product.toLowerCase().equals("coke")){
			cokeLeft -=1;
			lastOrder = "coke";
			this.deposit(cokePrice);
			int x =Integer.parseInt(this.getDeposited()) - cokePrice;
			System.out.println("Bought one coke" + " change " + x + " cents ");
		}
		if (product.toLowerCase().equals("pepsi")) {
			pepsiLeft -=1;
			lastOrder = "pepsi";
			this.deposit(pepsiPrice);
			int x =Integer.parseInt(this.getDeposited()) - pepsiPrice;
			System.out.println("Bought one pepsi" + " change " + x + " cents ");
		}


	}

	public String getDeposited(){
		return Integer.toString(quartersLeft*25 + dimesLeft*10 + nickelsLeft*5);

	}
	public String getMessage(){
		return "At the bottom, in blue";
	}
	public int getCokeLeft(){
		return cokeLeft;
	}
	public int getPepsiLeft(){
		return pepsiLeft;
	}
	public String getCokePrice(){
		return Integer.toString(cokePrice);
	}
	public String getPepsiPrice(){
		return Integer.toString(pepsiPrice);
	}

}
