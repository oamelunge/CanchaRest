package canchatest.example.testEnviroment;

import cancha.example.DBSystem;
import cancha.example.TransientDBSystem;



public class DevelopmentEnvironment extends Environment {

	@Override
	public DBSystem createDBSystem() {
		return new TransientDBSystem();
	}

	public static boolean isCurrent() {
		return !IntegrationEnvironment.isCurrent();
	}

}
