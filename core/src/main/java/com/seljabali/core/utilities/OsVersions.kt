package com.seljabali.core.utilities

import android.os.Build

enum class OsVersions(val apiLevel: Int) {
    LOLLIPOP_8(Build.VERSION_CODES.LOLLIPOP),
    LOLLIPOP_8_MR1(Build.VERSION_CODES.LOLLIPOP_MR1),
    M_9(Build.VERSION_CODES.M),
    N_10(Build.VERSION_CODES.N),
    N_10_MR1(Build.VERSION_CODES.N_MR1),
    O_11(Build.VERSION_CODES.O),
    O_11_MR1(Build.VERSION_CODES.O_MR1),
    P_12(Build.VERSION_CODES.P),
    Q_13(29),
    R_14(30),
    S_15(31),
}