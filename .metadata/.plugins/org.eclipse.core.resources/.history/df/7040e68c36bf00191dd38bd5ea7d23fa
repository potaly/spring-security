package com.potaly.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.potaly.security.core.properties.SecurityProperties;

/**
 * 生成图形验证码，并返回到response中
 * @author wang.qiang
 * @date 2019年8月15日
 */
@RestController
public class ValidateCodeController {
	private static final String SESSION_KEY= "SESSION_KEY_IMAGE_CODE";
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private  SecurityProperties securityProperties;
	
	
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//生成图片
		ImageCode imageCode =createImageCode(request);
		//放到session中
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		//将生成的图片写入到接口的相应中
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
		
	}

	 private ImageCode createImageCode(HttpServletRequest requestf) {
	        int width = 67;
	        int height = 23;
	        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

	        Graphics g = image.getGraphics();

	        Random random = new Random();

	        g.setColor(getRandColor(200,250));
	        g.fillRect(0,0,width,height);
	        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
	        g.setColor(getRandColor(160,200));
	        for (int i = 0;i < 155; i++){
	            int x = random.nextInt(width);
	            int y = random.nextInt(height);
	            int xl = random.nextInt(12);
	            int yl = random.nextInt(12);
	            g.drawLine(x,y,x+xl,y+yl);
	        }
	        String sRand = "";
	        for(int i = 0;i < 4; i++){
	            String rand = String.valueOf(random.nextInt(10));
	            sRand += rand;
	            g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
	            g.drawString(rand,13 * i + 6,16);
	        }
	        g.dispose();
	        return new ImageCode(image,sRand,60);
	    }

	    /**
	     * 生成随机背景条纹
	     * @param fc
	     * @param bc
	     * @return
	     */
	    private Color getRandColor(int fc, int bc) {
	        Random random = new Random();
	        if(fc > 255){
	            fc = 255;
	        }
	        if(bc > 255){
	            bc = 255;
	        }
	        int r = fc + random.nextInt(bc - fc);
	        int g = fc + random.nextInt(bc - fc);
	        int b = fc + random.nextInt(bc - fc);
	        return new Color(r,g,b);
	    }
	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
}
