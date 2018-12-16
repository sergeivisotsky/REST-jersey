package org.sergei.rest.service;

import org.sergei.rest.repository.AircraftDAO;
import org.sergei.rest.dto.AircraftDTO;
import org.sergei.rest.model.Aircraft;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergei Visotsky, 2018
 */
public class AircraftService {

    @Inject
    private AircraftDAO aircraftDAO;

    public List<AircraftDTO> findAll() {
        return aircraftDAO.findAll()
                .stream()
                .map(AircraftDTO::new)
                .collect(Collectors.toList());
    }

    public AircraftDTO findById(Long aircraftId) {
        Aircraft aircraft = aircraftDAO.findOne(aircraftId);
        return new AircraftDTO(aircraft);
    }

    public AircraftDTO save(AircraftDTO aircraftDTO) {
        Aircraft aircraft = aircraftDTO.toModelObject();
        aircraftDAO.save(aircraft);
        return aircraftDTO;
    }

    public AircraftDTO update(Long aircraftID, AircraftDTO aircraftDTO) {
        Aircraft aircraft = aircraftDTO.toModelObject();
        aircraft.setAircraftId(aircraftID);
        aircraftDAO.update(aircraft);
        return aircraftDTO;
    }

    public AircraftDTO delete(Long aircraftId) {
        Aircraft aircraft = aircraftDAO.findOne(aircraftId);
        aircraftDAO.delete(aircraft);
        return new AircraftDTO(aircraft);
    }
}
