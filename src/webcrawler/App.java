package webcrawler;

public class App {
	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		String root = "http://pistiaistyoryhma.myspecies.info/";
		crawler.discover(root);
	}
}
