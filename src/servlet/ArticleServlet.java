package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import dao.AccountDao;
import dao.ArticleDao;
import ov.Account;
import ov.Article;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public ArticleServlet(){
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{

		User user=null;
		Account account=null;
		Article article=null;

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		//String id=request.getParameter("id");
		//String a_id=(String)request.getAttribute("a_id");
		String a_id=request.getParameter("account_id");		//�������ڵĹ��ں�id
		System.out.println(a_id);
		//String a_id=(String)request.getSession().getAttribute("account_id");
		String title=new String(request.getParameter("title").getBytes("iso-8859-1"),"UTF-8");		//���±���
		//String title=request.getParameter("title");		//���±���
		String content=new String(request.getParameter("editorValue").getBytes("iso-8859-1"),"UTF-8");		//��������
		//String content=request.getParameter("editorValue");
		System.out.println(content);
		String u_id=(String)request.getSession().getAttribute("user_id");		//�������µ��û�id
		System.out.println(u_id);
		//int like_size=request.getParameter("like_size");

		article=new Article();
		//article.setId(id);
		article.setA_id(a_id);
		article.setTitle(title);
		article.setContent(content);
		article.setU_id(u_id);

		ArticleDao dao=new ArticleDao();
		if(dao.search(title)){
			System.out.println("�ñ����Ѿ��������");
			response.sendRedirect("edit_article.jsp?error=1");
			return ;
		}
		else{
			System.out.println("���Է�������£�");
		}
		if(dao.insertArticle(article)){
			System.out.println("����ɹ���");
			request.setAttribute("str1","����ɹ�");
			request.setAttribute("str2","���ں�����ҳ��");
			request.setAttribute("url1","edit_article.jsp");
			request.setAttribute("url2","content.jsp");
			AccountDao accountDao=new AccountDao();
			List<Account> accountList=accountDao.listAccount();
			request.setAttribute("list",accountList);
			request.getRequestDispatcher("content.jsp").forward(request,response);
		}
		else{
			System.out.println("����ʧ�ܣ�");
			response.sendRedirect("edit_article.jsp?error=2");
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



























