package com.thalesmonteiro.backendfirebase.Controller;



import com.thalesmonteiro.backendfirebase.entity.DeviceToken;
import com.thalesmonteiro.backendfirebase.repository.DeviceTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class DeviceTokenController {

    @Autowired
    private DeviceTokenRepository repository;

    public DeviceTokenController(DeviceTokenRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> salvarToken(@RequestBody DeviceToken request) {
        if (repository.existsByToken(request.getToken())) {
            return ResponseEntity.ok("Token já cadastrado");
        }

        repository.save(new DeviceToken(request.getToken(), request.getNomeaparelho()));
        return ResponseEntity.ok("Token salvo com sucesso");
    }
}

