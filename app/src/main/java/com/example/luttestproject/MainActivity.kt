package com.example.luttestproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

const val X_DEPTH = 16
const val Y_DEPTH = 16 //One little square has 16x16 pixels in it
const val ROW_DEPTH = 4
const val COLUMN_DEPTH = 4 // the image consists of 4x4 little squares
const val COLOR_DISTORTION = 16



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //에셋 파일 비트맵 이미지로 가져오기
        val assetManager = resources.assets
        val inputStream = assetManager.open("input.jpg")
        var originBitmap : Bitmap = BitmapFactory.decodeStream(inputStream)


        val mainLayout  = LinearLayout(this)
        val imageBtnList = LinearLayout(this)
        val imageView  = ImageView(this)
        val resetBtn = Button(this)
        val filterList = LinearLayout(this)

        //imageBtnList 자식 뷰
        val newYorkBtn = Button(this)
        val winterBtn = Button(this)
        val peopleBtn = Button(this)
        val girlBtn = Button(this)

        //filterList 자식 뷰
        val grayScaleFilter = Button(this)
        val roseRedFilter = Button(this)
        val sepiaFilter = Button(this)
        val snowstormFilter = Button(this)


        mainLayout.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.GRAY)
            orientation = LinearLayout.VERTICAL
            gravity = LinearLayout.VERTICAL
        }


        imageBtnList.apply {
            val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0,300,0,0)
            this.layoutParams = layoutParams
            gravity = LinearLayout.VERTICAL
            setBackgroundColor(Color.DKGRAY)
        }
        newYorkBtn.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "NEWYORK"
            this.textSize = 12f
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.DKGRAY)
        }
        winterBtn.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "Winter"
            this.textSize = 12f
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.DKGRAY)
        }
        peopleBtn.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "peopleBtn"
            this.textSize = 12f
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.DKGRAY)
        }
        girlBtn.apply {
            setBackgroundColor(Color.WHITE)
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "girlBtn"
            this.textSize = 12f
            setTextColor(Color.DKGRAY)
        }


        //이미지뷰 생성
        imageView.apply {
            val layoutParams = LinearLayout.LayoutParams(
                1500,
                1500
            )
            layoutParams.setMargins(0,0,0,0)
            ImageView.ScaleType.FIT_CENTER
            this.layoutParams = layoutParams
            setImageBitmap(originBitmap)
        }


        resetBtn.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                150
            )
            layoutParams.setMargins(0,10,0,0)
            this.layoutParams = layoutParams
            this.text = "reset"
            this.textSize = 10f
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.BLACK)
            setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_menu_revert, 0, 0, 0)
        }


        filterList.apply {
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(30,100,30,0)
            this.layoutParams = layoutParams
        }
        grayScaleFilter.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "GrayScale"
            this.textSize = 12f
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.DKGRAY)

        }
        roseRedFilter.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "roseRed"
            this.textSize = 15f
            setTextColor(Color.RED)
            setBackgroundColor(Color.WHITE)
        }
        sepiaFilter.apply {
            val brownColor = Color.rgb(165, 42, 42)
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "Sepia"
            this.textSize = 15f
            setTextColor(brownColor)
            setBackgroundColor(Color.WHITE)
        }
        snowstormFilter.apply {
            setBackgroundColor(Color.WHITE)
            val layoutParams = LinearLayout.LayoutParams(
                300,
                300
            )
            layoutParams.setMargins(20,10,20,10)
            this.layoutParams = layoutParams
            this.text = "SnowStorm"
            this.textSize = 12f
        }


        imageBtnList.addView(newYorkBtn)
        imageBtnList.addView(winterBtn)
        imageBtnList.addView(peopleBtn)
        imageBtnList.addView(girlBtn)
        mainLayout.addView(imageBtnList)
        mainLayout.addView(imageView)
        filterList.addView(grayScaleFilter)
        filterList.addView(roseRedFilter)
        filterList.addView(sepiaFilter)
        filterList.addView(snowstormFilter)
        mainLayout.addView(filterList)

        setContentView(mainLayout)


        resetBtn.setOnClickListener {
            imageView.setImageBitmap(originBitmap)
        }


        grayScaleFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("10B01.jpg")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            val filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        roseRedFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("rose.jpeg")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            val filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        sepiaFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("sepia.png")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            val filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        snowstormFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("snowstorm.png")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            val filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        newYorkBtn.setOnClickListener {
            val inputStream = assetManager.open("input.jpg")
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            originBitmap = bitmap
            imageView.setImageBitmap(bitmap)
        }

        winterBtn.setOnClickListener {
            val inputStream = assetManager.open("input2.jpg")
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            originBitmap = bitmap
            imageView.setImageBitmap(bitmap)
        }

        peopleBtn.setOnClickListener {
            val inputStream = assetManager.open("input3.jpg")
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            originBitmap = bitmap
            imageView.setImageBitmap(bitmap)
        }

        girlBtn.setOnClickListener {
            val inputStream = assetManager.open("input4.PNG")
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            originBitmap = bitmap
            imageView.setImageBitmap(bitmap)
        }
    }


    private fun applyLutToBitmap(src: Bitmap, lutBitmap: Bitmap): Bitmap {
        val width = src.width
        val height = src.height
        val srcPixels = IntArray(width * height)
        src.getPixels(srcPixels, 0, width, 0, 0, width, height)

        val lutSize = lutBitmap.width // LUT 이미지의 가로 또는 세로 크기
        val lutPixels = IntArray(lutSize * lutSize)
        lutBitmap.getPixels(lutPixels, 0, lutSize, 0, 0, lutSize, lutSize)

        // 원본 이미지의 각 픽셀에 대해 LUT를 이용하여 새 색상을 적용
        for (index in srcPixels.indices) {
            val pixel = srcPixels[index]
            val r = (pixel shr 16) and 0xff
            val g = (pixel shr 8) and 0xff
            val b = pixel and 0xff

            // LUT 인덱스 계산
            val lutIndex = getLutIndex(lutSize, r, g, b)
            val lutPixel = lutPixels[lutIndex]

            srcPixels[index] = lutPixel
        }

        // 필터가 적용된 비트맵을 생성하고 반환
        val filteredBitmap = Bitmap.createBitmap(width, height, src.config)
        filteredBitmap.setPixels(srcPixels, 0, width, 0, 0, width, height)
        return filteredBitmap
    }

    private fun getLutIndex(lutSize: Int, r: Int, g: Int, b: Int): Int {
        // LUT 인덱스 계산 로직 수정 필요
        // 이 예제에서는 단순화를 위해 직접 계산하지 않고, r, g, b 값을 바로 사용
        // 실제 LUT 구조에 맞게 인덱스 계산 방식을 조정해야함
        val row = g / 4 // 예시로, G 채널을 기준으로 행을 계산
        val column = r / 4 // 예시로, R 채널을 기준으로 열을 계산
        return row * lutSize + column
    }
}