import Libs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListaEnlazada{
    
    /**Atributos de la lista*/
    private Nodo nodoActual;
     private Nodo primerNodo;
	 private Nodo ultimoNodo;
	 private int tamaño;
     
	
	public class Nodo{

     /**Atributos del nodo*/

      //  private Mp3File mp3file;
        private final String ruta;
        private String artista;
        private String titulo;
        private String album;
        private String año;
        private String genero;
        private String nombre;
        private Nodo anterior; 
        private Nodo siguiente;
        
        

        /**Constructor nodo sin parametros*/
        public Nodo(){
            this.siguiente = null;
            this.anterior = null;
            this.ruta = null;
            this.nombre = null;
        }
        
        /**Constructor de nodo con parametros*/
        public Nodo(String ruta, String nombre, String artista, String album, String año, String genero){
        
        this.siguiente = null;
        this.anterior  = null;
        this.ruta = ruta;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.año = año;
        this.genero = genero;
        
   
        }
        



        
        public Nodo obtenerSiguiente(){
            return this.siguiente;
        }
       
        public String obtenerRuta(){
         return this.ruta;
         }

        public void establecerSiguiente(Nodo siguiente){
        	this.siguiente = siguiente;
        }
        
        public void establecerAnterior(Nodo anterior){
                this.anterior = anterior;
        }

     
        
   
        public String obtenerNombre(){
          return this.nombre;
        }

        public String obtenerArtista(){
          return this.artista;
        }

        public String obtenerAlbum(){
          return this.album;
        }

        public String obtenerAño(){
           return this.año;
        }

        public String obtenerGenero(){
           return this.genero;
        }
        
        public void cambiarNombre(String nuevo){
          this.nombre= nuevo;
        }

        public void cambiarArtista(String nuevo){
          this.artista= nuevo;
        }

        public void cambiarAlbum(String nuevo){
          this.album = nuevo;
        }

        public void cambiarAño(String nuevo){
           this.año = nuevo;
        }

        public void cambiarGenero(String nuevo){
           this.genero = nuevo;
        }
    

    }
        
     public ListaEnlazada(){
        this.nodoActual = null;
        this.primerNodo = null;
        this.ultimoNodo = null;
        this.tamaño     = 0;
    }
    
     public Nodo obtenerPrimero(){
         return this.primerNodo;
     }
     
    public void agregarCancion(String ruta, String nombre, String artista, String album, String año, String genero){

       
         Nodo nuevo = new Nodo(ruta, nombre, artista, album, año, genero);
       
        if (this.primerNodo == null){

            this.primerNodo = nuevo;
            this.ultimoNodo = nuevo;
        }
        
        else{
            this.nodoActual = this.ultimoNodo; 
            this.ultimoNodo.establecerSiguiente(nuevo);
            this.ultimoNodo = nuevo;
            this.ultimoNodo.establecerAnterior(this.nodoActual);
        }
        this.tamaño++;
    } 

  /*  public void eliminarCancion(String ruta){

        Nodo nodoActual = this.primerNodo;

        for (int contador = 0; contador < this.tamaño; contador++){
            
            if(nodoActual.obtenerRuta() == ruta){

                 Nodo nodo = nodoActual.obtenerSiguiente();
                 while (nodo.obtenerSiguiente() != null){
                    nodoActual.establecerRuta(nodo.obtenerRuta());
                    nodo = nodoActual.obtenerSiguiente();
                 }
                          
             }   
         }
         this.tamaño--; 
     }
 
*/  

     public Nodo obtenerUltimo(){
         return this.ultimoNodo;
     }
     
     public int size(){
         return this.tamaño;
     }
     
     public Boolean buscarNodo(String nombreCancion){
        this.nodoActual = this.primerNodo;
        for(int contador = 0; contador!=this.tamaño; contador++){
          
          if(nombreCancion==this.nodoActual.obtenerNombre()){
            return true;
           }
           else{
           this.nodoActual=this.nodoActual.obtenerSiguiente();
           }

        }
     return false;  
     }
     public Nodo obtenerActual(){
        return this.nodoActual;
     }

}
