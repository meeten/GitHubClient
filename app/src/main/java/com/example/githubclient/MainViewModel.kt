package com.example.githubclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.navigation.Screen
import com.example.storage.TokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


/**
 * onButtonPressed внутри ViewModel, обеспечивает защиту от множественных быстрых кликов.
 *
 * Проблема: навигационный вызов popBackStack() при быстрых нажатиях на кнопку "Назад"
 * в RepositoriesInfo мог вызываться несколько раз, что приводило к некорректному поведению.
 *
 * Решение: дебаунс 800 мс с помощью Mutex, блокирующий повторные вызовы во время выполнения
 * action и периода ожидания.
 *
 * @property clickMutex блокировка для предотвращения одновременных кликов
 * @see onButtonPressed обрабатывает клики с защитой от дребезга
 */
class MainViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {

    private val clickMutex = Mutex(false)

    fun getStartDestination(): String {
        val token = tokenManager.getToken()

        return when (token) {
            null -> Screen.Authorization.route
            else -> Screen.Home.route
        }
    }

    fun onLogoutButtonPressed(): String {
        tokenManager.clearToken()
        return Screen.Authorization.route
    }

    fun onButtonPressed(action: () -> Unit) {
        viewModelScope.launch {
            if (clickMutex.isLocked) return@launch
            clickMutex.withLock {
                action()
                delay(CLICK_DEBOUNCE_MILLS)
            }
        }
    }
}

private const val CLICK_DEBOUNCE_MILLS = 800L