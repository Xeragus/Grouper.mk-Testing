import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("When running Grouper.mk test cases")
class TestCases {
	
	static Grouper grouper;
	
	@BeforeAll
	public static void start() throws InterruptedException {
		grouper = new Grouper("https://grouper.mk/");
		grouper.startPage();
	}
	
	@AfterAll
	public static void end() throws InterruptedException {
		grouper.closeDriver();
	}

	// Log in with wrong credentials
	@Test
	@DisplayName("Testing loging in with faulty credentials")
	void a() throws InterruptedException {
		String email = "random@gmail.com";
		String password = "93427983274";
		String errorText = "Неуспешен логин. Вашето корисничко име или лозинка се неточни или профилот сеуште не ви е активиран.";
		assertEquals(grouper.wrongLogin(email, password), errorText);
	}
	
	// Log in with correct credentials
	@Test
	@DisplayName("Testing loging in with correct credentials")
	void b() throws InterruptedException {
		String email = "";
		String password = "";
		assertTrue(grouper.login(email, password).isEnabled());
	}
	
	// Logout
	@Test
	@DisplayName("Testing logout")
	void c() throws InterruptedException {
		assertTrue(grouper.logout().isEnabled());
	}
	
	// Register
	@Test
	@DisplayName("Testing register")
	void d() throws InterruptedException {
		String successText = "Успешно се регистриравте на нашиот сајт. Email за активација Ви беше испратен.";
		assertEquals(grouper.register(), successText);
	}
	
	// Variant required
	@Test
	@DisplayName("Testing variant required")
	void e() throws InterruptedException {
		String errorText = "Опција Опција е задолжително поле!";
		assertEquals(grouper.testVariantRequired(), errorText);
	}
	
	// Buy now
	@Test
	@DisplayName("Testing buy now")
	void f() throws InterruptedException {
		String email = "";
		String password = "";
		grouper.login(email, password);
		Thread.sleep(4000);
		assertTrue(grouper.buyNow().isEnabled());
	}
	
}	
