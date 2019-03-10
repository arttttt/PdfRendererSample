package com.arttttt.pdfviewerdemo.custom

import android.content.Context
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.arttttt.pdfviewerdemo.PdfViewerFragment
import com.arttttt.pdfviewerdemo.utils.PdfUtils

class ViewPagerAdapter(fm: FragmentManager, context: Context, fileName: String): FragmentPagerAdapter(fm) {
    init {
        PdfUtils.openRenderer(context, fileName)
    }

    override fun getCount() = PdfUtils.getPageCount()

    override fun getItem(position: Int): Fragment {
        return PdfViewerFragment.newInstance().apply {
            Handler().postDelayed(Runnable {
                PdfUtils.showPage(position, getImageView())
            }, 500)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        PdfUtils.closeRenderer()
    }
}