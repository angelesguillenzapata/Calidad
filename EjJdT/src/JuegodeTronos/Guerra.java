package JuegodeTronos;

public class Guerra {
	
	/*Guerra con los atributos atacante de tipo Personaje, 
			atacado de tipo Personaje y ganador de tipo personaje.*/
	
	private Personaje atacante;
	private Personaje contrincante;
	private Personaje ganador;
	
	public Guerra(Personaje atacante, Personaje contrincante){
		this.atacante = atacante;
		this.contrincante = contrincante;
		this.ganador = batalla(atacante,contrincante);
	}
	
	public Personaje batalla(Personaje p1, Personaje p2){	
		int tropas1 = Personaje.sumAliados(p1);
		int tropas2 = Personaje.sumAliados(p2);
		if(tropas1 == tropas2){
			System.out.println("Ha habido una masacre en ambos bandos, se podría decir que nadie ha ganado la guerra...");
		}else{
			if(tropas1 > tropas2){
				return this.ganador = p1;
			}else{
				return this.ganador = p2;			
			}
		}
		return null;	
	}

	public Personaje getGanador() {
		return ganador;
	}

	
	
}
