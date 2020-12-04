package nl.joozd.aocday4.utils

import kotlin.random.Random

object MoonMoonMaker{
    private val firstNames = "White Lone Ravenous Ancient Magic Ebony Dark Savage Regal Rogue Scarlet Fierce Alpha Beautiful Blood Moon Loyal Scarred Grey Mystic Prime Graceful Majestic Wild Vengeful Cruel".split(" ")
    private val lastNames = "Hunter Demon Howler Rain Red Shadow Storm Beat Bane Dagger Hound Fang Claw Wolf Crescent Paw Lupus Fire Temptress Warrior Rage Thorn Moon Lupe Vixen Omega".split(" ")

    fun makeName(id: Long?): String {
        if (id == null) return "Moon Moon"
        Random(id).nextInt(100,Int.MAX_VALUE).let{
            val first = (it/26) % 26
            val second = it%26
            return "${firstNames[first]} ${lastNames[second]}"
        }
    }
}