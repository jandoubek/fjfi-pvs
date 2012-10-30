package cz.fjfi.pvs.web;

import java.io.IOException;
import java.net.MalformedURLException;

public interface WebClient {

	public String getUrl(String URL) throws MalformedURLException, IOException;
	
}
