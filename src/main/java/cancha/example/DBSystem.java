package cancha.example;


public interface DBSystem {
	public abstract void beginTransaction();
	public abstract void commit();
	public abstract void close();
	
	public abstract CancheroSystem cancheroSystem();
}
