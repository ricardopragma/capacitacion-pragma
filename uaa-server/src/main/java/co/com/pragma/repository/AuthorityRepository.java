package co.com.pragma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.pragma.domain.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
