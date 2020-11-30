package de.hfu;

import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository{
	
	private List<Resident> resident;
	
	public ResidentRepositoryStub(List<Resident> rList) {
		resident = rList;
	}
	
	@Override
	public List<Resident> getResidents() {
		return resident;
	}
}
