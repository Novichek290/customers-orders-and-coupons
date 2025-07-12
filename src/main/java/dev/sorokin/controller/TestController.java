//package dev.sorokin.controller;
//
//import dev.sorokin.entity.Client;
//import dev.sorokin.repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class TestController {
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @GetMapping("/test")
//    public String test() {
//        return "приложение работает";
//    }
//
//    @PostMapping("/clients")
//    public Client createClient(@RequestBody Client client){
//        return clientRepository.save(client);
//    }
//
//    @GetMapping("/clients")
//    public List<Client> getAllClients() {
//        return clientRepository.findAll();
//    }
//}
