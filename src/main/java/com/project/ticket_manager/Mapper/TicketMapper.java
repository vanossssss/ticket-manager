package com.project.ticket_manager.Mapper;

import com.project.ticket_manager.dto.TicketDto;
import com.project.ticket_manager.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {

    @Mapping(target = "cinemaName", qualifiedByName = "getCinemaNameFromTicket", source = "ticket")
    TicketDto toTicketDto(Ticket ticket);

    List<TicketDto> toTicketDtoList(List<Ticket> tickets);

    @Named("getCinemaNameFromTicket")
    default String getCinemaNameFromCinema(Ticket ticket) {
        return ticket.getCinema().getName();
    }

}
