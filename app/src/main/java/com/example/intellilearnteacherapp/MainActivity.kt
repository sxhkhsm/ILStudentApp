package com.example.intellilearnteacherapp

import android.app.Activity
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), AdapterMcqItem.OnDeleteMcqClickListener {

    private val mcqItemAdapter = AdapterMcqItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mcqItemAdapter
        mcqItemAdapter.onDeleteMcqClickListener = this@MainActivity

        faAddNewMcq.setOnClickListener(){
            //Toast.makeText(this, "Clicked", Toast.LENGTH_LONG)
            AddNewMcqActivity.startActivity(this)

        }

        MyApp.getInstance().getApiServices().getAllMcqItems().enqueue(object : Callback<List<McqItem>>{

            override fun onResponse(call: Call<List<McqItem>>, response: Response<List<McqItem>>) {

                if (response.isSuccessful && !response.body().isNullOrEmpty()){

                    mcqItemAdapter.setList(response.body() as ArrayList<McqItem>)
                }
                else{

                    Toast.makeText(this@MainActivity, "Error loading MCQ list", Toast.LENGTH_LONG).show()

                }

            }

            override fun onFailure(call: Call<List<McqItem>>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Error loading MCQ list", Toast.LENGTH_LONG).show()

            }


        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)

        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){

            if(requestCode == AddNewMcqActivity.REQUEST_CODE_ADD_MCQ){


                data?.let {

                    val item = data.getParcelableExtra<McqItem>(AddNewMcqActivity.EXTRA_MCQ)
                    mcqItemAdapter.addItem(item!!)

                }


            }
        }
    }

    override fun onDeleteMcq(mcq: McqItem, position: Int) {

        MyApp.getInstance().getApiServices().deleteMcqItem(mcq.question_ID).enqueue(object : Callback<DeleteResponse>{
            override fun onResponse(
                call: Call<DeleteResponse>,
                response: Response<DeleteResponse>
            ) {

                if (response.isSuccessful && response.body() != null){

                    Toast.makeText(this@MainActivity, response.body()?.response, Toast.LENGTH_LONG).show()
                    mcqItemAdapter.deleteMcq(position)


                }
                else{

                    Toast.makeText(this@MainActivity, "Error deleting MCQ", Toast.LENGTH_LONG).show()


                }

            }

            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Error deleting MCQ", Toast.LENGTH_LONG).show()

            }


        })

    }

}