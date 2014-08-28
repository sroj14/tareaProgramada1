/** importando librerias*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Libs.*;
import com.mpatric.mp3agic.app.*;
import com.mpatric.mp3agic.*;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.List;

public class Interfaz extends JFrame{
   


    public static  ListaEnlazada listaCanciones = new ListaEnlazada(); 
    /**Etiquetas utilizadas*/

   
    private final JLabel cancionesTitulo = new JLabel("Lista de canciones:");
    private final JLabel datosTitulo = new JLabel ("Datos de la canción");
    private final JLabel nombreTitulo = new JLabel ("Nombre:");	
    private final JLabel artistaTitulo = new JLabel ("Artista:");
    private final JLabel albumTitulo = new JLabel ("Álbum:");
    private final JLabel generoTitulo = new JLabel ("Género:");
    private final JLabel añoTitulo = new JLabel ("Año:");


    private static JLabel nombreDato = new JLabel ("tarataraa");  
    private static JLabel artistaDato = new JLabel ("Bambucha");
    private static JLabel albumDato = new JLabel ("tiriri");
    private static JLabel generoDato = new JLabel ("Polka");
    private static JLabel añoDato= new JLabel ("1980"); 
    

    /**Campos de texto utilizados*/
    private final JTextField campoNombre = new JTextField(40);
    private final JTextField campoArtista = new JTextField(40);
    private final JTextField campoAlbum = new JTextField(40);
    private final JTextField campoGenero = new JTextField(40);
    private final JTextField campoAño = new JTextField(40);

    /**Botones utilizados*/
    private final JButton btGuardar = new JButton("Guardar");
    private final JButton btCancelar = new JButton("Cancelar");
    private final JButton btModificar = new JButton("Modificar");
    private final JButton btAgregar = new JButton("Agregar");
    private final JButton btEliminar = new JButton("Eliminar");
   
    private static JList<String> listBox;
    private DefaultListModel<String> listModel; 

    /**Constructor de interfaz*/
    public Interfaz (){
        
       setTitle("Reproductor MP3");
       setSize(1000,600);
       setLocation(0,0);
       setResizable(true);        
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       


      

       btGuardar.setPreferredSize(new Dimension(100,25));
       btCancelar.setPreferredSize(new Dimension(100,25));
       btModificar.setPreferredSize(new Dimension(100,25));
       btAgregar.setPreferredSize(new Dimension(100,25));
       btEliminar.setPreferredSize(new Dimension(100,25));

    

       datosTitulo.setForeground(Color.BLUE);
       datosTitulo.setFont(new Font("Arial", Font.BOLD, 15));
       cancionesTitulo.setForeground(Color.BLUE);
       cancionesTitulo.setFont(new Font("Arial", Font.BOLD, 15));
       nombreTitulo.setForeground(Color.BLUE);
       artistaTitulo.setForeground(Color.BLUE);
       albumTitulo.setForeground(Color.BLUE);
       generoTitulo.setForeground(Color.BLUE);
       añoTitulo.setForeground(Color.BLUE);

       btGuardar.setEnabled(false);
       btCancelar.setEnabled(false);

       campoNombre.setVisible(false);
       campoArtista.setVisible(false); 
       campoAlbum.setVisible(false); 
       campoGenero.setVisible(false); 
       campoAño.setVisible(false);

       nombreDato.setVisible(true);
       artistaDato.setVisible(true);
       albumDato.setVisible(true);
       generoDato.setVisible(true);
       añoDato.setVisible(true);


       listModel = new DefaultListModel<>();

   
       listModel.addElement("India");
       listModel.addElement("Vietnam");
       listModel.addElement("Canada");
       listModel.addElement("Denmark");
       listModel.addElement("France");
       listModel.addElement("Great Britain");
       listModel.addElement("Japan");
       listModel.addElement("USA");
       listModel.addElement("India");
       listModel.addElement("Vietnam");
       listModel.addElement("Canada");
       listModel.addElement("Denmark");
       listModel.addElement("France");
       listModel.addElement("Great Britain");
       listModel.addElement("Japan");
       listModel.addElement("USA");
       listModel.addElement("India");
       listModel.addElement("Vietnam");
      
       Container  contenedor = getContentPane();
       SpringLayout layout =  new SpringLayout();
       contenedor.setLayout(layout); 
       
       //Se crea el listbox
       listBox = new JList<>(listModel);
       listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       contenedor.add(listBox);
       JScrollPane scroll= new JScrollPane(listBox);
       scroll.setPreferredSize(new Dimension(250,540));
       
        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = listBox.getSelectedValuesList();
                    System.out.println(selectedValuesList);
                  
                    
                
                }
            }
        } );   
        
            /*if (listaCanciones.buscarNodo(listBox.getSelectedValue())==true){
       
            nombreDato.setText(listaCanciones.obtenerActual().obtenerNombre());
            artistaDato.setText(listaCanciones.obtenerActual().obtenerArtista());
            generoDato.setText(listaCanciones.obtenerActual().obtenerAlbum());
            añoDato.setText(listaCanciones.obtenerActual().obtenerGenero());
            albumDato.setText(listaCanciones.obtenerActual().obtenerAño());
            
       }    */
      

       layout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, scroll, 41, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, cancionesTitulo, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, cancionesTitulo, 7, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, datosTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, datosTitulo, 370, SpringLayout.NORTH, contenedor);
       
       layout.putConstraint(SpringLayout.WEST, nombreTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, nombreTitulo, 410, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, artistaTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, artistaTitulo, 440, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, albumTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, albumTitulo, 470, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, generoTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, generoTitulo, 500, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, añoTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, añoTitulo, 530, SpringLayout.NORTH, contenedor);


       layout.putConstraint(SpringLayout.WEST, nombreDato, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, nombreDato, 410, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, artistaDato, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, artistaDato, 440, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, albumDato, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, albumDato, 470, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, generoDato, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, generoDato, 500, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, añoDato, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, añoDato, 530, SpringLayout.NORTH, contenedor);
       
      
       layout.putConstraint(SpringLayout.WEST, campoNombre, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, campoNombre, 410, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, campoArtista, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, campoArtista, 440, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, campoAlbum, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, campoAlbum, 470, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, campoGenero, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, campoGenero, 500, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, campoAño, 350, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, campoAño, 530, SpringLayout.NORTH, contenedor);

       

       layout.putConstraint(SpringLayout.WEST, btAgregar, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btAgregar, 556, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btEliminar, 370, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btEliminar, 556, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btModificar, 475, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btModificar, 556, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btGuardar, 580, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btGuardar, 556, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btCancelar, 685, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btCancelar, 556, SpringLayout.NORTH, contenedor);

     

       contenedor.add(scroll);

       contenedor.add(cancionesTitulo);
       contenedor.add(datosTitulo);
       contenedor.add(nombreTitulo);
       contenedor.add(artistaTitulo);
       contenedor.add(albumTitulo);
       contenedor.add(generoTitulo);
       contenedor.add(añoTitulo);
       
       contenedor.add(nombreDato);
       contenedor.add(artistaDato);
       contenedor.add(albumDato);
       contenedor.add(generoDato);
       contenedor.add(añoDato);

       contenedor.add(campoNombre);
       contenedor.add(campoArtista);
       contenedor.add(campoAlbum);
       contenedor.add(campoGenero);
       contenedor.add(campoAño);

       contenedor.add(btModificar);
       contenedor.add(btGuardar);
       contenedor.add(btCancelar);
       contenedor.add(btAgregar);
       contenedor.add(btEliminar);


