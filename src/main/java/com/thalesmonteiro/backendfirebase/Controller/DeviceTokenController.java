package com.thalesmonteiro.backendfirebase.Controller;



import com.thalesmonteiro.backendfirebase.entity.DeviceToken;
import com.thalesmonteiro.backendfirebase.repository.DeviceTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<DeviceToken> existe = repository.findByNomeaparelho(request.getNomeaparelho());
        if (existe.isPresent()){
            DeviceToken deviceToken = existe.get();
            deviceToken.setToken(request.getToken());
            repository.save(deviceToken);
            return ResponseEntity.ok("Token atualizado com sucesso");
        }

        repository.save(new DeviceToken(request.getToken(), request.getNomeaparelho()));
        return ResponseEntity.ok("Token salvo com sucesso");
    }

    @GetMapping
    public List<DeviceToken> listarTokens() {
        return repository.findAll();
    }
}


