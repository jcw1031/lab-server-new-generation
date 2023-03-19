package knu.networksecuritylab.appserver;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		System.out.println(jasyptEncoding("KNU_NETWORK_SECURITY_LAB"));
	}

	public String jasyptEncoding(String value) {
		String key = "LAB_SECURITY_JASYPT_KEY";
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		pbeEnc.setAlgorithm("PBEWithMD5AndDES");
		pbeEnc.setPassword(key);
		return pbeEnc.encrypt(value);
	}

}
