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

public class SD2_Lecture_07_ToyCompany
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

        if (SD2_Lecture_07_ToyCompany.toy_list_array.isEmpty())
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


class Validations
{
    public static Scanner input = SD2_Lecture_07_ToyCompany.input;

    static String SetValidProductCode()
    {
        boolean valid_product_code = false;
        String product_code = null;

        while (!valid_product_code)
        {
            System.out.print(" ■ Enter toy product code        : ");
            String product_code_lowercase = input.next();
            product_code = product_code_lowercase.toUpperCase();

            if (product_code.equals("00"))
            {
                System.out.println("   ---------------------------------");
                System.out.println("   | ○ INAPPLICABLE PRODUCT CODE    |");
                System.out.println("   | ○ 00 : PROCESS TERMINATE CODE |");
                System.out.println("   ---------------------------------");
                continue;
            }
            else if (product_code.length() <= 1)
            {
                System.out.println("   ---------------------------------------");
                System.out.println("   | ○ CODE LENGTH SHOULD BE MORE THAN 1 |");
                System.out.println("   ---------------------------------------");
                continue;
            }
            else if (!Character.isLetter(product_code.charAt(0)) || !product_code.matches(".*[a-zA-Z].*"))
            {
                System.out.println("   ----------------------------------------");
                System.out.println("   | ○ ALPHABETICAL CHARACTERS REQUIRED   |");
                System.out.println("   | ○ 1ST CHARACTER MUST BE ALPHABETICAL |");
                System.out.println("   ----------------------------------------");
                continue;
            }


            valid_product_code = true;

            for (Toy toy_product_code : SD2_Lecture_07_ToyCompany.toy_list_array)
            {
                if (toy_product_code.getProduct_code().equals(product_code))
                {
                    System.out.println("   ------------------------------");
                    System.out.println("   | ○ PRODUCT CODE UNAVAILABLE |");
                    System.out.println("   ------------------------------");
                    valid_product_code = false;
                    break;
                }
            }
            SD2_Lecture_07_ToyCompany.input.nextLine();
        }
        return product_code;
    }

    static String CheckProductCode()
    {
        String product_code = null;

        boolean valid_product_code = false;

        if (SD2_Lecture_07_ToyCompany.toy_list_array.isEmpty())
        {
            System.out.println("\t\t\t  -----------------------");
            System.out.println("\t\t\t  | ○ TOY LIST IS EMPTY |");
            System.out.println("\t\t\t  -----------------------\n");
            return null;
        }

        while (!valid_product_code)
        {
            System.out.print(" ■ Enter toy product code       : ");
            String product_code_lowercase = input.next();
            product_code = product_code_lowercase.toUpperCase();

            if (product_code.equals("00"))
            {
                System.out.println("\n-------------------------------------------------");
                System.out.println("| >>> STOCK LEVEL UPDATE PROCESS TERMINATED <<< |");
                System.out.println("-------------------------------------------------\n");
                return product_code;
            }


            for ( Toy toy_object_array_index : SD2_Lecture_07_ToyCompany.toy_list_array )
            {
                if (toy_object_array_index.getProduct_code().equals(product_code))
                {
                    valid_product_code = true;
                    break;
                }
            }

            if (!valid_product_code)
            {
                System.out.println("\t------------------------------------");
                System.out.println("\t| ○ INVALID CODE.ENTER CODE AGAIN. |");
                System.out.println("\t------------------------------------");
            }
        }
        return product_code;
    }

    static String SetValidProductName()
    {
        while (true)
        {
            System.out.print(" ■ Enter toy product name        : ");
            String product_name = input.nextLine();

            if (product_name.isEmpty())
            {
                System.out.println("   ----------------------------------");
                System.out.println("   | ○ PRODUCT NAME CANNOT BE EMPTY |");
                System.out.println("   ----------------------------------");
            }
            else if (product_name.length() < 3)
            {
                System.out.println("   ------------------------------");
                System.out.println("   | ○ PRODUCT NAME TOO SHORT.  |");
                System.out.println("   | ○ MINIMUM LENGTH : 3 WORDS |");
                System.out.println("   ------------------------------");
            }
            else if (!Character.isLetter(product_name.charAt(0)) || !product_name.matches(".*[a-zA-Z].*"))
            {
                System.out.println("   ----------------------------------------");
                System.out.println("   | ○ 1ST CHARACTER MUST BE ALPHABETICAL |");
                System.out.println("   | ○ NAME MUST CONTAIN ALPHABETIC       |");
                System.out.println("   ----------------------------------------");
            }
            else
            {
                return product_name;
            }
        }
    }

