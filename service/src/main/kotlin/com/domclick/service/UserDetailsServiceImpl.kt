package com.domclick.service

import com.domclick.repository.UserDetailsRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@ConditionalOnExpression("'\${security.protocol}'!='jwt'")
@Service
class UserDetailsServiceImpl(
        private val userDetailsRepository: UserDetailsRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String) =
            userDetailsRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
}