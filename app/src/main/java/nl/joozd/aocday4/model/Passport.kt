package nl.joozd.aocday4.model

import kotlin.reflect.KProperty

data class Passport(private val data: Map<String, String>, private val input: String) {
    private val requiredItems = listOf(BYR, IYR, EYR, HGT, HCL, ECL, PID)
    private val validByr = try { data[BYR]!!.toInt() in (1920..2002) } catch (e: Exception) { false }
    private val validIyr = try { data[IYR]!!.toInt() in (2010..2020) } catch (e: Exception) { false }
    private val validEyr = try { data[EYR]!!.toInt() in (2020..2030) } catch (e: Exception) { false }
    private val validHgt = validateHeight(data[HGT])
    private val validHcl = "#[0-9a-f]{6}".toRegex() matches (data[HCL] ?: "false")
    private val validEcl = data[ECL] in listOf ("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    private val validPid = "[0-9]{9}".toRegex() matches (data[PID] ?: "nope")



    private fun validateHeight(height: String?): Boolean{
        if (height == null) return false
        val cmRegex = "(\\d{3})cm".toRegex()
        val inRegex = "(\\d{2})in".toRegex()
        cmRegex.find(height)?.groupValues?.let{
            return when {
                it[0] != height -> false
                it[1].toInt() in 150..193 -> true
                else -> false
            }
        }
        inRegex.find(height)?.groupValues?.let{
            return when {
                it[0] != height -> false
                it[1].toInt() in 59..76 -> true
                else -> false
            }
        }
        return false
    }

    val byr by PassportData()
    val iyr by PassportData()
    val eyr by PassportData()
    val hgt by PassportData()
    val hcl by PassportData()
    val ecl by PassportData()
    val pid by PassportData()
    val cid by PassportData()


    /**
     * Answer q1
     */
    val valid1: Boolean = requiredItems.all { it in data.keys }

    /**
     * Answer q2
     */
    val valid2 = (validByr && validIyr && validEyr && validHgt && validHcl && validEcl && validPid)

    companion object{
        fun of(s: String): Passport = Passport(s.split ('~', ' ').map{entry -> entry.split(':').let{it.first() to it.last()}}.toMap(), s)

        const val BYR = "byr"
        const val IYR = "iyr"
        const val EYR = "eyr"
        const val HGT = "hgt"
        const val HCL = "hcl"
        const val ECL = "ecl"
        const val PID = "pid"
        const val CID = "cid"
    }

    inner class PassportData {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String? =
            data[property.name]
    }


}
