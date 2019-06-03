package com.seljabali.templateapplication.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class SomeModel(
    @Id(assignable = true) var id: Long = 0,
    var name: String = ""
)