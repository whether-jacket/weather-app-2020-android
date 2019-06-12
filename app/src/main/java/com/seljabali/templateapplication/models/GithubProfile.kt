package com.seljabali.templateapplication.models

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class GithubProfile : BaseModel() {

    @Id(assignable = false)
    var objectBoxId: Long = 0

    @SerializedName("id")
    var githubId: Long = 0

    @SerializedName("avatar_url")
    var avatarUrl: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("public_repos")
    var publicRepos: Int = 0
}