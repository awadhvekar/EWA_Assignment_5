import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WirelessPlansList")
public class WirelessPlansList extends HttpServlet
{
	/* WirelessPlan Page Displays all the WirelessPlan and their Information in BESTDeal */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		
		HashMap<String, WirelessPlan> allWirelessPlans = new HashMap<String, WirelessPlan>();
		HashMap<String, WirelessPlan> hm = new HashMap<String, WirelessPlan>();

		try
		{
			allWirelessPlans = MySqlDataStoreUtilities.getWirelessPlans();
		}
		catch(Exception e)
		{

		}

		if(CategoryName==null)
		{
			hm.putAll(allWirelessPlans);
			//hm.putAll(SaxParserDataStore.wirelessPlans); //need to make change here
			name = "";
		}
		else
		{
			if(CategoryName.equals("basic"))
			{
				//for(Map.Entry<String,WirelessPlan> entry : SaxParserDataStore.wirelessPlans.entrySet())
				for(Map.Entry<String,WirelessPlan> entry : allWirelessPlans.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Basic"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Basic";
			}
			else if(CategoryName.equals("premium"))
			{
				for(Map.Entry<String,WirelessPlan> entry : allWirelessPlans.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Premium"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Premium";
			}
			else if(CategoryName.equals("ultimate"))
			{
				for(Map.Entry<String,WirelessPlan> entry : allWirelessPlans.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Ultimate"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Ultimate";
			}
		}
		
		/* Header, Left Navigation Bar are Printed.

		All the WirelessPlan and WirelessPlan information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" wirelessPlans</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, WirelessPlan> entry : hm.entrySet())
		{
			WirelessPlan wirelessPlan = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wirelessPlan.getName()+"</h3>");
			pw.print("<strong>$"+wirelessPlan.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/wirelessPlan/"+wirelessPlan.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wirelessPlans'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='price' value='"+wirelessPlan.getPrice()+"'>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wirelessPlans'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='price' value='"+wirelessPlan.getPrice()+"'>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wirelessPlans'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
	}
}