import java.io.*;
public class RunLinux {
    public static void main(String[] args) {
	try {
	    Process proc = Runtime.getRuntime().exec("ls /");
	    InputStreamReader ir = new InputStreamReader(proc.getInputStream());
	    LineNumberReader lr = new LineNumberReader(ir);
	    String line;
	    while ((line=lr.readLine()) != null) {
		System.out.println(line);
	    }

	} catch(java.io.IOException e) {
	    e.printStackTrace();
	}

    }

}
