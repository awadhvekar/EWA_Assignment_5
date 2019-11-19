import java.io.IOException;
import java.io.*;

public class TotalSalesDaily implements Serializable
{
    private String orderDate;
    private String totalDailySales;
    private String productsListDesciption;

    public TotalSalesDaily(String orderDate, String totalDailySales, String productsListDesciption)
    {
        this.orderDate = orderDate;
        this.totalDailySales = totalDailySales;
        this.productsListDesciption = productsListDesciption;
    }

    public String getOrderDate()
    {
		return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
		this.orderDate = orderDate;
    }
    
    public String getTotalDailySales()
    {
		return totalDailySales;
    }

    public void setTotalDailySales(String totalDailySales)
    {
		this.totalDailySales = totalDailySales;
    }

    public String getProductsListDesciption()
    {
		return productsListDesciption;
    }

    public void setProductsListDesciption(String productsListDesciption)
    {
		this.productsListDesciption = productsListDesciption;
    }
}