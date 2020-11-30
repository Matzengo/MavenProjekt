package de.hfu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.EasyMock.*;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceTestMock {
	public class TestMockResident {
		ResidentRepository repoMock = createMock(ResidentRepository.class);
		List<Resident> liste = new ArrayList<Resident>();
		@Before
		public void setup() {
			liste.add(new Resident("Jonas", "Müller", "Hauptsrasse", "Rottweil", new Date(123456)));
			liste.add(new Resident("Coco", "Sailer", "Heerstrasse", "Rottweil", new Date()));
			liste.add(new Resident("Tobi", "Müller", "Heerstrasse", "Furtwangen", new Date(123456)));
		}
		
		
		@Test
		public void testMockFilteredResidentsList() {
			expect(repoMock.getResidents()).andReturn(liste).times(3);
			replay(repoMock);
			
			BaseResidentService base = new BaseResidentService();
			base.setResidentRepository(repoMock);
			
			Resident filter = new Resident();
			filter.setFamilyName("Müller");
			filter.setDateOfBirth(new Date(123456));
			assertEquals(2, base.getFilteredResidentsList(filter).size());
			assertEquals(3, base.getFilteredResidentsList(new Resident()).size());
			filter = new Resident();
			filter.setStreet("H*");
			assertEquals(3, base.getFilteredResidentsList(filter).size());
			verify(repoMock);
		}
		
		@Test
		public void testMockGetUniqueResident() throws ResidentServiceException {
			expect(repoMock.getResidents()).andReturn(liste).times(1);
			replay(repoMock);
			
			BaseResidentService base = new BaseResidentService();
			Resident filter = new Resident();
			filter.setGivenName("Jonas");
			Resident control = new Resident("Jonas", "Müller", "Hauptsrasse", "Rottweil", new Date(123456));

			base.setResidentRepository(repoMock);

			Resident ergebnis = base.getUniqueResident(filter);
			assertEquals(control, ergebnis);
			verify(repoMock);
		}
		
		@Test(expected = ResidentServiceException.class, timeout = 1000)
		public void testMockExceptionUniqueResident() throws ResidentServiceException {
			expect(repoMock.getResidents()).andReturn(liste).times(1);
			replay(repoMock);
			
			BaseResidentService base = new BaseResidentService();
			base.setResidentRepository(repoMock);
			Resident filter = new Resident();
			filter.setStreet("Heerstrasse");
			base.getUniqueResident(filter);
			verify(repoMock);
		}
		@Test(expected = ResidentServiceException.class, timeout = 1000)
		public void testWildcadUniqueResident() throws ResidentServiceException {
			expect(repoMock.getResidents()).andReturn(liste).times(1);
			replay(repoMock);
			
			BaseResidentService base = new BaseResidentService();
			base.setResidentRepository(repoMock);
			
			Resident filter = new Resident();
			filter.setStreet("H*");
			base.getUniqueResident(filter);
		}
	}

}
