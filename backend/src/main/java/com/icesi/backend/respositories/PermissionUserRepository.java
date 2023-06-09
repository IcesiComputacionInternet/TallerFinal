package com.icesi.backend.respositories;

import com.icesi.backend.models.PermissionUser;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PermissionUserRepository extends CrudRepository<PermissionUser, UUID> {
}
