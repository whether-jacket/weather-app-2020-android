package com.seljabali.templateapplication.models

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.seljabali.templateapplication.utilities.threeten.ZonedDateTimeTypeAdapterFactory
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.ZonedDateTime

@Entity
@Parcelize
data class GithubProfile(
    @SerializedName("id")
    @Id(assignable = true)
    var githubId: Long = 0,

    @SerializedName("avatar_url")
    var avatarUrl: String = "",

    @SerializedName("name")
    var name: String = "",

    @SerializedName("public_repos")
    var publicRepos: Int = 0

//    @SerializedName("ReportTime")
//    @JsonAdapter(ZonedDateTimeTypeAdapterFactory::class)
//    var reportTime: ZonedDateTime? = null
) : BaseModel()