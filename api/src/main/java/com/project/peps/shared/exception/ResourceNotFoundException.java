package com.project.peps.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Constructeur avec 3 paramètres pour personnaliser le message d'erreur à utiliser en priorité
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }

    // Constructeur avec un seul paramètre message pour plus de flexibilité
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
