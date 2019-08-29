package com.example.firebaseee

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.logopicid
//import kotlinx.android.synthetic.main.activity_main.recyclerView_home
import kotlinx.android.synthetic.main.viewholder.view.*

class MainActivity : AppCompatActivity(){



//        fierebase = CoachngDhundho

    var photoUri: Uri? = null

    var photoUri1: Uri? = null

    var photoUri2: Uri? = null

    var photoUri3: Uri? = null

    var photoUri4: Uri? = null

    var photoUri5: Uri? = null

    var photoUri6: Uri? = null
    var data = ""

    lateinit var imagelogo: String

    lateinit var banner1: String

    lateinit var banner2: String

    lateinit var banner3: String

    lateinit var banner4: String

    lateinit var banner5: String



    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_main)


            val adapter = GroupAdapter<ViewHolder>()

          //  recyclerView_home.adapter = adapter







            logopicid.setOnClickListener {

                val intent = Intent(Intent.ACTION_PICK)

                intent.type = "image/*"

                startActivityForResult(intent, 0)

            }


        banner1_id.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 1)

        }


        banner2_id.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 2)

        }


        banner3_id.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 3)

        }


        banner4_id.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 4)

        }


        banner5_id.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 5)

        }




        button.setOnClickListener {
                uploadImage()
            }

        }














        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

                photoUri = data.data

                Toast.makeText(this," LOGO Selected",LENGTH_SHORT).show()

            }


            if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {

                photoUri1 = data.data

                Toast.makeText(this," Banner selected",LENGTH_SHORT).show()

            }


            if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {

                photoUri2 = data.data

                Toast.makeText(this," 2 ",LENGTH_SHORT).show()

            }


            if (requestCode == 3 && resultCode == Activity.RESULT_OK && data != null) {

                photoUri3 = data.data

                Toast.makeText(this," 3",LENGTH_SHORT).show()

            }


            if (requestCode == 4 && resultCode == Activity.RESULT_OK && data != null) {

                photoUri4 = data.data

                Toast.makeText(this," 4",LENGTH_SHORT).show()

            }


            if (requestCode == 5 && resultCode == Activity.RESULT_OK && data != null) {

                photoUri5 = data.data

                Toast.makeText(this," 5",LENGTH_SHORT).show()

            }




        }

        fun uploadData() {
            var msg: String=""





            computerchckid.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){

                   msg= "COMPUTER"
                    textView.text=msg
            }

                  if(isChangingConfigurations == false)

                {

                    msg="NULL"
                    textView.text=msg
                }

            }
            val coachingname = coachingnameid.text.toString()

            val State = stateid.text.toString()

            val city =  cityid.text.toString()

            val type =  typeid.text.toString()

            val owner =  ownerid.text.toString()
            val addresse = addressid.text.toString()

            val pincode =  pincodeid.text.toString()
            val price = priceid.text.toString()
            val fasilities =  fasilitiesid.text.toString()
            val mobilenumbre =mobilenumberid.text.toString()
            val location =Locationid.text.toString()
            val msgg = msg

            val ref = FirebaseDatabase.getInstance().getReference("CoachingDhundhoDatabase")


            val value = SaveData(coachingname, State,city,location,type,owner,addresse,pincode,price,fasilities,mobilenumbre,imagelogo,banner1,banner2,banner3,banner4,banner5,msgg)
            //,bannerimage1,bannerimage2,bannerimage3,bannerimage4,bannerimage5,instituteimage6
            ref.child("$State/$city/$location/$type/$coachingname").setValue(value).addOnSuccessListener {
                 //getData()


                Toast.makeText(this, "saved success",LENGTH_SHORT).show()
                Toast.makeText(this, "SAB HO GYA H  HEHEHE",LENGTH_SHORT).show()

            }

                .addOnFailureListener {


                }

        }




    fun getData() {

            val ref = FirebaseDatabase.getInstance().getReference("CoachingDhundhoDatabase")

            ref.addValueEventListener(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {
                }


                override fun onDataChange(p0: DataSnapshot) {

                    val adapter = GroupAdapter<ViewHolder>()

                    p0.children.forEach {

                        val COACHING = it.getValue(SaveData::class.java)




                           // adapter.add(Order(COACHING)




                    }

                    //recyclerView_home.adapter = adapter

                }


            })

        }


    fun uploadImage() {

        var refre = FirebaseStorage.getInstance().getReference("/IMG_LOGO/${coachingnameid.text}")

        refre.putFile(photoUri!!).addOnSuccessListener {

            refre.downloadUrl.addOnSuccessListener {

                imagelogo = it.toString()

                Toast.makeText(this, "logo added", LENGTH_SHORT).show()

            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "upload error",LENGTH_SHORT).show()

            }



        var refre1 = FirebaseStorage.getInstance().getReference("/Banner_IMG1/${coachingnameid.text}")

        refre1.putFile(photoUri1!!).addOnSuccessListener {

            refre1.downloadUrl.addOnSuccessListener {

                banner1 = it.toString()

                Toast.makeText(this, "Banner1 added", LENGTH_SHORT).show()

            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "banner 1 upload error",LENGTH_SHORT).show()

            }







        var refre2 = FirebaseStorage.getInstance().getReference("/Banner_IMG2/${coachingnameid.text}")

        refre2.putFile(photoUri2!!).addOnSuccessListener {

            refre2.downloadUrl.addOnSuccessListener {

                banner2 = it.toString()

                Toast.makeText(this, "Banner2 added", LENGTH_SHORT).show()

            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "banner 2 upload error",LENGTH_SHORT).show()

            }






        var refre3 = FirebaseStorage.getInstance().getReference("/Banner_IMG3/${coachingnameid.text}")

        refre3.putFile(photoUri3!!).addOnSuccessListener {

            refre3.downloadUrl.addOnSuccessListener {

                banner3 = it.toString()

                Toast.makeText(this, "Banner3 added", LENGTH_SHORT).show()

            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "banner 3 upload error",LENGTH_SHORT).show()

            }






        var refre4 = FirebaseStorage.getInstance().getReference("/Banner_IMG4/${coachingnameid.text}")

        refre4.putFile(photoUri4!!).addOnSuccessListener {

            refre4.downloadUrl.addOnSuccessListener {

                banner4 = it.toString()

                Toast.makeText(this, "Banner4 added", LENGTH_SHORT).show()

            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "banner 4 upload error",LENGTH_SHORT).show()

            }








        var refre5 = FirebaseStorage.getInstance().getReference("/cochng_IMG5/${coachingnameid.text}")

        refre5.putFile(photoUri5!!).addOnSuccessListener {

            refre5.downloadUrl.addOnSuccessListener {

                banner5 = it.toString()

                Toast.makeText(this, "Banner5 added", LENGTH_SHORT).show()

                uploadData()



            }

        }

            .addOnFailureListener {

                Toast.makeText(this, "banner 5 upload error",LENGTH_SHORT).show()

            }






         }



}


    class Order(val data: SaveData) : Item<ViewHolder>() {

        override fun getLayout(): Int {

            return R.layout.viewholder

        }


        override fun bind(viewHolder: ViewHolder, position: Int) {


            viewHolder.itemView.textView_name.text = data.coachingname
            viewHolder.itemView.textView_fee.text = data.price
            viewHolder.itemView.textView_add.text = data.addresse

            viewHolder.itemView.fasilities_id.text = data.fasilities
            viewHolder.itemView.mobilenumber_id.text = data.mobilenumber

            Picasso.get().load(data.link).into(viewHolder.itemView.imageView2)

        }


    }

