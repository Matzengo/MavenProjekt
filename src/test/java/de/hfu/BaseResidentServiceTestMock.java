package de.hfu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.*;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceTestMock {
	ResidentRepository repoMock = createMock(ResidentRepository.class);
	List<Resident> rList = new ArrayList<Resident>();
	@SuppressWarnings("unused")
	private Resident r1;
	
	@Before
	public void initialize() {
		for(int i = 1; i <=3; i++) {
			rList.add(new Resident(i+".Vorname", i+".Nachname", i+".Musterstraße", "Musterstadt", null));
		}
	}
	
	@Test
	public void getUniqueResidentMockTest() throws ResidentServiceException {
		expect(repoMock.getResidents()).andReturn(rList).times(1);
		replay(repoMock);
		BaseResidentService baseResidentServiceMock = new BaseResidentService();
		baseResidentServiceMock.setResidentRepository(repoMock);

		Resident unique = new Resident("1.Vorname","1.Nachname","1.Musterstraße","Musterstadt",null);
		Resident erg = baseResidentServiceMock.getUniqueResident(unique);
		assertThat(erg.getGivenName(), equalTo(unique.getGivenName()));
		assertThat(erg.getFamilyName(), equalTo(unique.getFamilyName()));
		assertThat(erg.getStreet(), equalTo(unique.getStreet()));
		assertThat(erg.getCity(), equalTo(unique.getCity()));
		assertThat(erg.getDateOfBirth(), equalTo(unique.getDateOfBirth()));
		verify(repoMock);
	}
	
	@Test(expected = ResidentServiceException.class, timeout = 1000)
	public void ExceptionTestUniqueMockTest() throws ResidentServiceException {
		expect(repoMock.getResidents()).andReturn(rList).times(1);
		replay(repoMock);
		BaseResidentService baseResidentServiceMock = new BaseResidentService();
		baseResidentServiceMock.setResidentRepository(repoMock);
		
		baseResidentServiceMock.getUniqueResident(new Resident("TestVorname","1.Nachname","1.Musterstraße","Musterstadt",null));
		verify(repoMock);
	}
	@Test(expected = ResidentServiceException.class, timeout = 1000)
	public void EcxeptionTestWildcadMockTest() throws ResidentServiceException {
		expect(repoMock.getResidents()).andReturn(rList).times(1);
		replay(repoMock);
		BaseResidentService baseResidentServiceMock = new BaseResidentService();
		baseResidentServiceMock.setResidentRepository(repoMock);
		
		baseResidentServiceMock.getUniqueResident(new Resident("2*", "", "", "", null));
		verify(repoMock);
	}
	
	@Test
	public void getFilteredResidentsListMockTest() {
		expect(repoMock.getResidents()).andReturn(rList).times(2);
		replay(repoMock);
		BaseResidentService baseResidentServiceMock = new BaseResidentService();
		baseResidentServiceMock.setResidentRepository(repoMock);
		
		assertThat(baseResidentServiceMock.getFilteredResidentsList(new Resident("1*", "", "", "", null)).size(), equalTo(1));
		assertThat(baseResidentServiceMock.getFilteredResidentsList(new Resident()).size(), equalTo(3));
		verify(repoMock);
	}
}
	

