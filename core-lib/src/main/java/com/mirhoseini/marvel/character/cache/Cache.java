package com.mirhoseini.marvel.character.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Cache Scope
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface Cache {
}
