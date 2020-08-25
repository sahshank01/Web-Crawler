package webcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
	
	private Queue<String> queue;
	private Map<String,Integer> discoveredWebsiteList;
	
	public WebCrawler() {
		this.queue=new LinkedList<>();
		this.discoveredWebsiteList=new HashMap<>();
	}
	
	public void discover(String root) {
		this.queue.add(root);
		discoveredWebsiteList.put(root, 1);
		
		while(!queue.isEmpty()) {
			String v=queue.remove();
			String rawHtml = readURL(v);
			
			String regexp = "((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(rawHtml);
			
			while(matcher.find()) {
				
				String actualURL = matcher.group();
				
				if(!discoveredWebsiteList.containsKey(actualURL)) {
					discoveredWebsiteList.put(actualURL,1);
					System.out.println("Website found\t\t: "+actualURL);
					queue.add(actualURL);
				}
			}
		}
	}

	private String readURL(String v) {
		String rawHtml="";
		try {
			URL url = new URL(v);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String inputLine = "";
			while((inputLine = in.readLine())!=null) {
				rawHtml+=inputLine;
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawHtml;
	}
}
