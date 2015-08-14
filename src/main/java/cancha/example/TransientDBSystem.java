package cancha.example;


public class TransientDBSystem implements DBSystem {

	private CancheroSystem cancheroSystem;
	
	public TransientDBSystem(){
		cancheroSystem = new CancheroTransient(this);		
	}

	@Override
	public void beginTransaction() {
	}

	@Override
	public void commit() {
	}

	@Override
	public void close() {
	}

	@Override
	public CancheroSystem cancheroSystem() {	
		return cancheroSystem;
	}




}
