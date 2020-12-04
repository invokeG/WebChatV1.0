package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDao;
import ov.Account;
import ov.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterAccountServlet")
public class RegisterAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public RegisterAccountServlet(){
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{

		User user=null;

		response.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String account_name=request.getParameter("account_name");
		String introduction=request.getParameter("introduction");
		String c_id=(String)request.getSession().getAttribute("user_id");
		System.out.println("�������ں��û�id:"+c_id);

		Account account=new Account();
		account.setId(id);
		account.setAccount_name(account_name);
		account.setC_id(c_id);
		account.setIntroduction(introduction);

		AccountDao dao=new AccountDao();
		if(dao.search(id)){
			System.out.println("��id����������ںţ�");
			response.sendRedirect("register_account.jsp?error=1");
			return ;
		}
		else{
			System.out.println("�������빫�ںţ�");
		}
		if(dao.insertAccount(account)){
			System.out.println("����ɹ���");
			request.setAttribute("str1","����ɹ�");
			request.setAttribute("str2","���ں����±༭����");
			request.setAttribute("url1","register_account.jsp");
			request.setAttribute("url2","edit_article.jsp");
			//request.setAttribute("a_id",id);		//�����ں�id��edit_article.jsp
			request.setAttribute("account1",account);
			System.out.println(account);
			List<Account> accountList=dao.listAccount();
			request.setAttribute("list",accountList);
			request.getRequestDispatcher("edit_article.jsp").forward(request,response);
		}
		else{
			System.out.println("����ʧ��");
			response.sendRedirect("register_account.jsp?error=2");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doGet(request,response);
	}
}
















