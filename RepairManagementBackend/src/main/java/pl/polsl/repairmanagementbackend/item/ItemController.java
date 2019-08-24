package pl.polsl.repairmanagementbackend.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.repairmanagementbackend.itemtype.ItemTypeRepository;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("api/item/search")
    public List<String> findByFullName(@RequestParam String fullName){
        String[] words = fullName.split("\\s");
        Pageable firstTen = new PageRequest(0, 10);
        List<ItemEntity> list;
        if (words.length == 0 || words[0].isEmpty()){
            list = itemRepository
                    .findAll(firstTen)
                    .getContent();
        } else if (words.length == 1){
            list = itemRepository
                    .findByTypeTypeStartsWithIgnoreCaseOrNameStartsWithIgnoreCase(words[0], words[0], firstTen)
                    .getContent();
        } else {
            var itemName = fullName.substring(fullName.indexOf(" ") + 1);
            list = itemRepository
                    .findByTypeTypeStartsWithIgnoreCaseAndNameStartsWithIgnoreCase(words[0], itemName, firstTen)
                    .getContent();
        }
        return  list
                .stream()
                .map(e -> e.getType().getType() + " " + e.getName() + "; " + e.getId())
                .collect(Collectors.toList());
    }
 }
