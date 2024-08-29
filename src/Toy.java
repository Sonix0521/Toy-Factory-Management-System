public class Toy
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
