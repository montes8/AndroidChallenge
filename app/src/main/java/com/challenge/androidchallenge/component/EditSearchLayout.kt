package com.challenge.androidchallenge.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.challenge.androidchallenge.R
import kotlinx.android.synthetic.main.edit_search_layout.view.*

class EditSearchLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defaultStyle: Int = 0
) : CardView(context, attrs, defaultStyle) {

    var hint: String = ""
        set(value) {
            field = value
            edtSearch.hint = value
        }
    var clearDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_close)?.apply {
        setTint(context.getColor(R.color.purple_500))
    }
        set(value) {
            field = value
            btnClear.setImageDrawable(value)
        }

    var editEnable: Boolean = true
        set(value) {
            field = value
            configEditEnable()
        }

    var uiDrawableBackground: Drawable? = null
        set(value) {
            field = value
            bgSearchLayout.background = value
        }

    var uiElevationCard: Float? = null
        set(value) {
            field = value
            value?.let { cardElevation = it }
        }

    var searchTextChangeListener: (CharSequence?) -> Unit = {}

    var searchListener: (CharSequence?) -> Unit = {}

    var backListener: () -> Unit = {}

    var text: String = ""
        get() = edtSearch.text.toString()
        set(value) {
            field = value
            edtSearch.setText(value)
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.edit_search_layout, this, true)
        loadAttributes()
        setUpView()
    }

    private fun loadAttributes() {
        context.obtainStyledAttributes(attrs, R.styleable.EditSearchLayout).apply {
            hint = getString(R.styleable.EditSearchLayout_search_hint) ?: ""
            clearDrawable =
                    getDrawable(R.styleable.EditSearchLayout_search_clear_icon) ?: clearDrawable
            editEnable = getBoolean(R.styleable.EditSearchLayout_search_enable_edit, editEnable)
        }.recycle()
    }

    private fun setUpView() {
        btnClear.setOnClickListener {
            edtSearch.setText("")
        }

        edtSearch.doOnTextChanged { text, _, _, _ ->
            btnClear.visibility =
                    if (text?.trim()?.isNotEmpty() == true) View.VISIBLE else View.GONE
            searchTextChangeListener(text)
        }
        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
                searchListener(edtSearch.text)
            true
        }
    }

    private fun configEditEnable() {
        edtSearch.apply {
            isFocusable = editEnable
        }
        btnClear.isEnabled = editEnable
        btnOpen.visibility = if (!editEnable) View.VISIBLE else View.GONE
    }

    fun setOnEditTextListener(onEditTextListener: () -> Unit) {
        btnOpen.setOnClickListener {
            onEditTextListener()
        }
    }
}