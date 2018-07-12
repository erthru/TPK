package online.erthru.tpk.network.response

import com.google.gson.annotations.SerializedName
import online.erthru.tpk.network.model.Result

data class ResultDetails(

        @SerializedName("result")
        var result: Result

)