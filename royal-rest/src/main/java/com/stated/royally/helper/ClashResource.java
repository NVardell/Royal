package com.stated.royally.helper;

/**
 * Generic Base for Official Clash Service
 *
 * @author Nate Vardell
 * @since 2/10/2020
 */
public interface ClashResource<T, ID> {
    T fetch(ID id);
}