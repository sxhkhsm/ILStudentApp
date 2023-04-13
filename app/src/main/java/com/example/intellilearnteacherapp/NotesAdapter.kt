package com.example.intellilearnteacherapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.avaliable_notes_list.*
import kotlinx.android.synthetic.main.avaliable_notes_list.view.*
import kotlinx.android.synthetic.main.table_row.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AdapterNoteItem : RecyclerView.Adapter<AdapterNoteItem.MyViewHolder>() {

    private var list = ArrayList<NoteItem>()
    var onDeleteNoteClickListener:OnDeleteNoteClickListener? = null

    interface OnDeleteNoteClickListener{

        fun onDeleteNote(note : NoteItem, position: Int)
        fun onEditNote(note : NoteItem)

    }



    inner class MyViewHolder(parent:ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.avaliable_notes_list, parent, false)){


        fun bind(note_item : NoteItem) = with(itemView){

            note_title.text =  note_item.title


            val outputDateFormat = SimpleDateFormat("yy-MM-dd", Locale.getDefault())
            note_created_on.text= outputDateFormat.format(note_item.created_at)

            note_content.text = note_item.content


            btnDelete.setOnClickListener{

                onDeleteNoteClickListener?.onDeleteNote(note_item, adapterPosition)

            }
            aNote.setOnClickListener{
                onDeleteNoteClickListener?.onEditNote(note_item)
            }

            aNote.setBackgroundColor(getRandomColor())


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(list[position])

    }

    private fun getRandomColor(): Int {
        val random = Random()
        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)
        return Color.rgb(red, green, blue)
    }


    override fun getItemCount(): Int = list.size

    fun setList(list : ArrayList<NoteItem>){

        this.list = list
        notifyDataSetChanged()

    }

    fun addItem(note: NoteItem){

        list.add(note)
        notifyItemInserted(list.size - 1)

    }

    fun deleteNote(position: Int){

        list.removeAt(position)
        notifyItemRemoved(position)

    }

}