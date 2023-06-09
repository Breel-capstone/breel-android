package com.example.breel.di

import com.example.breel.data.repository.chat.ChatRepository
import com.example.breel.data.repository.chat.ChatRepositorySource
import com.example.breel.data.repository.mentor.MentorRepository
import com.example.breel.data.repository.mentor.MentorRepositorySource
import com.example.breel.data.repository.project.ProjectRepository
import com.example.breel.data.repository.project.ProjectRepositorySource
import com.example.breel.data.repository.user.UserRepository
import com.example.breel.data.repository.user.UserRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideUserRepository(
        userRepository: UserRepository
    ): UserRepositorySource

    @Binds
    @Singleton
    abstract fun provideProjectRepository(
        projectRepository: ProjectRepository
    ): ProjectRepositorySource

    @Binds
    @Singleton
    abstract fun provideMentorRepository(
        mentorRepository: MentorRepository
    ): MentorRepositorySource

    @Binds
    @Singleton
    abstract fun provideChatRepository(
        chatRepository: ChatRepository
    ): ChatRepositorySource
}