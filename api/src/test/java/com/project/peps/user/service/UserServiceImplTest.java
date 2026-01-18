package com.project.peps.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.peps.menu.repository.MenuRepository;
import com.project.peps.shared.exception.ResourceNotFoundException;
import com.project.peps.user.model.User;
import com.project.peps.user.repository.UserRepository;

// Moteur de test Mockito pour JUnit 5 -- Initialisation des mocks
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    // Mocks des dépendances 
    @Mock
    private UserRepository userRepository;

    @Mock
    private MenuRepository menuRepository;

    // Classe testée avec les dépendances mockées injectées
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    // Création d'un utilisateur de test avant CHAQUE test
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .pseudo("testuser")
                .email("test@example.com")
                .password("password")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Test de création (sauvegarde) d'un utilisateur")
    void testSave_shouldCreateUser() {
        // Given
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User savedUser = userService.save(user);

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getPseudo()).isEqualTo("testuser");
        verify(userRepository, times(1)).save(user);
    }
    
    @Test
    @DisplayName("Test de mise à jour d'un utilisateur")
    void testUpdate_shouldUpdateUser() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User userToUpdate = userService.findUserById(1L);
        userToUpdate.setPseudo("updatedPseudo");
        User updatedUser = userService.save(userToUpdate);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getPseudo()).isEqualTo("updatedPseudo");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Test de récupération de tous les utilisateurs")
    void testFindAllUsers_shouldReturnUserList() {

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userService.findAllUsers();

        assertThat(users).isNotNull().hasSize(1);
        assertThat(users.get(0)).isEqualTo(user);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test de récupération d'un utilisateur par son ID")
    void testFindUserById_shouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1L);

        assertThat(foundUser).isNotNull();
        assertEquals(1L, foundUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    // Unhappy Path -- Test d'échec avec notfound exception
    @Test
    @DisplayName("Test d'échec de récupération d'un utilisateur par un ID inexistant")
    void testFindUserById_shouldThrowNotFoundException() {
        long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserById(userId);
        });

        assertEquals("User not found with id " + userId, exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }
    
    @Test
    @DisplayName("Test de récupération d'un utilisateur par son Email")
    void testFindUserByEmail_shouldReturnUser() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserByEmail(email);

        assertThat(foundUser).isNotNull();
        assertEquals(email, foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }
    
    // Unhappy Path -- Test d'échec
    @Test
    @DisplayName("Test d'échec de récupération d'un utilisateur par un Email inexistant")
    void testFindUserByEmail_shouldThrowNotFoundException() {
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        
         ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserByEmail(email);
        });

        assertEquals("User not found with email " + email, exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
    }


    @Test
    @DisplayName("Test de suppression d'un utilisateur par son ID")
    void testDeleteById_shouldDeleteUser() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(menuRepository).deleteByUserId(userId);
        doNothing().when(userRepository).deleteById(userId);

        User deletedUser = userService.deleteById(userId);

        assertThat(deletedUser).isNotNull();
        assertEquals(userId, deletedUser.getId());
        verify(userRepository, times(1)).findById(userId);
        verify(menuRepository, times(1)).deleteByUserId(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
    
    @Test
    @DisplayName("Test d'échec de suppression d'un utilisateur par un ID inexistant")
    void testDeleteById_shouldThrowNotFoundException() {
        long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteById(userId);
        });

        assertEquals("User not found with id " + userId, exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(menuRepository, times(0)).deleteByUserId(anyLong());
        verify(userRepository, times(0)).deleteById(anyLong());
    }
}
