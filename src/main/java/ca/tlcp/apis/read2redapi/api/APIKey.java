package ca.tlcp.apis.read2redapi.api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIKey {

    @Id
    private long PID;
    private String APIkey;
}
