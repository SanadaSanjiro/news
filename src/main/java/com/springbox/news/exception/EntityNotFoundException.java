package com.springbox.news.exception;

/**
 * Исключение, выбрасываемое если не найдена запрашиваемая сущность
 */
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
