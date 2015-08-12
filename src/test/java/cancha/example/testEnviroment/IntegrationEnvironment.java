package cancha.example.testEnviroment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import cancha.example.DBSystem;
import cancha.example.PersistentDBSystem;



public class IntegrationEnvironment extends Environment {

	@Override
	public DBSystem createDBSystem() {
		return new PersistentDBSystem();
	}

	public static boolean isCurrent() {
		return new File("src/main/java/integration.txt").exists();
	}
}
