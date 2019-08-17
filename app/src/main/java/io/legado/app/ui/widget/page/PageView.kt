package io.legado.app.ui.widget.page

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import io.legado.app.R
import io.legado.app.ui.widget.page.delegate.CoverPageDelegate
import io.legado.app.ui.widget.page.delegate.PageDelegate
import io.legado.app.utils.dp
import kotlinx.android.synthetic.main.view_book_page.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.horizontalPadding

class PageView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), PageDelegate.PageInterface {


    private var pageDelegate: PageDelegate? = null

    private var prevPage: ContentView? = null
    private var curPage: ContentView? = null
    private var nextPage: ContentView? = null

    init {
        inflate(context, R.layout.view_book_page, this)

        setWillNotDraw(false)

        page_panel.backgroundColor = Color.WHITE

        page_panel.horizontalPadding = 16.dp

        pageDelegate = CoverPageDelegate(this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        pageDelegate?.setViewSize(w, h)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

//        bringChildToFront(prevPage)

        pageDelegate?.onPerform(canvas)
    }

    override fun computeScroll() {
        pageDelegate?.scroll()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return pageDelegate?.onTouch(event) ?: super.onTouchEvent(event)
    }

    fun setPageFactory(factory: PageFactory<*>) {

    }

    override fun hasNext(): Boolean {
        return true
    }

    override fun hasPrev(): Boolean {
        return true
    }
}
