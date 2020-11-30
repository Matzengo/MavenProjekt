package de.hfu;

import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceTest{
	
	BaseResidentService baseResidentService = new BaseResidentService();
	List<Resident> rList = new ArrayList<Resident>();
	@SuppressWarnings("unused")
	private Resident r1;
	
	@Before
	public void initialize() {
		for(int i = 1; i <=3; i++) {
			rList.add(new Resident(i+".Vorname", i+".Nachname", i+".Musterstraße", "Musterstadt", null));
		}
	}
	
	// ============== getUniqueREsidentTest ====================
	// Test: Funtkionsfaehig
	@Test()
	public void getUniqueResidentTest1() throws ResidentServiceException {
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(rList);
		baseResidentService.setResidentRepository(residentRepositoryStub);
		r1 = baseResidentService.getUniqueResident(new Resident("1.Vorname","1.Nachname","1.Musterstraße","Musterstadt",null));
	}
	
	// Test: Eindeutig
	@Test(expected=ResidentServiceException.class, timeout=1000)
	public void getUniqueResidentTest2() throws ResidentServiceException {
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(rList);
		baseResidentService.setResidentRepository(residentRepositoryStub);
		r1 = baseResidentService.getUniqueResident(new Resident("TestVorname","1.Nachname","1.Musterstraße","Musterstadt",null));
	}
	
	// Test: keine Wildcards
	@Test(expected=ResidentServiceException.class, timeout=1000)
	public void getUniqueResidentTest3() throws ResidentServiceException {
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(rList);
		baseResidentService.setResidentRepository(residentRepositoryStub);
		r1 = baseResidentService.getUniqueResident(new Resident("*","*","*","*",null));
	}
	
	// ============== getFilteredResidentsList ====================
	// Test: Funtkionsfaehig
	@Test
	public void getFilteredResidentsList1() {
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(rList);
		baseResidentService.setResidentRepository(residentRepositoryStub);
		List<Resident> testFilterList = baseResidentService.getFilteredResidentsList(new Resident("2*", "", "", "", null));
        for (Resident test : testFilterList) {
            //System.out.println(test.getGivenName());
            assertEquals("2.Vorname", test.getGivenName());
            assertEquals("2.Nachname", test.getFamilyName());
            assertEquals("2.Musterstraße", test.getStreet());
            assertEquals("Musterstadt", test.getCity());
            assertEquals(null, test.getDateOfBirth());
        }
	}
	
	// Test: Anzahl bei Wildcard
	@Test
	public void getFilteredResidentsList2() {
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(rList);
		baseResidentService.setResidentRepository(residentRepositoryStub);
		List<Resident> testFilterList = baseResidentService.getFilteredResidentsList(new Resident("", "", "", "M*", null));
		assertEquals(3,testFilterList.size());
	}
	
	// Test: Anzahl bei Leerstring
	@Test
	public void getFilteredResidentsList3() {
		ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub(rList);
		baseResidentService.setResidentRepository(residentRepositoryStub);
		List<Resident> testFilterList = baseResidentService.getFilteredResidentsList(new Resident("", "", "", "", null));
		assertEquals(3,testFilterList.size());
	}

}
