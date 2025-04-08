package com.art.gestionart.security;


import com.art.gestionart.model.Artisan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String username;
    private final String email;
    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean active;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.active = active;
    }

    public static UserDetailsImpl build(Artisan artisan) {
        // Par défaut, on attribue le rôle "ROLE_USER" à tous les artisans
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        boolean active = artisan.getStatut() == Artisan.StatutCompte.ACTIF;

        return new UserDetailsImpl(
                artisan.getId(),
                artisan.getEmail(), // On utilise l'email comme username
                artisan.getEmail(),
                artisan.getPassword(),
                Collections.singletonList(authority),
                active);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
