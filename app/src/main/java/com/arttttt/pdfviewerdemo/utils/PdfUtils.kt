package com.arttttt.pdfviewerdemo.utils

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File
import java.io.FileOutputStream
import android.graphics.Bitmap
import android.widget.ImageView

object PdfUtils {
    private lateinit var pdfRenderer: PdfRenderer
    private var currentPage: PdfRenderer.Page? = null
    private lateinit var parcelFileDescriptor: ParcelFileDescriptor

    fun openRenderer(context: Context, fileName: String) {
        val file = File(context.cacheDir, fileName)
        if (!file.exists()) {
            val asset = context.assets.open(fileName)
            val output = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var size = 0
            while (size != -1) {
                size = asset.read(buffer)
                output.write(buffer, 0, size)
            }
            asset.close()
            output.close()
        }
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        pdfRenderer = PdfRenderer(parcelFileDescriptor)
    }

    fun showPage(index: Int, imageViewPdf: ImageView) {
        if (pdfRenderer.pageCount <= index) {
            return
        }
        currentPage?.close()
        currentPage = pdfRenderer.openPage(index)
        val bitmap = Bitmap.createBitmap(currentPage!!.width, currentPage!!.height, Bitmap.Config.ARGB_8888)
        currentPage?.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        imageViewPdf.setImageBitmap(bitmap)
    }

    fun getPageCount(): Int {
        return pdfRenderer.pageCount
    }
}