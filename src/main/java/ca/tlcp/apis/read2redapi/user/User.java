package ca.tlcp.apis.read2redapi.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long UUID;
    private String username;
    private String password;
    private String email;
    private String FullName;

    public User(String username, String password, String email, String FullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.FullName = FullName;
    }

}
