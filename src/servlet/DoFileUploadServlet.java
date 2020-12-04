package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FileDao;
import dao.UserDao;
import ov.Mfile;
import ov.User;

/**
 * Servlet implementation class DoFileUploadServlet
 */
@WebServlet("/DoFileUploadServlet")
public class DoFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoFileUploadServlet");
		System.out.println("c_id:"+request.getSession().getAttribute("userid")+" g_id:"+request.getParameter("g_id"));
		String c_id = (String) request.getSession().getAttribute("userid");
		UserDao dao = new UserDao();
		User user = dao.getUserById(c_id);
		int g_id = Integer.parseInt(request.getParameter("g_id"));
		FileDao dao2 = new FileDao();
		
		boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
		if(isFileUpload){
			try {
				//�õ��ļ��ϴ�����
				FileItemFactory factory = new DiskFileItemFactory();
				//�����ļ��ϴ�������
				ServletFileUpload fileUpload = new ServletFileUpload(factory);
				//�����ļ��ϴ���ı����ʽ
				fileUpload.setHeaderEncoding("UTF-8");
				// �������� :  FileItem���� ע��: ÿһ������ ��Ӧһ�� FileItem����(��װ)
				List<FileItem> fileItemList = fileUpload.parseRequest(request);
				//����fileItemList
				for(FileItem item: fileItemList){
				//�������ı������ļ����͵�
				if(!item.isFormField()){
				//�õ��ı����valueֵ���� ·��+�ļ���
				String value = item.getName();
				//�õ��ļ���
				String fileName = value.substring(value.lastIndexOf("\\")+1);
				//�õ��ϴ����ļ�����
				String fileType = fileName.substring(fileName.lastIndexOf("."));
				//���ļ��������� ����+�ļ��� 
				System.out.println("fileName="+fileName);
				/* 	fileName = new Date().getTime() + fileName;  */
				//�õ��������ĸ�·��
				String rootPath = request.getRealPath("/");
				//ָ���ļ����·��
				String realPath = rootPath+"/"+"upload"+"/"+g_id;
				//�����ļ���ŵ�Ŀ¼��ע�� Ŀ¼Ҳ���ļ�
				File file = new File(realPath);
				Mfile myfile = new Mfile();
				Date date = new Date();
				myfile.setName(fileName);
				myfile.setC_date(date);
				myfile.setType(fileType);
				myfile.setContent(""+realPath.toString()+"");
				myfile.setC_id(user.getId());
				myfile.setG_id(g_id+"");
				myfile.setC_name(user.getNickName());
				dao2.insertFile(myfile);
				System.out.println(realPath);
				//���Ŀ¼������
				if(!file.isDirectory()){
					//�����ļ��ϴ�Ŀ¼
					file.mkdirs();
				}
				File newFile = new File(realPath+"/"+fileName);
				//��newFile�ļ���д������
				item.write(newFile);
				}else {//��������ļ��ϴ����ı��򣬰������������ʾ��ҳ����
					System.out.println("�����ļ��ϴ����ı���");
				}
			}
				response.sendRedirect("GroupInformationServlet?info_type=1&flag=1&g_id="+g_id);
			} catch (FileUploadException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				System.out.println("�ļ��ϴ�ʧ��");
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				System.out.println("�ļ��ϴ�ʧ��");
			}
		}else {
			System.out.println("�����ļ��ϴ�������");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
