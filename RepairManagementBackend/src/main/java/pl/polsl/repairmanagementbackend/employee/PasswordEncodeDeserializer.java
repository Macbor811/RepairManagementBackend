package pl.polsl.repairmanagementbackend.employee;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class PasswordEncodeDeserializer extends JsonDeserializer<String> {

    //@Autowired
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

//    @Autowired
//    public PasswordEncodeDeserializer(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }


    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String encodedPassword = encoder.encode(node.asText());
        return encodedPassword;
    }
}
