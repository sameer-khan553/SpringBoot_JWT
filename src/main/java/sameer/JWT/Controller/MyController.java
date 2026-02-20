package sameer.JWT.Controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class MyController {

   private final SecretKey SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

   @GetMapping("/genrate-token")
   public String generateToken(@RequestParam String userName, @RequestParam String role ,
                               HttpServletResponse response) {
       String token = Jwts.builder()
               .setSubject(userName)
               .claim("role", role)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 3600000))
               .signWith(SIGNING_KEY)
               .compact();


       Cookie cookie  = new Cookie("username",token);
       cookie.setHttpOnly(true);       // js cannot access this cookies
       cookie.setMaxAge(600);       // Expiry Time
       cookie.setPath("/");     //  entire website can access
       response.addCookie(cookie);
       return "Jwt Store inside Cookie";

   }
}
