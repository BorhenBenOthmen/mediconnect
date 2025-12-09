// ==================== DossierMedicalService.java ====================
package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.DossierMedicalDTO;
import com.mediconnect.rest.soap.MediConnectSoapClient;
import com.mediconnect.rest.soap.SoapDtoMapper;
import com.mediconnect.rest.soap.client.CreerDossierMedicalResponse;
import com.mediconnect.rest.soap.client.ObtenirDossiersParPatientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DossierMedicalService {

    private final MediConnectSoapClient soapClient;
    private final SoapDtoMapper mapper;

    public DossierMedicalDTO creerDossierMedical(DossierMedicalDTO dossierDTO) {
        log.info("REST → SOAP: Création dossier médical patient ID: {}", dossierDTO.getPatientId());

        var request = mapper.dossierDtoToCrerRequest(dossierDTO);
        CreerDossierMedicalResponse response = soapClient.creerDossierMedical(request);

        return mapper.creerDossierResponseToDto(response);
    }

    public List<DossierMedicalDTO> obtenirDossiersParPatient(Long patientId) {
        log.info("REST → SOAP: Récupération dossiers patient ID: {}", patientId);

        var request = mapper.buildObtenirDossiersRequest(patientId);
        ObtenirDossiersParPatientResponse response = soapClient.obtenirDossiersParPatient(request);

        return mapper.obtenirDossiersResponseToDto(response);
    }
}
