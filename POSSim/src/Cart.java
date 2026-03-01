import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cart {

    private Item[][] categories;
    private int[][] quantities;
    private String[] cNames;
    private int cCategory;

    public Cart(Item[] meatItems, Item[] poultryItems, Item[] dairyItems, Item[] bakeryItems)
    {
        categories = new Item[][]{
            meatItems,
            poultryItems,
            dairyItems,
            bakeryItems,
        };

        quantities = new int[][]{
            new int[meatItems.length],
            new int[poultryItems.length],
            new int[dairyItems.length],
            new int[bakeryItems.length]
        };

        cNames = new String[]{
            "Meat",
            "Poultry",
            "Dairy",
            "Bakery"
        };
    }

    public void changeCategory(Scanner sc)
    {
        while(true)
        {
        System.out.println("Choose the category:");
        for(int i = 0; i < cNames.length; i++)
        {
            System.out.println((i + 1) + ") " + cNames[i]);
        }
        
        if(sc.hasNextInt())
        {
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice >= 1 && choice <= categories.length)
            {
                cCategory = choice - 1;
                break;
            }
            else
            {
                System.out.println("Invalid category.");
            }
        }
        else
        {
            System.out.println("Invalid category.");
            sc.nextLine();
        }

        }
    }

    public void displayMenu()
    {

        System.out.println("-".repeat(47));
        System.out.printf("| %-20s | %-20s | %n", "Item", "Price/unit");
        for(int i = 0; i < categories[cCategory].length; i++)
        {
            System.out.printf("| %-20s | %-20.2f | %n", (i + 1) + ") " + categories[cCategory][i].getName(), categories[cCategory][i].getPrice());
        }
        System.out.println("-".repeat(47));
    }

    public int checkItem(Scanner sc)
    {
        int choice;
        while(true)
        {
            if(sc.hasNextInt())
            {
            choice = sc.nextInt();
            sc.nextLine();
            if(choice >= 1 && choice <= categories[cCategory].length){return choice - 1;}
            else{System.out.println("Invalid input.");}
            }
            else
            {
                System.out.println("Invalid input: enter a whole number.");
                sc.nextLine();
            }
        }
    }

    public int checkQuantity(Scanner sc)
    {
        int quantity;
        while(true)
        {
            if(sc.hasNextInt())
            {
                quantity = sc.nextInt();
                sc.nextLine();
                if(quantity > 0)
                    {
                        return quantity;
                    }
                else
                {
                    System.out.println("Invalid input: enter a valid quantity.");
                }
            }
            else
            {
                System.out.println("Invalid input: enter a valid quantity.");
                sc.nextLine();
            }
        }
    }

    public void addItem(Scanner sc)
    { 
            System.out.println("Which item would you like to add? Use the corresponding number.");                
            int chosenItem = checkItem(sc);
            System.out.println("How many units would you like to add?");
            int quantity = checkQuantity(sc);
            quantities[cCategory][chosenItem] += quantity;
            System.out.println("Added " + quantity + " unit(s) of " + categories[cCategory][chosenItem].getName() + ".");
    }

    public void removeItem(Scanner sc)
    {
        System.out.println("Which item would you like to remove? Use the corresponding number.");

            int chosenItem = checkItem(sc);
            if(quantities[cCategory][chosenItem] == 0)
            {
                System.out.println("This item is not in your cart.");
                return;
            }

            System.out.println("How many units would you like to remove?");            
            int quantity = checkQuantity(sc);
            if(quantity <= quantities[cCategory][chosenItem])
            {
            quantities[cCategory][chosenItem] -= quantity;
                System.out.println("Removed " + quantity + " unit(s) of " + categories[cCategory][chosenItem].getName() + ".");;
            }
            else
            {
                System.out.println("Can't remove more than what is in the cart.");
            }
    }

    public void clearCart()
    {
        for(int i = 0; i < quantities.length; i++)
            {
                quantities[i] = new int[categories[i].length];
            }

        System.out.println("Cart cleared.");
    }

    public double calcTotal()
    {
        double total = 0;
        for(int i = 0; i < categories.length; i++)
        {
            for(int j = 0; j < quantities[i].length; j++)
            {
                if(quantities[i][j] > 0)
                {
                    total += quantities[i][j] * categories[i][j].getPrice();
                }
            }
        }
        return total;
    }

    public void printBill()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter cDate = DateTimeFormatter.ofPattern("'Issued on:' dd/MM/yy");
        DateTimeFormatter cTime = DateTimeFormatter.ofPattern("'At' HH:mm:ss");
        String formattedD = currentTime.format(cDate);
        String formattedT = currentTime.format(cTime);

        System.out.println("-".repeat(70));
        System.out.printf("| %-20s | %-20s | %-20s | %n", "ITEM", "QUANTITY", "TOTAL");
        for(int i = 0; i < categories.length; i++)
        {
            for(int j = 0; j < quantities[i].length; j++)
            {
                if(quantities[i][j] > 0)
                {
                    System.out.printf("| %-20s | %-20s | %-20.2f | %n", 
                    categories[i][j].getName(), quantities[i][j], categories[i][j].getPrice() * quantities[i][j]);
                }
            }
        }
        System.out.println("-".repeat(70));
        System.out.printf("| %-20s | %-20s | %-20.2f | %n", "Subtotal:", " ", calcTotal());
        System.out.println("-".repeat(70));
        System.out.printf("| %-66s | %n", formattedD);
        System.out.printf("| %-66s | %n", formattedT);
        System.out.println("-".repeat(70));
    }
}
