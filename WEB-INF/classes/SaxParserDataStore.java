
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler
{
    Console console;
    Game game;
    Tablet tablet;
    Accessory accessory;
	Television television;
	SoundSystem soundSystem;
	Phone phone;
	Laptop laptop;
	VoiceAssistant voiceAssistant;
	FitnessWatch fitnessWatch;
	SmartWatch smartWatch;
	Headphone headphone;
	WirelessPlan wirelessPlan;
	
    static HashMap<String,Console> consoles;
    static HashMap<String,Game> games;
    static HashMap<String,Tablet> tablets;
    static HashMap<String,Accessory> accessories;
	static HashMap<String,Television> televisions;
	static HashMap<String,SoundSystem> soundSystems;
	static HashMap<String,Phone> phones;
	static HashMap<String,Laptop> laptops;
	static HashMap<String,VoiceAssistant> voiceAssistants;
	static HashMap<String,FitnessWatch> fitnessWatches;
	static HashMap<String,SmartWatch> smartWatches;
	static HashMap<String,Headphone> headphones;
	static HashMap<String,WirelessPlan> wirelessPlans;
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName)
	{
		this.consoleXmlFileName = consoleXmlFileName;
		consoles = new HashMap<String, Console>();
		games=new  HashMap<String, Game>();
		tablets=new HashMap<String, Tablet>();
		accessories=new HashMap<String, Accessory>();
		accessoryHashMap=new HashMap<String,String>();
		televisions = new HashMap<String, Television>();
		soundSystems = new HashMap<String, SoundSystem>();
		phones = new HashMap<String, Phone>();
		laptops = new HashMap<String, Laptop>();
		voiceAssistants = new HashMap<String, VoiceAssistant>();
		fitnessWatches = new HashMap<String, FitnessWatch>();
		smartWatches = new HashMap<String, SmartWatch>();
		headphones = new HashMap<String, Headphone>();
		wirelessPlans = new HashMap<String, WirelessPlan>();
		parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for console,games etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("console")) 
		{
			currentElement="console";
			console = new Console();
            console.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("tablet"))
		{
			currentElement="tablet";
			tablet = new Tablet();
            tablet.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("game"))
		{
			currentElement="game";
			game= new Game();
            game.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("console"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }
		if (elementName.equalsIgnoreCase("television")) 
		{
			currentElement="television";
			television = new Television();
            television.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("soundSystem")) 
		{
			currentElement="soundSystem";
			soundSystem = new SoundSystem();
            soundSystem.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("phone")) 
		{
			currentElement="phone";
			phone = new Phone();
            phone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("laptop")) 
		{
			currentElement="laptop";
			laptop = new Laptop();
            laptop.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("voiceAssistant")) 
		{
			currentElement="voiceAssistant";
			voiceAssistant = new VoiceAssistant();
            voiceAssistant.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("fitnessWatch")) 
		{
			currentElement="fitnessWatch";
			fitnessWatch = new FitnessWatch();
            fitnessWatch.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("smartWatch")) 
		{
			currentElement="smartWatch";
			smartWatch = new SmartWatch();
            smartWatch.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("headphone")) 
		{
			currentElement="headphone";
			headphone = new Headphone();
            headphone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("wirelessPlan")) 
		{
			currentElement="wirelessPlan";
			wirelessPlan = new WirelessPlan();
            wirelessPlan.setId(attributes.getValue("id"));
		}

    }
	// when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException
	{
 
        if (element.equals("console"))
		{
			consoles.put(console.getId(),console);
			return;
        }
 
        if (element.equals("tablet"))
		{	
			tablets.put(tablet.getId(),tablet);
			return;
        }
        if (element.equals("game"))
		{
			games.put(game.getId(),game);
			return;
        }
		if (element.equals("television"))
		{
			televisions.put(television.getId(),television);
			return;
        }
		if (element.equals("soundSystem"))
		{
			soundSystems.put(soundSystem.getId(),soundSystem);
			return;
        }
		if (element.equals("phone"))
		{
			phones.put(phone.getId(),phone);
			return;
        }
		if (element.equals("laptop"))
		{
			laptops.put(laptop.getId(),laptop);
			return;
        }
		if (element.equals("voiceAssistant"))
		{
			voiceAssistants.put(voiceAssistant.getId(),voiceAssistant);
			return;
        }
		if (element.equals("fitnessWatch"))
		{
			fitnessWatches.put(fitnessWatch.getId(),fitnessWatch);
			return;
        }
		if (element.equals("smartWatch"))
		{
			smartWatches.put(smartWatch.getId(),smartWatch);
			return;
        }
		if (element.equals("headphone"))
		{
			headphones.put(headphone.getId(),headphone);
			return;
        }
		if (element.equals("wirelessPlan"))
		{
			wirelessPlans.put(wirelessPlan.getId(),wirelessPlan);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory"))
		{
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("console")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("console"))
		{
			console.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image"))
		{
			if(currentElement.equals("console"))
				console.setImage(elementValueRead);
			if(currentElement.equals("game"))
				game.setImage(elementValueRead);
			if(currentElement.equals("tablet"))
				tablet.setImage(elementValueRead);
			if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);
			if(currentElement.equals("television"))
				television.setImage(elementValueRead);
			if(currentElement.equals("soundSystem"))
				soundSystem.setImage(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setImage(elementValueRead);
			if(currentElement.equals("fitnessWatch"))
				fitnessWatch.setImage(elementValueRead);
			if(currentElement.equals("smartWatch"))
				smartWatch.setImage(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setImage(elementValueRead);
			if(currentElement.equals("wirelessPlan"))
				wirelessPlan.setImage(elementValueRead);

			return;
        }
        

		if (element.equalsIgnoreCase("discount"))
		{
			if(currentElement.equals("console"))
				console.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("game"))
				game.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("tablet"))
				tablet.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("television"))
				television.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("soundSystem"))
				soundSystem.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fitnessWatch"))
				fitnessWatch.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartWatch"))
				smartWatch.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wirelessPlan"))
				wirelessPlan.setDiscount(Double.parseDouble(elementValueRead));
			
			return;
	    }


		if (element.equalsIgnoreCase("condition"))
		{
			if(currentElement.equals("console"))
				console.setCondition(elementValueRead);
			if(currentElement.equals("game"))
				game.setCondition(elementValueRead);
			if(currentElement.equals("tablet"))
				tablet.setCondition(elementValueRead);
			if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);
			if(currentElement.equals("television"))
				television.setCondition(elementValueRead);
			if(currentElement.equals("soundSystem"))
				soundSystem.setCondition(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setCondition(elementValueRead);
			if(currentElement.equals("fitnessWatch"))
				fitnessWatch.setCondition(elementValueRead);
			if(currentElement.equals("smartWatch"))
				smartWatch.setCondition(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setCondition(elementValueRead);
			if(currentElement.equals("wirelessPlan"))
				wirelessPlan.setCondition(elementValueRead);
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer"))
		{
			if(currentElement.equals("console"))
				console.setRetailer(elementValueRead);
			if(currentElement.equals("game"))
				game.setRetailer(elementValueRead);
			if(currentElement.equals("tablet"))
				tablet.setRetailer(elementValueRead);
			if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);
			if(currentElement.equals("television"))
				television.setRetailer(elementValueRead);
			if(currentElement.equals("soundSystem"))
				soundSystem.setRetailer(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setRetailer(elementValueRead);
			if(currentElement.equals("fitnessWatch"))
				fitnessWatch.setRetailer(elementValueRead);
			if(currentElement.equals("smartWatch"))
				smartWatch.setRetailer(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setRetailer(elementValueRead);
			if(currentElement.equals("wirelessPlan"))
				wirelessPlan.setRetailer(elementValueRead);
			return;
		}

        if (element.equalsIgnoreCase("name"))
		{
			if(currentElement.equals("console"))
				console.setName(elementValueRead);
			if(currentElement.equals("game"))
				game.setName(elementValueRead);
			if(currentElement.equals("tablet"))
				tablet.setName(elementValueRead);
			if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);
			if(currentElement.equals("television"))
				television.setName(elementValueRead);
			if(currentElement.equals("soundSystem"))
				soundSystem.setName(elementValueRead);
			if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
			if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setName(elementValueRead);
			if(currentElement.equals("fitnessWatch"))
				fitnessWatch.setName(elementValueRead);
			if(currentElement.equals("smartWatch"))
				smartWatch.setName(elementValueRead);
			if(currentElement.equals("headphone"))
				headphone.setName(elementValueRead);
			if(currentElement.equals("wirelessPlan"))
				wirelessPlan.setName(elementValueRead);
			return;
	    }
	
        if(element.equalsIgnoreCase("price"))
		{
			if(currentElement.equals("console"))
				console.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("game"))
				game.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("tablet"))
				tablet.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("television"))
				television.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("soundSystem"))
				soundSystem.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("voiceAssistant"))
				voiceAssistant.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("fitnessWatch"))
				fitnessWatch.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartWatch"))
				smartWatch.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("headphone"))
				headphone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wirelessPlan"))
				wirelessPlan.setPrice(Double.parseDouble(elementValueRead));
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException
	{
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
	public static void addHashmap()
	{
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Assignment_2\\ProductCatalog.xml");
    } 
}
