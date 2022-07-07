package com.creativeduck.mailservice.di

import com.creativeduck.mailservice.adapter.MailType
import com.creativeduck.mailservice.adapter.WooWaMail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideData() : List<WooWaMail> {
        val now = 1000000000000
        return listOf(
            WooWaMail(
                MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.",
                Date(now)
            ),
            WooWaMail(
                MailType.Social.ordinal,
                "김영희",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 1)
            ),
            WooWaMail(
                MailType.Promotions.ordinal,
                "Facebook",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 2)
            ),
            WooWaMail(
                MailType.Primary.ordinal,
                "심플",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 3)
            ),
            WooWaMail(
                MailType.Primary.ordinal,
                "Google",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 4)
            ), WooWaMail(
                MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.",
                Date(now)
            ),
            WooWaMail(
                MailType.Social.ordinal,
                "김영희",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 31)
            ),
            WooWaMail(
                MailType.Promotions.ordinal,
                "Facebook",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 32)
            ),
            WooWaMail(
                MailType.Primary.ordinal,
                "심플",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 43)
            ),
            WooWaMail(
                MailType.Primary.ordinal,
                "Google",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 54)
            ), WooWaMail(
                MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.",
                Date(now)
            ),
            WooWaMail(
                MailType.Social.ordinal,
                "김영희",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 134)
            ),
            WooWaMail(
                MailType.Promotions.ordinal,
                "Facebook",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 23)
            ),
            WooWaMail(
                MailType.Primary.ordinal,
                "심플",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 33)
            ),
            WooWaMail(
                MailType.Primary.ordinal,
                "Google",
                "보안 알림",
                "Android 에서 로그인하셨습니다.",
                Date(now + 423)
            )    )
    }

}