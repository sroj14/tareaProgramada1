          /** importando librerias principales del reproductor*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.mpatric.mp3agic.app.*; /** libreria para obtener metadatos de la cancion*/
import com.mpatric.mp3agic.*;
import java.io.IOException; /** libreria para trabajar con entradas y salidad*/ 
import javax.swing.filechooser.FileNameExtensionFilter; /**util para eleccionar ficheros de la computadora*/ 
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Interfaz extends JFrame{

	/** ATRIBUTOS*/ 
   
    public static  ListaEnlazada listaCanciones = new ListaEnlazada(); 
   
    /**Etiquetas utilizadas*/

    private ReproducirMP3 cancion = new ReproducirMP3(); /**Iniciando un objeto de la clase reproductor MP3*/
    private final JLabel busquedaTitulo = new JLabel("Busqueda:");
    private final JLabel cancionesTitulo = new JLabel("Lista de canciones:");
    private final JLabel datosTitulo = new JLabel ("Datos de la canción");
    private final JLabel nombreTitulo = new JLabel ("Nombre:");	
    private final JLabel artistaTitulo = new JLabel ("Artista:");
    private final JLabel albumTitulo = new JLabel ("Álbum:");
    private final JLabel generoTitulo = new JLabel ("Género:");
    private final JLabel añoTitulo = new JLabel ("Año:");  
    private static JLabel nombreDato = new JLabel ("");  
    private static JLabel artistaDato = new JLabel ("");
    private static JLabel albumDato = new JLabel ("");
    private static JLabel generoDato = new JLabel ("");
    private static JLabel añoDato= new JLabel (""); 
    private static JLabel imagen = new JLabel();
    /**Campos de texto utilizados*/

    private final JTextField campoBusqueda = new JTextField(20);
    private final JTextField campoNombre = new JTextField(40);
    private final JTextField campoArtista = new JTextField(40);
    private final JTextField campoAlbum = new JTextField(40);
    private final JTextField campoGenero = new JTextField(40);
    private final JTextField campoAño = new JTextField(40);

    /**Botones utilizados*/
    private final JButton btBuscar =  new JButton("Buscar");
    private final JButton btGuardar = new JButton("Guardar");
    private final JButton btCancelar = new JButton("Cancelar");
    private final JButton btModificar = new JButton("Modificar");
    private final JButton btAgregar = new JButton("Agregar");
    private final JButton btEliminar = new JButton("Eliminar");
    private final JButton btPlay = new JButton("Play");
    private final JButton btStop = new JButton("Stop");
    private final JButton btPausa = new JButton("Pausa");
    private final JButton btContinuar = new JButton("Continuar");
    private final JButton btNext = new JButton("Next>>");
    private final JButton btPrevius = new JButton("<<Previus");
 	private JComboBox comboBusqueda; /** Presenta las opciones para realizar la busqueda avanzada "Nombre" "Titulo" "Artista" "Genero" */
 
    private static JList<String> listBox; /** Lisbox que contiene las canciones agregadas*/
    private static JList<String> listBoxBusqueda; /** Lisbox que contiene las cancione en la busqueda avanzada*/
    private DefaultListModel<String> listModel; 
    private DefaultListModel<String> modeloBusqueda; 

    /**Constructor de interfaz*/

    public Interfaz (){
        
       setTitle("Reproductor MP3");
       setSize(1220,700);
       setLocation(0,0);
       setResizable(true);        
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       /**BOTONES*/
       imagen.setPreferredSize(new Dimension(520,320));
       btGuardar.setPreferredSize(new Dimension(100,25));
       btCancelar.setPreferredSize(new Dimension(100,25));
       btModificar.setPreferredSize(new Dimension(100,25));
       btAgregar.setPreferredSize(new Dimension(100,25));
       btEliminar.setPreferredSize(new Dimension(100,25));
       btBuscar.setPreferredSize(new Dimension(100,25));
       btPlay.setPreferredSize(new Dimension(100,25));
       btStop.setPreferredSize(new Dimension(100,25));
       btPausa.setPreferredSize(new Dimension(100,25));
       btContinuar.setPreferredSize(new Dimension(100,25)); 
       btBuscar.setEnabled(true);
       btPrevius.setEnabled(true);
       btNext.setEnabled(true);
       btGuardar.setEnabled(false);
       btCancelar.setEnabled(false);

        /**ETIQUETAS**/

       datosTitulo.setForeground(Color.BLUE);
       datosTitulo.setFont(new Font("Arial", Font.BOLD, 15));
       cancionesTitulo.setForeground(Color.BLUE);
       cancionesTitulo.setFont(new Font("Arial", Font.BOLD, 15));
       nombreTitulo.setForeground(Color.BLUE);
       artistaTitulo.setForeground(Color.BLUE);
       albumTitulo.setForeground(Color.BLUE);
       generoTitulo.setForeground(Color.BLUE);
       añoTitulo.setForeground(Color.BLUE);
       busquedaTitulo.setForeground(Color.BLUE);

       /**CAMPOS DE TEXTO*/

       campoNombre.setVisible(false);
       campoArtista.setVisible(false); 
       campoAlbum.setVisible(false); 
       campoGenero.setVisible(false); 
       campoAño.setVisible(false);
       campoBusqueda.setVisible(true);
       nombreDato.setVisible(true);
       artistaDato.setVisible(true);
       albumDato.setVisible(true);
       generoDato.setVisible(true);
       añoDato.setVisible(true);
       btContinuar.setVisible(false);

      /**Lisbox: implementado para presentar las canciones de la busqueda avanzada*/ 

      String[] opciones = { "Nombre", "Artista", "Album", "Genero" }; 
      JComboBox<String> comboBusqueda = new JComboBox<>(opciones);
      comboBusqueda.setPreferredSize(new Dimension(120,25));
     
                                   /** EVENTOS DE LOS BOTONES*/

        /** btAgregar, Permite Agregar una cancion a la lista de reproduccion*/
        btAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		/** se habre un buscador de archivos, para poder seleccionar un archivo MP3 en nuestro computador*/
        		JFileChooser elegir = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("MP3 Files", "mp3"); 
                elegir.setFileFilter(filtro);
                int opcion = elegir.showOpenDialog(btAgregar);
                String ruta=null;
                String nombre=null;
                if(opcion == JFileChooser.APPROVE_OPTION) {
                    ruta = elegir.getSelectedFile().getPath(); /**Obtiene la ruta del archivo*/
                    nombre = elegir.getSelectedFile().getName(); /**obtiene nombre del archivo*/
                    Mp3File mp3file = null;
                    try {{ 
                    	/**Se obtiene los metadatos de la cancion agregada*/
                        mp3file = new Mp3File(elegir.getSelectedFile().getPath());
                            if (mp3file != null && mp3file.hasId3v1Tag()) {
                            	ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                                String album     = id3v1Tag.getAlbum(); /** Obtiene el album*/
                                String artista   = id3v1Tag.getArtist(); /** Obtiene el Artista*/
                                String año       = id3v1Tag.getYear(); /** Obtiene el Año*/
                                String genero    = id3v1Tag.getGenre() + "("+id3v1Tag.getGenreDescription() + ")"; /** Obtiene el Genero*/
                                ID3v2 id3v2tag   = mp3file.getId3v2Tag();
                                System.out.println(genero);
                                System.out.println(año);              
                                byte[] imageData = id3v2tag.getAlbumImage();/**Obtiene la foto del album*/
                                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));/** Se crea la imagen para almacenarla en la lista*/
                                listaCanciones.agregarCancion(ruta, nombre, artista, album, año, genero, img); /* Se agrega la cancion a la lista de reproduccion*/
                                /**Presenta los datos de la cancion en la interfaz*/
                                nombreDato.setText(nombre);
                                artistaDato.setText(artista);
                                generoDato.setText(genero);
                                añoDato.setText(año);
                                albumDato.setText(album);
                                listModel.addElement(listaCanciones.obtenerUltimo().obtenerNombre());
                         } 
                         /** En caso de que la cancion no contenga un dato se agrega como desconocido*/
                        if(mp3file !=null && !mp3file.hasId3v1Tag()){
                        	BufferedImage img= ImageIO.read(new File("advertencia1.png"));
                        	listaCanciones.agregarCancion(ruta, nombre, "Desconocido", "Desconocido", "Desconocido", "Desconocido", img);
      
                            nombreDato.setText(nombre);
                            artistaDato.setText("Desconocido");
                            generoDato.setText("Desconocido");
                            añoDato.setText("Desconocido");
                            albumDato.setText("Desconocido");
                            listModel.addElement(listaCanciones.obtenerUltimo().obtenerNombre());
                            imagen.setIcon(new ImageIcon(img));
                            }}} catch (UnsupportedTagException | InvalidDataException | IOException e) {
                            	JOptionPane.showMessageDialog(getContentPane(),"No se pudo agregar la canción", "Error", JOptionPane.ERROR_MESSAGE);
                            }}}});     

       /** btEliminar, elimina el elemento seleccionado de la listBox de la lista de reproduccion*/
       btEliminar.addActionListener(new ActionListener(){
       	public void actionPerformed(ActionEvent evento){
       		if(listBox.getSelectedIndex()!=-1){
       			listaCanciones.eliminarCancion(listBox.getSelectedIndex());/** Se invoca el metodo de la lista para eliminar la cancion de la misma*/ 
        	    listModel.removeElementAt(listBox.getSelectedIndex());}	/** Se elimina la cancion del listBox*/
            else{
               JOptionPane.showMessageDialog(getContentPane(),"Elija una canción para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }}});  
    
       /**btModificar, Modifica los metadatos de los canciones en la lista de reproduccion*/
       btModificar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent evento){

          if (listBox.getSelectedIndex()!=-1){
          	/** Se hace visible el boton guardar y se deshabilitan los demas botones.
          	 *Los campos de texto se habilitan para poder modificar los datos*/
              btGuardar.setEnabled(true);
              btCancelar.setEnabled(true);
              btModificar.setEnabled(false);
              btEliminar.setEnabled(false);
              btAgregar.setEnabled(false);
              btBuscar.setEnabled(false);
              campoNombre.setVisible(true);
              campoArtista.setVisible(true); 
              campoAlbum.setVisible(true); 
              campoGenero.setVisible(true); 
              campoAño.setVisible(true);
              campoNombre.setText(nombreDato.getText());
              campoArtista.setText(artistaDato.getText());
              campoGenero.setText(generoDato.getText());
              campoAlbum.setText(albumDato.getText());
              campoAño.setText(añoDato.getText());
              nombreDato.setVisible(false);  
              artistaDato.setVisible(false);
              albumDato.setVisible(false);
              generoDato.setVisible(false);
              añoDato.setVisible(false);}

          else{JOptionPane.showMessageDialog(getContentPane(),"Seleccione una canción para modificarla", "Error", JOptionPane.ERROR_MESSAGE);
         }}}); 

       /**btGuardar, permite guardar los datos que se han modificado*/
       btGuardar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent evento){
        	/** Se verifica que no esten campos de texto vacios*/
        	if (campoNombre.getText().isEmpty() || campoArtista.getText().isEmpty() || campoAlbum.getText().isEmpty()
            || campoGenero.getText().isEmpty()){
        		JOptionPane.showMessageDialog(getContentPane(),"Debe llenar todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);}
        	else{ 
        		listaCanciones.buscarNodo(listBox.getSelectedValuesList().get(0));
        		BufferedImage foto = listaCanciones.obtenerFoto();
                listaCanciones.agregarCancion( listaCanciones.obtenerActual().obtenerRuta(),campoNombre.getText(), campoArtista.getText(), campoAlbum.getText(), campoAño.getText(), campoGenero.getText(),foto);
                listaCanciones.eliminarCancion(listBox.getSelectedIndex());
                nombreDato.setText(campoNombre.getText());
                artistaDato.setText(campoArtista.getText());
                albumDato.setText(campoAlbum.getText());
                generoDato.setText(campoGenero.getText());
                añoDato.setText(campoAño.getText());
                listModel.addElement(listaCanciones.obtenerUltimo().obtenerNombre());
                listModel.removeElementAt(listBox.getSelectedIndex());
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
                btBuscar.setEnabled(true);
          }}});

     /**btCancelar, elimina los datos agregados a los campos de texto*/
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
            btBuscar.setEnabled(true);
         }});

       /**btBuscar permite realizar la busqueda avanzada */
       btBuscar.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent evento){	
       	     DefaultListModel<String> modeloBusqueda = new DefaultListModel<>(); 
       	     listBoxBusqueda.setModel(modeloBusqueda);
       	     int cont = 0; 
       	     /** Se implementaron cuatro tipo de busqueda por "Nombre" "Album" "Genero" "Artista" */
       	                                 /*BUSQUEDA POR NOMBRE*/
       	      if (comboBusqueda.getSelectedItem().toString() == "Nombre"){ 
			        listaCanciones.establecerActual(listaCanciones.obtenerPrimero());/** Asigno el primer nodo de la listaCanciones como actual*/
                    /** Ciclo para realizar la Busqueda*/
                    while(listaCanciones.obtenerTamaño() > cont){ 
                    	/** Se invoca el metodo listaCanciones.buscarCancionNombre("recibe el nombre ingresado en el campo de texto")
                    	 *busca la cancion con el nombre ingresado, retorna true si se encuentra la cancion sino retorna false 
                    	 *en caso de retornar true, establece ese nodo como actual*/
                         if (listaCanciones.buscarCancionNombre(campoBusqueda.getText())== true){ 
                            modeloBusqueda.addElement(listaCanciones.obtenerActual().obtenerNombre()); /* Se agrega el elemento encontrado al listBox*/
                            /** Se establece el nodo siguiente del actual, como actual
                              * esto se realiza para continuar con el ciclo, debido ha que puede haber dos o mas cancione con el mismo nombre*/
                            listaCanciones.establecerActual(listaCanciones.obtenerActual().obtenerSiguiente());}
                            else{break;}
                            cont ++;}}

                /** Se realiza lo mismo para las demas busquedas avanzadas */ 
                                         /*BUSQUEDA POR ARTISTA*/
       	      if (comboBusqueda.getSelectedItem().toString() == "Artista"){
			    listaCanciones.establecerActual(listaCanciones.obtenerPrimero());
                    while(listaCanciones.obtenerTamaño() > cont){
                         if (listaCanciones.buscarCancionArtista(campoBusqueda.getText())== true){
                               modeloBusqueda.addElement(listaCanciones.obtenerActual().obtenerNombre());
                               listaCanciones.establecerActual(listaCanciones.obtenerActual().obtenerSiguiente());}
                               else{break;}
                               cont ++;}}
                                          /*BUSQUEDA POR GENERO*/
       	      if (comboBusqueda.getSelectedItem().toString() == "Genero"){
			    listaCanciones.establecerActual(listaCanciones.obtenerPrimero());
                        while(listaCanciones.obtenerTamaño() > cont){
                         if (listaCanciones.buscarCancionGenero(campoBusqueda.getText())== true){
                               modeloBusqueda.addElement(listaCanciones.obtenerActual().obtenerNombre());
                               listaCanciones.establecerActual(listaCanciones.obtenerActual().obtenerSiguiente());
                               }else{break;}
                               cont ++;}}
                                           /*BUSQUEDA POR ALBUM*/
       	      if (comboBusqueda.getSelectedItem().toString() == "Album"){
			    listaCanciones.establecerActual(listaCanciones.obtenerPrimero());
                        while(listaCanciones.obtenerTamaño() > cont){
                         if (listaCanciones.buscarCancionAlbum(campoBusqueda.getText())== true){
                               modeloBusqueda.addElement(listaCanciones.obtenerActual().obtenerNombre());
                               listaCanciones.establecerActual(listaCanciones.obtenerActual().obtenerSiguiente());}
                               else{break;}
                               cont ++;}}}});

                            /** Botones Relacionados con la reproduccion de Canciones "Play" "Stop" "Continuar" "Previus" "Next"*/
        /** btPLAY, permite reproducir la cancion*/
        btPlay.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent evento){
        		if(listBox.getSelectedIndex()!= -1){ /**Se verifica que se seleccione una cancion en el listBox*/
        			if (listaCanciones.buscarNodo(listBox.getSelectedValuesList().get(0)) == true)
        				try{
       	  		        cancion.AbrirFichero(listaCanciones.obtenerActual().obtenerRuta()); /** Se abre el archivo*/
       	  		        cancion.Play(); /**Metodo en la clase Reproductor MP3 que permite reproducir la cancion*/
       	  		        } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        }}
                 else{
       			    if(listBoxBusqueda.getSelectedIndex()!= -1){ 
        			    if (listaCanciones.buscarNodo(listBoxBusqueda.getSelectedValuesList().get(0)) == true)
        				try{
       	  		        cancion.AbrirFichero(listaCanciones.obtenerActual().obtenerRuta()); /** Se abre el archivo*/
       	  		        cancion.Play(); /**Metodo en la clase Reproductor MP3 que permite reproducir la cancion*/
       	  		        } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        }}                	
                 }}});

       /** btStop, Permite detener la cancion que actualmente se esta reproduciendo*/
       btStop.addActionListener(new ActionListener(){
       	public void actionPerformed(ActionEvent evento){
       		try {
       		cancion.Stop();
       		}catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            }}});

       /** btPausa, Detiene la cancion 
        *pero a diferencia del boton Stop, este metodo puede continuar con la reproduccion de la cancion en el momento exacto donde se detuvo*/
       btPausa.addActionListener(new ActionListener(){
       	public void actionPerformed(ActionEvent evento){
       		try {
       		cancion.Pausa();
       		btContinuar.setVisible(true);
       		btPausa.setVisible(false);
       		} catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            }}});

        /** btContinuar, permite continuar con la reproduccion de la cancion, luego de que fuera puesta en pausa*/
        btContinuar.addActionListener(new ActionListener(){
       	public void actionPerformed(ActionEvent evento){
       		try {
       		cancion.Continuar();
       		btContinuar.setVisible(false);
       		btPausa.setVisible(true);
       		} catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            }}});

        /**btNext, realiza la busqueda de la siguiente cancion en cola y la reproduce*/
        btNext.addActionListener(new ActionListener(){
       	public void actionPerformed(ActionEvent evento){
       		try {
            cancion.AbrirFichero(listaCanciones.obtenerActual().obtenerSiguiente().obtenerRuta());
            listaCanciones.establecerActual(listaCanciones.obtenerActual().obtenerSiguiente());
            cancion.Play();
       		} catch (Exception ex) {
            JOptionPane.showMessageDialog(getContentPane(),"Ultima Cancion en lista de reproduccion", "Error", JOptionPane.ERROR_MESSAGE);
            }}});

        /**btNext, realiza la busqueda de la cancion anterior y la reproduce*/
       	btPrevius.addActionListener(new ActionListener(){
       	public void actionPerformed(ActionEvent evento){
       		try {
            cancion.AbrirFichero(listaCanciones.obtenerActual().obtenerAnterior().obtenerRuta());
            listaCanciones.establecerActual(listaCanciones.obtenerActual().obtenerAnterior());
            cancion.Play();
       		} catch (Exception ex) {
            JOptionPane.showMessageDialog(getContentPane(),"Esta es la Primera Cancion ", "Error", JOptionPane.ERROR_MESSAGE);
            }}});

        /** Se inicializa el contenedor de botones, campos de texto, etiquetas,etc.*/
       Container  contenedor = getContentPane();
       SpringLayout layout =  new SpringLayout();
       contenedor.setLayout(layout); 

       /** Lisbox que contiene la lista de las canciones agregadas*/
       listModel = new DefaultListModel<>();
       listBox = new JList<>(listModel);
       listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       contenedor.add(listBox);
       JScrollPane scroll= new JScrollPane(listBox);
       scroll.setPreferredSize(new Dimension(250,540));
       /** Metodo que muestra los datos de la cancion selecciona en el lisbox*/
       listBox.addListSelectionListener(new ListSelectionListener() {
           public void valueChanged(ListSelectionEvent e) {             
                if (listBox.getSelectedIndex()!=-1){
                if (!e.getValueIsAdjusting()) {
                    if (listaCanciones.buscarNodo(listBox.getSelectedValuesList().get(0)) == true){
                        nombreDato.setText(listaCanciones.obtenerActual().obtenerNombre());
                        artistaDato.setText(listaCanciones.obtenerActual().obtenerArtista());
                        generoDato.setText(listaCanciones.obtenerActual().obtenerGenero());
                        añoDato.setText(listaCanciones.obtenerActual().obtenerAño());
                        BufferedImage img = listaCanciones.obtenerFoto();
                        imagen.setIcon(new ImageIcon(img));
                        albumDato.setText(listaCanciones.obtenerActual().obtenerAlbum());
                    }else{System.out.println("noooo");}}}}});   
        
        /** Se crea el listbox que muestra las canciones encontradas en la busqueda avanzada*/ 
        modeloBusqueda = new DefaultListModel<>();
       listBoxBusqueda = new JList<>(modeloBusqueda);
       listBoxBusqueda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       contenedor.add(listBoxBusqueda);
       JScrollPane scrollBusqueda = new JScrollPane(listBoxBusqueda);
       scrollBusqueda.setPreferredSize(new Dimension(250,515));
       listBoxBusqueda.addListSelectionListener(new ListSelectionListener() {
        /** Metodo que muestra los datos de la cancion selecciona en el lisbox*/          
        public void valueChanged(ListSelectionEvent e) {
                if (listBoxBusqueda.getSelectedIndex()!=-1){
                     if (!e.getValueIsAdjusting()) {
                        if (listaCanciones.buscarNodo(listBoxBusqueda.getSelectedValuesList().get(0)) == true){
                             nombreDato.setText(listBoxBusqueda.getSelectedValuesList().get(0));
                             artistaDato.setText(listaCanciones.obtenerActual().obtenerArtista());
                             generoDato.setText(listaCanciones.obtenerActual().obtenerGenero());
                             añoDato.setText(listaCanciones.obtenerActual().obtenerAño());
                             albumDato.setText(listaCanciones.obtenerActual().obtenerAlbum());
                             BufferedImage ima = listaCanciones.obtenerFoto();
                             imagen.setIcon(new ImageIcon(ima));
                    }else{System.out.println("noooo");}}}}});   

         /** Se establece las posiciones de los botones, campos de texto, etiquetas,etc.*/ 
       layout.putConstraint(SpringLayout.WEST, imagen, 320, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, imagen, 30, SpringLayout.NORTH, contenedor);
             
       layout.putConstraint(SpringLayout.WEST, comboBusqueda, 960, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, comboBusqueda, 47, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, scroll, 41, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, scrollBusqueda, 960, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, scrollBusqueda, 75, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, busquedaTitulo, 960 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, busquedaTitulo, 10 , SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, cancionesTitulo, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, cancionesTitulo, 7, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, datosTitulo, 265, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, datosTitulo, 390, SpringLayout.NORTH, contenedor);
       
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

       layout.putConstraint(SpringLayout.WEST, campoBusqueda, 960 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, campoBusqueda, 27, SpringLayout.NORTH, contenedor);

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

       layout.putConstraint(SpringLayout.WEST, btBuscar, 1082, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btBuscar, 47, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btPlay, 550, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btPlay, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btStop, 650, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btStop, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btPausa, 450, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btPausa, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btContinuar, 450, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btContinuar, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btNext, 750, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btNext, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btPrevius, 344, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btPrevius, 350, SpringLayout.NORTH, contenedor);

        /** Se agregan al contenedor los botones, campos de texto, etiquetas,etc.*/
       contenedor.add(scroll);
       contenedor.add(scrollBusqueda);
       contenedor.add(cancionesTitulo);
       contenedor.add(comboBusqueda);
       contenedor.add(datosTitulo);
       contenedor.add(nombreTitulo);
       contenedor.add(artistaTitulo);
       contenedor.add(albumTitulo);
       contenedor.add(generoTitulo);
       contenedor.add(añoTitulo);
       contenedor.add(busquedaTitulo);
       contenedor.add(nombreDato);
       contenedor.add(artistaDato);
       contenedor.add(albumDato);
       contenedor.add(imagen);
       contenedor.add(generoDato);
       contenedor.add(añoDato);
       contenedor.add(campoNombre);
       contenedor.add(campoArtista);
       contenedor.add(campoAlbum);
       contenedor.add(campoGenero);
       contenedor.add(campoAño);
       contenedor.add(campoBusqueda);
       contenedor.add(btModificar);
       contenedor.add(btGuardar);
       contenedor.add(btCancelar);
       contenedor.add(btAgregar);
       contenedor.add(btEliminar);
       contenedor.add(btBuscar);
       contenedor.add(btPlay);
       contenedor.add(btStop);
       contenedor.add(btPausa);
       contenedor.add(btContinuar);
       contenedor.add(btPrevius);
       contenedor.add(btNext);

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
        /**Accionar los botones*/
        btGuardar.setEnabled(false);
        btCancelar.setEnabled(false);
        btModificar.setEnabled(true);
        btEliminar.setEnabled(true);
        btAgregar.setEnabled(true);
        btBuscar.setEnabled(true);
       }
       
      public static void main(String[] args){
        Interfaz ventana = new Interfaz(); 
        ventana.setVisible(true);		
    }}


