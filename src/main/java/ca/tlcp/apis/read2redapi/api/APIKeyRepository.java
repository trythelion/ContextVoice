package ca.tlcp.apis.read2redapi.api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface APIKeyRepository extends JpaRepository<APIKey, Long> {

    List<Optional<APIKey>> getAPIKeysByPID(long PID);
}
