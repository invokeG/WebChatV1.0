package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import ov.User;
import util.DatabaseUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			int i;
//			BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\Tomcat_server\\apache-tomcat-9.0.2\\webapps\\ROOT\\head_image\\0.png"));
//			BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream("D:\\Tomcat_server\\apache-tomcat-9.0.2\\webapps\\ROOT\\head_image\\1.png")); 
//			while((i=in.read())!=-1){  
//				System.out.println(" "+ i);
//				out1.write(i);  
//			}  
//		} catch (Exception e) {
//			System.out.println("?????????");
//		}  
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phonenumber = request.getParameter("phonenumber");
		
		try{
	       	BufferedInputStream in = new BufferedInputStream(new FileInputStream("images/headImage.png"));//E:\fileHuanCun\web\WeChatforWeb\WebContent\images\headImage.png
        	String p_headImage = "images/headImage/";
        	p_headImage += id+".png"; 
    		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(p_headImage));
    		int i;
    		while((i=in.read())!=-1){  
    			output.write(i);  
			}
    		
    		output.flush();  
    		output.close();
        }catch(Exception e){
        	System.out.println("Servlet���û�ͷ���ȡ�쳣");
        }
		String headImage = "images/headImage/"+id+".png";
		
		User user = new User();
		user.setId(id);
		user.setNickName(username);
		user.setPassword(password);
		user.setPhoneNumber(phonenumber);
		user.setHeadImage(headImage);
		user.setBalance(1000);
		
		UserDao dao = new UserDao();
		if(dao.search(id)) {
			System.out.println("id��ʹ��");
			return ;
		}else {
		}
		if(dao.insertUser(user)) {
			System.out.println("ע��ɹ�");
			request.setAttribute("str1", "ע��ɹ�");
			request.setAttribute("str2", "��¼ҳ��");
			request.setAttribute("url1", "register.jsp");
			request.setAttribute("url2", "login.jsp");
			request.getRequestDispatcher("turn.jsp").forward(request, response);
		}else {
			System.out.println("ע��ʧ��");
			response.sendRedirect("register.jsp?error=2");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
