package JuegodeTronos;

import java.util.ArrayList;


public class Personaje {
	
	/*Personaje con los atributos nombre de tipo string, casa de tipo string, apodo de tipo string, 
			numtropas de tipo entero  y aliados de tipo ArrayList.*/
	
	private String nombre;
	private String casa;
	private String apodo;
	private int tropas;
	private ArrayList <Personaje> aliados;
	
	public Personaje(String nombre, String casa, String apodo, int tropas){
		this.nombre = nombre;
		this.casa = casa;
		this.apodo = apodo;
		this.tropas = tropas;
		this.aliados = new ArrayList <Personaje>();
	}

	public void mostrarTodo(Personaje p0){
		System.out.println("Nombre: "+getNombre()+"\nCasa: "+getCasa()+"\nApodo: "+getApodo()+"\nTropas:"+getTropas());
		if(this.aliados.isEmpty()==true){
			System.out.println("Este personaje no tiene aliados\n");
		}else{
			p0.getAliados();
		}
	}
	
	
	public void setAliados(Personaje p2) {
		this.aliados.add(p2);
	}
	
	public void getAliados() {
		for(int i=0;i<this.aliados.size();i++){
			System.out.println("Aliados de "+this.nombre+":\nNombre del aliado: "+this.aliados.get(i).getNombre()+
							"\nTropas del aliado: "+this.aliados.get(i).getTropas()+"\n");
		}
	}
	
	public static int sumAliados(Personaje p0) {	
		int tropas = 0;
		for(int i=0;i<p0.aliados.size();i++){
			tropas += p0.aliados.get(i).getTropas();
		}
		tropas +=p0.getTropas();
		return tropas;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public int getTropas() {
		return tropas;
	}

	public void setTropas(int tropas) {
		this.tropas = tropas;
	}

	

	
	
	
}
