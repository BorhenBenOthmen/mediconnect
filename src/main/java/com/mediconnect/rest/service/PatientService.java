
// ==================== PatientService.java ====================
package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.PatientDTO;
import com.mediconnect.rest.soap.MediConnectSoapClient;
import com.mediconnect.rest.soap.SoapDtoMapper;
import com.mediconnect.rest.soap.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final MediConnectSoapClient soapClient;
    private final SoapDtoMapper mapper;

    public PatientDTO creerPatient(PatientDTO patientDTO) {
        log.info("REST → SOAP: Création patient {}", patientDTO.getNom());

        CreerPatientRequest request = mapper.patientDtoToCrerRequest(patientDTO);
        CreerPatientResponse response = soapClient.creerPatient(request);

        return mapper.soapPatientToDtoFrom(response.getPatient());
    }

    public PatientDTO obtenirPatient(Long patientId) {
        log.info("REST → SOAP: Récupération patient ID: {}", patientId);

        ObtenirPatientRequest request = mapper.buildObtenirPatientRequest(patientId);
        ObtenirPatientResponse response = soapClient.obtenirPatient(request);

        return mapper.soapPatientToDtoFrom(response.getPatient());
    }

    public List<PatientDTO> listerPatients() {
        log.info("REST → SOAP: Lister tous les patients");

        ListerPatientsRequest request = mapper.buildListerPatientsRequest();
        ListerPatientsResponse response = soapClient.listerPatients(request);

        return mapper.soapPatientListToDtoList(response.getPatients());
    }
}
