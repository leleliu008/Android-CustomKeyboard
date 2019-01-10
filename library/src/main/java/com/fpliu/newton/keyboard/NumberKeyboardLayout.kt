package com.fpliu.newton.keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fpliu.newton.ui.recyclerview.adapter.ItemAdapter
import com.fpliu.newton.ui.recyclerview.decoration.GridDividerItemDecoration
import com.fpliu.newton.ui.recyclerview.holder.ItemViewHolder
import java.util.*

/**
 * 数字键盘
 *
 * @author 792793182@qq.com 2018-01-13.
 */
class NumberKeyboardLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    companion object {

        const val KEY_CODE_DELETE = -1
        const val KEY_CODE_0 = 0
        const val KEY_CODE_1 = 1
        const val KEY_CODE_2 = 2
        const val KEY_CODE_3 = 3
        const val KEY_CODE_4 = 4
        const val KEY_CODE_5 = 5
        const val KEY_CODE_6 = 6
        const val KEY_CODE_7 = 7
        const val KEY_CODE_8 = 8
        const val KEY_CODE_9 = 9

        private const val TYPE_NUMBER = 0
        private const val TYPE_BLACK = 1
        private const val TYPE_DELETE = 2
    }

    var onKeyClicked: ((keyCode: Int) -> Unit)? = null

    init {
        val items = ArrayList<String>(12)
        repeat(9) {
            items.add((it + 1).toString())
        }
        items.add("")
        items.add("0")
        items.add("-1")
        layoutManager = GridLayoutManager(context, 3)
        addItemDecoration(GridDividerItemDecoration(context))
        val itemAdapter = object : ItemAdapter<String>(items) {

            override fun onBindLayout(parent: ViewGroup, viewType: Int): Int {
                return when (viewType) {
                    TYPE_BLACK -> R.layout.number_keyboard_layout_grid_item_type_black
                    TYPE_DELETE -> R.layout.number_keyboard_layout_grid_item_type_delete
                    else -> R.layout.number_keyboard_layout_grid_item_type_number
                }
            }

            override fun onBindViewHolder(holder: ItemViewHolder, position: Int, item: String) {
                if (getItemViewType(position) == TYPE_NUMBER) {
                    holder.id(R.id.number_keyboard_layout_grid_item).text(item)
                }
            }

            override fun getItemViewType(position: Int): Int {
                return when (position) {
                    9 -> TYPE_BLACK
                    11 -> TYPE_DELETE
                    else -> TYPE_NUMBER
                }
            }
        }
        itemAdapter.setOnItemClickListener { holder, position, item ->
            when {
                position < 9 -> onKeyClicked?.invoke(position + 1)
                position == 9 -> return@setOnItemClickListener
                position == 10 -> onKeyClicked?.invoke(KEY_CODE_0)
                position == 11 -> onKeyClicked?.invoke(KEY_CODE_DELETE)
            }
        }
        adapter = itemAdapter
    }
}
