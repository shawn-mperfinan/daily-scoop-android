package com.dailyscoop.app.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dailyscoop.app.data.repositories.IUserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingVM @Inject constructor(
    private val userPreferencesRepository: IUserPreferencesRepository,
) : ViewModel() {
    fun setIsAppFirstLaunch(value: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setIsAppFirstLaunch(value)
        }
    }
}
