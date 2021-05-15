package barracksWars.interfaces;

import jdk.jshell.spi.ExecutionControl;

import javax.naming.OperationNotSupportedException;

public interface Repository {

	void addUnit(Unit unit);

	String getStatistics();

	void removeUnit(String unitType) throws OperationNotSupportedException;
}
