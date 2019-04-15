package pl.polsl.repairmanagementbackend.address;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressRepository repository;

    public AddressController(AddressRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<AddressDTO> find(){

        List<AddressEntity> entities = repository.findAll();
        List<AddressDTO> dtos = entities.stream().map(AddressEntity::toDTO).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/{id}")
    public AddressDTO find(@PathVariable int id){

        AddressEntity entity = repository.findById(id);
        return entity.toDTO();
    }

    @PostMapping
    public void save(@RequestBody AddressDTO toSave){

        repository.save(toSave.toEntity());

    }




//    @GetMapping(path="/address/{id}")
//    public AddressDTO findById(@PathVariable int id){
//
//    }
}
