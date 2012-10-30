package cz.fjfi.pvs.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PureJavaWebClient implements WebClient {

	@Override
	public String getUrl(String URL) throws MalformedURLException, IOException {
		URL myUrl = new URL(URL);
		BufferedReader in = new BufferedReader(new InputStreamReader(myUrl.openStream()));

		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null)
			sb.append(line);

		in.close();
		return sb.toString();
	}

}
