package com.cocobob.server.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    private Set<AuthorityDTO> authorityDtoSet;

    public static UserDTO from(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .username(user.getUsername())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDTO.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}