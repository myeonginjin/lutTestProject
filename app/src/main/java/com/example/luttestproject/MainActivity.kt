package com.example.luttestproject

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.ceil
import kotlin.math.floor

const val X_DEPTH = 16
const val Y_DEPTH = 16 //One little square has 16x16 pixels in it
const val ROW_DEPTH = 4
const val COLUMN_DEPTH = 4 // the image consists of 4x4 little squares
const val COLOR_DISTORTION = 16



class MainActivity : AppCompatActivity() {

    private lateinit var lutPixels : IntArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //에셋 파일 비트맵 이미지로 가져오기
        val assetManager = resources.assets
        val inputStream = assetManager.open("rgbTest.png")
        var originBitmap : Bitmap = BitmapFactory.decodeStream(inputStream)
        var filteredBitmap : Bitmap = originBitmap


        val mainLayout  = LinearLayout(this)
        val imageBtnList = LinearLayout(this)
        val imageView  = ImageView(this)
        val saveBtn = Button(this)
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
            this.text = "rgbTest"
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


        saveBtn.apply {
            val layoutParams = LinearLayout.LayoutParams(
                300,
                150
            )
            layoutParams.setMargins(0,10,0,0)
            this.layoutParams = layoutParams
            this.text = "save"
            this.textSize = 10f
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.BLACK)
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
            this.text = "redSplash"
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
            this.text = "greenSplash"
            this.textSize = 12f
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
            this.text = "blueSplash"
            this.textSize = 12f
        }


        imageBtnList.addView(newYorkBtn)
        imageBtnList.addView(winterBtn)
        imageBtnList.addView(peopleBtn)
        imageBtnList.addView(girlBtn)
        mainLayout.addView(imageBtnList)
        mainLayout.addView(imageView)
        mainLayout.addView(saveBtn)
        filterList.addView(grayScaleFilter)
        filterList.addView(roseRedFilter)
        filterList.addView(sepiaFilter)
        filterList.addView(snowstormFilter)
        mainLayout.addView(filterList)

        setContentView(mainLayout)


        saveBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                filteredBitmap.let {
                    saveImageOnAboveAndroidQ(filteredBitmap)
                    Toast.makeText(baseContext, "이미지 저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                } ?:run {  Toast.makeText(this, "처리를 완료해주세요.", Toast.LENGTH_SHORT).show()}
            } else {
                // Q 버전 이하일 경우. 저장소 권한을 얻어온다.
                val writePermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                if(writePermission == PackageManager.PERMISSION_GRANTED) {
                    saveImageOnUnderAndroidQ(filteredBitmap) ?:run {  Toast.makeText(this, "처리를 완료해주세요.", Toast.LENGTH_SHORT).show()}
                    Toast.makeText(baseContext, "이미지 저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    val requestExternalStorageCode = 1

                    val permissionStorage = arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )

                    ActivityCompat.requestPermissions(this, permissionStorage, requestExternalStorageCode)
                }
            }
        }


        grayScaleFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("10B01.jpg")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        roseRedFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("redS.jpg")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        sepiaFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("greenS.jpg")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
            imageView.setImageBitmap(filteredBitmap)
        }

        snowstormFilter.setOnClickListener {
            val inputStreamLUT = assetManager.open("blueS.jpg")
            val lutBitmap: Bitmap = BitmapFactory.decodeStream(inputStreamLUT)
            filteredBitmap = applyLutToBitmap(originBitmap, lutBitmap)
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
            val inputStream = assetManager.open("rgbTest.png")
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
        lutPixels = IntArray(lutSize * lutSize)
        lutBitmap.getPixels(lutPixels, 0, lutSize, 0, 0, lutSize, lutSize)

        // 원본 이미지의 각 픽셀에 대해 LUT를 이용하여 새 색상을 적용
        for (index in srcPixels.indices) {
            val pixel = srcPixels[index]
            val r = (pixel shr 16) and 0xff
            val g = (pixel shr 8) and 0xff
            val b = pixel and 0xff

            // LUT 인덱스 계산
            val lutPixel = getLutPixel(r, g, b)

            srcPixels[index] = lutPixel
        }

        // 필터가 적용된 비트맵을 생성하고 반환
        val filteredBitmap = Bitmap.createBitmap(width, height, src.config)
        filteredBitmap.setPixels(srcPixels, 0, width, 0, 0, width, height)
        return filteredBitmap
    }

    // LUT이미지 파일을 통해 필터링한 픽셀을 반환해주는 함수
    private fun getLutPixel(red: Int, green: Int, blue: Int) : Int{

        val r = red / 4.0
        val g = green / 4.0
        val b = blue / 4.0

        // R,G,B 값을 각각 내림했을 때 얻게 되는 LUT의 픽셀 인덱스
        val floorLutIndex = getLutIndex(floor(r).toInt(), floor(g).toInt(), floor(b).toInt())

        // R,G,B 값을 각각 올림했을 때 얻게 되는 LUT의 픽셀 인덱스
        var ceilLutIndex : Int


        // 올림한 R,G,B 값 중, LUT이미지 파일의 인덱스 범위(0~63)를 벗어 나는 것이 없는 경우
        if (ceil(r) <= 63 &&  ceil(g) <= 63 && ceil(b) <= 63){
            ceilLutIndex = getLutIndex(ceil(r).toInt(), ceil(g).toInt(), ceil(b).toInt())
        }

        // 올림한 R,G,B 값 중, LUT이미지 파일의 인덱스 범위(0~63)를 벗어 나는 것이 있는 경우
        else{
            var adjustR = ceil(r)
            var adjustG = ceil(g)
            var adjustB = ceil(b)

            if (ceil(r) > 63){
                adjustR = 63.0
            }
            if (ceil(g) > 63){
                adjustG = 63.0
            }
            if (ceil(b) > 63){
                adjustB = 63.0
            }

            // LUT이미지 파일의 인덱스 범위의 최대값인 63으로 조정
            ceilLutIndex = getLutIndex(adjustR.toInt(), adjustG.toInt(), adjustB.toInt())
        }

        /*
        Log.i("rgb","r : $r   g : $g  b : $b   \n" )
        Log.i("floorLutIndex","r : ${floor(r).toInt()}  g : ${floor(g).toInt()}  b : ${floor(b).toInt()}   \n" )
        Log.i("ceilLutIndex","r : ${ceil(r).toInt()}  g : ${ceil(g).toInt()}  b : ${ceil(b).toInt()}   \n" )
        Log.i("floorLutIndex2","floorLutIndex : $floorLutIndex   \n" )
        Log.i("ceilLutIndex2","ceilLutIndex : $ceilLutIndex   \n" )
         */

        // R,G,B 각각 값을 내림했을 때 얻게되는 각각의 LUT 픽셀값
        val floorLutPixel = lutPixels[floorLutIndex]

        // R,G,B 각각 값을 올림했을 때 얻게되는 각각의 LUT 픽셀값
        val ceilLutPixel = lutPixels[ceilLutIndex]


        /*
        val testR = Color.red(floorLutPixel)
        val testR2 = Color.red(ceilLutPixel)

        Log.i("floorLutPixel","floorLutPixel R  : $testR  \n" )
        Log.i("ceilLutPixel","ceilLutPixel R : $testR2   \n" )
         */

        // 4배 축소된 LUT이미지(63*63*63) 파일로 인해 선형 보간법 적용
        val outPutR = bilinearInterpolation(Color.red(floorLutPixel), Color.red(ceilLutPixel), r - r.toInt())
        val outPutG = bilinearInterpolation(Color.green(floorLutPixel), Color.green(ceilLutPixel), g - g.toInt())
        val outPutB = bilinearInterpolation(Color.blue(floorLutPixel), Color.blue(ceilLutPixel), b - b.toInt())

        /*
        Log.i("floorLutPixel","outPutR : $outPutR   outPutG : $outPutG   outPutB : $outPutB  \n" )
         */

        return Color.rgb(outPutR, outPutG, outPutB)
    }


    // LUT 이미지 파일로부터 얻어야 할 픽셀의 인덱스 정보를 반횐해주는 함수
    private fun getLutIndex(red: Int, green: Int, blue: Int): Int {

        var index = 0

        index += (blue / 8) * (64 * 64 * 8)
        index += green * 8 * 64

        index += (blue % 8) * 64 + red

        return index
    }

    // R,G,B값을 4로 나누었을 때의 나머지 값을 통해 가중 평균값 반환
    private  fun bilinearInterpolation (color1 : Int , color2 : Int,  ratio : Double ): Int {

//        //가중 평균값
//        var outPutColor : Double
//
//        // 두 광원 값의 차이 절댓값
//        val dis = kotlin.math.abs(color1 - color2)
//
//        // 가중 평균값을 구할 필요 없는 경우 (광원의 값이 4의 배수인 경우)
//        if (ratio == 0.0){
//            outPutColor = (color1 + color2) / 2.0
//            outPutColor = round(outPutColor)
//
//            return outPutColor.toInt()
//        }
//
//        // RGB 올림값으로 조회한 LUT픽셀의 광원값이 RGB 내림값으로 조회한 LUT픽셀의 광원값 보다 큰 경우
//        if(color1 < color2) {
//            outPutColor =  (color1 + (dis * ratio))
//        }
//
//        // RGB 내림값으로 조회한 LUT픽셀의 광원값이 RGB 올림값으로 조회한 LUT픽셀의 광원값 보다 큰 경우
//        else{
//            outPutColor =  (color1 - (dis * ratio))
//        }
//
//        // 광원 가중 평균값 반올림
//        outPutColor = round( outPutColor )
//
//        return outPutColor.toInt()

        val ratio1 : Double = 1.0 - ratio
        val outputColor : Double = color1 * ( ratio1 ) + color2 * ratio

        return outputColor.toInt()

    }

    // saveImageOnAboveAndroidQ 함수 수정
    private fun saveImageOnAboveAndroidQ(bitmap: Bitmap) {
        val fileName = System.currentTimeMillis().toString() + ".jpg" // 파일이름 현재시간.png

        val contentValues = ContentValues()
        contentValues.apply {
            put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/ImageSave")
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
        Log.i("size11","w : ${bitmap.width}   h : ${bitmap.height}")


        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        try {
            if (uri != null) {
                val image = contentResolver.openFileDescriptor(uri, "w", null)

                if (image != null) {
                    val fos = FileOutputStream(image.fileDescriptor)

                    // PNG 형식으로 압축 (압축률 조절 가능, 100은 최대 압축)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos)

                    fos.close()

                    contentValues.clear()
                    contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                    contentResolver.update(uri, contentValues, null, null)
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    // saveImageOnUnderAndroidQ 함수 수정
    private fun saveImageOnUnderAndroidQ(bitmap: Bitmap) {
        val fileName = System.currentTimeMillis().toString() + ".png"
        val externalStorage = Environment.getExternalStorageDirectory().absolutePath
        val path = "$externalStorage/DCIM/imageSave"
        val dir = File(path)

        Log.i("size22","w : ${bitmap.width}   h : ${bitmap.height}")

        if (dir.exists().not()) {
            dir.mkdirs()
        }

        try {
            val fileItem = File("$dir/$fileName")
            fileItem.createNewFile()

            val fos = FileOutputStream(fileItem)

            // PNG 형식으로 압축 (압축률 조절 가능, 100은 최대 압축)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos)

            fos.close()

            // 미디어 스캔을 통해 갤러리에 반영
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(fileItem)))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}