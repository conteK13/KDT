package j241018;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamEx {
	public static void main(String[] args) {
		byte result[] = new byte[6]; // 비어 있는 byte 배열
		try {
			FileInputStream fin = new FileInputStream("c:\\javadata\\test.out");
			int n = 0, c;
			while ((c = fin.read()) != -1) {
				result[n] = (byte) c; // 읽은 바이트를 배열에 저장
				n++;
			}
			System.out.println("c:\\javadata\\test.out에서 읽은 배열을 출력합니다.");
			for (int i = 0; i < result.length; i++)
				System.out.print(result[i] + " ");
			System.out.println();
			fin.close();
		} catch (IOException e) {
		}
	}
}
