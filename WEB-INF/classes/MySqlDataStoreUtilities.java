import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
    static Connection conn = null;

    public static void getConnection()
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2","root","root");
        }
        catch(Exception e)
        {
        
        }
    }


    public static void deleteOrder(int orderId,String orderName)
    {
        try
        {
            
            getConnection();
            String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1,orderId);
            pst.setString(2,orderName);
            pst.executeUpdate();
        }
        catch(Exception e)
        {
                
        }
    }

    public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, String orderDate, String deliveryDate, String maxOrderCancellationDate)
    {
        try
        {
        
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo,orderDate,deliveryDate,maxOrderCancellationDate) "
            + "VALUES (?,?,?,?,?,?,?,?,?);";
                
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1,orderId);
            pst.setString(2,userName);
            pst.setString(3,orderName);
            pst.setDouble(4,orderPrice);
            pst.setString(5,userAddress);
            pst.setString(6,creditCardNo);
            pst.setString(7,orderDate);
            pst.setString(8,deliveryDate);
            pst.setString(9,maxOrderCancellationDate);
            pst.execute();

            String procedureCall = "{CALL SP_UpdateProductCatalogCount(?)}";
            System.out.println("procedureCall: " + procedureCall);
            System.out.println("productName: " + orderName);
            CallableStatement cs = conn.prepareCall(procedureCall);
            cs.setString(1, orderName);
            //cs.execute();
            cs.executeUpdate();
        }
        catch(Exception e)
        {
        
        }		
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
    {	

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
            
        try
        {					

            getConnection();
            //select the table 
            String selectOrderQuery ="select * from customerorders";			
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();	
            ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
            while(rs.next())
            {
                if(!orderPayments.containsKey(rs.getInt("OrderId")))
                {	
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
                System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

                //add to orderpayment hashmap
                OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getString("orderDate"),rs.getString("deliveryDate"),rs.getString("maxOrderCancellationDate"));
                listOrderPayment.add(order);
                        
            }
                    
                        
        }
        catch(Exception e)
        {
            
        }
        return orderPayments;
    }


    public static void insertUser(String username,String password,String repassword,String usertype)
    {
        try
        {	

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
            + "VALUES (?,?,?,?);";	
                    
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,repassword);
            pst.setString(4,usertype);
            pst.execute();
        }
        catch(Exception e)
        {
        
        }	
    }

    public static HashMap<String,User> selectUser()
    {	
        HashMap<String,User> hm=new HashMap<String,User>();
        try 
        {
            getConnection();
            Statement stmt=conn.createStatement();
            String selectCustomerQuery="select * from  Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while(rs.next())
            {	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
                    hm.put(rs.getString("username"), user);
            }
        }
        catch(Exception e)
        {
        }
        return hm;			
    }

    public static HashMap<String,WirelessPlan> getWirelessPlans()
    {	
        HashMap<String,WirelessPlan> hm=new HashMap<String,WirelessPlan>();
        try 
        {
            getConnection();
            
            String selectWirelessPlan="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectWirelessPlan);
            pst.setString(1,"wirelessPlan");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                WirelessPlan wp = new WirelessPlan(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), wp);
                wp.setId(rs.getString("Id"));
                
                /*
                try
                {
                    String selectaccessory = "Select * from Product_accessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1,rs.getString("Id"));
                    ResultSet rsacc = pstacc.executeQuery();
                    
                    HashMap<String,String> acchashmap = new HashMap<String,String>();
                    while(rsacc.next())
                    {    
                        if (rsacc.getString("accessoriesName") != null)
                        {
                        
                        acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
                        con.setAccessories(acchashmap);
                        }
                    }					
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
                */
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,Television> getTelevisions()
    {	
        HashMap<String,Television> hm=new HashMap<String,Television>();
        try 
        {
            getConnection();
            
            String selectTelevision="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectTelevision);
            pst.setString(1,"television");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                Television tv = new Television(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), tv);
                tv.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,FitnessWatch> getFitnessWatches()
    {	
        HashMap<String,FitnessWatch> hm=new HashMap<String,FitnessWatch>();
        try 
        {
            getConnection();
            
            String selectFitnessWatch="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
            pst.setString(1,"fitnessWatch");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                FitnessWatch fw = new FitnessWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), fw);
                fw.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,Headphone> getHeadphones()
    {	
        HashMap<String,Headphone> hm=new HashMap<String,Headphone>();
        try 
        {
            getConnection();
            
            String selectHeadphone="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectHeadphone);
            pst.setString(1,"headphone");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                Headphone hp = new Headphone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), hp);
                hp.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,Laptop> getLaptops()
    {	
        HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
        try 
        {
            getConnection();
            
            String selectLaptop="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectLaptop);
            pst.setString(1,"laptop");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                Laptop lp = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), lp);
                lp.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,Phone> getPhones()
    {	
        HashMap<String,Phone> hm=new HashMap<String,Phone>();
        try 
        {
            getConnection();
            
            String selectPhone="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectPhone);
            pst.setString(1,"phone");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                Phone ph = new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), ph);
                ph.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,SmartWatch> getSmartWatches()
    {	
        HashMap<String,SmartWatch> hm=new HashMap<String,SmartWatch>();
        try 
        {
            getConnection();
            
            String selectSmartWatch="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
            pst.setString(1,"smartWatch");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                SmartWatch sw = new SmartWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), sw);
                sw.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,SoundSystem> getSoundSystems()
    {	
        HashMap<String,SoundSystem> hm=new HashMap<String,SoundSystem>();
        try 
        {
            getConnection();
            
            String selectSoundSystem="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectSoundSystem);
            pst.setString(1,"soundSystem");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                SoundSystem ss = new SoundSystem(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), ss);
                ss.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,Tablet> getTablets()
    {	
        HashMap<String,Tablet> hm=new HashMap<String,Tablet>();
        try 
        {
            getConnection();
            
            String selectTablet="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"tablet");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                Tablet tab = new Tablet(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), tab);
                tab.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static HashMap<String,VoiceAssistant> getVoiceAssistants()
    {	
        HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
        try 
        {
            getConnection();
            
            String selectVoiceAssistant="select * from  Productdetails where ProductType=? and numberOfAvailableProducts > 0";
            PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
            pst.setString(1,"voiceAssistant");
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                VoiceAssistant va = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), va);
                va.setId(rs.getString("Id"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;			
    }

    public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
    {
        String msg = "Product is added successfully";
        try
        {    
            getConnection();
            String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
            "VALUES (?,?,?,?,?,?,?,?);";
            
            String name = producttype;
                        
            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1,name);
            pst.setString(2,productId);
            pst.setString(3,productName);
            pst.setDouble(4,productPrice);
            pst.setString(5,productImage);
            pst.setString(6,productManufacturer);
            pst.setString(7,productCondition);
            pst.setDouble(8,productDiscount);
            
            pst.executeUpdate();
            try
            {
                if (!prod.isEmpty())
                {
                    String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
                    "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
                    pst1.setString(1,prod);
                    pst1.setString(2,productId);
                    pst1.executeUpdate();
                }
            }
            catch(Exception e)
            {
                msg = "Erro while adding the product";
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            msg = "Erro while adding the product";
            e.printStackTrace();
            
        }
        return msg;
    }
    public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
    { 
        String msg = "Product is updated successfully";
        try
        {    
            getConnection();
            String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;                        
            PreparedStatement pst = conn.prepareStatement(updateProductQurey);
            
            pst.setString(1,productName);
            pst.setDouble(2,productPrice);
            pst.setString(3,productImage);
            pst.setString(4,productManufacturer);
            pst.setString(5,productCondition);
            pst.setDouble(6,productDiscount);
            pst.setString(7,productId);
            pst.executeUpdate();
        }
        catch(Exception e)
        {
            msg = "Product cannot be updated";
            e.printStackTrace();    
        }
        return msg;	
    }

    public static String deleteproducts(String productId)
    {   
        String msg = "Product is deleted successfully";
        try
        {
            getConnection();
            String deleteproductsQuery ="Delete from Productdetails where Id=?";
            PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
            pst.setString(1,productId);
            pst.executeUpdate();
        }
        catch(Exception e)
        {
            msg = "Proudct cannot be deleted";
        }
        return msg;
    }

    public static ArrayList<NoOfAvailableProducts> availableProductsList()
    {
        ArrayList<NoOfAvailableProducts> availableProductsList = new ArrayList<NoOfAvailableProducts>();

        try 
        {
            getConnection();
            
            String availableProductsListQuery="SELECT productName, productPrice, productDiscount, numberOfAvailableProducts, manufacturerRebate FROM productdetails;";
            PreparedStatement pst = conn.prepareStatement(availableProductsListQuery);
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                NoOfAvailableProducts product = new NoOfAvailableProducts(rs.getString("productName"),rs.getString("productPrice"),rs.getString("productPrice"),rs.getString("numberOfAvailableProducts"),rs.getString("manufacturerRebate"));
                availableProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("availableProductsList(): "+e);
        }

        return availableProductsList;
    }

    public static ArrayList<NoOfAvailableProducts> currentOnSaleProductsList()
    {
        ArrayList<NoOfAvailableProducts> currentOnSaleProductsList = new ArrayList<NoOfAvailableProducts>();
        
        try 
        {
            getConnection();
            
            String currentOnSaleProductsListQuery="SELECT productName, productPrice, productDiscount, numberOfAvailableProducts, manufacturerRebate FROM productdetails WHERE productDiscount > 0;";
            PreparedStatement pst = conn.prepareStatement(currentOnSaleProductsListQuery);
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                NoOfAvailableProducts product = new NoOfAvailableProducts(rs.getString("productName"),rs.getString("productPrice"),rs.getString("productDiscount") ,rs.getString("numberOfAvailableProducts"),rs.getString("manufacturerRebate"));
                currentOnSaleProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("currentOnSaleProductsList(): "+e);
        }

        return currentOnSaleProductsList;
    }

    //manufacturerRebateProductsList
    public static ArrayList<NoOfAvailableProducts> manufacturerRebateProductsList()
    {
        ArrayList<NoOfAvailableProducts> manufacturerRebateProductsList = new ArrayList<NoOfAvailableProducts>();
        
        try 
        {
            getConnection();
            
            String manufacturerRebateProductsListQuery="SELECT productName, productPrice, productDiscount,numberOfAvailableProducts, manufacturerRebate FROM productdetails WHERE manufacturerRebate > 0;";
            PreparedStatement pst = conn.prepareStatement(manufacturerRebateProductsListQuery);
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                NoOfAvailableProducts product = new NoOfAvailableProducts(rs.getString("productName"),rs.getString("productPrice"),rs.getString("productDiscount") ,rs.getString("numberOfAvailableProducts") ,rs.getString("manufacturerRebate"));
                manufacturerRebateProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("manufacturerRebateProductsList(): "+e);
        }

        return manufacturerRebateProductsList;
    }

    public static ArrayList<NoOfProductsSold> totalSoldProductsList()
    {
        ArrayList<NoOfProductsSold> totalSoldProductsList = new ArrayList<NoOfProductsSold>();

        try 
        {
            getConnection();
            
            String totalSoldProductsListQuery="SELECT productName, productPrice, numberOfItemsSold, (productPrice * numberOfItemsSold) AS productTotalSales FROM productdetails;";
            PreparedStatement pst = conn.prepareStatement(totalSoldProductsListQuery);
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                NoOfProductsSold product = new NoOfProductsSold(rs.getString("productName"),rs.getString("productPrice"),rs.getString("numberOfItemsSold") ,rs.getString("productTotalSales"));
                totalSoldProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("totalSoldProductsList(): "+e);
        }

        return totalSoldProductsList;
    }

    public static ArrayList<TotalSalesDaily> totalSalesDailyOrdersList()
    {
        ArrayList<TotalSalesDaily> totalSalesDailyOrdersList = new ArrayList<TotalSalesDaily>();

        try 
        {
            getConnection();
            
            String totalSalesDailyOrdersListQuery="SELECT orderDate, SUM(orderPrice) AS totalDailySales, GROUP_CONCAT(CONCAT(orderName, ' (', orderPrice, ')')) AS productsListDesciption FROM customerorders GROUP BY orderDate;";
            PreparedStatement pst = conn.prepareStatement(totalSalesDailyOrdersListQuery);
            ResultSet rs = pst.executeQuery();
        
            while(rs.next())
            {	
                TotalSalesDaily order = new TotalSalesDaily(rs.getString("orderDate"),rs.getString("totalDailySales"), rs.getString("productsListDesciption"));
                totalSalesDailyOrdersList.add(order);
            }
        }
        catch(Exception e)
        {
            System.out.println("totalSalesDailyOrdersList(): "+e);
        }

        return totalSalesDailyOrdersList;
    }
}	