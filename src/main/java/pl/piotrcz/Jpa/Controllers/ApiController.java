package pl.piotrcz.Jpa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import pl.piotrcz.Jpa.Models.User;
import pl.piotrcz.Jpa.UserRepository;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Piotr Czubkowski on 2017-06-12.
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UserRepository userRepository;

    // localhost:8080/api/user
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json") // uzyskiwanie danych
//    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity getAllUser(@RequestHeader("Access-Password") String key) {

        if (!key.equals("Jestesfajny")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        userRepository.findAll(); // itterable
        Iterable<User> users = userRepository.findAll();
        return new ResponseEntity(users, HttpStatus.OK);

    }

    //  Metoda wyszukiwania jednego usera po jego nazwie
    @RequestMapping(value = "/user/{username}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity getUser(@RequestHeader("Access-Password") String key,
                                  @PathVariable("username") String username) {

        if (!key.equals("Jestesfajny")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return new ResponseEntity("User no exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user.get(), HttpStatus.OK);

    }

    // Aktualizacja bieżących danych  (np zmiana hasła)
    // localhost:8080/api/usr

    @RequestMapping(value = "api/user/",
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity editUser(@RequestBody User user, @RequestHeader("Access-Password") String key) {

        if (!key.equals("Jestesfajny")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User userLocal = userRepository.findOne(user.getId());
        if (userLocal == null) {
            return new ResponseEntity("User no exist", HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

//    Sprawdzenie Loginu i hasła
    @RequestMapping(value = "/api/checklogin/{login}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public String CechkLogin(@PathVariable("login") String login, @PathVariable("password") String password){
        if ((login.isEmpty() && login == null) || password == null ){
            return "null except";
        }
        Optional<User> userLocal = userRepository.findByUsername(login);
        if (userLocal.isPresent()){
            if (userLocal.get().getPassword().equals(password)){
                return "OK";
            }
        }
        return "BAD";
    }

//    Dynamiczne adresy URL
    @RequestMapping(value = "/piotr/**", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest servletRequest) throws Exception{
        String path = (String) servletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//        String[] split = path.split("/");
        return "Pozostały path to: " + path;
    }

    // Pobieranie pojedynczych pól z formularza
    @RequestMapping(value = "/oskar/**", method = RequestMethod.POST)
    @ResponseBody
    public String test( @RequestParam(value = "login" /*required = false */) String login, @RequestParam("password") String password){
        return "Login: " + login;
    }


}


