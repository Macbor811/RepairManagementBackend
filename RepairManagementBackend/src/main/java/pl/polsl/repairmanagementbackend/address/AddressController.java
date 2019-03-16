package pl.polsl.repairmanagementbackend.address;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class AddressController {

    private final AddressRepository repository;

    public AddressController(AddressRepository repository) {
        this.repository = repository;
    }

    private AddressDTO toDTO(AddressEntity entity){
        return new AddressDTO(entity.getId(), entity.getPostCode(), entity.getCity(), entity.getStreet(), entity.getNumber());
    }

    private AddressEntity toEntity(AddressDTO dto){
        return new AddressEntity(dto.getPostCode(), dto.getCity(), dto.getStreet(), dto.getNumber(), null, null);
    }


    @GetMapping(path="/address")
    public List<AddressDTO> findAll(){

        List<AddressEntity> entities = repository.findAll();
        List<AddressDTO> dtos = entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return dtos;
    }

    @PostMapping(path="/address/save")
    public void save(@RequestBody AddressDTO toSave){

        repository.save(new AddressEntity(toSave.getPostCode(), toSave.getCity(), toSave.getStreet(), toSave.getNumber(),null, null));

    }


//    @GetMapping(path="/address/{id}")
//    public AddressDTO findById(@PathVariable int id){
//
//    }
}
