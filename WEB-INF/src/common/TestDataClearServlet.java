package common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DataClearServlet", urlPatterns = { "/DataClearServlet" })
public class TestDataClearServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doMain(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doMain(req, res);
	}

	public void doMain(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		String fileName=req.getParameter("filename");

		ServletContext sc = this.getServletContext();
		String path = sc.getRealPath("/testdata/" + fileName);

		TestDataBase db = new TestDataBase();
		db.removeTestData(path);

		PrintWriter out=res.getWriter();
		out.println("テストデータ["+fileName+"]のテーブルのデータを削除しました。");

	}
}
