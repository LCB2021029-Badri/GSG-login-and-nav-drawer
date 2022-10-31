package com.example.get_set_go

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.get_set_go.databinding.FragmentCamBinding
import com.example.get_set_go.databinding.FragmentLoginBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import androidx.appcompat.app.AppCompatActivity
import java.lang.ProcessBuilder.Redirect.to

//

class CamFragment : Fragment() {

    lateinit var binding: FragmentCamBinding
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCamBinding.inflate(inflater)

        binding.buttonFakeC.setOnClickListener {
            findNavController().navigate(R.id.action_camFragment_to_stylesFragment)
        }
        binding.btnCameraC.setOnClickListener {
            cameraCheckPermission()
        }
        binding.btnGalleryC.setOnClickListener {
            galleryCheckPermission()
        }
        // when we click on the image
        binding.imageViewC.setOnClickListener{
            val pictureDialog = AlertDialog.Builder(this@CamFragment.requireContext())
            pictureDialog.setTitle("Select Action(Badri)")
            val pictureDialogItem = arrayOf("Select photo from gallery(badri)","Capture photo from camera(badri)")
            pictureDialog.setItems(pictureDialogItem){dialog,which ->
                when(which){
                    0 -> gallery()
                    1 -> camera()
                }
            }
            pictureDialog.show()
        }
        binding.buttonNextC.setOnClickListener {  // to navigate from this "CamFragment" to different "ThirdActivity"
            val intent = Intent(this@CamFragment.requireContext(),ThirdActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun cameraCheckPermission() {
        Dexter.withContext(this@CamFragment.requireContext()).withPermissions(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA).withListener(
            object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if(report.areAllPermissionsGranted()){
                            camera()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    showRotationalDialogForPermission()
                }

            }
        ).onSameThread().check()
    }

    private fun galleryCheckPermission(){
        Dexter.withContext(this@CamFragment.requireContext()).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                gallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(getActivity(),"you have denied the permission to access gallery", Toast.LENGTH_SHORT).show()
                showRotationalDialogForPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                showRotationalDialogForPermission()
            }
        }).onSameThread().check()
    }

    private fun camera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA_REQUEST_CODE)  // might be an error
    }

    private fun gallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                CAMERA_REQUEST_CODE->{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    // we are using coroutine image loader (coil)
                    // what image we clicked gets updated to the "imageView" automatically
                    binding.imageViewC.load(bitmap){
                        // transformation effects to the above clicked photo
                        crossfade(true)
                        crossfade(1000)
//                        transformations(CircleCropTransformation())   // image is displayed in circle view
                    }
                }
                GALLERY_REQUEST_CODE->{
                    binding.imageViewC.load(data?.data){
                        crossfade(true)
                        crossfade(1000)
//                        transformations(CircleCropTransformation())   // image is displayed in circle view
                    }
                }
            }
        }
    }

    private fun showRotationalDialogForPermission(){
        AlertDialog.Builder(this@CamFragment.requireContext()).setMessage("It looks like you have turned off permissions "+"required for this feature. It can be made under App Settings!!!")
            .setPositiveButton("Go to Settings(Badri)"){_,_->

                try{
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package",(activity as SecondActivity?)!!.packageName,null) // most imposrtant*** step for ssp ( to extract data form activity to fragment)
                    intent.data = uri
                    startActivity(intent)
//                    startActivity((activity as SecondActivity?)!!.intent)     // this type is used to use method/objects from activities in fragments
                }catch (e: ActivityNotFoundException){
                    e.printStackTrace()
                }

            }.setNegativeButton("Cancel(Badri)"){dialog,_->
                dialog.dismiss()
            }.show()
    }
}