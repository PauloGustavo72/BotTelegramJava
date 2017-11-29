
public class Estado {
	private String open_now;

	
	@Override
	public String toString() {
		if(open_now.equals("false"))
			return "Fechado";
		else if(open_now.equals(null))
			return "Estabelecimento não estabeleceu horario de funcionamento para o Google";
		else
			return "Aberto";
	}
	
	public String getOpen_now() {
		return open_now;
	}

	public void setOpen_now(String open_now) {
		this.open_now = open_now;
	}
	
	
}
