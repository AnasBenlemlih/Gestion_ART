package com.art.gestionart.security;

import com.art.gestionart.model.Artisan;
import com.art.gestionart.repository.ArtisanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ArtisanRepository artisanRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Artisan artisan = artisanRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + username));

        return UserDetailsImpl.build(artisan);
    }
}