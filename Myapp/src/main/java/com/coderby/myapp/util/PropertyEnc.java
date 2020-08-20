package com.coderby.myapp.util;

import java.io.FileWriter;
import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class PropertyEnc {
	
	static {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("djadudqja");
		FileWriter fw = null;
		try {
			fw = new FileWriter(new ClassPathResource("db/jdbc.properties").getURI().getPath());
			fw.write("jdbc.driverClassName=net.sf.log4jdbc.DriverSpy\n");
			fw.write("jdbc.url=ENC("+enc.encrypt("jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1")+")\n");
			fw.write("jdbc.username=ENC("+enc.encrypt("hr")+")\n");
			fw.write("jdbc.password=ENC("+enc.encrypt("hr")+")\n");
			System.out.println("파일 작성 완료.");
		}catch(IOException e) {
			System.out.println("정상적으로 암호화가 되지 않았습니다.");
			System.out.println(e.getMessage());
		}finally {
			if(fw!=null) {try{fw.close();}catch(IOException e) {}}
		}
	}

}
