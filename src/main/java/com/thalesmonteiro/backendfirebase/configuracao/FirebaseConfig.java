package com.thalesmonteiro.backendfirebase.configuracao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            String base64Config = System.getenv("FIREBASE_CONFIG_BASE64");

            if (base64Config == null || base64Config.isEmpty()) {
                throw new IllegalStateException("Variável de ambiente FIREBASE_CONFIG_BASE64 não encontrada!");
            }

            byte[] decodedBytes = Base64.getDecoder().decode(base64Config);
            ByteArrayInputStream serviceAccount = new ByteArrayInputStream(decodedBytes);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("🔥 Firebase inicializado com segurança via base64!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
