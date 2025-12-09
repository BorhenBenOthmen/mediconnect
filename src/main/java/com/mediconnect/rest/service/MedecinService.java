// ==================== MedecinService.java ====================
package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.MedecinDTO;
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
public class MedecinService {

    private final MediConnectSoapClient soapClient;
    private final SoapDtoMapper mapper;

    public MedecinDTO creerMedecin(MedecinDTO medecinDTO) {
        log.info("REST → SOAP: Création médecin {}", medecinDTO.getNom());

        CreerMedecinRequest request = mapper.medecinDtoToCrerRequest(medecinDTO);
        CreerMedecinResponse response = soapClient.creerMedecin(request);

        return mapper.soapMedecinToDtoFrom(response.getMedecin());
    }

    public MedecinDTO obtenirMedecin(Long medecinId) {
        log.info("REST → SOAP: Récupération médecin ID: {}", medecinId);

        ObtenirMedecinRequest request = mapper.buildObtenirMedecinRequest(medecinId);
        ObtenirMedecinResponse response = soapClient.obtenirMedecin(request);

        return mapper.soapMedecinToDtoFrom(response.getMedecin());
    }

    public List<MedecinDTO> listerMedecins() {
        log.info("REST → SOAP: Lister tous les médecins");

        ListerMedecinsRequest request = mapper.buildListerMedecinsRequest();
        ListerMedecinsResponse response = soapClient.listerMedecins(request);

        return mapper.soapMedecinListToDtoList(response.getMedecins());
    }
}
