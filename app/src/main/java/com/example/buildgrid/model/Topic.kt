package com.example.buildgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(@StringRes val topic: Int ,val topicNumber: Int,@DrawableRes val imageResource:Int)
