package com.thalesmonteiro.backendfirebase.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.thalesmonteiro.backendfirebase.entity.DeviceToken;
import com.thalesmonteiro.backendfirebase.repository.DeviceTokenRepository;
import com.thalesmonteiro.backendfirebase.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private DeviceTokenRepository deviceTokenRepository;

    @PostMapping
    public ResponseEntity<String> enviarParaTodos(@RequestParam String titulo, @RequestParam String corpo) {
        List<DeviceToken> tokens = deviceTokenRepository.findAll();

        tokens.forEach(t -> {
            try {
                firebaseService.enviarNotificacao(t.getToken(), titulo, corpo);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.ok("Notificações enviadas!");
    }

    @PostMapping("/por-nome")
    public ResponseEntity<String> enviarParaNomeAparelho(
            @RequestParam String nomeaparelho,
            @RequestParam String titulo,
            @RequestParam String corpo) {

        return deviceTokenRepository.findByNomeaparelho(nomeaparelho)
                .map(deviceToken -> {
                    try {
                        firebaseService.enviarNotificacao(deviceToken.getToken(), titulo, corpo);
                        return ResponseEntity.ok("Notificação enviada para: " + nomeaparelho);
                    } catch (FirebaseMessagingException e) {
                        e.printStackTrace();
                        return ResponseEntity.status(500).body("Erro ao enviar notificação.");
                    }
                })
                .orElse(ResponseEntity.status(404).body("Dispositivo não encontrado."));
    }
}

