package com.creativeduck.mailservice.presentation.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.creativeduck.mailservice.R
import com.google.android.material.imageview.ShapeableImageView

class MailItemView : ConstraintLayout {
    lateinit var imgMailProfile : ShapeableImageView
    lateinit var textMailFirst : TextView
    lateinit var textMailFrom : TextView
    lateinit var textMailTitle : TextView
    lateinit var textMailContent : TextView

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, delStyleAttr: Int) : super(context, attrs, delStyleAttr) {
        init(context, attrs, delStyleAttr)
        getAttrs(attrs, delStyleAttr)
    }

    fun init(context: Context, attrs: AttributeSet?, delStyleAttr: Int) {
        var infService : String = Context.LAYOUT_INFLATER_SERVICE
        var inflater: LayoutInflater? = null
        var tmp: Any? = getContext().getSystemService(infService)
        if (tmp is LayoutInflater) {
            inflater = tmp
        }
        var view : View = inflater!!.inflate(R.layout.item_mail, this, false)
        addView(view)

        imgMailProfile = view.findViewById(R.id.img_mail_profile)
        textMailFirst = view.findViewById(R.id.text_mail_first)
        textMailFrom = view.findViewById(R.id.text_mail_from)
        textMailTitle = view.findViewById(R.id.text_mail_title)
        textMailContent = view.findViewById(R.id.text_mail_content)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MailItemView)
        setAttrs(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, delstyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MailItemView)
        setAttrs(typedArray)
    }

    private fun setAttrs(typedArray: TypedArray) {
        imgMailProfile.setImageResource(typedArray.getResourceId(R.styleable.MailItemView_img_mail_profile, R.drawable.ic_baseline_person_24))
        textMailFirst.text = typedArray.getText(R.styleable.MailItemView_text_mail_first)
        textMailFrom.text = typedArray.getText(R.styleable.MailItemView_text_mail_from)
        textMailTitle.text = typedArray.getText(R.styleable.MailItemView_text_mail_title)
        textMailContent.text = typedArray.getText(R.styleable.MailItemView_text_mail_content)
        typedArray.recycle()
    }
}