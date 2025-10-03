package Actividad02;

public class Libro {
	private int ISBN;
	private String libro;
	private String editorial;
	private int paginas;
	private String autor;
	private int copias;
	
	public Libro(int ISBN, String libro, String editorial, int paginas,String autor,int copias) {
		this.ISBN = ISBN;
		this.libro = libro;
		this.editorial = editorial;
		this.paginas = paginas;
		this.autor = autor;
		this.copias = copias;
	}

	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", libro=" + libro + ", editorial=" + editorial + ", paginas=" + paginas
				+ ", autor=" + autor + ", copias=" + copias + "]";
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getCopias() {
		return copias;
	}

	public void setCopias(int copias) {
		this.copias = copias;
	}
	
}
