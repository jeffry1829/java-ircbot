package ysitd.ircbot.java.plugin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import ysitd.ircbot.java.api.*;

public class TextImage implements CommandExecutor{

	@Override
	public String getName() {
		final String name = "textimage";
		return name;
	}

	@Override
	public boolean onCommand(String username , String prefix , String[] argument) {
		if(Permission.contains(username, "operator.textimage")){
			if(!argument[1].isEmpty()){
				System.out.println(argument[1]);
				if(!argument[1].contains("http")){
					System.out.println(getName());
						try {
							getImageBack(argument[1]);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else{
						try {
							getImage(argument[1]);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				return true;
			}
			return true;
		}
		return false;
	}
	
	public void Download(String url,String distination) throws IOException, Exception, FileNotFoundException{
	// Give UA (user agent)
	// http://stackoverflow.com/questions/2529682/setting-user-agent-of-a-java-urlconnection
	//X-UA-Compatible: IE=EmulateIE7
	//X-UA-Compatible: IE=edge
	//X-UA-Compatible: Chrome=1
	System.setProperty("http.agent", "1");
	InputStream is = new URL(url).openConnection().getInputStream();
	FileOutputStream fos = new FileOutputStream(distination);
	byte[] buffer = new byte[1024];
	for(int length;(length = is.read(buffer) ) > 0;fos.write(buffer,0,length));
	is.close();
	fos.close();
}
	
	//From: http://www.mediafire.com/view/bv4cq2zew724amy/ASCII.java
    BufferedImage image; 
    double gValue;
    File fi=new File("./image.jpg");
	
	public void getImage(String url) throws InterruptedException // Actual method that reads the image.
	    {
	    try {
	    	Download(url,"./image.jpg");
	        image = ImageIO.read(fi); //The name of the input image is image.jpg, change it to suit your needs
	       } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	       for (int y = 0; y <image.getHeight(); y++) //iterate through all the rows
	       {
	    	   StringBuilder sb=new StringBuilder();
	        for (int x = 0; x < image.getWidth(); x++) //iterate through all the columns in one row, thus accessing each pixel in each cell
	        {
	            Color pixelColor = new Color(image.getRGB(x,y)); // Create a new color object with the RGB color of the current pixel
	            gValue = (((pixelColor.getRed()*0.2989)+(pixelColor.getBlue()*0.5870)+(pixelColor.getGreen()*0.1140))); // The grayscale value is 30% of the red value, 59% of the green value and 11% of the blue value.
	            sb.append(returnStrPos(gValue));// a helper method, see below. This method returns the appropriate character according to the darkness of the pixel.
	        }
	        Thread.sleep(1000L);
	        JIRCBOTPlugin.say(sb.toString() , JIRCBOTPlugin.getChannel());
	       }
	       
	       fi.delete();
	       
	    }
	    
	    public String returnStrPos(double g)//takes the grayscale value as parameter
	    {
	    String str = " ";
	    /*
	     Create a new string and assign to it a string based on the grayscale value.
	     * If the grayscale value is very high, the pixel is very bright and assign characters 
	     * such as . and , that do not appear very dark. If the grayscale value is very lowm the pixel is very dark, 
	     * assign characters such as # and @ which appear very dark. 
	     */

	    if (g >= 230)
	    {
	        str = " ";
	    }
	    else if (g >= 200)
	    {
	        str = ".";
	    }
	    else if (g >= 180)
	    {
	        str = "*";
	    }
	    else if (g>= 160)
	    {
	        str = ":";
	    }
	    else if (g >= 130)
	    {
	        str = "o";
	    }
	    else if (g >= 100)
	    {
	        str = "&";
	    }
	    else if (g >= 70)
	    {
	        str = "8";
	    }
	    else if (g >= 50)
	    {
	        str = "#";
	    }
	    else
	    {
	        str = "@";
	    }

	    return str; // return the character
	        
	    }
	    
	    public String returnStrNeg(double g) // same method as above, except it reverses the darkness of the pixel. A dark pixel is given a light character and vice versa.
	    {
	    String str = " ";

	    if (g >= 230)
	    {
	        str = "@";
	    }
	    else if (g >= 200)
	    {
	        str = "#";
	    }
	    else if (g >= 180)
	    {
	        str = "8";
	    }
	    else if (g>= 160)
	    {
	        str = "&";
	    }
	    else if (g >= 130)
	    {
	        str = "o";
	    }
	    else if (g >= 100)
	    {
	        str = ":";
	    }
	    else if (g >= 70)
	    {
	        str = "*";
	    }
	    else if (g >= 50)
	    {
	        str = ".";
	    }
	    else
	    {
	        str = " ";
	    }

	    return str;
	        
	    }
	    
	public void getImageBack(String lineline) throws InterruptedException{ //使用等寬字體
		BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("Dialog", Font.PLAIN, 24));
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString(lineline, 6, 24);
		for (int y = 0; y < 32; y++) {
		    StringBuilder sb = new StringBuilder();
		    for (int x = 0; x < 144; x++)
		        sb.append(image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "#" : "*");
		    if (sb.toString().trim().isEmpty()) continue;
		    Thread.sleep(1000L);
		    JIRCBOTPlugin.say(sb.toString(),JIRCBOTPlugin.getChannel());
		}
	}

	@Override
	public String getHelp() {
		return " usage: ]textimage text or " +
				"]textimage imgururl+";
	}
	
}
