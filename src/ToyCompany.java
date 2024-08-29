/**
 *  ----- Task 01 -----
 *  Develop an application to a Toy Factory.
 *  This application should facilitate to add a new toy, update stock level of each toy and many more functions.
 *  Each toy has a name, but it's uniquely identified by its code number. Different toys have different prices.
 *  Some toys are made for little girls and some for little boys and some are general.
 *  The stock level for each toy should be maintained as well.
 *  ----- Task 02 -----
 *  When a new toy is introduced, there should be a way to add it to the system.
 *  Stock level of each toy must be updated when toys been made or when toys are distributed to the outlets.
 *  If the raw material cost go high, sometimes the price of a toy can be increased.
 *  For reporting the details of  each toy such as name , code , category , price and the stock level.
 *  ----- Task 03 -----
 *  Create a driver class called Factory. It should do the following;
 *      ex:- Teddy bear code is TD0012 & the price is 1200/=. The stock level is 12000, General category
 *  Factory issues 500 teddy bears to outlets, have to update the stock level.
 *  Factory produces 100 racing cars more, have to update the stock level.
 *  Price of a tea set changes to 1200/=
 *  To view the new details of each toy, after the changes.
 */

import java.util.*;

public class ToyCompany
{
    static Scanner input = new Scanner(System.in);
    static ArrayList<Toy> toy_list_array = new ArrayList<>();
    static Factory factory_object = new Factory(toy_list_array);
    public static int maximum_stock_level = 100000;
    public static double maximum_product_price = 100000;

    public static void main(String[] args)
    {

        boolean iteration = true;

        do
        {
            System.out.println("""
                    |************************************************|
                    |         TOY FACTORY MANAGEMENT SYSTEM          |
                    |************************************************|
                    |      1) PRESS 01 : ADD NEW TOY                 |
                    |      2) PRESS 02 : UPDATE STOCK LEVEL          | 
                    |      3) PRESS 03 : UPDATE TOY PRICE            |
                    |      4) PRESS 04 : DISPLAY TOY INFORMATION     |   
                    |      5) PRESS 05 : DISPLAY ALL TOYS            |
                    |      6) PRESS 00 : EXIT / TERMINATE PROCESS    |
                    |************************************************|
                    """);

            try
            {
                System.out.print("PLEASE SELECT AN OPTION   : ");
                int menu_option = input.nextInt();

                System.out.println();

                switch (menu_option)
                {
                    case 1 :
                        AddNewToyProduct();
                        break;

                    case 2 :
                        UpdateToyStockLevel();
                        break;

                    case 3 :
                        UpdateToyPrice();
                        break;

                    case 4:
                        DisplayToyInformation();
                        break;

                    case 5 :
                        DisplayAllToys();
                        break;

                    case 0 :
                        System.out.println("""
                                
                                **************************************************
                                || >>>>>>>>>>>> | EXITING SYSTEM | <<<<<<<<<<<< ||
                                **************************************************
                                """);
                        iteration = false;
                        break;

                    default :
                        System.out.println("""
                            \t\t\t--------------------------
                            \t\t\t| ERROR : Invalid Option |
                            \t\t\t--------------------------
                            """);
                        break;
                }

            }
            catch (Exception Error)
            {
                System.out.println("\n ---------------------------------------------");
                System.out.println(" | ○ ERROR : " + Error + " |");
                System.out.println(" | ○ NUMERICAL VALUES REQUIRED                |");
                System.out.println(" ---------------------------------------------\n");
                input.nextLine();
            }

        }
        while (iteration);
    }

    static void AddNewToyProduct()
    {
        System.out.println("|------------------------------------------------|");
        System.out.println("|                 PRODUCT DETAILS                |");
        System.out.println("|------------------------------------------------|\n");

        String product_code = Validations.SetValidProductCode();

        String product_name = Validations.SetValidProductName();

        int product_stock_level = Validations.SetValidStockLevel();

        double product_price = Validations.SetValidProductPrice();

        String product_category = Validations.SetValidProductCategory();

        System.out.println("\n|------------------------------------------------|\n");

        // Passing in product attributes into the object of Toy constructor of the Toy class.
        Toy toy_object = new Toy(product_code, product_name, product_stock_level, product_price, product_category);

        //  Passing in the object into AddToy() method of the Factory class as arguments.
        factory_object.AddToy(toy_object);

        //  toy_list_array.add(toy_object); <-- The direct approach for passing in the object into the ArrayList.
    }

