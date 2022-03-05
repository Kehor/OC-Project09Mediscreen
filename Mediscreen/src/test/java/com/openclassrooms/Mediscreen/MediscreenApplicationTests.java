package com.openclassrooms.Mediscreen;

import com.openclassrooms.Mediscreen.entity.Notes;
import com.openclassrooms.Mediscreen.entity.Patient;
import com.openclassrooms.Mediscreen.entity.Test;
import com.openclassrooms.Mediscreen.service.AppointementService;
import com.openclassrooms.Mediscreen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class MediscreenApplicationTests {

	@Autowired
	private PatientService patientService;
	@Autowired
	private AppointementService appointementService;

	@org.junit.jupiter.api.Test
	public void savePatient(){
		Patient patient = patientService.savePatient("email@test","test","prenom","nom","2022-01-11 23:00:00","M","ici","0000000000");
		Patient patientTest = patientService.getpatientbyemail("email@test");
		assertEquals(patient.toString(),patientTest.toString());
	}

	@org.junit.jupiter.api.Test
	public void editnotes(){
		Notes notes = patientService.editnotes(0l,patientService.getpatientbyemail("email@test").getId(),"test, Fumeur");
		Notes notesTest = patientService.getNotesByPatientid(patientService.getpatientbyemail("email@test").getId()).get(0);
		assertEquals(notes.toString(),notesTest.toString());
	}

	@org.junit.jupiter.api.Test
	public void getTestDiabete(){
		Test test = patientService.getTestDiabete(patientService.getpatientbyemail("email@test").getId());
		Test test1 = new Test(1l,"None");
		assertEquals(test.toString(),test1.toString());
	}
}
