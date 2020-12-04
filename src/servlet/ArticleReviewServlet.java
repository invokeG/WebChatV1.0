package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import dao.ArticleReviewDao;
import ov.Account;
import ov.Article;
import ov.ArticleReview;

/**
 * Servlet implementation class ArticleReviewServlet
 */
@WebServlet("/ArticleReviewServlet")
public class ArticleReviewServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
	public ArticleReviewServlet(){
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
		ArticleReview articleReview=null;

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		//String a_id=request.getParameter("article_id");		//����id
		//System.out.println(a_id);
		String c_id=(String)request.getSession().getAttribute("user_id");		//���۵��û�id
		System.out.println(c_id);
		String content=request.getParameter("article_review");
		System.out.println(content);

		articleReview=new ArticleReview();
		//articleReview.setA_id(a_id);
		articleReview.setC_id(c_id);
		articleReview.setContent(content);

		ArticleReviewDao dao=new ArticleReviewDao();
		if(dao.insertReview(articleReview)){
			System.out.println("����ɹ���");
			request.setAttribute("str1","����ɹ�");
			request.setAttribute("str2","��������ҳ��");
			request.setAttribute("url1","content");
			request.setAttribute("url2","article_review.jsp");
			request.getRequestDispatcher("reviewed.jsp").forward(request,response);
		}
		else{
			System.out.println("����ʧ�ܣ�");
			response.sendRedirect("content.jsp?error=1");
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










