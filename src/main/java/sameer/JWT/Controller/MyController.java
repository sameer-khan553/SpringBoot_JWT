package sameer.JWT.Controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class MyController {

   private final SecretKey SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

   @GetMapping("/genrate-token")
   public String generateToken(@RequestParam String userName, @RequestParam String role) {
       String token = Jwts.builder()
               .setSubject(userName)
               .claim("role", role)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 3600000))
               .signWith(SIGNING_KEY)
               .compact();
       return "Token generated SuccessFully:"
               + token;
   }
}
