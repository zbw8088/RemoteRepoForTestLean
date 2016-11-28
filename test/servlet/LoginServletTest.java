package servlet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TestDataBase;
import common.TestServlet;
import model.User;

public class LoginServletTest extends TestServlet {


	@Test
	public void ログインに成功する() throws Exception {
		// データベースにテストデータを挿入
		TestDataBase db=new TestDataBase();
		db.setTestData("./testdata/story01/LoginServlet_test.xls");

		// POSTメソッドを指定
		setPost();

		// テキストボックスに入力値を設定
		webRequest.setParameter("LoginName", "test1");
		webRequest.setParameter("PassWord", "xyz");

		// Servletを呼び出す。
		callServlet();

		// RequestにUserオブジェクトが保存されていること（nullではないこと）を確認
		assertThat(request.getAttribute("User"),notNullValue());

		// RequestからUserオブジェクトを読み込み
		User user=(User)request.getAttribute("User");

		// 読み込んだオブジェクトのプロパティの値が適切かどうかをチェック
		assertThat(user.getLoginName(),is("test1"));
		assertThat(user.getPassWord(),is("xyz"));
		assertThat(user.getName(),is("Mr.x"));

	}

	@Test
	public void ログインに失敗する() throws Exception {
		// データベースにテストデータを挿入
		TestDataBase db=new TestDataBase();
		db.setTestData("./testdata/story01/Story01TestData.xls");

		// POSTメソッドを指定
		setPost();
		// テキストボックスに入力値を設定
		webRequest.setParameter("LoginName", "test");
		webRequest.setParameter("PassWord", "xyz");

		// Servletを呼び出す。
		callServlet();

		// RequestにUserオブジェクトが保存されていないこと（nullであること）を確認
		assertThat(request.getAttribute("User"),nullValue());

	}

}
