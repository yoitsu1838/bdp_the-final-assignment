
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
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

//JSON In Java
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class musicInfo
 */
@WebServlet("/musicInfo")
public class musicInfo extends HttpServlet {
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
	public musicInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String musicId = request.getParameter("musicId");
		String programName = "";
		programName = request.getParameter("programName");
		String programId = request.getParameter("programId");

		/* annict APIからアニメ情報を取得*/

		// InputStreamの用意
		if (!(programName.length() == 0)) {
			URL url = new URL(
					"https://api.annict.com/v1/works?access_token=*******&filter_title="
							+ URLEncoder.encode(programName, "UTF-8"));
			URLConnection connection = url.openConnection();
			// 接続
			connection.connect();
			// サーバからやってくるデータをInputStreamとして取得
			InputStream inputStream = connection.getInputStream();
			// 次に inputStream を読み込む InputStreamReader のインスタンス inputStreamReader を生成
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			// さらに inputStreamReader をラップする BufferedReader のインスタンス reader を生成
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String jsonRaw;
			jsonRaw = reader.readLine();
			//json配列の形へ整形
			String escapeWord = "total_count";
			int endIndex = jsonRaw.indexOf(escapeWord);
			System.out.println(escapeWord + endIndex);
			endIndex = endIndex - 2;
			System.out.println(jsonRaw);
			jsonRaw = jsonRaw.substring(9, endIndex);//[～～]ノカタチへ
			System.out.println(jsonRaw);

			//配列へ
			JSONArray jsonArray = new JSONArray(jsonRaw);
			ArrayList<HashMap<String, String>> annictInfos = new ArrayList<HashMap<String, String>>();
			String ogImgUrl = "";
			for (int i = 0; i < jsonArray.length(); i++) {
				HashMap<String, String> annictInfo = new HashMap<String, String>();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				//System.out.println(jsonObject.getString("images"));　//To do jsonの項目の中に配列存在する。どう扱う？
					//ogImgUrl = jsonObject.getString("og_image_url");
					//annictInfo.put(jsonObject.getString("title"), ogImgUrl);
				annictInfo.put("title", jsonObject.getString("title"));
				annictInfo.put("annictId", String.valueOf(jsonObject.getInt("id")));
				annictInfo.put("officialHpUrl", jsonObject.getString("official_site_url"));

			}

		}

		String view = "/WEB-INF/views/musicInfo.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
