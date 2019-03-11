package pl.polsl.repairmanagment.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.polsl.repairmanagment.entities.*;

@Controller
public class MainController {

    @FXML
    private Label label;

    @FXML
    private TableView table;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @FXML
    private void buttonClicked(){
//        table.setEditable(true);
//        table.getColumns().add(new TableColumn("Id"));
//        table.getColumns().add(new TableColumn("Name"));
//
//
//        label.setText( clientRepository.selectAll().get(0).getName());
//        clientRepository.selectAll();

        AddressEntity address = new AddressEntity();
        addressRepository.save(address);

        ClientEntity client = new ClientEntity();
        client.setFirstName("From");
        client.setLastName("Click");
        client.setAddressByAddressId(address);
        //client.setAddressByAddressId();

        clientRepository.save(client);
    }



}
