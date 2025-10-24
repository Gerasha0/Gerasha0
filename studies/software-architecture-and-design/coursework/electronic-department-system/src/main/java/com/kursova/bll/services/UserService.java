package com.kursova.bll.services;

import com.kursova.bll.dto.UserDto;
import com.kursova.dal.entities.UserRole;

import java.util.List;

/**
 * Service interface for User operations
 */
public interface UserService extends BaseService<UserDto, Long> {

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    List<UserDto> findByRole(UserRole role);

    List<UserDto> findActiveByRole(UserRole role);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserDto> searchByName(String name);

    List<UserDto> findActiveUsers();

    UserDto createWithPassword(UserDto userDto, String password);

    void updatePassword(Long userId, String oldPassword, String newPassword);

    UserDto activateUser(Long userId);

    UserDto deactivateUser(Long userId);
}
