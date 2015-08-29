package ysitd.ircbot.java.plugin;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import ysitd.ircbot.java.api.*;

public class TextImage implements CommandExecutor{

	@Override
	public String getName() {
		final String name = "textimage";
		return name;
	}

	@Override
	public boolean onCommand(String username , String prefix , String command , String[] argument) {
		if(argument[0].equals(command)){
			if(!Permission.contains(username , "operator.textimage")) return false;
			if(!argument[1].isEmpty()){
				System.out.println(command);
				getImageBack(argument[1]);
			}
			/*else{
				FileDialog fd = new FileDialog(new JFrame(),"開啟圖檔",FileDialog.LOAD);
				fd.setVisible(true);
				File filep=new File(fd.getDirectory() + System.getProperty("file.separator").charAt(0) + fd.getFile());
				BufferedImage bi=null; 
				try {
					bi=ImageIO.read(filep);
				} catch (IOException e) {
					e.printStackTrace();
				}
				getImageBack(bi);
			}*/
		}
		return false;
	}
	
	public void getImageBack(BufferedImage im){ //未完成
		for(int y=0;y<32;y++){
			StringBuilder sbr=new StringBuilder();
			for(int x=0;x<144;x++){
		        sbr.append(im.getRGB(x, y) == -16777216 ? " " : im.getRGB(x, y) == -1 ? "#" : "*");
		        if (sbr.toString().trim().isEmpty()) continue;
		        JIRCBOTPlugin.say(sbr.toString() , JIRCBOTPlugin.getChannel());
			}
		}
	}
	
	public void getImageBack(String lineline){
		BufferedImage bi=new BufferedImage(144 , 32 , BufferedImage.TYPE_INT_RGB);
		Graphics g=bi.getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g.setFont(new Font("Dialog" , Font.PLAIN , 24));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString(lineline , 6 , 24);
		for(int y=0;y<32;y++){
			StringBuilder sbr=new StringBuilder();
			for(int x=0;x<144;x++) //這範圍到哪一行程式
		        sbr.append(bi.getRGB(x, y) == -16777216 ? " " : bi.getRGB(x, y) == -1 ? "#" : "*");
		        if (sbr.toString().trim().isEmpty()) continue;
		        try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		        JIRCBOTPlugin.say(sbr.toString() , JIRCBOTPlugin.getChannel());
		}
	}
	
}
