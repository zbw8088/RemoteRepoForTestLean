package common;

import java.io.File;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class TestServlet {
	private static final String PROJECT_NAME="TestGit";

	private ServletUnitClient sc;
	private String method = "";

	protected WebRequest webRequest;
	protected WebResponse webResponse;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@Before
	public void setUp() throws Exception {
		HttpUnitOptions.setScriptingEnabled(false);
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		HttpUnitOptions.setExceptionsThrownOnErrorStatus(false);
		HttpUnitOptions.setDefaultCharacterSet("UTF-8");

		changeDirectory();

//		ServletRunner sr = new ServletRunner(new File("WEB-INF/web.xml"), "");
		ServletRunner sr = new ServletRunner();

		sr.registerServlet("myServlet", getClassName());
		sc = sr.newClient();
	}

	public void initialize(){

	}

	private String getClassName() {
		String testClassName = this.getClass().getName();
		String testClassLowerName = testClassName.toLowerCase();
//		if (!testClassLowerName.endsWith("test")) {
//			return "";
//		}

		int pos = testClassLowerName.indexOf("test");
		return testClassName.substring(0, pos);
	}

	protected void setGet() throws Exception {
		method = "doGet";
		webRequest = new GetMethodWebRequest("http://localhost/myServlet");
	}

	protected void setPost() throws Exception {
		method = "doPost";
		webRequest = new PostMethodWebRequest("http://localhost/myServlet");
	}

	protected void callServlet() throws Exception {
		InvocationContext ic = sc.newInvocation(webRequest);
		Object servlet = ic.getServlet();
		request = ic.getRequest();
		response = ic.getResponse();
		session = request.getSession();

		initialize();

		Method m = servlet.getClass().getMethod(
				method,
				new Class[] { HttpServletRequest.class,
						HttpServletResponse.class });
		m.invoke(servlet, new Object[] { request, response });
		webResponse = sc.getResponse(ic);
	}

	@After
	public void tearDown() {
		webRequest=null;
		webResponse=null;
		request=null;
		response=null;
		session=null;
	}

	private void changeDirectory() {
		String dir = System.getProperty("user.dir").toLowerCase();
//		if (!dir.endsWith("war")) {
//			if (dir.startsWith("d") || dir.startsWith("z")) {
//				System.setProperty("user.dir", System.getProperty("user.dir")
//						+ "\\war");
//			}
//			if (dir.startsWith("c")) {
//				System.setProperty("user.dir", System.getProperty("user.dir")
//						+ "\\workspace\\"+PROJECT_NAME+"\\war");
//			}
//		}
	}
}
