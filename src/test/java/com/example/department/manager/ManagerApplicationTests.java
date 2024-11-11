package com.example.department.manager;

import com.example.department.manager.Entity.Department;
import com.example.department.manager.Error.DepartmentNotFoundException;
import com.example.department.manager.Repository.DepartmentRepository;
import com.example.department.manager.Service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.web.client.ExpectedCount;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.Matchers.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@Nested
@SpringBootTest
public class ManagerApplicationTests {

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	private Department department;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		department = Department.builder()
				.departmentId(1L)
				.departmentName("HR")
				.departmentAddress("Headquarters")
				.departmentCode("HR01")
				.build();
	}

	@Test
	void saveDepartment() {
		when(departmentRepository.save(department)).thenReturn(department);

		Department savedDepartment = departmentService.saveDepartment(department);

		assertNotNull(savedDepartment);
		assertEquals("HR", savedDepartment.getDepartmentName());
	}

	@Test
	void fetchDepartmentList() {
		Department department2 = Department.builder()
				.departmentId(2L)
				.departmentName("IT")
				.departmentAddress("Headquarters")
				.departmentCode("IT01")
				.build();

		List<Department> departmentList = Arrays.asList(department, department2);

		when(departmentRepository.findAll()).thenReturn(departmentList);

		List<Department> departments = departmentService.fetchDepartmentList();

		assertEquals(2, departments.size());
	}

	@Test
	void fetchDepartmentById_DepartmentExists() throws DepartmentNotFoundException {
		when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

		Department foundDepartment = departmentService.fetchDepartmentById(1L);

		assertNotNull(foundDepartment);
		assertEquals("HR", foundDepartment.getDepartmentName());
	}

	@Test
	void fetchDepartmentById_DepartmentNotFound() {
		when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(DepartmentNotFoundException.class, () -> departmentService.fetchDepartmentById(1L));
	}

	@Test
	void deleteDepartmentById() {
		Long departmentId = 1L;

		doNothing().when(departmentRepository).deleteById(departmentId);

		departmentService.deleteDepartmentById(departmentId);

		verify(departmentRepository, times(1)).deleteById(departmentId);
	}

	private <T> CrudRepository verify(DepartmentRepository departmentRepository, ExpectedCount times) {
		return null;
	}

	/*private <T> CrudRepository<T, Long> verify(DepartmentRepository departmentRepository, ExpectedCount times) {
	}*/

	/*@Test
	void updateDepartment() {
		Department updatedDepartment = Department.builder()
				.departmentId(1L)
				.departmentName("HR and Admin")
				.departmentAddress("Main Office")
				.departmentCode("HR02")
				.build();

		when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
		when(departmentRepository.save(any(Department.class))).thenReturn(updatedDepartment);

		Department result = departmentService.updateDepartment(1L, updatedDepartment);

		assertEquals("HR and Admin", result.getDepartmentName());
		assertEquals("HR02", result.getDepartmentCode());
	}*/

	@Test
	void fetchDepartmentByName_DepartmentExists() {
		when(departmentRepository.findByDepartmentNameIgnoreCase("HR")).thenReturn(department);

		Department foundDepartment = departmentService.fetchDepartmentByName("HR");

		assertNotNull(foundDepartment);
		assertEquals("HR", foundDepartment.getDepartmentName());
	}

	@Test
	void fetchDepartmentByName_DepartmentNotFound() {
		when(departmentRepository.findByDepartmentNameIgnoreCase("NonExisting")).thenReturn(null);

		Department foundDepartment = departmentService.fetchDepartmentByName("NonExisting");

		assertNull(foundDepartment);
	}
}




