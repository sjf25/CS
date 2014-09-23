import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

//Class gathered from http://leejava.wordpress.com/2009/09/01/make-http-post-or-get-request-from-java/
public class Email {
	/*
	* Sends an HTTP GET request to a url
	*
	* @param endpoint - The URL of the server. (Example: " http://www.yahoo.com/search")
	* @param requestParameters - all the request parameters (Example: "param1=val1&param2=val2"). Note: This method will add the question mark (?) to the request - DO NOT add it yourself
	* @return - The response from the end point
	*/
	public static String sendGetRequest(String endpoint, String requestParameters){
		String result = null;
		if (endpoint.startsWith("http://")){// Send a GET request to the servlet
			try {// Construct data
				StringBuffer data = new StringBuffer();// Send data
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length () > 0){
					urlStr += "?" + requestParameters;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection ();// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null){
					sb.append(line);
				}
				rd.close();
				result = sb.toString();
			} catch (Exception e) {e.printStackTrace();}
		}
		return result;
	}
 
	/**
	* Pipes everything from the reader to the writer via a buffer
	*/
	
	private static void pipe(Reader reader, Writer writer) throws IOException {
		char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0){
			writer.write(buf, 0, read);
		}
		writer.flush();
	}
	//block by Gerardo Salazar 230914
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String email,name;
		System.out.println("Please input your first and last name");
		name = input.nextLine().replace(" ","%20");
		System.out.println("Please input your E-Mail address");
		email = input.next();
		sendGetRequest("http://99-64-57-180.lightspeed.hstntx.sbcglobal.net/email.php","email="+email+"&name="+name);
	}
}
