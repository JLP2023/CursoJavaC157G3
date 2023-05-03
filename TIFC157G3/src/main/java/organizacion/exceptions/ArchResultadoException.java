package organizacion.exceptions;

public class ArchResultadoException extends Exception {

	private String archivoCsv;
	
	public ArchResultadoException(String archivoCsv) {
		this.archivoCsv = archivoCsv;
	}

	public String getArchivoCsv() {
		return archivoCsv;
	}

	public void setArchivoCsv(String archivoCsv) {
		this.archivoCsv = archivoCsv;
	}

	
	
	
}
