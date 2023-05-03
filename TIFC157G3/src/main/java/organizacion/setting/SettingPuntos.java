package organizacion.setting;


import organizacion.exceptions.ArchSeteoException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SettingPuntos {
    private String settingCsv;
    public  String puntosPartido;
    public  String puntosRonda;
    public  String puntosFase;

    public SettingPuntos(String path) {
        super();
        this.settingCsv = path;
    }

    public String getPuntosPartido() {
        return puntosPartido;
    }
    public String getPuntosRonda() {
        return puntosRonda;
    }
    public String getPuntosFase() {
        return puntosFase;
    }

    public void cargarSeteo() throws ArchSeteoException {
        Path pathResultados = Paths.get(this.settingCsv);
        List<String> lineasResultados = null;
        try {
            lineasResultados = Files.readAllLines(pathResultados);
        } catch (IOException e) {
            throw new ArchSeteoException(this.settingCsv);
        }
        boolean primera = true;

        for (String lineaResultado : lineasResultados) {
            if (primera) {
                primera = false;
            } else {
                String[] campos = lineaResultado.split(",");

               puntosPartido=(campos[0]);
               puntosRonda=(campos[1]);
               puntosFase=(campos[2]);
            }
        }
    }
}
