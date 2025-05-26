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
            String firebaseConfigBase64 = System.getenv("FIREBASE_CONFIG_BASE64");

            if (firebaseConfigBase64 == null || firebaseConfigBase64.isEmpty()) {
                throw new IllegalStateException("Variável de ambiente FIREBASE_CONFIG_BASE64 não está definida!");
            }

            byte[] decodedBytes = Base64.getDecoder().decode(firebaseConfigBase64);
            ByteArrayInputStream serviceAccount = new ByteArrayInputStream(decodedBytes);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("🔥 Firebase inicializado com sucesso via base64!");
            }

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o Firebase:");
            e.printStackTrace();
        }
    }
}
