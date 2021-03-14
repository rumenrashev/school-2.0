package school.service.impl;

import org.springframework.stereotype.Service;
import school.constants.enumuration.AuthorityEnum;
import school.model.entity.AuthorityEntity;
import school.repository.AuthorityRepository;
import school.service.AuthorityService;

import javax.transaction.Transactional;
import java.util.EnumSet;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    @Transactional
    public void seedRoles() {
        EnumSet.allOf(AuthorityEnum.class)
                .stream()
                .map(Enum::name)
                .filter(a-> !this.authorityRepository.existsByAuthority(a))
                .map(a-> new AuthorityEntity().setAuthority(a))
                .forEach(this.authorityRepository::save);
    }
}
