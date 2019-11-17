package io.fnska.blog.site.repository;

import io.fnska.blog.site.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findAllByName(String name);
}
