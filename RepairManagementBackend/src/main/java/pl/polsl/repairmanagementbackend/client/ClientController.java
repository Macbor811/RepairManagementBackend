package pl.polsl.repairmanagementbackend.client;


import org.springframework.web.bind.annotation.*;
import pl.polsl.repairmanagementbackend.address.AddressDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<ClientDTO> find(){

        List<ClientEntity> entities = repository.findAll();
        List<ClientDTO> dtos = entities.stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
        return dtos;
    }

//    @GetMapping("/{id}")
//    public ClientDTO find(@PathVariable int id){
//
//        ClientEntity entity = repository.findById(id);
//        return toDTO(entity);
//    }

    @PostMapping
    public void save(@RequestBody ClientDTO toSave){

        repository.save(toSave.toEntity());

    }

    @GetMapping("/test")
    public ClientDTO testFind(){

        AddressDTO address = new AddressDTO(1, "42-420", "Miasto", "Ulica", 42);
        ClientDTO client = new ClientDTO(1, "Jan", "Kowalski", "123456789", address);

        return client;
    }

//    @GetMapping("/{id}")
//    public ClientDTO find(@PathVariable int id){
//
//        ClientEntity entity = repository.findById(id);
//        return toDTO(entity);
//    }

    @PostMapping("/test")
    public void testSave(@RequestBody ClientDTO toSave){

        repository.save(toSave.toEntity());

    }
    
}
