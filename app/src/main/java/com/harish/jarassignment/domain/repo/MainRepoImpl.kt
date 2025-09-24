package com.harish.jarassignment.domain.repo

import com.harish.jarassignment.core.util.DomainException.NetworkException
import com.harish.jarassignment.core.util.DomainException.NotFoundException
import com.harish.jarassignment.core.util.DomainException.TimeoutException
import com.harish.jarassignment.core.util.DomainException.UnauthorizedException
import com.harish.jarassignment.core.util.DomainException.UnknownException
import com.harish.jarassignment.data.mapper.toDomain
import com.harish.jarassignment.data.remote.MainApiInterface
import com.harish.jarassignment.data.repo.MainRepo
import com.harish.jarassignment.domain.model.OnboardingModel
import java.io.IOException
import javax.inject.Inject


class MainRepoImpl @Inject constructor(
    private val mainApi: MainApiInterface
) : MainRepo {


    override suspend fun getOnboardingData(): OnboardingModel {
        return try {
            val response = mainApi.getOnboardingData()
            response.toDomain()
        } catch (e: retrofit2.HttpException) {
            throw when (e.code()) {
                401 -> UnauthorizedException(e)
                404 -> NotFoundException(e)
                else -> UnknownException("Http ${e.code()}", e)
            }
        } catch (e: java.net.SocketTimeoutException) {
            throw TimeoutException(e)
        } catch (e: IOException) {
            throw NetworkException(e)
        } catch (e: Exception) {
            throw UnknownException(e.message, e)
        }
    }


}