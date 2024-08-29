import java.util.*;

public class Factory
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
