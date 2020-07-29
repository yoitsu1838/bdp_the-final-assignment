
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnisonSearch
 */
@WebServlet({ "/AnisonSearch", "/search" })
public class AnisonSearch extends HttpServlet {
	String driverClassName = "org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost/anison";
    String user = "dbpuser";
    String password = "hogehoge";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    String musicName;
    String artistName;
    String programName;

    PreparedStatement prepStmt_S;
    PreparedStatement prepStmt_S2;



    //To do OR検索とAND検索の選択
    String strPrepSQL_S = "SELECT * FROM anison WHERE musicname LIKE ? AND artistname LIKE ? AND programname LIKE ?";
    String strPrepSQL_S2 = "SELECT * FROM program WHERE programNameKana LIKE ? ";//カナから作品名を検索


    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnisonSearch() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setAttribute("isPost", false);



		String view = "/WEB-INF/views/search.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");


		showSearchResult(request, response);
		request.setAttribute("isPost", true);
        String view = "/WEB-INF/views/search.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);

	}


	  public void showSearchResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	        	Class.forName(driverClassName);
	            connection = DriverManager.getConnection(url, user, password);

	        	musicName = request.getParameter("musicName");
	            artistName = request.getParameter("artistName");
	            programName = request.getParameter("programName");

	        	prepStmt_S = connection.prepareStatement(strPrepSQL_S);

	        	prepStmt_S.setString(1,"%"+ musicName +"%");
	        	prepStmt_S.setString(2,"%"+ artistName +"%");
	        	prepStmt_S.setString(3,"%"+ programName +"%");
	            resultSet = prepStmt_S.executeQuery();

	            response.setContentType("text/html; charset=UTF-8");

	            ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();



	            while (resultSet.next()) {

	            	HashMap<String, String> columns = new HashMap<String, String>();
	            	String programid = resultSet.getString("programid");
	            	columns.put("programid", programid);

	            	String programtype = resultSet.getString("programtype");
	            	columns.put("programtype", programtype);

	            	String programname = resultSet.getString("programname");
	            	columns.put("programname", programname);

	            	String oped = resultSet.getString("oped");
	            	columns.put("oped", oped);

	            	String orderstr = resultSet.getString("orderstr");
	            	columns.put("orderstr", orderstr);

	            	String musicid = resultSet.getString("musicid");
	            	columns.put("musicid", musicid);

	            	String musicname = resultSet.getString("musicname");
	            	columns.put("musicname", musicname);

	            	String artistname = resultSet.getString("artistname");
	            	columns.put("artistname", artistname);

	            	rows.add(columns);

	            }
	            request.setAttribute("rows", rows);







	        } catch (Exception e) {
	            printError(response, e);
	        }
	  }



	 public void printError(HttpServletResponse response, Exception e) {
		try {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<html><head><title>Error</title></head><body>");
		    out.println("printError");
		    out.println(e.getMessage()+"<br>"+e);
		    out.println("</body></html>");
		    out.close();
		} catch (Exception er) {
		    er.printStackTrace();
		}
	}




}
