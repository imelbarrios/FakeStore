package com.store.fake.proxy.response.users;

import lombok.Builder;

@Builder
public record UserResponse(String firstname,
                           String lastname) {
    @Override
    public String firstname() {
        return firstname;
    }

    @Override
    public String lastname() {
        return lastname;
    }
}
