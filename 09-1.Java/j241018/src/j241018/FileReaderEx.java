package j241018;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderEx {
	public static void main(String[] args) {
		FileReader in = null;
//		try {
//			in = new FileReader("C:/javadata/test.log");
//			int c;
//			while ((c = in.read()) != -1) { // 한 문자씩 파일 끝까지 읽는다.
//				System.out.print((char) c);
//			}
//			in.close();
//		} catch (IOException e) {
//			System.out.println("입출력 오류");
//		}
		
		// buffer(배열)에 파일 내용 저장하기
		try {
			in = new FileReader("C:/javadata/test.log");
			char [] cbuf = new char [999999];
			int c;
			int i = 0;
			while ((c= in.read(cbuf)) != -1) { // 한 문자씩 파일 끝까지 읽는다.
				System.out.print((char) c);
				System.out.print((char) cbuf[i]);
				i++;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}
