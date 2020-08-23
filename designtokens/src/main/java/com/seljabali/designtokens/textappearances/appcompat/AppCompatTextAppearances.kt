package com.seljabali.designtokens.textappearances.appcompat

import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import com.seljabali.designtokens.R
import com.seljabali.designtokens.textappearances.TextStyles

enum class AppCompatTextAppearances(@StyleRes override val textAppearanceStyle: Int, @StringRes override val textAppearanceName: Int):
    TextStyles {
    Body1(R.style.TextAppearance_AppCompat_Body1, R.string.body1),
    Body2(R.style.TextAppearance_AppCompat_Body2, R.string.body2),
    Button(R.style.TextAppearance_AppCompat_Button, R.string.button),
    Caption(R.style.TextAppearance_AppCompat_Caption, R.string.caption),
    Display1(R.style.TextAppearance_AppCompat_Display1, R.string.display1),
    Display2(R.style.TextAppearance_AppCompat_Display2, R.string.display2),
    Display3(R.style.TextAppearance_AppCompat_Display3, R.string.display3),
    Display4(R.style.TextAppearance_AppCompat_Display4, R.string.display4),
    Headline(R.style.TextAppearance_AppCompat_Headline, R.string.headline),
    Inverse(R.style.TextAppearance_AppCompat_Inverse, R.string.inverse),
    Large(R.style.TextAppearance_AppCompat_Large, R.string.large),
    LargeInverse(R.style.TextAppearance_AppCompat_Large_Inverse, R.string.large_inverse),
    LightSearchResultSubtitle(R.style.TextAppearance_AppCompat_Light_SearchResult_Subtitle, R.string.light_searchresult_subtitle),
    LightSearchResultTitle(R.style.TextAppearance_AppCompat_Light_SearchResult_Title, R.string.light_searchresult_title),
    LightWidgetPopupMenuLarge(R.style.TextAppearance_AppCompat_Light_Widget_PopupMenu_Large, R.string.light_widget_popupMenu_large),
    LightWidgetPopupMenuSmall(R.style.TextAppearance_AppCompat_Light_Widget_PopupMenu_Small, R.string.light_widget_popupMenu_small),
    Medium(R.style.TextAppearance_AppCompat_Medium, R.string.medium),
    MediumInverse(R.style.TextAppearance_AppCompat_Medium_Inverse, R.string.medium_inverse),
    Menu(R.style.TextAppearance_AppCompat_Menu, R.string.menu),
    SearchResultSubtitle(R.style.TextAppearance_AppCompat_SearchResult_Subtitle, R.string.searchresult_subtitle),
    SearchResultTitle(R.style.TextAppearance_AppCompat_SearchResult_Title, R.string.searchresult_title),
    Small(R.style.TextAppearance_AppCompat_Small, R.string.small),
    SmallInverse(R.style.TextAppearance_AppCompat_Small_Inverse, R.string.small_inverse),
    Subhead(R.style.TextAppearance_AppCompat_Subhead, R.string.subhead),
    SubheadInverse(R.style.TextAppearance_AppCompat_Subhead_Inverse, R.string.subhead_inverse),
    Title(R.style.TextAppearance_AppCompat_Title, R.string.title),
    TitleInverse(R.style.TextAppearance_AppCompat_Title_Inverse, R.string.title_inverse),
}