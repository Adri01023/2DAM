package CreacionYEjecucionProcesos;

public abstract class Comandos {
    public abstract void lanzarping(String ip);
    public abstract void listar(String archivo);
    public abstract void listarprocesos();
    public abstract void matarproceso(String PID);
    public abstract void visitarweb(String web);
}
