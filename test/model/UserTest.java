package model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void test() {
		User user=new User();
		user.setRid(1);
		user.setLoginName("test");
		user.setPassWord("xyz");
		user.setName("Mr.x");
		user.setEmail("mrx@gmail.com");

		assertThat(user.getRid(),is(1));
		assertThat(user.getLoginName(),is("test"));
		assertThat(user.getPassWord(),is("xyz"));
		assertThat(user.getName(),is("Mr.x"));
		assertThat(user.getEmail(),is("mrx@gmail.com"));

	}

}
