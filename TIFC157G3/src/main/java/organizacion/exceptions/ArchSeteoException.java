package organizacion.exceptions;

public class ArchSeteoException extends Exception{
    private String archivoCsv;

    public ArchSeteoException(String archivoCsv) {
        this.archivoCsv = archivoCsv;
    }

    public String getArchivoCsv() {
        return archivoCsv;
    }

    public void setArchivoCsv(String archivoCsv) {
        this.archivoCsv = archivoCsv;
    }

}
