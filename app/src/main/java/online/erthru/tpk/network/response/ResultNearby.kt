package online.erthru.tpk.network.response

import com.google.gson.annotations.SerializedName
import online.erthru.tpk.network.model.Results

data class ResultNearby(

        @SerializedName("results")
        var results:ArrayList<Results>?

)