import java.util.Scanner;

public class POS {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        // Item categories.
        Item[] meatItems =
        {
            new Item("Steak", 14.99),
            new Item("Ground Beef", 8.49),
            new Item("Ribs", 12.75),
            new Item("Cow liver", 6.30),
            new Item("Mutton", 13.20),
            
        };
        
        Item[] poultryItems =
        {
            new Item("Chicken", 7.99),
            new Item("Eggs", 3.49),
            new Item("Chicken Thighs", 6.85),
            new Item("Chicken Breasts", 9.40),
            new Item("C. Drumstick", 5.60),
            new Item("Duck", 15.25),
            new Item("Turkey", 18.90)

        };

        Item[] dairyItems =
        {
            new Item("Milk A", 2.35),
            new Item("Milk B", 2.60),
            new Item("Butter", 4.75),
            new Item("Ghee", 6.90),
            new Item("Swiss Cheese", 5.80),
            new Item("Parmesan Cheese", 7.45),
        };

        Item[] bakeryItems =
        {
            new Item("Toast", 2.10),
            new Item("Pita Bread", 1.95),
            new Item("Tortilla", 2.85),
            new Item("Whole Grain Bread", 3.40),
            new Item("White Bread", 2.50),
            new Item("Baguette", 3.15)
        };
        
        //Creating "Cart" object carrying all item categories.
        Cart cart = new Cart(meatItems, poultryItems, dairyItems, bakeryItems);
        // Main program loop.
        while(isRunning)
        {
            int ans;
            
            System.out.println();
            cart.displayMenu();
            System.out.println();
            // Choice prompt.
            System.out.println("1) Change category");
            System.out.println("2) Add item");
            System.out.println("3) Remove item");
            System.out.println("4) Clear cart");
            System.out.println("5) Quit and print bill");
            if(sc.hasNextInt())
                {
                    ans = sc.nextInt();
                sc.nextLine();
                switch (ans) {
                    case 1:
                        cart.changeCategory(sc);
                        break;
                    case 2:  
                        cart.addItem(sc);
                        break;
                    
                    case 3:
                        cart.removeItem(sc);
                        break;
                    
                    case 4:
                        cart.clearCart();
                        break;

                    case 5:
                        isRunning = false;
                        break;


                    default:
                        System.out.println("Invalid input: enter a number from 1 to 5 only.");
                }
            }
            else
            {
                System.out.println("Invalid input: enter a number from 1 to 5 only.");
                sc.nextLine();
            }
            
            
        }
        cart.printBill();
        sc.close();
    }

}
