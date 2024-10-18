package j241018;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileWriterEx {
	public static void main(String[] args) {
		InputStreamReader in = new InputStreamReader(System.in);
		FileWriter fout = null;
		int c;
		try {
			fout = new FileWriter("c:\\javadata\\test_for_writer.txt");
			while ((c = in.read()) != -1) {
				fout.write(c); // 키보드로부터 받은 문자를 파일에 저장
			}
			in.close();
			fout.close();
		} catch (IOException e) {
			System.out.println("입출력 오류");
		}

		byte b[] = { 7, 51, 3, 4, -1, 24 };
		try {
			FileOutputStream foutp = new FileOutputStream("c:\\javadata\\test.out");
			for (int i = 0; i < b.length; i++)
				foutp.write(b[i]); // 배열 b의 바이너리를 그대로 기록
			foutp.close();
		} catch (IOException e) {
		}
		System.out.println("c:\\javadata\\test.out을 저장하였습니다.");

	}

}
