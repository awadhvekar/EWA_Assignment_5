import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet
{
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");
        System.out.print(action);
        String msg = "good";
        String producttype= "",productId="",productName="",productImage="",productManufacturer="",productCondition="",prod = "";
        double productPrice=0.0,productDiscount = 0.0;
        /*
        HashMap<String,Console> allconsoles = new HashMap<String,Console> ();
        HashMap<String,Tablet> alltablets = new HashMap<String,Tablet> ();
        HashMap<String,Game> allgames = new HashMap<String,Game> ();
        HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
        */
        HashMap<String,Television> allTelevisions = new HashMap<String,Television> ();
        HashMap<String, WirelessPlan> allWirelessPlans = new HashMap<String, WirelessPlan>();
		HashMap<String, FitnessWatch> allFitnessWatches = new HashMap<String, FitnessWatch>();
		HashMap<String, Headphone> allHeadphones = new HashMap<String, Headphone>();
		HashMap<String, Laptop> allLaptops = new HashMap<String, Laptop>();
		HashMap<String, Phone> allPhones = new HashMap<String, Phone>();
		HashMap<String, SmartWatch> allSmartWatches = new HashMap<String, SmartWatch>();
		HashMap<String, SoundSystem> allSoundSystems = new HashMap<String, SoundSystem>();
		HashMap<String, VoiceAssistant> allVoiceAssistants = new HashMap<String, VoiceAssistant>();
        if (action.equals("add") || action.equals("update"))
        {	
            producttype = request.getParameter("producttype");
            productId   = request.getParameter("productId");
            productName = request.getParameter("productName"); 
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            productImage = request.getParameter("productImage");
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
        }
        else
        {
            productId   = request.getParameter("productId");
        }	
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if(action.equals("add"))
        {
            if(producttype.equals("television"))
            {
                allTelevisions = MySqlDataStoreUtilities.getTelevisions();
                if(allTelevisions.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("wirelessPlan"))
            {
                allWirelessPlans = MySqlDataStoreUtilities.getWirelessPlans();
                if(allWirelessPlans.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("fitnessWatch"))
            {
                allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(allFitnessWatches.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("headphone"))
            {
                allHeadphones = MySqlDataStoreUtilities.getHeadphones();
                if(allHeadphones.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("laptop"))
            {
                allLaptops = MySqlDataStoreUtilities.getLaptops();
                if(allLaptops.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("phone"))
            {
                allPhones = MySqlDataStoreUtilities.getPhones();
                if(allPhones.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("smartWatch"))
            {
                allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(allSmartWatches.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("soundSystem"))
            {
                allSoundSystems = MySqlDataStoreUtilities.getSoundSystems();
                if(allSoundSystems.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }
            else if(producttype.equals("voiceAssistant"))
            {
                allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(allVoiceAssistants.containsKey(productId))
                {
                    msg = "Product already available";
                }
            }

            /*
            else if (producttype.equals("accessories"))
            {  
                if(!request.getParameter("product").isEmpty())
                {
                    prod = request.getParameter("product");
                    allconsoles = MySqlDataStoreUtilities.getConsoles();
                    if(allconsoles.containsKey(prod))
                    {
                        allaccessories = MySqlDataStoreUtilities.getAccessories();
                        if(allaccessories.containsKey(productId))
                        {
                            msg = "Product already available";
                        }
                    }
                    else
                    {
                        msg = "The product related to accessories is not available";
                    }
                }
                
                else
                {
                    msg = "Please add the prodcut name";
                }
                	  
            }
            */
            
            if (msg.equals("good"))
            {  
                try
                {
                    msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,prod);
                }
                catch(Exception e)
                { 
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }					
        }
        else if(action.equals("update"))
        {
            if(producttype.equals("television"))
            {
                allTelevisions = MySqlDataStoreUtilities.getTelevisions();
                if(!allTelevisions.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("wirelessPlan"))
            {
                allWirelessPlans = MySqlDataStoreUtilities.getWirelessPlans();
                if(!allWirelessPlans.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("fitnessWatch"))
            {
                allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(!allFitnessWatches.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("headphone"))
            {
                allHeadphones = MySqlDataStoreUtilities.getHeadphones();
                if(!allHeadphones.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("laptop"))
            {
                allLaptops = MySqlDataStoreUtilities.getLaptops();
                if(!allLaptops.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("phone"))
            {
                allPhones = MySqlDataStoreUtilities.getPhones();
                if(!allPhones.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("smartWatch"))
            {
                allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(!allSmartWatches.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("soundSystem"))
            {
                allSoundSystems = MySqlDataStoreUtilities.getSoundSystems();
                if(!allSoundSystems.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("voiceAssistant"))
            {
                allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(!allVoiceAssistants.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }

            /*
            if(producttype.equals("console"))
            {
                allconsoles = MySqlDataStoreUtilities.getConsoles();
                if(!allconsoles.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("tablets"))
            {
                alltablets = MySqlDataStoreUtilities.getTablets();
                if(!alltablets.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("accessories"))
            {
                allaccessories = MySqlDataStoreUtilities.getAccessories();
                if(!allaccessories.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            */	
            if (msg.equals("good"))
            {		
                try
                {
                    msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount);
                }
                catch(Exception e)
                { 
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            } 
        }
        else if(action.equals("delete"))
        {
            /*
            msg = "bad";

            allTelevisions = MySqlDataStoreUtilities.getTelevisions();
            if(allTelevisions.containsKey(productId))
            {
                msg = "good";
            }
		       		
            if (msg.equals("good"))
            {
            */
                try
                {  
                    System.out.print("delete the prodcut");
                    msg = MySqlDataStoreUtilities.deleteproducts(productId);
                }
                catch(Exception e)
                { 
                    msg = "Product cannot be deleted";
                }
                msg = "Product has been successfully deleted";
            /*
            }
            else
            {
                msg = "Product not available";
            }
            */
		}	
				
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h4 style='color:blue'>"+msg+"</h4>");
        pw.print("</div></div></div>");		
        utility.printHtml("Footer.html");
			
	}
}