package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class checkServlet extends HttpServlet {

	private static int WIDTH=60;
	private static int HEIGHT=30;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType("image/type");
		ServletOutputStream sout = response.getOutputStream();
		//设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//创建图形
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		char[] rands = generateCheckCode();
		drawBackground(g);
		drawRands(g, rands);
		g.dispose();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", bos);
		
		byte[] buff = bos.toByteArray();
		response.setContentLength(buff.length);
		sout.write(buff);
		
		bos.close();
		sout.close();
		
		session.setAttribute("checkCode", new String(rands));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	/**
	 * 
	 * @return 四位验证码
	 */
	private char[] generateCheckCode(){
		String chars="0123456789abcdefghijklmnopqrstuvwxyz";
		char[] rands = new char[4];
		for(int i=0;i<4;i++){
			int index = (int)(Math.random()*36);
			rands[i] = chars.charAt(index);
		}
		return rands;
	}

	/**
	 * 绘点
	 * @param g
	 * @param rands
	 */
	private void drawRands(Graphics g,char[] rands){
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC|Font.BOLD, 18));
		//不同高度上输出
		g.drawString(""+rands[0], 1, 17);
		g.drawString(""+rands[1], 16, 15);
		g.drawString(""+rands[2], 31, 18);
		g.drawString(""+rands[3], 46, 16);
		System.out.println(rands);
	}
	
	private void drawBackground(Graphics g){
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i=0 ;i<120;i++){
			int x = (int)(Math.random()*WIDTH);
			int y = (int)(Math.random()*HEIGHT);
			int R = (int)(Math.random()*255);
			int G = (int)(Math.random()*255);
			int B = (int)(Math.random()*255);
			g.setColor(new Color(R,G,B));
			g.drawRect(x, y, 1, 0);
			
			
		}
	}
}
