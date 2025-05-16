package com.project.ticket_manager.Mapper;

import com.project.ticket_manager.dto.CinemaDto;
import com.project.ticket_manager.entity.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CinemaMapper {

    CinemaDto toCinemaDto(Cinema cinema);

    List<CinemaDto> toCinemaDtoList(List<Cinema> cinemas);

}
