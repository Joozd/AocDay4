package nl.joozd.aocday4.ui.mainActivity

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.RecyclerView
import nl.joozd.aocday4.R
import nl.joozd.aocday4.databinding.ItemPassportBinding
import nl.joozd.aocday4.model.Passport
import nl.joozd.aocday4.utils.MoonMoonMaker
import java.lang.Exception

class PassportsAdapter: RecyclerView.Adapter<PassportsAdapter.ViewHolder>() {
    private val itemLayout: Int = R.layout.item_passport

    var list = emptyList<Passport>()
    set(it){
        field = it
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(private val itemPassportBinding: ItemPassportBinding) : RecyclerView.ViewHolder(itemPassportBinding.root) {
        fun bindItem(passport: Passport) = with (itemPassportBinding){
            nameText.text = MoonMoonMaker.makeName(if (passport.pid?.isDigitsOnly() == true) passport.pid?.toLong() else null)
            dobText.text = passport.byr
            heightText.text = passport.hgt
            issueText.text = passport.iyr
            validText.text = passport.eyr
            passIdText.text = passport.pid
            try{ hairImage.setColorFilter(Color.parseColor(passport.hcl ?: "#FFFFFF"), PorterDuff.Mode.SRC_ATOP) } catch(e: Exception) { Log.w("yolo", "passport: $passport\n${e.stackTraceToString()}")}
            when(passport.ecl){
                "amb" -> eyesImage.setColorFilter(Color.parseColor("#ffbf00"), PorterDuff.Mode.SRC_ATOP)
                "blu" -> eyesImage.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP)
                "brn" -> eyesImage.setColorFilter(Color.parseColor("#663300"), PorterDuff.Mode.SRC_ATOP)
                "gry" -> eyesImage.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
                "grn" -> eyesImage.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
                "hzl" -> eyesImage.setColorFilter(Color.parseColor("#878110"), PorterDuff.Mode.SRC_ATOP)
                else -> eyesImage.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
            }
            //TODO other fields

            //make background red if not valid or white if OK
            itemBackground.setCardBackgroundColor(if (passport.valid2) Color.WHITE else Color.parseColor("#ffc0cb") )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(ItemPassportBinding.bind(view))
    }

    override fun getItemCount(): Int  = list.size

}