package project_Version1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naoController.naoActions;


@WebServlet("/walkAhead.do")
public class walkAhead extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public final static String Walk_left = "1";  
    public final static String Walk_ahead = "2";  
    public final static String Walk_right = "3"; 
    public final static String Walk_back = "4"; 
    public final static String Stop = "5";
    public final static String Detect = "6";
    public final static String LED = "7";
    public final static String Speech = "8";
    public final static String lookForward = "9";
    public final static String lookDown = "10";
    public final static String lookAround = "11";
    public final static String Stand = "12";
    public final static String Rest = "13";
    public final static String ExMotion = "14";
    public final static String Say = "15";
    public final static String reactToEvent = "16";
    public static int Id=0;
    public String[] args={};
	public naoActions naoAct=new naoActions(args);	

	public walkAhead(){
		naoAct.startConnection();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		String IsOK = "success";
		String caseNO  = req.getParameter("caseNO");
		String colorNO  = req.getParameter("colorNO");
		int color = Integer.parseInt(colorNO);
		String ledNO  = req.getParameter("ledNO");
		int led = Integer.parseInt(ledNO);
		String time  = req.getParameter("time");
		float timeF = Float.parseFloat(time);
		String text  = req.getParameter("text");
		
		try {
			naoAct.moveIntial();
			switch(caseNO){  
	        case Walk_left:  
	        	naoAct.moveLeft();
	            break;  
	        case Walk_ahead:  
	           naoAct.moveForward();
	            break;  
	        case Walk_right:  
	            naoAct.moveRight();
	            break;
	        case Walk_back:  
	            naoAct.moveBackward();
	            break; 
	        case Stop:  
	            naoAct.moveStop();
	            break; 
	        case Detect:  
	            naoAct.ExSmileDetector_peroform();
	            break;   
	        case LED:  
	            naoAct.changeLedColor(led, color, timeF);
	            break;
	        case Speech:  
	            naoAct.speechSomething(text);
	            break; 
	        case lookForward:  
	            naoAct.lookForward();
	            break; 
	        case lookDown:  
	            naoAct.lookDown();;
	            break;
	        case lookAround:  
	            naoAct.lookAround();;
	            break;
	        case Stand:  
	            naoAct.standUp();
	            break;
	        case Rest:  
	            naoAct.rest();
	            break;
	        case ExMotion:  
	            naoAct.ExmotionPlay();
	            break;
	        case Say:  
	            naoAct.speechSomething("");
	            break;
	        case reactToEvent:  
	            naoAct.reactToEvent();
	            break;
	        }  
			StringBuffer xmlResult = new StringBuffer();
	          xmlResult.append("<root>");
	          xmlResult.append("<yesNum>" + IsOK + "</yesNum>");
	          xmlResult.append("</root>");
	          PrintWriter out = res.getWriter();  
	          out.write(xmlResult.toString());
	          out.close(); 
	          
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(caseNO);
		
	}
	
}
