package com.fpliu.newton.keyboard.sample

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.fpliu.newton.keyboard.NumberKeyboardLayout
import com.fpliu.newton.ui.base.BaseActivity
import com.fpliu.newton.ui.base.UIUtil
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 数字键盘使用示例
 * @author 792793182@qq.com 2018-03-28.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "数字键盘使用示例"
        setContentView(R.layout.activity_main)
        numberKeyboardLayout.onKeyClicked = {
            when (it) {
                NumberKeyboardLayout.KEY_CODE_DELETE -> {
                    val text = editText.text.toString()
                    val length = text.length
                    if (length > 0) {
                        editText.setText(text.substring(0, length - 1))
                    }
                }
                else -> {
                    editText.append(it.toString())
                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev != null) {
            if (UIUtil.isInMyView(ev, editText)) {
                editText.run {
                    isFocusable = true
                    isFocusableInTouchMode = true
                    isCursorVisible = true
                    UIUtil.hideSoftInput(this@MainActivity, this)
                    numberKeyboardLayout.visibility = View.VISIBLE
                }
            } else if (!UIUtil.isInMyView(ev, numberKeyboardLayout)) {
                editText.run {
                    isFocusable = false
                    isFocusableInTouchMode = false
                    isCursorVisible = false
//                    clearFocus()
                    numberKeyboardLayout.visibility = View.GONE
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}