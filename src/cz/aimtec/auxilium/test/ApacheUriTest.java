package cz.aimtec.auxilium.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class ApacheUriTest {

	public static void main(String[] args) throws URISyntaxException {

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http");
		builder.setHost("localhost");
		builder.setPort(8080);
		builder.setPath("requestList");
		builder.addParameter("client_id", "client.id");
		builder.addParameter("client_secret", "client.secret");

		URI uri = builder.build();
		System.out.println(uri.toString());

	}

}
