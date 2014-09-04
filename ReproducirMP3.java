
import javazoom.jlgui.basicplayer.*;
import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import javazoom.jl.converter.*;
import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.*;
import javazoom.spi.*;
import javazoom.spi.mpeg.sampled.convert.*;
import javazoom.spi.mpeg.sampled.file.*;
import javazoom.spi.mpeg.sampled.file.tag.*;
import org.tritonus.share.*;
import org.tritonus.share.midi.*;
import org.tritonus.share.sampled.*;
import org.tritonus.share.sampled.convert.*;
import org.tritonus.share.sampled.file.*;
import org.tritonus.share.sampled.mixer.*;
import javazoom.spi.*;
import javazoom.spi.vorbis.sampled.convert.*;
import javazoom.spi.vorbis.sampled.file.*;
import java.io.File; 

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;


/**Clase que permite reproducir archivos mp3*/
public class ReproducirMP3 {
    /*ATRIBUTOS*/
    private BasicPlayer player;
 
    /**Constructor*/
    public ReproducirMP3(){
    player = new BasicPlayer();
    }
    /** Metodos*/
    /* Reproduce la Cancion*/
    public void Play() throws Exception {
    player.play();

    }
    /** Abre la ruta del archivo*/ 
    public void AbrirFichero(String ruta) throws Exception {
    player.open(new File(ruta));
    }
     /*Detiene la Reproduccion*/
    public void Pausa() throws Exception {
    player.pause();
    }
    /*Continua con la Reproduccion de la Cancion*/
    public void Continuar() throws Exception {
    player.resume();
    }
    /* Detiene la cancion, pero este metodo no permite continuar con la reproduccion posteriormente*/
    public void Stop() throws Exception {
    player.stop();
    }

}