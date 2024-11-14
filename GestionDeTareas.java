

package gestiondetareas;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionDeTareas{

    private List<String>Tareas;
   
    private List<String>TareasAsignadas; 

    private List<String> TareasPendientes;

    private List<String> TareasCompletadas;
    
    private List<String> TareasEnCurso;
   

    
    public GestionDeTareas() {
        
    Tareas = new ArrayList<>();
        TareasAsignadas = new ArrayList<>();
        TareasPendientes = new ArrayList<>();
        TareasCompletadas = new ArrayList<>();
        TareasEnCurso = new ArrayList<>();
    }
    
 public static void main(String [] args) {
    GestionDeTareas gestion = new GestionDeTareas();
    gestion.menu();   
 } 
  public void menu(){
     Scanner entrada = new Scanner(System.in);
        int Eleccion;
     
       while (true) {
        System.out.println("Seleccione que quiere hacer: ");
        System.out.println("1--Crear tarea");
        System.out.println("2--Asignar tareas");
        System.out.println("3--Actualizar estados de las tareas");
        System.out.println("4--Ver todas las tareas");
        System.out.println("5--Salir del programa");
        System.out.println("\n");
        Eleccion = entrada.nextInt();
     
            switch(Eleccion){
            case 1: crear();
             break;
            case 2: asignar();
             break; 
            case 3: actualizar(); 
             break;
             case 4: vertareas();
              break;
            case 5:
                    System.out.println("Saliendo del programa...");
                    entrada.close();
                    return;
            default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");    
        }
      }
    }    
   public void crear(){                                      // Opcion de crear tarea
       String titulo,descripcion,fechalimite,T;
        Scanner entrada = new Scanner(System.in);            

        System.out.println("Ingrese el titulo de la tarea:");
        titulo = entrada.next();
        System.out.println("Ingrese una descripcion de la tarea");
        descripcion = entrada.next();
        System.out.println("Ingrese la fecha limite");
        fechalimite = entrada.next();
        T = "Titulo: "+titulo+" // Descripcion: "+ descripcion +" //Fecha Limite:  "+ fechalimite;
        
    Tareas.add(T);
        
        String tareasSin = "Tareas Sin Asignar";
        
         escribirListaEnArchivo(Tareas,tareasSin);
} 
   public void escribirListaEnArchivo(List<String> Tareas, String tareasSin) {    //Escribir en el txt de tareas sin asignar
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(tareasSin, true))) { 
            for (String tarea : Tareas) {
                escribir.write(tarea);
                escribir.newLine(); 
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
      System.out.println("\n");
    }
    
     public void asignar(){                                           //Opcion de asignar
       int indice;
       String Usuario;
       Scanner entrada = new Scanner(System.in); 
       
       System.out.println("Tareas disponibles para asignar:");

       for (int i = 0; i <Tareas.size(); i++) {
        System.out.println(i + ": " +Tareas.get(i));
        System.out.println("\n");
    }
       
       System.out.println("Ingrese el usuario al cual va asignar la tarea");
       Usuario= entrada.next();
       
      
       System.out.println("Ingrese que tarea quiere asignar");
       indice = entrada.nextInt();

       if (indice >= 0 && indice <Tareas.size()) {
        String elemento =Tareas.get(indice);
        
        TareasAsignadas.add(elemento + "// Asignada a :"+ Usuario);
        Tareas.remove(indice);
        actualizarArchivo();
        String tareasAsing = "Tareas Asignadas";
        escribirListaEnArchivo(TareasAsignadas,tareasAsing);
       }
    }
    
    public void actualizarArchivo() {
        String tareasSin = "Tareas Sin Asignar.txt";
        
        escribirListaEnArchivo(Tareas, tareasSin);
    }
    public void actualizar(){                                       //Opcion de Actualizar
     int org;
     int estado;
     Scanner entrada = new Scanner(System.in); 
       
     System.out.println("Tareas:");

     for (int i = 0; i < TareasAsignadas.size(); i++) {
      System.out.println(i + ": " + TareasAsignadas.get(i));
      System.out.println("\n");

    }
    System.out.println("Ingrese que tarea quiere modificar:");
    org = entrada.nextInt();

    System.out.println("Ingrese en que estado esta la tarea seleccionada:");
    System.out.println("1--Peniente");
    System.out.println("2--En curso");
    System.out.println("3--Completada");
    estado = entrada.nextInt();
       
    switch (estado) {
        case 1:if (org >= 0 && org < TareasAsignadas.size()) {
            String elemento = TareasAsignadas.get(org);
            
        TareasPendientes.add(elemento + "// Estado: Pendiente");
            actualizarArchivo();
            String tareasPen = "Tareas Pendientes";
            escribirListaEnArchivo(TareasPendientes,tareasPen);  
        }
             break;
       
       case 2:if (org >= 0 && org < TareasAsignadas.size()) {
        String elemento = TareasAsignadas.get(org);
        
        TareasEnCurso.add(elemento + "// Estado: En curso");
        actualizarArchivo();
        String tareasEnCurso = "Tareas en curso";
        escribirListaEnArchivo(TareasEnCurso,tareasEnCurso);
    }
             break;
        case 3:if (org >= 0 && org < TareasAsignadas.size()) {
            String elemento = TareasAsignadas.get(org);
            
            TareasCompletadas.add(elemento + "// Estado: Completada");
            actualizarArchivo();
            String tareasComp = "Tareas Completadas";
            escribirListaEnArchivo(TareasCompletadas,tareasComp);
        }
           break;
        default:System.out.println("Opcion no valida porfavor seleccione una que si sea valida.");
    }
  }
  public void vertareas(){
     System.out.println("TAREAS SIN ASIGNAR:");
     System.out.println(Tareas);
     System.out.println("\n");
     
     System.out.println("TAREAS ASIGNADAS:");
     System.out.println(TareasAsignadas);
     System.out.println("\n");

     System.out.println("TAREAS PENDIENTES:");
     System.out.println(TareasPendientes);
     System.out.println("\n");

     System.out.println("TAREAS EN CURSO:");
     System.out.println(TareasEnCurso);
     System.out.println("\n");

     System.out.println("TAREAS COMPLETADAS:");
     System.out.println(TareasCompletadas);
     System.out.println("\n");
  }
}


