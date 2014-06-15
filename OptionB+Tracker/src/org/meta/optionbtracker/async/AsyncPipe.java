package org.meta.optionbtracker.async;

import java.util.ArrayList;

public interface AsyncPipe {

	public ArrayList<String> getEntities(String level);
	public ArrayList<?> getWeeks();
	public String getCurrentWeek();
	public ArrayList<?> getFacilities(String entityName);
	public ArrayList<?> getFacilitiesSockOut(String facilityName);
	public ArrayList<?> getLevels();
	public ArrayList<?> getLevelEntities(String entity);
	public ArrayList<?> getRegisteredFacilities(String entity);
	
}
