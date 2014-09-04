import Libs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ListaEnlazada{
    
    /**Atributos de la lista*/
     private Nodo nodoActual;
     private Nodo primerNodo;
	 private Nodo ultimoNodo;
	 private int tamaño;
     
	public class Nodo{
        /**Atributos del nodo*/
        private final String ruta;
        private String artista;
        private String titulo;
        private String album;
        private String año;
        private String genero;
        private String nombre;
        private Nodo anterior; 
        private Nodo siguiente;
        private BufferedImage foto;
        /**Constructor nodo sin parametros*/
        public Nodo(){
            this.siguiente = null;
            this.anterior = null;
            this.ruta = null;
            this.nombre = null;
            this.foto = null;
        }
        
        /**Constructor de nodo con parametros
        *recibe la ruta, nombre, artista, album, año, genero, imagen del album*/ 
        public Nodo(String ruta, String nombre, String artista, String album, String año, String genero, BufferedImage foto){
        
        this.siguiente = null;
        this.anterior  = null;
        this.ruta = ruta;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.año = año;
        this.genero = genero;
        this.foto = foto;
        }
                 /**Metodos Principales de la clase nodo*/

        /** Obtiene el nodo anterior*/
        public Nodo obtenerAnterior(){
            return this.anterior;
         }
        /** obtiene el nodo siguiente*/
        public Nodo obtenerSiguiente(){
            return this.siguiente;
        }
        /** enlaza el nodo con el siguiente*/
        public void establecerSiguiente(Nodo siguiente){
            this.siguiente = siguiente;
        }
        /** enlaza al nodo con el anterior*/
        public void establecerAnterior(Nodo anterior){
                this.anterior = anterior;
        }
        /**Metodos para obtener lo datos de la cancion*/
        /**Obtiene la ruta de la cancion*/
        public String obtenerRuta(){
         return this.ruta;
         }
   
        /** obtiene el nombre de la cancion*/
        public String obtenerNombre(){
          return this.nombre;
        }
        /** Obtiene el artista de la cancion*/
        public String obtenerArtista(){
          return this.artista;
        }
        /** Obtiene el album de la cancion*/
        public String obtenerAlbum(){
          return this.album;
        }
        /** Obtiene el año de la cancion*/
        public String obtenerAño(){
           return this.año;
        }
        /**Obtiene el genero de la cancion*/
        public String obtenerGenero(){
           return this.genero;
        }
        /** Metodos para modificar los datos de la cancion*/
                      /**Modifica el nombre*/
        public void cambiarNombre(String nuevo){
          this.nombre= nuevo;
        }
        /**Modifica el artista*/
        public void cambiarArtista(String nuevo){
          this.artista= nuevo;
        }
        /**Modifica el album*/
        public void cambiarAlbum(String nuevo){
          this.album = nuevo;
        }
        /**Modifica el año*/
        public void cambiarAño(String nuevo){
           this.año = nuevo;
        }
        /**Modifica el Genero*/
        public void cambiarGenero(String nuevo){
           this.genero = nuevo;
        }}
     /**Contructor de la lista*/   
     public ListaEnlazada(){
        this.nodoActual = null;
        this.primerNodo = null;
        this.ultimoNodo = null;
        this.tamaño     = 0;
    }
     /**Metodos Principales de la lista Enlazada*/
     /**Obtiene el primer elemento de la lista*/ 
     public Nodo obtenerPrimero(){
         return this.primerNodo;}

     /** Obtiene el nodo Actual*/
     public Nodo obtenerActual(){
        return this.nodoActual;}
 
     /**Establece el nodo como actual*/
     public void establecerActual(Nodo nuevo){
        this.nodoActual = nuevo;}   

     /** Obtiene el ultimo nodo*/
     public Nodo obtenerUltimo(){
         return this.ultimoNodo;}

     /** Obtiene el tamaño de la lista*/
     public int obtenerTamaño(){
         return this.tamaño;}
     
     public BufferedImage obtenerFoto(){
        return this.nodoActual.foto;}

    /** Agrega una Cancion a la lista*/
    public void agregarCancion(String ruta, String nombre, String artista, String album, String año, String genero, BufferedImage foto){
        /**Se crea el nuevo nodo*/
        Nodo nuevo = new Nodo(ruta, nombre, artista, album, año, genero,foto);
        /**Verifica si la lista Contiene elemento*/
        if (this.primerNodo == null){
            this.primerNodo = nuevo;
            this.ultimoNodo = nuevo;
            this.nodoActual = nuevo;}
        else{ /** Sino se agrega la canion al final de la lita*/
            this.nodoActual = this.ultimoNodo; 
            this.ultimoNodo.establecerSiguiente(nuevo);
            this.ultimoNodo = nuevo;
            this.ultimoNodo.establecerAnterior(this.nodoActual);
            this.nodoActual = this.ultimoNodo;}
            this.tamaño++;} 

    /**Elimina una cancion,recibe el indice de la canion a eliminar*/
    public void eliminarCancion(int indice){
         this.nodoActual = this.primerNodo;/*Establece el nodo actual como primero*/
        if (this.obtenerTamaño() == 1){/**verifia si hay elementos en la lista*/
            this.primerNodo = null;
            this.ultimoNodo = null;
            this.nodoActual = null;}
        else{ /**Sino realiza un ciclo para eliminar el eleento en el indice*/
         for(int cont=0;cont<this.tamaño;cont++){
         	if (cont == this.tamaño-1 && cont == indice){
         		this.ultimoNodo = this.nodoActual.obtenerAnterior();
         		break;}
         	if (cont == 0 && cont == indice){
         		this.primerNodo = this.nodoActual.obtenerSiguiente();
         		break;}
            if (cont == indice){
             this.nodoActual.obtenerAnterior().establecerSiguiente(this.nodoActual.obtenerSiguiente());
             this.nodoActual.obtenerSiguiente().establecerAnterior(this.nodoActual.obtenerAnterior());
             this.nodoActual = this.nodoActual.obtenerSiguiente();           
             break;} 
             this.nodoActual = this.nodoActual.obtenerSiguiente();}}
             this.tamaño--;}

     /** Metodo para buscar una cancion, recibe por parametros el nombre*/
     public Boolean buscarNodo(String nombreCancion){

        this.nodoActual = this.primerNodo;/** Establece el actual como primero*/
        for(int contador = 0; contador!=this.tamaño; contador++){/** Realiza el ciclo de busqueda*/
          if(nombreCancion==this.nodoActual.obtenerNombre()){
            return true;/** Retorna true si se encuentra la cancion en lista*/
           }
           else{
           this.nodoActual=this.nodoActual.obtenerSiguiente();}}             
        return false;}/** retorna false si no e encuentra la cancion*/
     
     /** Metodos empleados para la busqueda avanzada*/
     public Boolean buscarCancionNombre(String nombre){
        while(this.nodoActual != null){
            if (this.nodoActual.obtenerNombre().equals(nombre)){
                return true;}
             this.nodoActual=this.nodoActual.obtenerSiguiente();
             }return false;} 

     public Boolean buscarCancionArtista(String artista){
        while(this.nodoActual != null){
            if (this.nodoActual.obtenerArtista().equals(artista)){
                return true;}
             this.nodoActual=this.nodoActual.obtenerSiguiente();
             }return false;} 

     public Boolean buscarCancionGenero(String genero){
        while(this.nodoActual != null){
            if (this.nodoActual.obtenerGenero().equals(genero)){
                return true;}
             this.nodoActual=this.nodoActual.obtenerSiguiente();
             }return false;} 

     public Boolean buscarCancionAlbum(String album){
        while(this.nodoActual != null){
            if (this.nodoActual.obtenerAlbum().equals(album)){
                return true;}
             this.nodoActual=this.nodoActual.obtenerSiguiente();
             }return false;} 

}
