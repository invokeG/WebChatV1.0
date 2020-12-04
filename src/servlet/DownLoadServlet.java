package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
//import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�õ���Ҫ���ص��ļ�������ǰһ��ҳ�洫������
        String fileName = new String
                (request.getParameter("fileName").getBytes("ISO-8859-1"),"UTF-8");
        //�õ���Ŀ��tomcat �еĸ�·��
        String realPath = request.getRealPath("/");
        //�õ��ļ��ڷ������е�·��
        String path = realPath+ "/upload/";//
//				path =request.getParameter("fileName")+"/"+request.getParameter("filePath");
        path += request.getParameter("g_id");
        path +="/"+request.getParameter("fileName");
        //���response ������Ϣ
        response.reset();
        //����IE������������� ��ʾΪ ����
        response.setContentType("application/x-download;charset=UTF-8");
        //����IE������� ͷ
        response.setHeader("Content-Disposition","attachment;filename="+ fileName);
        //�ӷ������� ��ȡ�ļ�
        File file=new File(path);    response.setContentLength(Integer.valueOf(((Long)file.length()).toString()));
        System.out.println(file.toString());
        //������   ��ȡĿ���ļ�
        FileInputStream  fis=new FileInputStream(file);
        int len=-1;
        byte[] data=new byte[1024];
        ByteArrayOutputStream bos=new ByteArrayOutputStream(1024);
        // �ļ�������ĩβ ���� -1
        while((len=fis.read(data))!=-1)
        {
            //���������е����� ת���ɶ��������� �����ڴ���
            bos.write(data,0,len);
        }
        //�� �������ϵ��ļ�ת���� ����������   OutPutStream ����� д���Ӧ�ļ���
        OutputStream os= response.getOutputStream();
        //�ӷ����� �õ����� ��ͻ��˽���д��
        os.write(bos.toByteArray());
        //����ڴ��ļ� ��ͻ���д��
        os.flush();
        //�ر������
        os.close();
        //�ر�������
        fis.close();
        response.sendRedirect("GroupInformationServlet?info_type=1&flag=1&g_id="+request.getParameter("g_id"));

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
