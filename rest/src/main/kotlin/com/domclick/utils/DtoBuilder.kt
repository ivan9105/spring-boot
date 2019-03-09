package com.domclick.utils

import com.domclick.dto.*
import com.domclick.dto.response.AccountResponse
import com.domclick.dto.response.CompanyResponse
import com.domclick.dto.response.UserResponse
import com.domclick.entity.Account
import com.domclick.entity.User
import com.domclick.entity.oauth2.Company
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils

@Component
@Scope("singleton")
class DtoBuilder {
    @Value("\${server.url}")
    lateinit var serverUrl: String

    fun buildCompanyResponse(companies: List<Company>): CompanyResponse {
        val response = CompanyResponse()
        companies.forEach { company ->
            val companyDto = CompanyDto(company)
            companyDto.links.add(LinkDto("self", "GET", serverUrl + "api/company/get/" + company.id))
            response.companies.add(companyDto)
        }
        return response
    }

    fun buildUserResponse(users: List<User>): UserResponse {
        val response = UserResponse()
        users.forEach { user ->
            val userDto = UserDto(user)
            userDto.links.add(LinkDto("self", "GET", serverUrl + "api/user/get/" + user.id))
            response.users.add(userDto)
        }

        return response
    }

    fun buildUserAccountsDto(user: User): UserAccountsDto {
        val res = UserAccountsDto(user)
        res.links.add(LinkDto("self", "GET", serverUrl + "api/user/get/" + user.id))
        if (!CollectionUtils.isEmpty(user.accounts)) {
            user.accounts.forEach { account -> res.accounts.add(buildAccountDto(account)) }
        }
        return res
    }

    fun buildAccountResponse(accounts: List<Account>): AccountResponse {
        val response = AccountResponse()
        if (!CollectionUtils.isEmpty(accounts)) {
            accounts.forEach { account -> response.accounts.add(buildAccountDto(account)) }
        }
        return response
    }

    fun buildAccountDto(account: Account): AccountDto {
        val res = AccountDto(account)
        res.links.add(LinkDto("self", "GET", serverUrl + "api/account/get/" + account.id))
        res.links.add(LinkDto("deposit", "POST", serverUrl + "api/account/deposit"))
        res.links.add(LinkDto("transfer", "POST", serverUrl + "api/account/transfer"))
        res.links.add(LinkDto("withdraw", "POST", serverUrl + "api/account/withdraw"))
        return res
    }
}
