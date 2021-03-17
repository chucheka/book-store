package net.polarisdigitech.book_store.book_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.polarisdigitech.book_store.book_store.entity.Role;
import net.polarisdigitech.book_store.book_store.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName roleUser);

}
