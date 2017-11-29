

import java.util.ArrayList;
import java.util.List;

public class CustomSearch {
	
	private List<CustomSearchItems> results = new ArrayList<>();
	
	

	public List<CustomSearchItems> getResults() {
		return results;
	}

	public void setResults(List<CustomSearchItems> results) {
		this.results = results;
	} 
	
	
	@Override
	public String toString() {
		String s = "";
		for (CustomSearchItems c : results) {
			s += "Nome: " + c.getName() + "\n" + "Endereço: " + c.getVicinity() + "\n" + 
			"\nEstado: " + c.getOpening_hours() + "\n\n";		
			
			
		}
		
		
		
//		for (CustomSearchItems c : results) {
//			//s+=c.getTitle()+": " + c.getLink()+"\n\n\n";
//			s+= "Nome: " + c.getName() + "\n" +"Endereço: " +  c.getVicinity() + "\n" + c.getHtml_attributions() + "\n\n\n";
//		}
		return s;
	}
	

	

}
