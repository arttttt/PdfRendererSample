package com.arttttt.pdfviewerdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_pdf_viewer.*

class PdfViewerFragment : Fragment() {

    companion object {
        fun newInstance(): PdfViewerFragment {
            return PdfViewerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pdf_viewer, container, false).apply {

        }
    }

    fun getImageView() = pdfView
}
