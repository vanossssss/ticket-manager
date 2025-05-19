package com.project.ticket_manager.service;

import com.project.ticket_manager.Mapper.TicketMapper;
import com.project.ticket_manager.dto.TicketDto;
import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import com.project.ticket_manager.repository.TicketRepository;
import com.project.ticket_manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private static final String EMAIL_ATTRIBUTE= "email";

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    //вызов этого метода происходит, когда user не может быть null
    public List<Ticket> getTicketsByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute(EMAIL_ATTRIBUTE);
        User user = userRepository.findByEmail(email).get();

        return ticketRepository.findByUser(user);
    }

    public List<TicketDto> getTicketDtoListByUser() {
        return ticketMapper.toTicketDtoList(getTicketsByUser());
    }

    public List<Ticket> getNotBoughtTicketsByCinema(Cinema cinema) {
        return ticketRepository.findNotBoughtTicketsByCinemaId(cinema.getCinemaId());
    }

    public List<TicketDto> getNotBoughtTicketDtoByCinema(Cinema cinema) {
        return ticketMapper.toTicketDtoList(getNotBoughtTicketsByCinema(cinema));
    }

    //вызов этого метода происходит, когда ticket и user не могут быть null
    public void buyTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        Optional<User> ticketUser = Optional.ofNullable(ticket.getUser());

        if(ticketUser.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String email = oAuth2User.getAttribute(EMAIL_ATTRIBUTE);
            User currentUser = userRepository.findByEmail(email).get();

            ticket.setUser(currentUser);
            ticketRepository.save(ticket);
        }
    }

    public void cancelTicketById(Long id) {
        Optional<Ticket> boughtTicket = ticketRepository.findById(id);

        boughtTicket.ifPresent(ticket -> {
            ticket.setUser(null);
            ticketRepository.save(ticket);
        });
    }
}
