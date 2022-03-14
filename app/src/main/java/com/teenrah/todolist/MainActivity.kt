package com.teenrah.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.teenrah.todolist.databinding.ActivityMainBinding


/* links of where some code was learned, used, or inspired from. Also image and font:
https://cdn.vox-cdn.com/thumbor/s0JCNYbgzUIgaT8g_PgKFMrX1W8=/0x0:4896x3264/1200x800/filters:focal(2057x1241:2839x2023)/cdn.vox-cdn.com/uploads/chorus_image/image/64715574/GettyImages_577660404.0.jpg
https://fonts.google.com/specimen/Satisfy?preview.text=hello&preview.text_type=custom&query=satisfy
https://developer.android.com/reference/android/widget/Button
https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml
https://stackoverflow.com/questions/17741886/retrieve-checked-items-in-listview
https://stackoverflow.com/questions/7426443/how-to-clear-the-text-in-edittext
 */


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // creating variables and initializing an arraylist for the to do items and an array adapter
        var todolist = arrayListOf<String>()
        var todoAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, todolist)


        //using the ADD button to add items to the to do list
        binding.add.setOnClickListener() {

            todolist.add(binding.itemText.text.toString())      //add the string from the edit text to the array list
            binding.todoListView.adapter = todoAdapter
            todoAdapter.notifyDataSetChanged()

            binding.itemText.text.clear()       //clear the edit text box when the item has been added to the list
        }

        //using the Delete button to delete items from the to do list
        binding.delete.setOnClickListener() {

            var listPosition = binding.todoListView.checkedItemPositions
            var listCount = binding.todoListView.count
            var itemCount = listCount - 1

            do {                    // do the following function
                if (listPosition.get(itemCount)) {      // if an item in a certain position is selected
                    todoAdapter.remove(todolist.get(itemCount))         // remove the selected item from the list
                }
                itemCount--     // reduce the item
            } while (itemCount >= 0)    // while the itemcount is greater than or equal to 0

            listPosition.clear()        // clear the position
            todoAdapter.notifyDataSetChanged()
        }

        //using the clear button to clear all the items in the list
        binding.clear.setOnClickListener() {

            todolist.clear()        // clear the entire list
            todoAdapter.notifyDataSetChanged()
        }


    }


}
