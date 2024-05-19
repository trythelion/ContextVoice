package ca.tlcp.apis.read2redapi.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Optional<Project>> getProjectsByUUID(long UUID);
}
