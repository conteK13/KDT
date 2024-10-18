package j241018;

import java.io.File;

public class FileClassExample {
	public static void listDirectory(File dir) {
		System.out.println("-----" + dir.getPath() + "의 서브 리스트 입니다.-----");
		File[] subFiles = dir.listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			File f = subFiles[i];
			long t = f.lastModified(); // 마지막으로 수정된 시간
			System.out.print(f.getName());
			System.out.print("\t파일 크기: " + f.length()); // 파일 크기
			System.out.printf("\t수정한 시간: %tb %td %ta %tT\n", t, t, t, t);
		}
	}

	public static void main(String[] args) {
		File f1 = new File("c:\\javadata\\system.txt");
		System.out.println(f1.getPath() + ", " + f1.getParent() + ", " + f1.getName());
		
		String res = "";
		if (f1.isFile())
			res = "파일";
		else if (f1.isDirectory())
			res = "디렉토리";
		System.out.println(f1.getPath() + "은 " + res + "입니다.");	// 파일 타입 판단 후 출력
		
		File f2 = new File("c:\\javadata\\PPT");
		
		if (!f2.exists()) {		// 존재 하지 않으면 폴더 생성
			f2.mkdir();
		}
		listDirectory(new File("c:\\javadata\\PPT"));	// 해당 폴더의 안의 파일 및 디렉토리 목록을 불러온다
		f2.renameTo(new File("c:\\javadata\\PPT2"));	// f2의 이름을 다음과 같이 수정한다
		listDirectory(new File("c:\\javadata"));		// javadata 안의 파일 및 디렉토리 목록을 불러온다

	}

}
