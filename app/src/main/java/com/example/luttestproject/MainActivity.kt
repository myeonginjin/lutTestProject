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
            val inputStreamLUT = assetManager.open("grayscale.jpeg")
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
        // LUT 비트맵에서 색상 값을 가져옴

        val lutColors = IntArray(lutBitmap.width * lutBitmap.height)
        lutBitmap.getPixels(lutColors, 0, lutBitmap.width, 0, 0, lutBitmap.width, lutBitmap.height)
        val mWidth = src.width
        val mHeight = src.height
        val pix = IntArray(mWidth * mHeight)
        src.getPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight)

        // 원본 이미지의 각 픽셀에 대해 LUT를 이용하여 새 색상을 적용
        for (y in 0 until mHeight) {
            for (x in 0 until mWidth) {
                val index = y * mWidth + x

                //COLOR_DISTORTION는 색상 값을 조절하기 위해 사용
                val r: Int = (pix[index] shr 16 and 0xff) / COLOR_DISTORTION
                val g: Int = (pix[index] shr 8 and 0xff) / COLOR_DISTORTION
                val b: Int = (pix[index] and 0xff) / COLOR_DISTORTION
                val lutIndex: Int = getLutIndex(lutBitmap.width, r, g, b)

                // 새로운 색상 값을 계산
                val R = lutColors[lutIndex] shr 16 and 0xff
                val G = lutColors[lutIndex] shr 8 and 0xff
                val B = lutColors[lutIndex] and 0xff
                pix[index] = -0x1000000 or (R shl 16) or (G shl 8) or B
            }
        }

        // 필터가 적용된 비트맵을 생성하고 반환
        val filteredBitmap = Bitmap.createBitmap(mWidth, mHeight, src.config)
        filteredBitmap.setPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight)
        return filteredBitmap
    }

    // LUT 인덱스를 계산
    private fun getLutIndex(lutWidth: Int, redDepth: Int, greenDepth: Int, blueDepth: Int): Int {
        val lutX: Int = greenDepth % ROW_DEPTH * X_DEPTH + blueDepth
        val lutY: Int = greenDepth / COLUMN_DEPTH * Y_DEPTH + redDepth
        return lutY * lutWidth + lutX
    }
}