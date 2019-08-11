import android.widget.Toast
import com.seljabali.core.utilities.Res

fun Toast.setTheme(): Toast {
    view.setBackgroundColor(Res.getColorViaAttr(view.context, android.R.attr.background))
    return this
}