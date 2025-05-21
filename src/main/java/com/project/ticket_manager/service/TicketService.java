package com.project.ticket_manager.service;

import com.project.ticket_manager.mapper.TicketMapper;
import com.project.ticket_manager.dto.TicketDto;
import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import com.project.ticket_manager.repository.TicketRepository;
import com.project.ticket_manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@CacheConfig(cacheNames = "userTickets")
public class TicketService {

    private static final String EMAIL_ATTRIBUTE= "email";

    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Cacheable(key = "#user.userId")
    public List<TicketDto> getTicketDtoListByUser(User user) {
        List<Ticket> ticketsList = ticketRepository.findByUser(user);
        return ticketMapper.toTicketDtoList(ticketsList);
    }

    public List<TicketDto> getTicketDtoList() {
        TicketService self = applicationContext.getBean(TicketService.class);
        return self.getTicketDtoListByUser(getCurrentUser());
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute(EMAIL_ATTRIBUTE);
        return userRepository.findByEmail(email).get();
    }

    //вызов этого метода происходит, когда ticket и user не могут быть null
    @CacheEvict(key = "#user.userId")
    public void buyTicketByTicketId(Long ticketId, User user) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        Optional<User> ticketUser = Optional.ofNullable(ticket.getUser());

        if(ticketUser.isEmpty()) {
            User currentUser = getCurrentUser();

            ticket.setUser(currentUser);
            ticketRepository.save(ticket);
        }
    }

    public void buyCurrentUserTicket(Long ticketId) {
        TicketService self = applicationContext.getBean(TicketService.class);
        self.buyTicketByTicketId(ticketId, getCurrentUser());
    }

    @CacheEvict(key = "#user.userId")
    public void cancelTicketById(Long id, User user) {
        Optional<Ticket> boughtTicket = ticketRepository.findById(id);

        boughtTicket.ifPresent(ticket -> {
            ticket.setUser(null);
            ticketRepository.save(ticket);
        });
    }

    public void cancelCurrentUserTicket(Long id) {
        TicketService self = applicationContext.getBean(TicketService.class);
        self.cancelTicketById(id, getCurrentUser());
    }
}
