// ==================== RendezVousService.java ====================
package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.RendezVousDTO;
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
public class RendezVousService {

    private final MediConnectSoapClient soapClient;
    private final SoapDtoMapper mapper;

    public RendezVousDTO creerRendezVous(RendezVousDTO rendezVousDTO) {
        log.info("REST → SOAP: Création RDV patient ID: {}", rendezVousDTO.getPatientId());

        CreerRendezVousRequest request = mapper.rendezVousDtoToCrerRequest(rendezVousDTO);
        CreerRendezVousResponse response = soapClient.creerRendezVous(request);

        return mapper.soapRendezVousToDtoFrom(response.getRendezVous());
    }

    public RendezVousDTO obtenirRendezVous(Long rendezVousId) {
        log.info("REST → SOAP: Récupération RDV ID: {}", rendezVousId);

        ObtenirRendezVousRequest request = mapper.buildObtenirRendezVousRequest(rendezVousId);
        ObtenirRendezVousResponse response = soapClient.obtenirRendezVous(request);

        return mapper.soapRendezVousToDtoFrom(response.getRendezVous());
    }

    public List<RendezVousDTO> listerRendezVousParMedecin(Long medecinId) {
        log.info("REST → SOAP: Lister RDV médecin ID: {}", medecinId);

        ListerRendezVousParMedecinRequest request = mapper.buildListerRendezVousRequest(medecinId);
        ListerRendezVousParMedecinResponse response = soapClient.listerRendezVousParMedecin(request);

        return mapper.soapRendezVousListToDtoList(response.getRendezVous());
    }
}