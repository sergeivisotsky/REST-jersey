package org.sergei.rest.service;

import org.sergei.rest.repository.RouteDAO;
import org.sergei.rest.dto.RouteDTO;
import org.sergei.rest.model.Route;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergei Visotsky, 2018
 */
public class RouteService {

    @Inject
    private RouteDAO routeDAO;

    public List<RouteDTO> findAll() {
        return routeDAO.findAll()
                .stream()
                .map(RouteDTO::new)
                .collect(Collectors.toList());
    }

    public RouteDTO findById(Long routeId) {
        Route route = routeDAO.findOne(routeId);
        return new RouteDTO(route);
    }

    public RouteDTO save(RouteDTO routeDTO) {
        Route route = routeDTO.toModelObject();
        routeDAO.save(route);
        return routeDTO;
    }

    public RouteDTO update(Long routeId, RouteDTO routeDTO) {
        Route route = routeDTO.toModelObject();
        route.setRouteId(routeId);
        routeDAO.update(route);
        return routeDTO;
    }

    public RouteDTO delete(Long routeId) {
        Route route = routeDAO.findOne(routeId);
        routeDAO.delete(route);
        return new RouteDTO(route);
    }
}
