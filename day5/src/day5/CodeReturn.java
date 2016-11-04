package day5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeReturn extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/jar");
		
		ServletContext ctx = getServletContext();
		InputStream is = ctx.getResourceAsStream("/bookCode.jar");
		
		int read = 0;
		byte[] bytes = new byte[1024];
		
		OutputStream os = res.getOutputStream();
		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
	}
}