    static void UpdateToyStockLevel()
    {
        System.out.println("|------------------------------------------------|");
        System.out.println("|               UPDATE STOCK LEVEL               |");
        System.out.println("|------------------------------------------------|\n");

        String product_code = Validations.CheckProductCode();

        if (product_code == null)
        {
            System.out.println("|------------------------------------------------|\n");
            return;
        }
        else if (product_code.equals("00"))
        {
            System.out.println("|------------------------------------------------|\n");
            return;
        }

        boolean update_condition = true;

        while (update_condition)
        {
            try
            {
                System.out.println(" ■ Chose update option;");
                System.out.println("\t▸ PRESS 01 -> Add Crafted Toys\n" + "\t▸ PRESS 02 -> Remove Distributed Toys");
                System.out.print("   Select an option             : ");
                int update_option = input.nextInt();

                if (update_option == 0)
                {
                    System.out.println("\n-------------------------------------------------");
                    System.out.println("| >>> STOCK LEVEL UPDATE PROCESS TERMINATED <<< |");
                    System.out.println("-------------------------------------------------");
                    System.out.println("\n|------------------------------------------------|\n");
                    return;
                }

                input.nextLine();

                switch (update_option)
                {
                    case 1 :
                        System.out.print(" ■ Enter quantity to be added   : ");
                        int product_quantity_update_value_to_add = input.nextInt();

                        factory_object.UpdateStockLevel(product_code , +product_quantity_update_value_to_add , maximum_stock_level);

                        update_condition = false;
                        break;

                    case 2 :
                        System.out.print(" ■ Enter quantity to be removed : ");
                        int product_quantity_update_value_to_remove = input.nextInt();

                        factory_object.UpdateStockLevel(product_code , -product_quantity_update_value_to_remove , maximum_stock_level);

                        update_condition = false;
                        break;

                    default :
                        System.out.println("""
                                           ----------------------------
                                           | ○ ERROR : Invalid Option |
                                           ----------------------------\
                                        """);
                        break;
                }
            }
            catch (Exception Error)
            {
                System.out.println("   ---------------------------------------------");
                System.out.println("   | ○ ERROR : " + Error + " |");
                System.out.println("   | ○ NUMERICAL VALUES REQUIRED               |");
                System.out.println("   ---------------------------------------------");
                input.nextLine();
            }
        }

    }

    static void UpdateToyPrice()
    {
        System.out.println("|------------------------------------------------|");
        System.out.println("|                  UPDATE PRICE                  |");
        System.out.println("|------------------------------------------------|\n");

        String product_code = Validations.CheckProductCode();

        if (product_code == null)
        {
            System.out.println("|------------------------------------------------|\n");
            return;
        }
        else if (product_code.equals("00"))
        {
            System.out.println("|------------------------------------------------|\n");
            return;
        }

        boolean update_condition = true;

        while (update_condition)
        {
            try
            {
                System.out.println(" ■ Chose update option;");
                System.out.println("\t▸ PRESS 01 -> Increase Price\n" + "\t▸ PRESS 02 -> Decrease Price");
                System.out.print("   Select an option             : ");
                int update_option = input.nextInt();

                if (update_option == 0)
                {
                    System.out.println("\n-------------------------------------------------");
                    System.out.println("| >>> STOCK LEVEL UPDATE PROCESS TERMINATED <<< |");
                    System.out.println("-------------------------------------------------\n");
                    System.out.println("\n|------------------------------------------------|\n");
                    return;
                }

                input.nextLine();

                switch (update_option)
                {
                    case 1 :
                        System.out.print(" ■ Enter amount to be increased : ");
                        double product_price_value_to_be_increased = input.nextInt();

                        factory_object.UpdatePrice(product_code , +product_price_value_to_be_increased , maximum_product_price);

                        update_condition = false;
                        break;

                    case 2 :
                        System.out.print(" ■ Enter amount to be decreased : ");
                        double product_price_value_to_be_decreased = input.nextInt();

                        factory_object.UpdatePrice(product_code , -product_price_value_to_be_decreased , maximum_product_price);

                        update_condition = false;
                        break;

                    default :
                        System.out.println(
                                """
                                           --------------------------
                                           | ERROR : Invalid Option |
                                           --------------------------\
                                        """);
                        break;
                }
            }
            catch (Exception Error)
            {
                System.out.println("   ---------------------------------------------");
                System.out.println("   | ○ ERROR : " + Error + " |");
                System.out.println("   | ○ NUMERICAL VALUES REQUIRED               |");
                System.out.println("   ---------------------------------------------");
                input.nextLine();
            }
        }
    }


    static void DisplayToyInformation()
    {
        System.out.println("|------------------------------------------------|");
        System.out.println("|               PRODUCT INFORMATION              |");
        System.out.println("|------------------------------------------------|\n");

        String product_code = Validations.CheckProductCode();

        if (product_code == null)
        {
            System.out.println("|------------------------------------------------|\n");
            return;
        }
        else if (product_code.equals("00"))
        {
            return;
        }

        factory_object.DisplayToyDetails(product_code);

        System.out.println("\n|------------------------------------------------|\n");
    }

    static void DisplayAllToys()
    {
        System.out.println("|------------------------------------------------|");
        System.out.println("|                  ALL PRODUCTS                  |");
        System.out.println("|------------------------------------------------|\n");

        if (ToyCompany.toy_list_array.isEmpty())
        {
            System.out.println("\t\t\t  -----------------------");
            System.out.println("\t\t\t  | ○ TOY LIST IS EMPTY |");
            System.out.println("\t\t\t  -----------------------\n");
        }
        else
        {
            factory_object.DisplayAllToyProducts();
        }

        System.out.println("|------------------------------------------------|\n");
    }
}