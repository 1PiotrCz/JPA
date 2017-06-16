package pl.piotrcz.Jpa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.piotrcz.Jpa.*;
import pl.piotrcz.Jpa.Models.Ticket;
import pl.piotrcz.Jpa.Models.User;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Piotr Czubkowski on 2017-06-12.
 */
@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    //-------------------------------- 03.06.2017 -----------------------------------------
    @Autowired
    TicketRepository ticketRepository;

    //------------------------------------- mail ----------------------------------------------

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    MailService mailService;

    //    --------------------------------------------------------------------------------------
    @RequestMapping(value = "/{ticketId}", method = RequestMethod.GET)
    @ResponseBody
    public String home(@PathVariable("ticketId") int id) {

//        Ticket ticket = ticketRepository.findOne(3);
//        ---------------------------------------------
//        Ticket ticket = ticketRepository.findOne(id);
//        ---------------------------------------------
//        Optional<Ticket> ticket = ticketRepository.findOne(id);
//        if (ticket.isPresent()) {
//            return "Odczytałem z bazy: " + ticket.get().getMessage();
//        }
//        return "To id nie istnieje";
////        Zamiennie z ifem można zastosowac lambde:

////        return ticket.map()
//        ---------------------------------------------
////        Ze strumieniem:
//        List<Ticket> tickets = ticketRepository.findByAuthor("Lukasz Kolacz");
////        return tickets.stream().map(s -> s.getMessage()).collect(Collectors.joining(" , ", "Tickety: ", ""));
//
////        Bez strumienia:
//        String massages = "Tickety Lukasz Kolacza: ";
//        for (Ticket ticket : tickets) {
//            massages += ticket.getMessage() + " , ";
//        }
//        return massages;
//        ---------------------------------------------

        List<Ticket> tickets = ticketRepository.findByMessageLike("Wiadomość%");
        return tickets.stream().map(s -> s.getMessage()).collect(Collectors.joining(" , ", "Tickety: ", ""));

//        ------------------ 03.06.2017 ---------------------------------------------------

    }

    //    @RequestMapping(value="/user",method = RequestMethod.GET)
//    @ResponseBody
//    public String user(){
//        List<User> users = userRepository.findUserByRole("ADMIN");
//        return users.stream().map(s->s.getUsername()).collect(Collectors.joining("", "Role: ", ""));
//    }
//    ------------------------------------------------------------------------------------------
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    @ResponseBody
//    public String user(){
//        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date date1 = null;
//        Date date2 = null;
//        try {
//            date1 = formater.parse("2017-04-12 16:32:06");
//            date2 = formater.parse("2017-06-13 00:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        List<User> users = userRepository.findByDatetimeBetween(date1, date2);
//        return users.stream().map(s -> s.getUsername()).collect(
//                Collectors.joining(" , ", "Role: ", ""));
//
//    }
//    ----------------------------------------------------------------------------------------------
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    @ResponseBody
//    public String user(){
//
//        List<User> users = userRepository.findByUsernameContainingAndIdGreaterThan("os", 3);
//        return users.stream().map(s -> s.getUsername()).collect(
//                Collectors.joining(" , ", "Role: ", ""));
//
//    }
//    --------------------------------- paginacja ---------------------------------------------------------------
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    @ResponseBody
//    public String user() {
//
//        Page<User> currentPage = userRepository.findAll(new PageRequest(0, 4));
//        StringBuilder builder = new StringBuilder();
//        for (User user : currentPage.getContent()) {
//            builder.append("Username: " + user.getUsername() + "<br>");
//        }
//
//        builder.append("<br> Ilość stron: " + currentPage.getTotalPages());
//        builder.append("<br> Czy zawiera następną stronę? " + currentPage.hasNext());
//        builder.append("<br> Czy zawiera poprzednia stronę? " + currentPage.hasPrevious());
//
//        currentPage=userRepository.findAll(currentPage.nextPageable());
//
//        builder.append("<br><br><br><br>");
//
//        for (User user : currentPage.getContent()) {
//            builder.append("Username: " + user.getUsername() + "<br>");
//        }
//
//        return builder.toString();
//
//    }
//    ------------------------------------ mailService ---------------------------------------

    @RequestMapping(value = "/mail/{cash}", method = RequestMethod.GET)
    @ResponseBody
    public String email(@PathVariable("cash") int cash){
        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
        context.setVariable("welcome", "witaj Henio Fenio!");
        context.setVariable("message", "Zalegaz z opłatami " + cash + "zł");

        String bodyHTML = templateEngine.process("emailone",context);

        mailService.sendEmail("piotreknkp@gmail.com", bodyHTML, "Wysłane z wykładu");
        return "Wysłano maila!";
    }

//    @RequestMapping(value = "/mail/{cash}", method = RequestMethod.GET)
//    @ResponseBody
//    public String email(@PathVariable("cash") int cash) {
//        Context context = new Context();
//        context.setVariable("welcome", "Witaj Janie Kowalski!");
//        context.setVariable("message", "Wisisz nam już " + cash + " zł");
//
//        String bodyHtml = templateEngine.process("emailone", context);
//
//        mailSerivce.sendEmail("piotreknkp@gmail.com", bodyHtml, "Wysłane z wykładu");
//        return "Wysłano maila!";
//    }

}
