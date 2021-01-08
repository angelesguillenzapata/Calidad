package JuegodeTronos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Principal {
	
	static Scanner sc = new Scanner(System.in);
	static Scanner sc2 = new Scanner(System.in);
	//Tengo puestos un par de Scanneres porque si no el programa no identifica cual es cual en el menú switch, no se que error es pero siempre pasa con el nextLine();

	public static void main(String[] args) {
			
		/*	Crear un programa con Java basado en POO que contena las clases:
			Personaje con los atributos nombre de tipo string, casa de tipo string, apodo de tipo string, 
			numtropas de tipo entero  y aliados de tipo ArrayList.
			Guerra con los atributos atacante de tipo Personaje, 
			atacado de tipo Personaje y ganador de tipo personaje.
			
			El programa debe cargar los personajes desde un fichero con los datos de cada 
			personaje separados por punto y coma.
			Una vez cargados, debe permitir la posibilidad mediante entrada de teclado de:
			
			Listar los personajes.
			Listar las alianzas.
			Crear alianzas.
			Declarar la guerra.
			Salir del programa.
			
			Al salir del programa se debe liberar la memoria.			*/
		
		
		ArrayList <Personaje> persona = new ArrayList <Personaje>();
		
		cargarDatosTXT(persona);			//Carga de personajes desde documento de texto
		
		//Configuración para funcionamiento del menú:
		int opcion = 0;			//	Variable inicializada para moverse por el menú
		String pj = "";			/*	Variable tipo String para usar a la hora de buscar un personaje tipo "personaje" para no iniciar una y otra vez la variable 
									en el bucle de menú		*/
		Personaje p1 = null;	// 	Dos variables tipo "personaje" sin información para no iniciar una y otra vez las variables dentro del bucle
		Personaje p2 = null;

		while(opcion !=6){
			System.out.print("Menu:\n1) Listar personajes\n2) Listar Alianzas\n3) Crear Alianzas\n4) Declarar Guerra\n5) Borrar Personaje\n6) Salir.\nOpcion:");
			opcion = sc.nextInt();
			switch(opcion){
			case 1: 							//	Bucle for que recorre el ArrayList para mostrar con un método void todos los datos de cada uno de los personajes almacenados.
					for(int i=0;i<persona.size();i++){
			        	persona.get(i).mostrarTodo(persona.get(i));
			        }
					break;
			case 2:								//	Lista las alianzas (en caso de haberlas) del personaje que escribas
					System.out.println("Elija personaje para mostrar aliados:");
					pj=sc2.nextLine();
					p1 = buscaPJ(persona,pj);	// 	Enlaza el String introducido con la persona y lo iguala a una variable tipo "personaje" vacío (Ver metodo en la clase main)
					p1.getAliados();			//	Llama al metodo para recorrer el bucle del array atributo alianza del personaje seleccionado (ver clase Personaje)
					break;
			case 3: 							//	Crear alianza entre personajes:
					System.out.print("Va a crear una alianza, elija los personajes que se van a aliar:\nPersonaje 1: ");
					pj=sc2.nextLine();
					p1=buscaPJ(persona,pj);
					System.out.println("Personaje 2: ");
					pj=sc2.nextLine();
					p2=buscaPJ(persona,pj);
					p1.setAliados(p2);			//	En estas dos lineas se llama al método que añade al "Personaje 2" como aliado del "1" y viceversa
					p2.setAliados(p1);			// 	Ver método en Clase Personaje
					break;
			case 4:								//	 Realizar una guerra entre dos personajes
					System.out.println("Elija el atacante: ");
					String atacante = sc2.nextLine();
					p1 = buscaPJ(persona,atacante);
					System.out.println("Elija el contrincante: ");
					String contrincante = sc2.nextLine();
					p2 = buscaPJ(persona,contrincante);
					Guerra batalla = new Guerra(p1,p2);		// 	Se crea un objeto tipo "Guerra" en la que al pasar los atributos tipo "personaje" compara tropas y hacer ganar al 
														//	que más tropas tiene, teniendo en cuenta sus alianzas, ver clase "Guerra" y método "batalla()" dentro del mismo
														//	sumTropas() se encuentra en la clase "Personaje" como método para sumar las tropas aliadas del personaje.
					System.out.println(batalla.getGanador().getNombre()+" ha ganado la batalla");
					break;
			case 5:
					System.out.print("Se va a proceder a eliminar un personaje:\nEscriba el nombre del personaje a borrar: ");
					pj=sc2.nextLine();
					p1=buscaPJ(persona,pj);
					deletePJ(persona,p1);				//	Llama al método deletePJ de la clase principal para eliminar un Personaje del arraylist
					System.gc(); 						// 	Libera la memoria ya que el espacio que habia en el rango del arraylist queda null.
					break;
			case 6: 									//	Sale del menú
					persona.clear();
					System.gc();
					System.out.println("Ha salido del Juego de Tronos. ¡Hasta la próxima!");				
					break;
			default:									//	Si se indica un numero entero fuera del rango determinado guía al usuario hacia el dato correcto para que funcione
					System.out.println("Elija una opción válida");
			}
		}
	}
	
	
	
	public static void cargarDatosTXT(ArrayList<Personaje> al){		
		String [] datos = null;
		System.out.println("Juego de tronos:\nCargue sus personajes desde docuTXT:");
		try {			
			JFileChooser fc = new JFileChooser();
			int respuesta = fc.showOpenDialog(fc);
		        if (respuesta == JFileChooser.APPROVE_OPTION) {
	        	File fichero = fc.getSelectedFile();
	        	FileReader fr = new FileReader(fichero);
	        	BufferedReader br = new BufferedReader(fr);
				String line;
				while((line = br.readLine()) != null){
					datos = line.split(";");
					int tropas = Integer.parseInt(datos[3]);
					Personaje p0 = new Personaje(datos[0],datos[1],datos[2],tropas);
					al.add(p0);
				}
				fr.close();	
				}      
		} catch (Exception e) {
		System.out.println("");
		}		
	}
	
	
	public static Personaje buscaPJ(ArrayList<Personaje> persona,String pj){
		for(int i=0;i<persona.size();i++){
			if(pj.equals(persona.get(i).getNombre())){
				return persona.get(i);
			}
		}
		return null;
	}

	
	public static void deletePJ(ArrayList<Personaje> persona, Personaje p0){
		persona.remove(p0);
	}
	
	
}