    static int SetValidStockLevel()
    {
        while (true)
        {
            int maximum_stock_level = SD2_Lecture_07_ToyCompany.maximum_stock_level;

            try
            {
                System.out.print(" ■ Enter toy product stock level : ");
                int product_stock_level = input.nextInt();

                if (product_stock_level < 0)
                {
                    System.out.println("   -------------------------------------");
                    System.out.println("   | ○ STOCK LEVEL CAN'T BE LESS THAN 0 |");
                    System.out.println("   -------------------------------------");
                }
                else if (maximum_stock_level < product_stock_level)
                {
                    System.out.println("   ----------------------------------------");
                    System.out.println("   | ○ ALERT : STOCK LIMIT EXCEEDED       |");
                    System.out.println("   | ○ MAXIMUM STOCK LEVEL : " + maximum_stock_level + " UNITS |");
                    System.out.println("   ----------------------------------------");
                }
                else
                {
                    return product_stock_level;
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

    static double SetValidProductPrice()
    {
        while (true)
        {
            double maximum_product_price = SD2_Lecture_07_ToyCompany.maximum_product_price;

            try
            {
                System.out.print(" ■ Enter toy product price       : $");
                double product_price = input.nextDouble();

                if (product_price <= 0)
                {
                    System.out.println("   ----------------------------------");
                    System.out.println("   | ○ PRICE SHOULD BE MORE THAN $0 |");
                    System.out.println("   ----------------------------------");
                }
                else if (maximum_product_price < product_price)
                {
                    System.out.println("   ---------------------------------------");
                    System.out.println("   | ○ ALERT : STOCK LIMIT EXCEEDED      |");
                    System.out.println("   | ○ MAXIMUM PRODUCT PRICE : $" + maximum_product_price + " |");
                    System.out.println("   ---------------------------------------");
                }
                else
                {
                    return product_price;
                }
            }
            catch (Exception Error)
            {
                System.out.println("   ---------------------------------------------");
                System.out.println("   |  ERROR : " + Error + " |");
                System.out.println("   |  ○ NUMERICAL VALUES REQUIRED              |");
                System.out.println("   ---------------------------------------------");
                input.nextLine();
            }
        }
    }

    static String SetValidProductCategory()
    {

        char product_category;

        while (true)
        {
            System.out.print("""
                 ■ Choose product category ;
                    ▸ M -> Male child toy
                    ▸ F -> Female child toy
                    ▸ G -> General child toy         
                """);
            System.out.print("   Enter toy product category    : ");
            product_category = input.next().charAt(0);

            product_category = Character.toUpperCase(product_category);

            // Using HashSets to check if the inputted product category exists in the valid product category section.
            Set<Character> valid_category_hashset = new HashSet<>(Arrays.asList('M', 'F', 'G'));

            if ( valid_category_hashset.contains(product_category) )
            {
                return switch (product_category)
                {
                    case 'M' -> "Male child toy";
                    case 'F' -> "Female child toy";
                    case 'G' -> "General";
                    default  -> "Unknown Error : Switch Case for Product Category Validation";
                };
            }
            else
            {
                System.out.println("   ----------------------");
                System.out.println("   | ○ INVALID CATEGORY |");
                System.out.println("   ----------------------");
                input.nextLine();
            }
        }
    }
}


class Toy
{
    private String product_code;
    private String product_name;
    private int stock_level;
    private double product_price;
    private String toy_category;

    public Toy (String product_code , String product_name , int stock_level, double product_price , String toy_category)
    {
        this.product_code = product_code;
        this.product_name = product_name;
        this.stock_level = stock_level;
        this.product_price = product_price;
        this.toy_category = toy_category;
    }

    public void ToyDetails ()
    {
        System.out.print("\n   +-------------------------------------+");
        System.out.printf("\n   | %-16s = %-16s |\n", "PRODUCT CODE", getProduct_code());
        System.out.printf("   | %-16s = %-16s |\n", "PRODUCT NAME", getProduct_name());
        System.out.printf("   | %-16s = %-16d |\n", "STOCK LEVEL", getStock_level());
        System.out.printf("   | %-16s = $%-15.2f |\n", "PRODUCT PRICE", getProduct_price());
        System.out.printf("   | %-16s = %-16s |\n", "PRODUCT CATEGORY", getToy_category());
        System.out.print("   +-------------------------------------+\n");
    }

    public String getProduct_code()
    {
        return product_code;
    }
    public String getProduct_name()
    {
        return product_name;
    }
    public double getProduct_price()
    {
        return product_price;
    }
    public String getToy_category()
    {
        return toy_category;
    }
    public int getStock_level()
    {
        return stock_level;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
    public void setStock_level(int stock_level) {
        this.stock_level = stock_level;
    }
}



class Factory
{
    // Instance variable that initialize the connection between the Factory class and the Passed-in parameter toy_list_array
    private ArrayList<Toy> toy_list_array;

    /**
     * @param toy_list_array : Data type :- ArrayList<Toy>
     * The toy_list_array is passed in to the Factory constructor as a parameter from the object(at the initialization of it at the very beginning of the code.) to forge the connection between the ArrayList and the Factory class (Factory class needs the toy_list_array for its functionalities such as to store and retrieve data from the array_list.)
     */
    public Factory( ArrayList<Toy> toy_list_array )
    {
        this.toy_list_array = toy_list_array;
    }

    /**
     *  @param toy_product : Data type :- Toy class
     *  This method is instantiated to Add the passed in object of Toy (in the AddToy method.) to the ArrayList.
     *  So this method would take parameter toy_product of Toy class which is passed in as an object from the AddToy method. : ' factory_object.AddToy(toy_object); '
     *  Then the method will save that specific object into the ArrayList.

     *  Even though we could have directly passed in the object to the ArrayList from the AddToy() method ' toy_list_array.add(toy_object); ' ,
    this approach would be much suitable, bcz all functionalities related to ArrayList are performed from the Factory class itself and not all over the program. This way its much more organized.
     */
    public void AddToy(Toy toy_product)
    {
        toy_list_array.add(toy_product);
    }

    public void UpdateStockLevel(String product_code , int product_quantity_update_value , double maximum_stock_level)
    {
        for ( Toy toy_object_array_index : toy_list_array)
        {
            if ( toy_object_array_index.getProduct_code().equals(product_code) )
            {
                int current_product_stock_level = toy_object_array_index.getStock_level();
                int new_product_stock_level = toy_object_array_index.getStock_level() + product_quantity_update_value;

                if (new_product_stock_level < 0)
                {
                    System.out.println("\n   ------------------------------------");
                    System.out.println("   | ○ WARNING : NEGATIVE STOCK LEVEL |");
                    System.out.println("   ------------------------------------");
                    System.out.println("\n   |---| PRODUCT CODE = " + toy_object_array_index.getProduct_code()+ " |---|" +
                            "\n     ● AVAILABLE STOCK LEVEL = " + toy_object_array_index.getStock_level());
                    System.out.println("\n|-------------------------------------------|\n");
                }
                else if (maximum_stock_level < new_product_stock_level)
                {
                    System.out.println("\n   ----------------------------------");
                    System.out.println("   | ○ WARNING : INSUFFICIENT STOCK |");
                    System.out.println("   ----------------------------------");
                    System.out.println("\n   |---| PRODUCT CODE = " + toy_object_array_index.getProduct_code()+ " |---|" +
                            "\n     ● MAXIMUM STOCK LEVEL = " + maximum_stock_level);
                    System.out.println("\n|-------------------------------------------|\n");
                }
                else
                {
                    toy_object_array_index.setStock_level(new_product_stock_level);
                    System.out.println("\n   ---------------------------------------");
                    System.out.println("   | ○ STOCK LEVEL SUCCESSFULLY UPDATED. |");
                    System.out.println("   ---------------------------------------");
                    System.out.println("\n   |---| PRODUCT CODE = " + toy_object_array_index.getProduct_code() + " |---|" +
                            "\n     ● PREVIOUS STOCK LEVEL = " + current_product_stock_level +
                            "\n     ● UPDATED STOCK LEVEL  = " + toy_object_array_index.getStock_level());
                    System.out.println("\n|-------------------------------------------|\n");
                }
            }
        }
    }

    public void UpdatePrice(String product_code , double update_value_of_product_price , double maximum_product_price)
    {
        for (Toy toy_object_array_index : toy_list_array)
        {
            if (toy_object_array_index.getProduct_code().equals(product_code))           //  Reason for using 'equals()' instead of '==' is bcz the product_code is a String variable, and String variables are compared with
            {
                double previous_product_price = toy_object_array_index.getProduct_price();
                double new_product_price = toy_object_array_index.getProduct_price() + update_value_of_product_price;

                if (new_product_price < 0)
                {
                    System.out.println("\n   --------------------------------");
                    System.out.println("   | ○ INSUFFICIENT PRODUCT PRICE |");
                    System.out.println("   --------------------------------");
                    System.out.println("\n   |---| PRODUCT CODE = " + toy_object_array_index.getProduct_code()+ " |---|" +
                            "\n     ● CURRENT PRODUCT PRICE = $" + toy_object_array_index.getProduct_price());
                    System.out.println("\n|-------------------------------------------|\n");
                }
                else if (maximum_product_price < new_product_price)
                {
                    System.out.println("\n   ----------------------------------");
                    System.out.println("   | ○ WARNING : INSUFFICIENT STOCK |");
                    System.out.println("   ----------------------------------");
                    System.out.println("\n   |---| PRODUCT CODE = " + toy_object_array_index.getProduct_code() + " |---|" +
                            "\n     ● ALLOWED MAXIMUM PRICE = $" + maximum_product_price);
                    System.out.println("\n|-------------------------------------------|\n");
                }
                else
                {
                    toy_object_array_index.setProduct_price(new_product_price);
                    System.out.println("\n   ----------------------------------------");
                    System.out.println("   | ○ PRODUCT PRICE SUCCESSFULLY UPDATED |");
                    System.out.println("   ----------------------------------------");
                    System.out.println("\n   |---| PRODUCT CODE = " + toy_object_array_index.getProduct_code() + " |---|" +
                            "\n     ● PREVIOUS PRODUCT PRICE = $" + previous_product_price +
                            "\n     ● UPDATED PRODUCT PRICE  = $" + toy_object_array_index.getProduct_price());
                    System.out.println("\n|-------------------------------------------|\n");
                }
            }
        }
    }

    public void DisplayToyDetails(String product_code)
    {
        for ( Toy toy_object_array_index : toy_list_array)
        {
            if ( toy_object_array_index.getProduct_code().equals(product_code) )
            {
                toy_object_array_index.ToyDetails();
            }
        }
    }

    public void DisplayAllToyProducts()
    {
        System.out.println("\n+--------------+--------------+------------------+");
        System.out.println("|                STOCK DETAILS                   |");
        System.out.println("+--------------+--------------+------------------+");
        System.out.printf("| %-12s | %-12s | %-16s |\n", "PRODUCT CODE", "STOCK LEVEL", "PRICE OF PRODUCT");
        System.out.println("+--------------+--------------+------------------+");
        for (Toy toy_index : toy_list_array )
        {
            System.out.printf("| %-12s | %-12s | $ %-14s |\n",
                    toy_index.getProduct_code(),
                    toy_index.getStock_level(),
                    toy_index.getProduct_price());
        }
        System.out.println("+--------------+--------------+------------------+");

        System.out.println("\n+--------------+--------------+------------------+");
        System.out.println("|               PRODUCT DETAILS                  |");
        System.out.println("+--------------+--------------+------------------+");
        System.out.printf("| %-12s | %-12s | %-16s |\n", "PRODUCT CODE", "PRODUCT NAME", "PRODUCT CATEGORY");
        System.out.println("+--------------+--------------+------------------+");
        for (Toy toy_index : toy_list_array )
        {
            System.out.printf("| %-12s | %-12s | %-16s |\n",
                    toy_index.getProduct_code(),
                    toy_index.getProduct_name(),
                    toy_index.getToy_category());
        }
        System.out.println("+--------------+--------------+------------------+\n\n");
    }
}
