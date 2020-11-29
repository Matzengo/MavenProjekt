package de.hfu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository{
	
	List<Resident> resident = new ArrayList<Resident>();
	
	public ResidentRepositoryStub() {
		super();
		for(int i = 1; i <=3; i++) {
			resident.add(new Resident(i+".Vorname", i+".Nachname", i+"Musterstraße", i+"Musterstadt", new Date(i)));
		}
	}

	@Override
	public List<Resident> getResidents() {
		return null;
	}

}
