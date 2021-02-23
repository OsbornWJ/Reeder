package com.jeven.reeder.data

/**
 * 创建人：wenjie
 * 邮箱： Osbornjie@163.com
 * 功能：
 */
object Repository {
    private val _users = mutableListOf<User>()
    fun getUsers(): List<User>? {
        return _users
    }

    val formattedUserNames: List<String?>
        get() {
            return _users.map { user ->
                if (user.lastName != null) {
                    if (user.firstName != null) {
                        "${user.firstName} ${user.lastName}"
                    } else {
                        user.lastName ?: "Unknown"
                    }
                } else {
                    user.firstName ?: "Unknown"
                }
            }
        }

    // keeping the constructor private to enforce the usage of getInstance
    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")
        apply {
            _users.add(user1)
            _users.add(user2)
            _users.add(user3)
        }
    }
}