/**Acciones de los botones*/

       
       btEliminar.addActionListener(new ActionListener(){

       public void actionPerformed(ActionEvent evento){
    
        if(listBox.getSelectedIndex()!=-1){
           
           listModel.removeElementAt(listBox.getSelectedIndex());
        }
        else{
          JOptionPane.showMessageDialog(getContentPane(),"Elija una canción para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
       });

        
       btModificar.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evento){
          
          btGuardar.setEnabled(true);
          btCancelar.setEnabled(true);
          btModificar.setEnabled(false);
          btEliminar.setEnabled(false);
          btAgregar.setEnabled(false);
          
          campoNombre.setVisible(true);
          campoArtista.setVisible(true); 
          campoAlbum.setVisible(true); 
          campoGenero.setVisible(true); 
          campoAño.setVisible(true);

          nombreDato.setVisible(false);
          artistaDato.setVisible(false);
          albumDato.setVisible(false);
          generoDato.setVisible(false);
          añoDato.setVisible(false);
        }
       }); 

       /**Agregando evento al boton aceptar*/
       btGuardar.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evento){

          if (campoNombre.getText().isEmpty() || campoArtista.getText().isEmpty() || campoAlbum.getText().isEmpty()
               || campoGenero.getText().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(),"Debe llenar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
          }
          else{
            nombreDato.setText(campoNombre.getText());
            artistaDato.setText(campoArtista.getText());
            albumDato.setText(campoAlbum.getText());
            generoDato.setText(campoGenero.getText());
            añoDato.setText(campoAño.getText());

            campoNombre.setVisible(false);
            campoArtista.setVisible(false); 
            campoAlbum.setVisible(false); 
            campoGenero.setVisible(false);
            campoAño.setVisible(false);

            nombreDato.setVisible(true);
            artistaDato.setVisible(true);
            albumDato.setVisible(true);
            generoDato.setVisible(true);
            añoDato.setVisible(true);


            campoNombre.setText("");
            campoArtista.setText("");
            campoGenero.setText("");
            campoAlbum.setText("");
            campoAño.setText("");

            btGuardar.setEnabled(false);
            btCancelar.setEnabled(false);
            btModificar.setEnabled(true);
            btEliminar.setEnabled(true);
            btAgregar.setEnabled(true);
          }/////FALTA AGREGAR CODIGO
        }
       });


     btCancelar.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evento){
      

       campoNombre.setVisible(false);
       campoArtista.setVisible(false); 
       campoAlbum.setVisible(false); 
       campoGenero.setVisible(false); 
       campoAño.setVisible(false);

       campoNombre.setText("");
       campoArtista.setText("");
       campoGenero.setText("");
       campoAlbum.setText("");
       campoAño.setText("");

       nombreDato.setVisible(true);
       artistaDato.setVisible(true);
       albumDato.setVisible(true);
       generoDato.setVisible(true);
       añoDato.setVisible(true);

        btGuardar.setEnabled(false);
        btCancelar.setEnabled(false);
        btModificar.setEnabled(true);
        btEliminar.setEnabled(true);
        btAgregar.setEnabled(true);
        }
       });

          btAgregar.addActionListener(new ActionListener() {
          
           public void actionPerformed(ActionEvent evento) {
                        JFileChooser elegir = new JFileChooser();
                        FileNameExtensionFilter filtro = new FileNameExtensionFilter("MP3 Files", "mp3");
                        elegir.setFileFilter(filtro);
                        int opcion = elegir.showOpenDialog(btAgregar);
                   
                        String ruta=null;
                        String nombre=null;
                        if (opcion == JFileChooser.APPROVE_OPTION) {
                            ruta = elegir.getSelectedFile().getPath(); //Obtiene la ruta del archivo
                            nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
   
                        }
                            
    
                        Mp3File mp3file = null;
                        try {{ 
                        
                        mp3file = new Mp3File(elegir.getSelectedFile().getPath());
                         if (mp3file !=null && mp3file.hasId3v1Tag()) {
                        ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                        
                        nombre =  id3v1Tag.getTitle();
                        String album     = id3v1Tag.getAlbum();
                        String artista   = id3v1Tag.getArtist();
                        String año       = id3v1Tag.getYear();
                        String genero    = id3v1Tag.getGenre() + "("+id3v1Tag.getGenreDescription() + ")";
                        listaCanciones.agregarCancion(ruta, nombre, artista, album, año, genero);
                              
                        
                        nombreDato.setText(nombre);
                        artistaDato.setText(artista);
                        generoDato.setText(genero);
                        añoDato.setText(año);
                        albumDato.setText(album);
                      
                         } 
         
                        if(mp3file !=null && !mp3file.hasId3v1Tag()){
                      

                        listaCanciones.agregarCancion(ruta, nombre, "Desconocido", "Desconocido", "Desconocido", "Desconocido");
                              
                        nombreDato.setText(nombre);
                        artistaDato.setText("Desconocido");
                        generoDato.setText("Desconocido");
                        añoDato.setText("Desconocido");
                        albumDato.setText("Desconocido");
                        listaCanciones.imprimir();
                        listModel.addElement(nombre);

                                        }
                   
                      }
                      
                       } catch (UnsupportedTagException | InvalidDataException | IOException e) {
                       JOptionPane.showMessageDialog(getContentPane(),"No se pudo agregar la canción", "Error", JOptionPane.ERROR_MESSAGE);
                       }
                      }});  

}      


    
 
public static void main(String []args){
 
        Interfaz ventana = new Interfaz(); 
        ventana.setVisible(true);		

    }            
}
