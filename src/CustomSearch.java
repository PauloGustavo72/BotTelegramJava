

import java.util.ArrayList;
import java.util.List;

public class CustomSearch {
	
	private List<CustomSearchItems> items = new ArrayList<>(); 
	
	
	@Override
	public String toString() {
		String s = "";
		for (CustomSearchItems c : items) {
			s+=c.getTitle()+": " + c.getLink()+"\n\n\n";
		}
		return s;
	}
	

	public List<CustomSearchItems> getItems() {
		return items;
	}

	public void setItems(List<CustomSearchItems> items) {
		this.items = items;
	}

}
