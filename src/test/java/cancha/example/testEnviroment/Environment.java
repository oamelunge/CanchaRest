package cancha.example.testEnviroment;

import cancha.example.DBSystem;


public abstract class Environment {

	public static Environment current() {
		
		if (DevelopmentEnvironment.isCurrent())
			return new DevelopmentEnvironment();
		else if (IntegrationEnvironment.isCurrent())
			return new IntegrationEnvironment();
		else
			throw new RuntimeException("Can not detect environment");
			
	}
	
	public abstract DBSystem createDBSystem();
}
