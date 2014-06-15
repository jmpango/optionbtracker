package org.meta.optionbtracker.async.impl;

import java.util.ArrayList;

import org.meta.optionbtracker.async.AsyncPipe;
import org.meta.optionbtracker.utils.Contants;

public class AsyncPipeImpl implements AsyncPipe{

	@Override
	public ArrayList<String> getEntities(String level) {
		ArrayList<String> entities = new ArrayList<String>();
		
		entities.add(Contants.FACILITIES_REGISTERED + " (120)");
		entities.add(Contants.FACILITIES_REPORTED + " (75)");
		entities.add(Contants.FACILITIES_NOT_REPORTED + " (35)");
		entities.add(Contants.REPORTING_RATE + " 76%");
		
		if(level == "District"){
			entities.clear();
			entities.add(Contants.FACILITIES_REGISTERED + " (50)");
			entities.add(Contants.FACILITIES_REPORTED + " (45)");
			entities.add(Contants.FACILITIES_NOT_REPORTED + " (5)");
			entities.add(Contants.REPORTING_RATE + " 98%");
		}else if(level == "Implementing Partner"){
			entities.clear();
			entities.add(Contants.FACILITIES_REGISTERED + " (20)");
			entities.add(Contants.FACILITIES_REPORTED + " (12)");
			entities.add(Contants.FACILITIES_NOT_REPORTED + " (8)");
			entities.add(Contants.REPORTING_RATE + " 80%");
		}		
		
		return entities;
	}

	@Override
	public ArrayList<?> getWeeks() {
		ArrayList<String> weeksEntities = new ArrayList<String>();
		
		weeksEntities.add("Week ending 2014-06-08 (2014W23)");
		weeksEntities.add("Week ending 2014-06-08 (2014W22)");
		weeksEntities.add("Week ending 2014-06-08 (2014W21)");
		weeksEntities.add("Week ending 2014-06-08 (2014W20)");
		weeksEntities.add("Week ending 2014-06-08 (2014W19)");
		
		return weeksEntities;
	}

	@Override
	public String getCurrentWeek() {
		return "Week ending 2013-06-08 2014W23";
	}

	@Override
	public ArrayList<?> getFacilities(String entityName) {
		return null;
	}

	@Override
	public ArrayList<String> getFacilitiesSockOut(String facilityName) {
		return null;
	}

	@Override
	public ArrayList<?> getLevels() {
	    ArrayList<String> levelEntities = new ArrayList<String>();
	    levelEntities.add("National");
		levelEntities.add("District");
		levelEntities.add("Implementing Partner");
		
		return levelEntities;
	}

	@Override
	public ArrayList<?> getLevelEntities(String entity) {
		
		ArrayList<String> ipORDistrictEntities = new ArrayList<String>();
		
		if (entity == "District") {
			ipORDistrictEntities.add("Abim District");
			ipORDistrictEntities.add("kampala District");
			ipORDistrictEntities.add("Jinja District");
			ipORDistrictEntities.add("Mbarara District");

		} else if (entity == "Implementing Partner") {
			ipORDistrictEntities.add("PMTCT Option B+ Balyor Sites");
			ipORDistrictEntities.add("PMTCT Option B+ CAUMM Sites");
			ipORDistrictEntities.add("PMTCT Option B+ KCCA Sites");
			ipORDistrictEntities.add("PMTCT Option B+ IDI Sites");

		} else {
			ipORDistrictEntities.add("Uganda");
		}
		
		return ipORDistrictEntities;
	}

	public ArrayList<?> getRegisteredFacilities(String entity) {
		ArrayList<String> facilities = new ArrayList<String>();
		facilities.add("Bowa HC III");
		facilities.add("Kiyigwa Hospital");
		facilities.add("Kamego HC IV");
		
		return facilities;
	}

}
