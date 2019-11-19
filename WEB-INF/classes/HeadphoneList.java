import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HeadphoneList")
public class HeadphoneList extends HttpServlet
{
	/* Headphone Page Displays all the Headphone and their Information in BESTDeal */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		
		HashMap<String, Headphone> allHeadphones = new HashMap<String, Headphone>();
		HashMap<String, Headphone> hm = new HashMap<String, Headphone>();

		try
		{
			allHeadphones = MySqlDataStoreUtilities.getHeadphones();
		}
		catch(Exception e)
		{

		}

		if(CategoryName==null)
		{
			//hm.putAll(SaxParserDataStore.headphones); //need to make change here
			hm.putAll(allHeadphones); //need to make change here
			name = "";
		}
		else
		{
			if(CategoryName.equals("microsoft"))
			{
				//for(Map.Entry<String,Headphone> entry : SaxParserDataStore.headphones.entrySet())
				for(Map.Entry<String,Headphone> entry : allHeadphones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Microsoft"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Microsoft";
			}
			else if(CategoryName.equals("sony"))
			{
				for(Map.Entry<String,Headphone> entry : allHeadphones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Sony"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Sony";
			}
			else if(CategoryName.equals("lg"))
			{
				for(Map.Entry<String,Headphone> entry : allHeadphones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("LG"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "LG";
			}
			else if(CategoryName.equals("samsung"))
			{
				for(Map.Entry<String,Headphone> entry : allHeadphones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Samsung"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Samsung";
			}
			else if(CategoryName.equals("onida"))
			{
				for(Map.Entry<String,Headphone> entry : allHeadphones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Onida"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Onida";
			}
		}
		
		/* Header, Left Navigation Bar are Printed.

		All the Headphone and Headphone information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" headphones</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Headphone> entry : hm.entrySet())
		{
			Headphone headphone = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+headphone.getName()+"</h3>");
			pw.print("<strong>$"+headphone.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/headphone/"+headphone.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='headphones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='headphones'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='hidden' name='price' value='"+headphone.getPrice()+"'>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='headphones'>"+
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