package sunny.koranpagi.utils

/**
 * Created by Wayan-MECS on 11/26/2018.
 */
class Constant {

    companion object {
        //Url
        const val NEWS_KEY: String = "3403dd66a7b04619a9f32a438675abe5"
        const val NEWS_API_URL: String = "http://newsapi.org/v2/"
        const val NEWS_API_KEYS_COUNTRY: String = "top-headlines"

        //EventBusKeys
        const val GameFragmentBus = "GameBusEvent"
        const val HiburanFragmentBus = "HiburanBusEvent"
        const val MusicFragmentBus = "MusicBusEvent"
        const val SportFragmentBus = "SportBusEvent"
        const val TechnoFragmentBus = "TechnoBusEvent"

        //SharedPrefKey
        const val techNewsKey = "TechSP"
        const val sportNewsKey = "SportSP"
        const val EntNewsKey = "EntSP"
    }
}