package cz.fjfi.pvs.parser;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class UrlSearchTest {
	
	
	@Test
	public void shoudFindSomeUrls(){
		String testString = "<div>" + 
					"<b>SOUTĚŽTE O VYMAZLENÉ CENY S FILMEM \"MAZEL\"!</b><br />" +
					" Po celý říjen můžete soutěžit s filmem MAZEL o vymazlené ceny od kavárny Friends Coffee <br>"+
					"House /Mazel v kavárně/ nebo od sportovního centra Fitnesskotva /Mazel ve fitku/.<br>" +
					"<br>" +
					"<a href=\"http://www.cinemart.cz/upload/tz/2012/190912170358..pdf\" target=\"_blank\"><strong>VÍCE O PODMÍNKÁCH SOUTĚŽE ZDE </strong></a><br>" +
					"<br>" +
					"<br>" +
					"Více o filmu <a href=\"http://www.cinemart.cz/detailf.aspx?IDFilm=10971\" target=\"_blank\"><strong>MAZEL si přečtěte zde</strong></a>" +
					"</div>" + 
					"<div>" +
					"<b>VIRTUÁLNÍ PROHLÍDKA PROSTOR KINA EVALD:</b><br />" +
					"<a href=\"http://www.panoramika.cz/firma/komorni-kino-evald/11193/\" target=\"_blank\"><strong>Prosím vstupte</strong></a><br>" +
					"</div>" + 
					"<div>" +
					"<b>OD ZÁŘÍ ROZŠIŘUJEME PŘEDSTAVENÍ PRO SENIORY!</b><br />" +
					"Budeme pro Vás hrát minimálně 3x týdně za vstupné 60,-Kč. Každé pondělí, čtvrtek a pátek první odpolední představení, zpravidla od 15:00 hodin.<br>" +
					"Přístup mají i ostatní diváci za běžné vstupné.<br>" +
					"Těšíme se na Vás..." +
					"</div>" +  
					"<div>" +
					"<b>STÁHNĚTE SI:</b><br />" +
					" <a href=\"http://www.nordfest.cz/programs/RIJEN_2012www.pdf\" target=\"_blank\">| PROGRAM KINA NA ŘÍJEN 2012 </a> |" +
					"</div>";
		
		Set<String> urls = UrlSearch.getUrls(testString);
		System.out.println(urls);
		Assert.assertTrue(urls.contains("http://www.cinemart.cz/upload/tz/2012/190912170358..pdf"));
		Assert.assertTrue(urls.contains("http://www.nordfest.cz/programs/RIJEN_2012www.pdf"));
	}

}
