package name.lizhe.channeladapter.file;

import java.io.File;

public class Printer {
	public File print(File content){
		System.out.println(content.getName());
		return content;
	}
}
