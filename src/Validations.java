import java.util.*;
public class Validations
{
    public static Scanner input = ToyCompany.input;

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

            for (Toy toy_product_code : ToyCompany.toy_list_array)
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
            ToyCompany.input.nextLine();
        }
        return product_code;
    }

    static String CheckProductCode()
    {
        String product_code = null;

        boolean valid_product_code = false;

        if (ToyCompany.toy_list_array.isEmpty())
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


            for ( Toy toy_object_array_index : ToyCompany.toy_list_array )
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
            int maximum_stock_level = ToyCompany.maximum_stock_level;

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
            double maximum_product_price = ToyCompany.maximum_product_price;

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
